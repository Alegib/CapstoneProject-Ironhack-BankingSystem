package Ironhack.CapstoneProject.Controllers.InterfaceControllers;

import Ironhack.CapstoneProject.DTO.AccountHolderDTO;
import Ironhack.CapstoneProject.models.Users.AccountHolder;

import java.util.List;

public interface AccountHolderControllerInterface {
    List<AccountHolder> findAllAccountHolders();
    AccountHolder createAccountHolder(AccountHolderDTO accountHolderDTO);
}
