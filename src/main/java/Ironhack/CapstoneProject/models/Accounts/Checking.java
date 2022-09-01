package Ironhack.CapstoneProject.models.Accounts;

import Ironhack.CapstoneProject.models.Enums.Status;
import Ironhack.CapstoneProject.models.Transactions.Money;
import Ironhack.CapstoneProject.models.Users.AccountHolder;

import javax.persistence.*;


@Entity

public class Checking extends Account {


    // penaltyFee = 40

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "monthly_maintenance_fee"))
    private Money monthlyMaintenanceFee;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance"))
    private  Money minimumBalance = new Money();


    public Checking(AccountHolder primaryOwner, Money balance, Status status, String secretKey) {
        super(primaryOwner, balance, status, secretKey);
        setCreationDate();

    }

    public Checking(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, Status status,  String secretKey) {
        super(primaryOwner, secondaryOwner, balance, status, secretKey);
        setCreationDate();
    }

    public Checking() {
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(Money monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }


}
