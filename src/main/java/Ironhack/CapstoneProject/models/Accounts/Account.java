package Ironhack.CapstoneProject.models.Accounts;

import Ironhack.CapstoneProject.models.Enums.Status;
import Ironhack.CapstoneProject.models.Transactions.Money;
import Ironhack.CapstoneProject.models.Transactions.Transaction;
import Ironhack.CapstoneProject.models.Users.AccountHolder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "current_balance"))

    private Money balance;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime creationDate;

    private String secretKey;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private AccountHolder primaryOwner;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private AccountHolder secondaryOwner;
    private LocalDateTime interestDate;
    private LocalDateTime monthlyFeeDate;


    @OneToMany(mappedBy = "recipientAccount", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> transactionReceivedList;
    @OneToMany(mappedBy = "senderAccount", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> transactionSentList;

    //Checking Accounts, StudentChecking and Savings Constructors
    public Account(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, Status status) {
        this.balance = balance;
        this.status = status;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

    public Account(AccountHolder primaryOwner, Money balance, Status status) {
        this.balance = balance;
        this.status = status;
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


    public Account() {
    }

    public void setSecretKey() {
        Random rand = new Random();
        List<Character> characterList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int num = rand.nextInt(65, 90);
            if (i < 4) {
                char ch = (char) num;
                characterList.add(ch);
            }
            num = rand.nextInt(48, 57);
            char ch = (char) num;
            characterList.add(ch);
        }
        StringBuilder sb = new StringBuilder();

        for (Character ch : characterList) {
            sb.append(ch);
        }
        secretKey = sb.toString();
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


    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public List<Transaction> getTransactionList() {
        return transactionReceivedList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionReceivedList = transactionList;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getInterestDate() {
        return interestDate;
    }

    public void setInterestDate(LocalDateTime interestDate) {
        this.interestDate = interestDate;
    }

    public List<Transaction> getTransactionReceivedList() {
        return transactionReceivedList;
    }

    public void setTransactionReceivedList(List<Transaction> transactionReceivedList) {
        this.transactionReceivedList = transactionReceivedList;
    }

    public List<Transaction> getTransactionSentList() {
        return transactionSentList;
    }

    public void setTransactionSentList(List<Transaction> transactionSentList) {
        this.transactionSentList = transactionSentList;
    }

    public LocalDateTime getMonthlyFeeDate() {
        return monthlyFeeDate;
    }

    public void setMonthlyFeeDate(LocalDateTime monthlyFeeDate) {
        this.monthlyFeeDate = monthlyFeeDate;
    }
}
