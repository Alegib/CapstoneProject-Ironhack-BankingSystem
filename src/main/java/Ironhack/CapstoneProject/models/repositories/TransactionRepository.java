package Ironhack.CapstoneProject.models.repositories;

import Ironhack.CapstoneProject.DTO.AccountDTO;
import Ironhack.CapstoneProject.models.Transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM transaction JOIN account on account.primary_owner_id = account.id where secret_key = :secretKey")
    List<Transaction> findAllBySecretKey(@Param("secretKey") String secretKey);

    @Query(nativeQuery = true, value = "Select COUNT(*) from transaction JOIN account ON account.primary_owner_id = account.id WHERE secret_key = :secretKey AND transaction.date_time LIKE :date%")
    int findCountByDateAndSecretKey(@Param("secretKey") String secretKey, @Param("date") LocalDate date);

    List<Transaction> findBySenderAccountId(Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM transaction WHERE sender_account_id = :id AND description LIKE 'Penalty Fee%'")
    List<Transaction> findByDescription(Long id);
}
