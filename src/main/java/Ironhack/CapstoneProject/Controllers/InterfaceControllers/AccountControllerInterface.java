package Ironhack.CapstoneProject.Controllers.InterfaceControllers;

import Ironhack.CapstoneProject.DTO.AccountDTO;
import Ironhack.CapstoneProject.models.Accounts.Account;
import Ironhack.CapstoneProject.models.Accounts.Checking;
import Ironhack.CapstoneProject.models.Accounts.ThirdPartyAccount;

import java.util.List;

public interface AccountControllerInterface {
    List<Account> findAllAccounts();
    Account createAccount(AccountDTO accountDTO);
    ThirdPartyAccount createThirdPartyAccount();
}
