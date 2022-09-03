package Ironhack.CapstoneProject.models.Accounts;

import Ironhack.CapstoneProject.models.Transactions.Money;
import Ironhack.CapstoneProject.models.Transactions.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Entity
public class ThirdPartyAccount extends Account{

    @Column(length = 30, unique = true)

    private String name;
    private String hashKey;

    @OneToMany(mappedBy = "thirdPartyAccount", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> transactionList;

    public ThirdPartyAccount(String name) {
        this.name = name;
        setHashKey();
        setCreationDate();
        setBalance(new Money(BigDecimal.valueOf(0)));

    }

    public ThirdPartyAccount() {
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

    public void setHashKey() {
        String randName = "";
        Random rand = new Random();
        String[] splitName = name.split("");
        for (int i = 0; i < splitName.length; i++) {

            splitName[rand.nextInt(splitName.length)] += splitName[i];
        }
        for (String letter : splitName){
            randName += letter;
        }

        this.hashKey = String.valueOf(randName.hashCode());

    }
    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
