package Ironhack.CapstoneProject.models.Accounts;

import Ironhack.CapstoneProject.models.Transactions.Transaction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class ThirdPartyAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String hashKey;

    @OneToMany(mappedBy = "thirdPartyAccount")
    private List<Transaction> transactionList;

    public ThirdPartyAccount(String name, String hashKey) {
        this.name = name;
        this.hashKey = hashKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
