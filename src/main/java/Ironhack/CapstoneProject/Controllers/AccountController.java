package Ironhack.CapstoneProject.Controllers;

import Ironhack.CapstoneProject.DTO.AccountDTO;
import Ironhack.CapstoneProject.Services.AccountService;
import Ironhack.CapstoneProject.models.Accounts.Account;
import Ironhack.CapstoneProject.models.Accounts.ThirdPartyAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/account-page")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/all-accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAllAccounts(){
        return accountService.findAllAccounts();

    }
      /*
    @PostMapping("/create-account")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody AccountDTO accountDTO){
        return accountService.createAccount(accountDTO);
    }

    @PostMapping("/create-thirdParty")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdPartyAccount createThirdPartyAccount(){
        return accountService.save();
    }

   */





}
