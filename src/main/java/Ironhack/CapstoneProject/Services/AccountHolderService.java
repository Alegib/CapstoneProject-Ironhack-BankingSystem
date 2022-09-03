package Ironhack.CapstoneProject.Services;

import Ironhack.CapstoneProject.DTO.AccountHolderDTO;
import Ironhack.CapstoneProject.models.Users.AccountHolder;
import Ironhack.CapstoneProject.models.Users.Role;
import Ironhack.CapstoneProject.models.repositories.AccountHolderRepository;
import Ironhack.CapstoneProject.models.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountHolderService {
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<AccountHolder> findAllAccountHolders(){
        return accountHolderRepository.findAll();
    }

    public AccountHolder createAccountHolder(AccountHolderDTO accountHolderDTO){
                AccountHolder accountHolder = new AccountHolder(accountHolderDTO.getName(), accountHolderDTO.getAge(),
                accountHolderDTO.getUsername(), passwordEncoder.encode(accountHolderDTO.getPassword()),
                accountHolderDTO.getDateOfBirth(), accountHolderDTO.getAddress());
        accountHolder.setEmail(accountHolderDTO.getEmail());
        accountHolder.setPhoneNumber(accountHolderDTO.getPhoneNumber());

        if(accountHolderDTO.getMailingAddress() != null){
            accountHolder.setMailingAddress(accountHolderDTO.getMailingAddress());
        }
        accountHolder = accountHolderRepository.save(accountHolder);
        Role role = roleRepository.save(new Role("ACCOUNT_HOLDER", accountHolder));
        return accountHolderRepository.save(accountHolder);
    }
}
