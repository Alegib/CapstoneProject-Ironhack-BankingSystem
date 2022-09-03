package Ironhack.CapstoneProject.Tasks;

import Ironhack.CapstoneProject.Services.EmailService.EmailService;
import Ironhack.CapstoneProject.models.Accounts.Account;
import Ironhack.CapstoneProject.models.Accounts.Checking;
import Ironhack.CapstoneProject.models.Accounts.Savings;
import Ironhack.CapstoneProject.models.Transactions.Transaction;
import Ironhack.CapstoneProject.models.Users.User;
import Ironhack.CapstoneProject.models.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import static java.time.temporal.ChronoUnit.*;

import java.time.LocalDateTime;
import java.util.List;


@Component
public class ApplyPenaltyFee implements ApplicationListener<AuthenticationSuccessEvent> {

    private static final Logger log = LoggerFactory.getLogger(ApplyPenaltyFee.class);
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  * * * * * * * * * * * * *
    Each time a user logs in, it checks if the balance of his Checking/Savings account is below the minimum and, if so, applies the penalty fee every 15 days.
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  * * * * * * * * * * * * * */

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Authentication auth = event.getAuthentication();
        User user = userRepository.findByUsername(auth.getName()).get();
        String userEmail = accountHolderRepository.findById(user.getId()).get().getEmail();

        List<Account> userAccounts = accountRepository.findByPrimaryOwnerId(user.getId());

        for (Account account : userAccounts) {

            List<Transaction> penaltyTransactions = transactionRepository.findByDescription(account.getId());

            if (account instanceof Checking) {

                if (account.getBalance().getAmount().compareTo(Checking.MINIMUM_BALANCE.getAmount()) < 0) {
                    account.getBalance().setAmount(account.getBalance().getAmount().subtract(Transaction.PENALTY_FEE.getAmount()));
                    Transaction deductPenaltyFee = new Transaction(Transaction.PENALTY_FEE, "Penalty Fee", account);

                    if (penaltyTransactions.size() == 0) {
                        transactionRepository.save(deductPenaltyFee);
                        accountRepository.save(account);
                        emailService.sendMail(userEmail, "Penalty fee", "Dear Customer, notice that a penalty fee has been applied on your account since your funds are below the minimum.");
                        log.info("Penalty fee applied on account Id: {}", account.getId());

                    } else if (DAYS.between(penaltyTransactions.get(penaltyTransactions.size() - 1).getDateTime(), LocalDateTime.now()) > 15) {
                            transactionRepository.save(deductPenaltyFee);
                            accountRepository.save(account);
                            log.info("Penalty fee applied on account Id: {}", account.getId());

                    }
                }
            }

            if (account instanceof Savings) {
                if (account.getBalance().getAmount().compareTo(Savings.MINIMUM_BALANCE.getAmount()) < 0) {
                    Transaction deductPenaltyFee = new Transaction(Transaction.PENALTY_FEE, "Penalty Fee", account);
                    account.getBalance().setAmount(account.getBalance().getAmount().subtract(Transaction.PENALTY_FEE.getAmount()));
                    if (penaltyTransactions.size() == 0) {
                        transactionRepository.save(deductPenaltyFee);
                        accountRepository.save(account);
                        emailService.sendMail(userEmail, "Penalty fee", "Dear Customer, notice that a penalty fee has been applied on your account since your funds are below the minimum.");
                        log.info("Penalty fee applied on account Id: {}", account.getId());
                    } else if (DAYS.between(penaltyTransactions.get(penaltyTransactions.size() - 1).getDateTime(), LocalDateTime.now()) > 15) {
                            transactionRepository.save(deductPenaltyFee);
                            accountRepository.save(account);
                            log.info("Penalty fee applied on account Id: {}", account.getId());

                    }
                }
            }
        }
    }
}

