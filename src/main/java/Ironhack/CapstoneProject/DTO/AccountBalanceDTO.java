package Ironhack.CapstoneProject.DTO;

import Ironhack.CapstoneProject.models.Transactions.Money;

public class AccountBalanceDTO {
    private Long id;
    private Money balance;

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
}
