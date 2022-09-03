package Ironhack.CapstoneProject.models.Accounts;

import Ironhack.CapstoneProject.models.Enums.Status;
import Ironhack.CapstoneProject.models.Transactions.Money;
import Ironhack.CapstoneProject.models.Users.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity

public class Checking extends Account {


    public static Money MONTHLY_MAINTENANCE_FEE = new Money(BigDecimal.valueOf(12));

    public static Money MINIMUM_BALANCE = new Money(BigDecimal.valueOf(250));


    public Checking(AccountHolder primaryOwner, Money balance, Status status) {
        super(primaryOwner, balance, status);
        setCreationDate();
        setSecretKey();

    }

    public Checking(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, Status status) {
        super(primaryOwner, secondaryOwner, balance, status);
        setCreationDate();
        setSecretKey();
    }

    public Checking() {
    }


}
