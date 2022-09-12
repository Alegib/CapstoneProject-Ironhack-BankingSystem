package Ironhack.CapstoneProject.Services;

import Ironhack.CapstoneProject.DTO.TransactionDTO;
import Ironhack.CapstoneProject.Services.EmailService.EmailService;
import Ironhack.CapstoneProject.models.Accounts.Account;
import Ironhack.CapstoneProject.models.Accounts.ThirdPartyAccount;
import Ironhack.CapstoneProject.models.Enums.PaymentMode;
import Ironhack.CapstoneProject.models.Enums.Status;
import Ironhack.CapstoneProject.models.Transactions.Money;
import Ironhack.CapstoneProject.models.Transactions.Transaction;
import Ironhack.CapstoneProject.models.Users.User;
import Ironhack.CapstoneProject.models.repositories.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    EmailService emailService;


    public List<Transaction> findAllTransactionsByAccount(Long id){
        if (accountRepository.findById(id).isPresent()){
            return transactionRepository.findAllById(Collections.singleton(id));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No records found.");
    }

    public Transaction setStandardOrProgrammedTransactions(TransactionDTO transactionDTO, UserDetails userDetails) {

        if (transactionDTO.getPaymentMode().equals(PaymentMode.SCHEDULED) || transactionDTO.getPaymentMode().equals(PaymentMode.STANDARD)) {
            if (accountRepository.findById(transactionDTO.getRecipientAccountId()).isPresent()) {
                Account recipientAccount = accountRepository.findById(transactionDTO.getRecipientAccountId()).get();
                User userAccount = userRepository.findByUsername(userDetails.getUsername()).get();
                List<Account> userAccountList = accountRepository.findByPrimaryOwnerId(userAccount.getId());

                for (Account account : userAccountList) {

                    if (account.getSecretKey().equals(transactionDTO.getSecretKey())) {
                        if (account.getSecretKey().equals(recipientAccount.getSecretKey())) {
                            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Unfortunately you cannot transfer funds from your account to itself.");
                        }
                        Account senderAccount = accountRepository.findBySecretKey(transactionDTO.getSecretKey());
                        if (senderAccount.getStatus().equals(Status.FROZEN)) {
                            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Your account is currently frozen, contact our support for more information.");
                        }
                        if (senderAccount.getBalance().getAmount().compareTo(transactionDTO.getTransactionAmount().getAmount()) < 0) {
                            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Insufficient funds.");
                        }

                        Transaction transaction = new Transaction(transactionDTO.getTransactionAmount(), recipientAccount, senderAccount, transactionDTO.getDescription(), transactionDTO.getPaymentMode());

                        if (transactionDTO.getPaymentMode().equals(PaymentMode.STANDARD)) {
                            transaction.setDateTime();
                            return transactionRepository.save(transaction);
                        }
                        if (transactionDTO.getPaymentMode().equals(PaymentMode.SCHEDULED)) {

                            if(transactionDTO.getDate() == null || transactionDTO.getDate().isBefore(LocalDateTime.now())){
                                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "In order to schedule a transaction, you must provide a valid date.");
                            }
                            transaction.setDateTime(transactionDTO.getDate());
                            return transactionRepository.save(transaction);
                        }
                    }
                }
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect secret key.");
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The recipient Account doesn't exist.");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You must specify the Payment Mode (keep in mind that for INSTANT payments you must use POST http request)");
    }



    public Transaction performInstantTransaction(TransactionDTO transactionDTO, UserDetails userDetails) {
        if (transactionDTO.getPaymentMode().equals(PaymentMode.INSTANT)) {
            if (accountRepository.findById(transactionDTO.getRecipientAccountId()).isPresent()) {
                Account recipientAccount = accountRepository.findById(transactionDTO.getRecipientAccountId()).get();

                User userAccount = userRepository.findByUsername(userDetails.getUsername()).get();

                List<Account> userAccountList = accountRepository.findByPrimaryOwnerId(userAccount.getId());


                for (Account account : userAccountList) {

                    if (account.getSecretKey().equals(transactionDTO.getSecretKey())) {
                        if (account.getSecretKey().equals(recipientAccount.getSecretKey())) {
                            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Unfortunately you cannot transfer funds from your account to itself.");
                        }
                        Account senderAccount = accountRepository.findBySecretKey(transactionDTO.getSecretKey());
                        if (senderAccount.getStatus().equals(Status.FROZEN)) {
                            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Your account is currently frozen, contact our support for more information.");
                        }
                        Transaction transaction = new Transaction(transactionDTO.getTransactionAmount(), recipientAccount, senderAccount, transactionDTO.getDescription(), PaymentMode.INSTANT);
                        transaction.setDateTime();


                        // ===========   DETECT POTENTIAL FRAUD =============

                        /*
                        Calculates the average between the total amount of transactions related to a specific secret key, and the number of days until last transaction.
                        If the count of the current's day transactions is bigger than 150% of the average, it sends a warning email to the customer.
                         */


                        double days = DAYS.between(account.getCreationDate(), transaction.getDateTime());
                        int count = transactionRepository.findCountByDateAndSecretKey(transactionDTO.getSecretKey(), LocalDate.now());
                        double transactionsPerDayAverage = count / days;
                        String email = accountHolderRepository.findById(userAccount.getId()).get().getEmail();
                        List<Transaction> transactionsList = transactionRepository.findAllBySecretKey(transactionDTO.getSecretKey());
                        if (transactionsList.size() > 0) {
                            Transaction lastTransaction = transactionRepository.findById(transactionsList.get(transactionsList.size() - 1).getId()).get();
                       /*
                            Determines if is passed less than one second from the last transaction removing 2 seconds from current time and comparing;
                       */

                            if (transactionsList.size() != 0 && lastTransaction.getDateTime().isAfter(LocalDateTime.now().minusSeconds(2))) {
                                emailService.sendMail(email, "Alert! Suspicious Activity Detected!", "Your account has been temporarily frozen due to an abnormal activity.");
                                senderAccount.setStatus(Status.FROZEN);
                                accountRepository.save(senderAccount);
                                throw new ResponseStatusException(HttpStatus.LOOP_DETECTED, "Your account has been temporarily frozen, potential fraud detected.");
                            }
                            if ((transactionsPerDayAverage * 1.5) < count) {
                                emailService.sendMail(email, "Alert! Suspicious Activity Detected!", "We noticed that today you are exceeding the number of transactions you used to do daily. Please, contact our support if you didn't perform the operations.");
                            }
                        }

                        if (transactionDTO.getTransactionAmount().getAmount().compareTo(senderAccount.getBalance().getAmount()) > 0) {
                            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not enough founds to perform the transaction.");
                        }
                        if (transactionDTO.getTransactionAmount().getAmount().compareTo(senderAccount.getBalance().getAmount()) <= 0) {

                            senderAccount.setBalance(new Money(senderAccount.getBalance().getAmount().subtract(transactionDTO.getTransactionAmount().getAmount().add(Transaction.SURCHARGE_FEE_INSTANT_PAYMENT.getAmount()))));
                            accountRepository.save(senderAccount);
                            recipientAccount.setBalance(new Money(recipientAccount.getBalance().getAmount().add(transaction.getAmount().getAmount())));
                            accountRepository.save(recipientAccount);

                            return transactionRepository.save(transaction);

                        }

                    }

                }
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect secret key.");
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The recipient Account doesn't exist.");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You must specify the Payment Mode (keep in mind that for STANDARD/SCHEDULED payments you must use PUT http request)");
    }

    public Transaction thirdPartyTransaction(Map<String, String> headers) {

        if (thirdPartyRepository.findById(thirdPartyRepository.findByHashKey(headers.get("hashkey"))).isPresent()) {
            ThirdPartyAccount thirdPartyAccount = thirdPartyRepository.findById(thirdPartyRepository.findByHashKey(headers.get("hashkey"))).get();
            if (accountRepository.findById(Long.valueOf(headers.get("accountid"))).isPresent()) {
                if (accountRepository.findBySecretKey(headers.get("secretkey")) != null) {
                    Account account = accountRepository.findBySecretKey(headers.get("secretkey"));

                    if (headers.get("request").equals("send")) {

                        Transaction sendingTrans = new Transaction(thirdPartyAccount, new Money(BigDecimal.valueOf(Long.parseLong(headers.get("amount")))), account);
                        account.setBalance(new Money(account.getBalance().getAmount().add(BigDecimal.valueOf(Long.parseLong(headers.get("amount"))))));
                        return transactionRepository.save(sendingTrans);
                    }
                    if (headers.get("request").equals("receive")) {
                        Transaction receivingTrans = new Transaction(thirdPartyAccount, account, new Money(BigDecimal.valueOf(Long.parseLong(headers.get("amount")))));

                        if (account.getBalance().getAmount().compareTo(BigDecimal.valueOf(Long.parseLong(headers.get("amount")))) > 0) {
                            account.setBalance(new Money(account.getBalance().getAmount().subtract(BigDecimal.valueOf(Long.parseLong(headers.get("amount"))))));
                            return transactionRepository.save(receivingTrans);
                        } else {
                            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Insufficient funds to perform the transaction.");
                        }
                    }
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request must be 'send' or 'receive'.");
                }
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect secretKey.");
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account id not found.");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect hashKey.");
    }

        public List<Transaction> showTransactions (UserDetails userDetails){

            User userAccount = userRepository.findByUsername(userDetails.getUsername()).get();
            return transactionRepository.findAllById(Collections.singleton(userAccount.getId()));

        }

    public Transaction findById(Long id){
        return transactionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found."));
    }
}

