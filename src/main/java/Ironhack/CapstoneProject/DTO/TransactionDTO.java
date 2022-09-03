package Ironhack.CapstoneProject.DTO;

import Ironhack.CapstoneProject.models.Enums.PaymentMode;
import Ironhack.CapstoneProject.models.Transactions.Money;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TransactionDTO {
    @NotNull
    private Money transactionAmount;

    private PaymentMode paymentMode;
    @NotNull
    private Long recipientAccountId;
    @NotEmpty
    private String secretKey;
    private String description;

    private LocalDateTime date;

    public TransactionDTO(Money transactionAmount, PaymentMode paymentMode, Long recipientAccountId, String secretKey, String description) {
        this.transactionAmount = transactionAmount;
        this.paymentMode = paymentMode;
        this.recipientAccountId = recipientAccountId;
        this.secretKey = secretKey;
        this.description = description;
    }

    public Money getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Money transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Long getRecipientAccountId() {
        return recipientAccountId;
    }

    public void setRecipientAccountId(Long recipientAccountId) {
        this.recipientAccountId = recipientAccountId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }


    public String getDescription() {
        return description;
    }


    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
