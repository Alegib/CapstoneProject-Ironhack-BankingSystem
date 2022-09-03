
package Ironhack.CapstoneProject.models.Accounts;


import Ironhack.CapstoneProject.models.Enums.Status;
import Ironhack.CapstoneProject.models.Transactions.Money;
import Ironhack.CapstoneProject.models.Users.AccountHolder;

import javax.persistence.Entity;

@Entity
public class StudentChecking extends Account{

    public StudentChecking(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, Status status) {
        super(primaryOwner, secondaryOwner, balance, status);
        setSecretKey();
        setCreationDate();
    }

    public StudentChecking(AccountHolder primaryOwner, Money balance, Status status) {
        super(primaryOwner, balance, status);
        setCreationDate();
        setSecretKey();
    }

    public StudentChecking() {
    }


}

