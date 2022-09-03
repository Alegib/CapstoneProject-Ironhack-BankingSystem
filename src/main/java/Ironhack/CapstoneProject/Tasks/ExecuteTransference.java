package Ironhack.CapstoneProject.Tasks;

import Ironhack.CapstoneProject.Services.EmailService.EmailService;
import Ironhack.CapstoneProject.models.Accounts.Account;
import Ironhack.CapstoneProject.models.Enums.PaymentMode;
import Ironhack.CapstoneProject.models.Transactions.Money;
import Ironhack.CapstoneProject.models.Transactions.Transaction;
import Ironhack.CapstoneProject.models.Users.AccountHolder;
import Ironhack.CapstoneProject.models.repositories.AccountHolderRepository;
import Ironhack.CapstoneProject.models.repositories.AccountRepository;
import Ironhack.CapstoneProject.models.repositories.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import static java.time.temporal.ChronoUnit.*;


import java.time.LocalDateTime;
import java.util.List;

@Component
@EnableScheduling
@Async
public class ExecuteTransference {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    private static final Logger log = LoggerFactory.getLogger(ExecuteTransference.class);

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  * * * * * * * * * * * * *

    It checks every minute from Monday to Friday if a programmed or a standard transaction must be executed.

      * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  * * * * * * * * * * * * * */

    @Scheduled(cron = "1 * * * * 1-5")
    public void applyInterestAndMonthlyFee() {
        List<Transaction> allTransactionList = transactionRepository.findAll();

        for (Transaction tran : allTransactionList){
            PaymentMode paymentMode = tran.getPaymentMode();
            Account recipientAccount = tran.getRecipientAccount();
            Account senderAccount = tran.getSenderAccount();
            AccountHolder accountHolder = accountHolderRepository.getReferenceById(senderAccount.getId());


            switch (paymentMode){
                case STANDARD -> {
                    if (DAYS.between(tran.getDateTime(), LocalDateTime.now()) >= 2) {
                        if (senderAccount.getBalance().getAmount().compareTo(tran.getAmount().getAmount()) < 0){
                            emailService.sendMail(accountHolder.getEmail(), "Transaction Canceled.", "We're sorry to inform you that the transaction has been canceled due to insufficient funds in your account.");
                            transactionRepository.delete(tran);
                        }
                       recipientAccount.setBalance(new Money(recipientAccount.getBalance().getAmount().add(tran.getAmount().getAmount())));
                       senderAccount.setBalance(new Money(senderAccount.getBalance().getAmount().subtract(tran.getAmount().getAmount())));
                       accountRepository.save(recipientAccount);
                       accountRepository.save(senderAccount);
                    }

                }
                case SCHEDULED -> {
                    if (MINUTES.between(tran.getDateTime(), LocalDateTime.now()) <= 0){
                        if (senderAccount.getBalance().getAmount().compareTo(tran.getAmount().getAmount()) < 0){
                            emailService.sendMail(accountHolder.getEmail(), "Transaction Canceled.", "We're sorry to inform you that the transaction has been canceled due to insufficient funds in your account.");
                            transactionRepository.delete(tran);
                        }
                        recipientAccount.setBalance(new Money(recipientAccount.getBalance().getAmount().add(tran.getAmount().getAmount())));
                        senderAccount.setBalance(new Money(senderAccount.getBalance().getAmount().subtract(tran.getAmount().getAmount())));
                        accountRepository.save(recipientAccount);
                        accountRepository.save(senderAccount);

                    }
                }
            }
        }
    }
}
