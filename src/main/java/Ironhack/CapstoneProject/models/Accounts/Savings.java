
package Ironhack.CapstoneProject.models.Accounts;

import Ironhack.CapstoneProject.models.Enums.Status;
import Ironhack.CapstoneProject.models.Transactions.Money;
import Ironhack.CapstoneProject.models.Users.AccountHolder;


import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.MathContext;

@Entity
public class Savings extends Account{

    public static final BigDecimal DEFAULT_INTEREST_RATE = BigDecimal.valueOf(0.0025);
    public static final BigDecimal MAX_INTEREST_RATE = BigDecimal.valueOf(0.5);

    public static final Money MINIMUM_BALANCE = new Money(new BigDecimal(1000));
    @Column(precision = 10, scale = 4)
    private BigDecimal interestRate;

    public Savings(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, Status status, BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner, balance, status);
        this.interestRate = interestRate;
        setSecretKey();
        setCreationDate();
    }

    public Savings(AccountHolder primaryOwner, Money balance, Status status, BigDecimal interestRate) {
        super(primaryOwner, balance, status);
        this.interestRate = interestRate;
        setCreationDate();
        setSecretKey();
    }

    public Savings() {
    }


    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }





}



