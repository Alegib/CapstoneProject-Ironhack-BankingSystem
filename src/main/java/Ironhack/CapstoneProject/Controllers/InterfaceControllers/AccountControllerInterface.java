package Ironhack.CapstoneProject.Controllers.InterfaceControllers;

import Ironhack.CapstoneProject.DTO.AccountBalanceDTO;
import Ironhack.CapstoneProject.DTO.AccountDTO;
import Ironhack.CapstoneProject.DTO.AccountStatusDTO;
import Ironhack.CapstoneProject.models.Accounts.Account;
import Ironhack.CapstoneProject.models.Accounts.Checking;
import Ironhack.CapstoneProject.models.Accounts.ThirdPartyAccount;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AccountControllerInterface {
    List<Account> findAllAccounts();
    Account createAccount(AccountDTO accountDTO);
    Account changeStatus(AccountStatusDTO accountStatusDTO);
    List<Account> showAccounts(UserDetails userDetails);
    Account modifyBalance(AccountBalanceDTO accountBalanceDTO);
    Account findById(Long id);
    void deleteAccount(AccountDTO accountDTO);


}
