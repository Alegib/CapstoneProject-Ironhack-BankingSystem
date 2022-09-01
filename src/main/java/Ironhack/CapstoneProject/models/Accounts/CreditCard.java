
package Ironhack.CapstoneProject.models.Accounts;

import Ironhack.CapstoneProject.models.Transactions.Money;
import Ironhack.CapstoneProject.models.Users.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Random;

@Entity
public class CreditCard extends Account{

    public static final BigDecimal DEFAULT_CREDIT_LIMIT = BigDecimal.valueOf(100);
    public static final BigDecimal MAX_CREDIT_LIMIT = BigDecimal.valueOf(100000);
    public static final BigDecimal DEFAULT_INTEREST_RATE = BigDecimal.valueOf(0.2);
    public static final BigDecimal MIN_INTEREST_RATE = BigDecimal.valueOf(0.1);

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "card_interest_rate"))
    private BigDecimal interestRate;
    private Money creditLimit;

    private String cardNumber;

    public CreditCard(AccountHolder primaryOwner, Money balance, AccountHolder secondaryOwner, BigDecimal interestRate, Money creditLimit) {
        super(primaryOwner, balance, secondaryOwner);
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
        setCardNumber();
        setCreationDate();
    }

    public CreditCard(AccountHolder primaryOwner, Money balance, BigDecimal interestRate, Money creditLimit) {
        super(primaryOwner, balance);
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
        setCardNumber();
        setCreationDate();
    }


    public CreditCard() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getDEFAULT_CREDIT_LIMIT() {
        return DEFAULT_CREDIT_LIMIT;
    }

    public BigDecimal getMAX_CREDIT_LIMIT() {
        return MAX_CREDIT_LIMIT;
    }

    public BigDecimal getDEFAULT_INTEREST_RATE() {
        return DEFAULT_INTEREST_RATE;
    }

    public BigDecimal getMIN_INTEREST_RATE() {
        return MIN_INTEREST_RATE;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardNumber() {
        int length = 4;
        Random rand = new Random();
        for (int i = 0; i < length; i++){
            int randomGen = rand.nextInt(1000, 9999);
            if (i < 3) cardNumber += randomGen + "-";
            else{cardNumber += randomGen;}

        }
    }
}




