package Ironhack.CapstoneProject.Controllers;

import Ironhack.CapstoneProject.DTO.TransactionDTO;

import Ironhack.CapstoneProject.Services.TransactionService;
import Ironhack.CapstoneProject.models.Transactions.Transaction;
import Ironhack.CapstoneProject.models.repositories.AccountHolderRepository;
import Ironhack.CapstoneProject.models.repositories.LoginRepository;
import Ironhack.CapstoneProject.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

@RestController

public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    HttpServletRequest request;




    @GetMapping("/all-transactions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> findAllTransactionsByAccount(@PathVariable Long id){
        return transactionService.findAllTransactionsByAccount(id);
    }

    @PostMapping("/transaction/send")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Transaction performInstantTransaction(@RequestBody TransactionDTO transactionDTO, @AuthenticationPrincipal UserDetails userDetails){
        return transactionService.performInstantTransaction(transactionDTO, userDetails);
    }
    @PutMapping("/transaction/send")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Transaction setStandardOrProgrammedTransactions(@RequestBody TransactionDTO transactionDTO, @AuthenticationPrincipal UserDetails userDetails){
        return transactionService.setStandardOrProgrammedTransactions(transactionDTO, userDetails);
    }

    @PostMapping("/transaction/third-party")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Transaction thirdPartyTransaction(@RequestHeader Map<String, String> headers){
        return transactionService.thirdPartyTransaction(headers);
    }

    @GetMapping("/user-transactions")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> showTransactions(@AuthenticationPrincipal UserDetails userDetails){
        return transactionService.showTransactions(userDetails);
    }

    @GetMapping("/find-transaction/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Transaction findTransaction(@PathVariable Long id){
        return transactionService.findById(id);
    }



}
