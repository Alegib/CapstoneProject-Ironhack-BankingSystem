package Ironhack.CapstoneProject.Controllers.InterfaceControllers;

import Ironhack.CapstoneProject.DTO.AccountDTO;
import Ironhack.CapstoneProject.models.Transactions.Transaction;

import java.util.List;

public interface TransactionControllerInterface {

    List<Transaction> findAllByAccount(AccountDTO accountDTO);
    Transaction sendMoney();
}
