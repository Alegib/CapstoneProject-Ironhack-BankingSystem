package Ironhack.CapstoneProject.models.Accounts;

import Ironhack.CapstoneProject.models.Enums.Status;
import Ironhack.CapstoneProject.models.Transactions.Money;
import Ironhack.CapstoneProject.models.Transactions.Transaction;
import Ironhack.CapstoneProject.models.Users.AccountHolder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "current_balance"))
    @NotNull
    private Money balance;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
    @NotNull
    private LocalDateTime creationDate;
    @NotEmpty
    private String secretKey;
    @ManyToOne
    @NotNull
    private AccountHolder primaryOwner;
    @ManyToOne
    private AccountHolder secondaryOwner;

    @OneToMany(mappedBy = "sendingAccount")
    private List<Transaction> transactionSentList;

    @OneToMany(mappedBy = "receivingAccount")
    private List<Transaction> transactionReceivedList;

    //Checking Accounts, StudentChecking and Savings Constructors
    public Account(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, Status status, String secretKey) {
        this.balance = balance;
        this.status = status;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

    public Account(AccountHolder primaryOwner, Money balance, Status status, String secretKey) {
        this.balance = balance;
        this.status = status;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
    }

    // Credit Cards Constructors


    public Account(AccountHolder primaryOwner, Money balance, AccountHolder secondaryOwner) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

    public Account(AccountHolder primaryOwner, Money balance) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate() {
        this.creationDate = LocalDateTime.now();
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public List<Transaction> getTransactionSentList() {
        return transactionSentList;
    }

    public void setTransactionSentList(List<Transaction> transactionSentList) {
        this.transactionSentList = transactionSentList;
    }

    public List<Transaction> getTransactionReceivedList() {
        return transactionReceivedList;
    }

    public void setTransactionReceivedList(List<Transaction> transactionReceivedList) {
        this.transactionReceivedList = transactionReceivedList;
    }
}
