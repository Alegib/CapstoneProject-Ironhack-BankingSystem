package Ironhack.CapstoneProject.Controllers.InterfaceControllers;

import Ironhack.CapstoneProject.DTO.AccountDTO;
import Ironhack.CapstoneProject.DTO.TransactionDTO;
import Ironhack.CapstoneProject.models.Transactions.Transaction;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.Map;

public interface TransactionControllerInterface {

    List<Transaction> findAllTransactionsByAccount(AccountDTO accountDTO);
    Transaction performTransaction(TransactionDTO transactionDTO, UserDetails userDetails);
    Transaction thirdPartyTransaction(Map<String, String> headers);
    List<Transaction> showTransactions(UserDetails userDetails);
    Transaction findTransaction(Long id);
}
