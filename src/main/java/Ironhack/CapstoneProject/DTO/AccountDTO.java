package Ironhack.CapstoneProject.DTO;

import Ironhack.CapstoneProject.models.Accounts.Savings;
import Ironhack.CapstoneProject.models.Enums.AccountType;
import Ironhack.CapstoneProject.models.Enums.Status;
import Ironhack.CapstoneProject.models.Transactions.Money;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.number.money.MonetaryAmountFormatter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountDTO {
    // balance status creationDate secretKey primaryOwner
    @NotNull
    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    @NotNull
    private Money balance;
    private Status status;
    @NotEmpty
    private String secretKey;
    @Enumerated(EnumType.STRING)
    @NotNull
    private AccountType accountType;
    private BigDecimal interestRate;
    private Money creditLimit;

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


    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
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
}
