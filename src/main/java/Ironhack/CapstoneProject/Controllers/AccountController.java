package Ironhack.CapstoneProject.Controllers;

import Ironhack.CapstoneProject.DTO.AccountBalanceDTO;
import Ironhack.CapstoneProject.DTO.AccountDTO;
import Ironhack.CapstoneProject.DTO.AccountStatusDTO;
import Ironhack.CapstoneProject.Services.AccountService;
import Ironhack.CapstoneProject.models.Accounts.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class AccountController {
    @Autowired
    AccountService accountService;


    @GetMapping("/all-accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAllAccounts(){
        return accountService.findAllAccounts();

    }

    @PostMapping("/create-savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createSavings(@RequestBody AccountDTO accountDTO){
        return accountService.createSavingsAccount(accountDTO);
    }

    @PostMapping("/create-creditCard")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createCreditCard(@RequestBody AccountDTO accountDTO){
        return accountService.createCreditCard(accountDTO);
    }
    @PostMapping("/create-checking")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createCheckingAccount(@RequestBody AccountDTO accountDTO){
        return accountService.createCheckingAccount(accountDTO);
    }



    @PatchMapping("/change-status")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account changeStatus(@RequestBody AccountStatusDTO accountStatusDTO){
        return accountService.changeStatus(accountStatusDTO);
    }

    @GetMapping("/user-accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> showAccounts(@AuthenticationPrincipal UserDetails userDetails){
        return accountService.showAccounts(userDetails);
    }

    @PutMapping("/modify-balance")
    @ResponseStatus(HttpStatus.OK)
    public Account modifyBalance(@RequestBody AccountBalanceDTO accountBalanceDTO){
        return accountService.modifyBalance(accountBalanceDTO);
    }

    @GetMapping("/find-account")
    @ResponseStatus(HttpStatus.OK)
    public Account findById(@RequestHeader Map<String, Long> header){
        return accountService.findById(header);
    }

    @DeleteMapping("/delete-account")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAccount(@RequestBody AccountDTO accountDTO){
        accountService.deleteAccount(accountDTO);
    }


}
