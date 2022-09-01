
package Ironhack.CapstoneProject.models.Accounts;

import Ironhack.CapstoneProject.models.Enums.Status;
import Ironhack.CapstoneProject.models.Transactions.Money;
import Ironhack.CapstoneProject.models.Users.AccountHolder;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@EnableScheduling
public class Savings extends Account{

    public static final BigDecimal DEFAULT_INTEREST_RATE = BigDecimal.valueOf(0.0025);
    public static final BigDecimal MAX_INTEREST_RATE = BigDecimal.valueOf(0.5);
    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance"))
    private final Money MINIMUM_BALANCE = new Money(new BigDecimal(1000));

    private BigDecimal interestRate;

    public Savings(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, Status status, String secretKey, BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner, balance, status,secretKey);
        this.interestRate = interestRate;
        setCreationDate();
    }

    public Savings(AccountHolder primaryOwner, Money balance, Status status, String secretKey, BigDecimal interestRate) {
        super(primaryOwner, balance, status, secretKey);
        this.interestRate = interestRate;
        setCreationDate();
    }

    public Savings() {
    }

    public BigDecimal getDEFAULT_INTEREST_RATE() {
        return DEFAULT_INTEREST_RATE;
    }

    public BigDecimal getMAX_INTEREST_RATE() {
        return MAX_INTEREST_RATE;
    }

    public Money getMINIMUM_BALANCE() {
        return MINIMUM_BALANCE;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }





}



