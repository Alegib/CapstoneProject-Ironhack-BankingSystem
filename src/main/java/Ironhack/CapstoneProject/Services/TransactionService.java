package Ironhack.CapstoneProject.Services;

import Ironhack.CapstoneProject.DTO.AccountDTO;
import Ironhack.CapstoneProject.DTO.TransactionDTO;
import Ironhack.CapstoneProject.models.Accounts.Account;
import Ironhack.CapstoneProject.models.Enums.PaymentMode;
import Ironhack.CapstoneProject.models.Transactions.Transaction;
import Ironhack.CapstoneProject.models.Users.User;
import Ironhack.CapstoneProject.models.repositories.AccountRepository;
import Ironhack.CapstoneProject.models.repositories.ThirdPartyRepository;
import Ironhack.CapstoneProject.models.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;
/*
    public List<Transaction> findAllByAccount(AccountDTO accountDTO){
        if (transactionRepository.findById)
    }


 */
    /*
    public Transaction sendMoney(TransactionDTO transactionDTO) {
        if (accountRepository.findById(transactionDTO.getReceivingAccountId()).isPresent()) {
            if(accountRepository.findById(transactionDTO.) )
            if (transactionDTO.getPaymentMode().equals(PaymentMode.STANDARD) || transactionDTO.getPaymentMode() == null){
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                Object pricipal = auth.getPrincipal();
                if (pricipal instanceof User) {
                    Long user = ((Account) pricipal).getId();
                }

        }


    }
        return transactionDTO;


 */
}
