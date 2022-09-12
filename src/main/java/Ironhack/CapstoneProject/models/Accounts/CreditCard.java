
package Ironhack.CapstoneProject.models.Accounts;

import Ironhack.CapstoneProject.models.Transactions.Money;
import Ironhack.CapstoneProject.models.Users.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Random;

@Entity

public class CreditCard extends Account{

    public static final BigDecimal DEFAULT_CREDIT_LIMIT = BigDecimal.valueOf(100);
    public static final BigDecimal MAX_CREDIT_LIMIT = BigDecimal.valueOf(100000);
    public static final BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal(0.2, new MathContext(4));
    public static final BigDecimal MIN_INTEREST_RATE = new BigDecimal(0.1, new MathContext(4));

    private BigDecimal interestRate;
    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "credit_limit"))
    private Money creditLimit;

    private String cardNumber;

    public CreditCard(AccountHolder primaryOwner, Money balance, AccountHolder secondaryOwner, BigDecimal interestRate, Money creditLimit) {
        super(primaryOwner, balance, secondaryOwner);
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
        setCardNumber();
        setCreationDate();
        setSecretKey();
    }

    public CreditCard(AccountHolder primaryOwner, Money balance, BigDecimal interestRate, Money creditLimit) {
        super(primaryOwner, balance);
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
        setCardNumber();
        setCreationDate();
        setSecretKey();
    }

    public CreditCard() {
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber() {
        int length = 4;
        Random rand = new Random();
        String randCardNum = "";
        for (int i = 0; i < length; i++){
            int randomGen = rand.nextInt(1000, 9999);
            if (i < 3) randCardNum += randomGen + "-";
            else{randCardNum += randomGen;}
        }
        cardNumber = randCardNum;

    }
}




