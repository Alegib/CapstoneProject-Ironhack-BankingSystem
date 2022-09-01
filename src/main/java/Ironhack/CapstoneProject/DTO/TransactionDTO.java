package Ironhack.CapstoneProject.DTO;

import Ironhack.CapstoneProject.models.Enums.PaymentMode;
import Ironhack.CapstoneProject.models.Transactions.Money;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TransactionDTO {
    @NotNull
    private Money amount;
    private PaymentMode paymentMode;
    @NotNull
    private Long receivingAccountId;
    private LocalDateTime dateTime;

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Long getReceivingAccountId() {
        return receivingAccountId;
    }

    public void setReceivingAccountId(Long receivingAccountId) {
        this.receivingAccountId = receivingAccountId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
