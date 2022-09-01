package Ironhack.CapstoneProject.Controllers;

import Ironhack.CapstoneProject.DTO.AccountDTO;
import Ironhack.CapstoneProject.DTO.TransactionDTO;
import Ironhack.CapstoneProject.Services.TransactionService;
import Ironhack.CapstoneProject.models.Transactions.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
/*
    @GetMapping("/all-transactions")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> findAllTransactionsByAccount(@RequestBody AccountDTO accountDTO){
        return transactionService.findAllTransactionsByAccount(accountDTO);
    }

 */
/*
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Transaction sendMoney(@RequestBody TransactionDTO transactionDTO){
        return transactionService.sendMoney();
    }

 */
}
