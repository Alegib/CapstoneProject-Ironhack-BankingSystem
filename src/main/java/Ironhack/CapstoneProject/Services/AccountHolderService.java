package Ironhack.CapstoneProject.Services;

import Ironhack.CapstoneProject.DTO.AccountHolderDTO;
import Ironhack.CapstoneProject.models.Users.AccountHolder;
import Ironhack.CapstoneProject.models.repositories.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountHolderService {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public List<AccountHolder> findAllAccountHolders(){
        return accountHolderRepository.findAll();
    }

    public AccountHolder createAccountHolder(AccountHolderDTO accountHolderDTO){
        return null;
    }
}
