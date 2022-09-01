package Ironhack.CapstoneProject.Controllers;

import Ironhack.CapstoneProject.DTO.AccountHolderDTO;
import Ironhack.CapstoneProject.Services.AccountHolderService;
import Ironhack.CapstoneProject.models.Users.AccountHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountHolderController {
    @Autowired
    AccountHolderService accountHolderService;

    @GetMapping("/all-accountHolders")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<AccountHolder> findAllAccountHolders(){
        return accountHolderService.findAllAccountHolders();

    }

    @PostMapping("/add-accountHolder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder createAccountHolder(AccountHolderDTO accountHolderDTO){
        return accountHolderService.createAccountHolder(accountHolderDTO);
    }


}
