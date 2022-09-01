package Ironhack.CapstoneProject.Tasks;

import Ironhack.CapstoneProject.models.Accounts.Account;
import Ironhack.CapstoneProject.models.Accounts.Checking;
import Ironhack.CapstoneProject.models.Accounts.Savings;
import Ironhack.CapstoneProject.models.Transactions.Transaction;
import Ironhack.CapstoneProject.models.Users.User;
import Ironhack.CapstoneProject.models.repositories.AccountRepository;
import Ironhack.CapstoneProject.models.repositories.LoginRepository;
import Ironhack.CapstoneProject.models.repositories.TransactionRepository;
import Ironhack.CapstoneProject.models.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ApplyFee implements ApplicationListener<AuthenticationSuccessEvent> {
    private static final Logger log = LoggerFactory.getLogger(ApplyFee.class);
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginRepository loginRepository;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Authentication auth = event.getAuthentication();
        User user = userRepository.findByUsername(auth.getName()).get();

        List<Account> userAccounts = accountRepository.findByPrimaryOwnerId(user.getId());

        for (Account account : userAccounts) {

            if (account instanceof Checking) {
                if (account.getBalance().getAmount().compareTo(Checking.MINIMUM_BALANCE.getAmount()) < 0) {
                    account.getBalance().setAmount(account.getBalance().getAmount().subtract(Transaction.PENALTY_FEE.getAmount()));
                    Transaction deductPenaltyFee = new Transaction(Transaction.PENALTY_FEE, "Penalty Fee");
                    transactionRepository.save(deductPenaltyFee);
                    accountRepository.save(account);
                    log.info("Penalty fee applied on account Id: {}", account.getId());


                    accountRepository.save(account);
                }
            }
            if (account instanceof Savings) {
                if (account.getBalance().getAmount().compareTo(Savings.MINIMUM_BALANCE.getAmount()) < 0) {

                    account.getBalance().setAmount(account.getBalance().getAmount().subtract(Transaction.PENALTY_FEE.getAmount()));
                    Transaction deductPenaltyFee = new Transaction(Transaction.PENALTY_FEE, "Penalty Fee");
                    transactionRepository.save(deductPenaltyFee);
                    accountRepository.save(account);
                    log.info("Penalty fee applied on account Id: {}", account.getId());

                }
            }

        }

    }
}
