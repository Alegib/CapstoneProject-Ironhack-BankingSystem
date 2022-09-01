
package Ironhack.CapstoneProject.models.Accounts;


import Ironhack.CapstoneProject.models.Enums.Status;
import Ironhack.CapstoneProject.models.Transactions.Money;
import Ironhack.CapstoneProject.models.Users.AccountHolder;

import javax.persistence.Entity;

@Entity
public class StudentChecking extends Account{

    // NO maintenance fee, NO minimum balance


    public StudentChecking(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, Status status, String secretKey) {
        super(primaryOwner, secondaryOwner, balance, status, secretKey);
    }

    public StudentChecking(AccountHolder primaryOwner, Money balance, Status status, String secretKey) {
        super(primaryOwner, balance, status, secretKey);
    }

    public StudentChecking() {
    }


}

