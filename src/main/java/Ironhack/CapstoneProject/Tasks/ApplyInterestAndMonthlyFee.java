package Ironhack.CapstoneProject.Tasks;

import Ironhack.CapstoneProject.models.Accounts.*;
import Ironhack.CapstoneProject.models.Transactions.Money;
import Ironhack.CapstoneProject.models.Transactions.Transaction;
import Ironhack.CapstoneProject.models.repositories.AccountRepository;
import Ironhack.CapstoneProject.models.repositories.LoginRepository;
import Ironhack.CapstoneProject.models.repositories.TransactionRepository;
import Ironhack.CapstoneProject.models.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import static java.time.temporal.ChronoUnit.*;

@Component
@EnableScheduling
public class ApplyInterest {

    private static final Logger log = LoggerFactory.getLogger(ApplyInterest.class);
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginRepository loginRepository;


    @Scheduled(fixedRateString = "PT01s")
    public void reportCurrentTime() {


        for (Account account : accountRepository.findAll()) {
            if (account instanceof Savings){
                if (MONTHS.between(account.getInterestDate(), LocalDateTime.now())  == 0){
                    Savings savings = (Savings) account;
                    savings.setCreationDate(LocalDateTime.now());
                    BigDecimal accruedInterest = savings.getBalance().getAmount().multiply(savings.getInterestRate());
                    savings.setBalance(new Money(savings.getBalance().getAmount().add(accruedInterest)));
                    Transaction creditInterest = new Transaction(new Money(accruedInterest), savings, "Accrued interest");
                    transactionRepository.save(creditInterest);
                    accountRepository.save(savings);

                    log.info("Interest credited on account Id: {}", account.getId());
/*

                    INTEREST RATE NEEDS TO BE FIXED

                    System.err.println(" SAVINGS ");
                    System.err.println(Savings.DEFAULT_INTEREST_RATE);
                    System.err.println(savings.getBalance().getAmount());
                    System.err.println(accruedInterest);



 */



                }
            }
            if (account instanceof Checking){
                if (MONTHS.between(account.getMonthlyFeeDate(), LocalDateTime.now()) == 0){
                    Checking checking = (Checking) account;
                    checking.setMonthlyFeeDate(LocalDateTime.now());
                    checking.setBalance(new Money(checking.getBalance().getAmount().subtract(Checking.MONTHLY_MAINTENANCE_FEE.getAmount())));
                    Transaction deductFee = new Transaction(Checking.MONTHLY_MAINTENANCE_FEE, "Monthly fee", checking);

                    transactionRepository.save(deductFee);
                    accountRepository.save(checking);

                    log.info("Monthly fee deducted from account Id: {}", account.getId());

                }

            }
            if (account instanceof CreditCard){
                if(MONTHS.between(account.getInterestDate(), LocalDateTime.now()) == 0){
                    CreditCard creditCard = (CreditCard) account;
                    creditCard.setCreationDate(LocalDateTime.now());
                    BigDecimal accruedInterest = creditCard.getInterestRate().divide(BigDecimal.valueOf(12), 4, RoundingMode.HALF_EVEN).multiply(creditCard.getBalance().getAmount());
                    creditCard.setBalance(new Money(creditCard.getBalance().getAmount().add(accruedInterest)));
                    Transaction creditInterest = new Transaction(new Money(accruedInterest), creditCard, "Accrued interest");

                    transactionRepository.save(creditInterest);
                    accountRepository.save(creditCard);

                    log.info("Interest credited on account Id: {}", account.getId());

                }
            }

        }

    }

}
