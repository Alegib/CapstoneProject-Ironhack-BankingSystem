package Ironhack.CapstoneProject.models.Transactions;

import Ironhack.CapstoneProject.models.Accounts.Account;
import Ironhack.CapstoneProject.models.Enums.PaymentMode;
import Ironhack.CapstoneProject.models.Accounts.ThirdPartyAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
public class Transaction{
    public static final Money PENALTY_FEE = new Money(new BigDecimal(40), Money.USD);
    public static final Money SURCHARGE_FEE_INSTANT_PAYMENT = new Money(new BigDecimal(1.5), Money.USD);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "transference_amount"))
    private Money amount;
    private LocalDateTime dateTime;
    @Column(length = 30)
    private String description;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    private Account recipientAccount;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    private Account senderAccount;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    private ThirdPartyAccount thirdPartyAccount;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;


    public Transaction(Money amount, Account recipientAccount, Account senderAccount, String description, PaymentMode paymentMode) {
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.description = description;
        this.senderAccount = senderAccount;
        this.recipientAccount = recipientAccount;
        setDateTime();
    }

    public Transaction(Money amount) {
        this.amount = amount;
    }

    public Transaction(ThirdPartyAccount thirdPartyAccount, Money amount, Account recipientAccount) {
        this.paymentMode = PaymentMode.INSTANT;
        this.amount = amount;
        this.recipientAccount = recipientAccount;
        this.thirdPartyAccount = thirdPartyAccount;
        setDateTime();
    }
    public Transaction(ThirdPartyAccount thirdPartyAccount, Account senderAccount, Money amount) {
        this.paymentMode = PaymentMode.INSTANT;
        this.amount = amount;
        this.senderAccount = senderAccount;
        this.thirdPartyAccount = thirdPartyAccount;
        setDateTime();
    }

    public Transaction(Money amount, Account recipientAccount, String description, PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
        this.amount = amount;
        this.description = description;
        this.recipientAccount = recipientAccount;
        setDateTime();
    }

    public Transaction(Money amount, String description, Account senderAccount) {
        setDateTime();
        this.paymentMode = PaymentMode.INSTANT;
        this.amount = amount;
        this.description = description;
        this.senderAccount = senderAccount;
    }

    public Transaction(Money amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public Transaction() {
    }

    public ThirdPartyAccount getThirdPartyAccount() {
        return thirdPartyAccount;
    }

    public void setThirdPartyAccount(ThirdPartyAccount thirdPartyAccount) {
        this.thirdPartyAccount = thirdPartyAccount;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime() {
        this.dateTime = LocalDateTime.now();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }


    public Account getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(Account recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public ThirdPartyAccount getThirdParty() {
        return thirdPartyAccount;
    }

    public void setThirdParty(ThirdPartyAccount thirdPartyAccount) {
        this.thirdPartyAccount = thirdPartyAccount;
    }


    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

}
