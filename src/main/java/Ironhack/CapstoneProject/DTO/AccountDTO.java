package Ironhack.CapstoneProject.DTO;

import Ironhack.CapstoneProject.models.Enums.AccountType;
import Ironhack.CapstoneProject.models.Enums.Status;
import Ironhack.CapstoneProject.models.Transactions.Money;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import java.math.BigDecimal;

public class AccountDTO {
    private Long accountId;
    private Long primaryOwnerId;
    private Long secondaryOwnerId;

    private Money balance;
    private Status status;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private BigDecimal interestRate;
    private Money creditLimit;

    public AccountDTO(Long accountId) {
        this.accountId = accountId;
    }

    public AccountDTO() {
    }

    public Long getPrimaryOwnerId() {
        return primaryOwnerId;
    }

    public Long getSecondaryOwnerId() {
        return secondaryOwnerId;
    }

    public void setSecondaryOwnerId(Long secondaryOwnerId) {
        this.secondaryOwnerId = secondaryOwnerId;
    }

    public void setPrimaryOwnerId(Long primaryOwnerId) {
        this.primaryOwnerId = primaryOwnerId;

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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
