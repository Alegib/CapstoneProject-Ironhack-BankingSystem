package Ironhack.CapstoneProject.models.Transactions;

import Ironhack.CapstoneProject.models.Accounts.Account;
import Ironhack.CapstoneProject.models.Enums.PaymentMode;
import Ironhack.CapstoneProject.models.Accounts.ThirdPartyAccount;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
public class Transaction{
    public static final Money penaltyFee = new Money(new BigDecimal(40), Money.USD);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "transference_amount"))
    private Money amount;
    private LocalDateTime dateTime;

    @ManyToOne
    private Account sendingAccount;
    @ManyToOne
    private Account receivingAccount;

    @ManyToOne
    private ThirdPartyAccount thirdPartyAccount;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;



    public Transaction(Account sendingAccount, Money amount, LocalDateTime dateTime, Account receivingAccount) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.sendingAccount = sendingAccount;
        this.receivingAccount = receivingAccount;
    }

    public Transaction(Account sendingAccount, ThirdPartyAccount thirdPartyAccount, Money amount, LocalDateTime dateTime) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.sendingAccount = sendingAccount;
        this.thirdPartyAccount = thirdPartyAccount;
    }

    public Transaction(ThirdPartyAccount thirdPartyAccount, Money amount, Account receivingAccount, LocalDateTime dateTime) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.receivingAccount = receivingAccount;
        this.thirdPartyAccount = thirdPartyAccount;
    }



    public Transaction(ThirdPartyAccount thirdPartyAccount, Money amount, LocalDateTime dateTime) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.thirdPartyAccount = thirdPartyAccount;
    }

    public Transaction() {
    }


    public Money getPenaltyFee() {
        return penaltyFee;
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

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public Account getSendingAccount() {
        return sendingAccount;
    }

    public void setSendingAccount(Account sendingAccount) {
        this.sendingAccount = sendingAccount;
    }

    public Account getReceivingAccount() {
        return receivingAccount;
    }

    public void setReceivingAccount(Account receivingAccount) {
        this.receivingAccount = receivingAccount;
    }

    public ThirdPartyAccount getThirdParty() {
        return thirdPartyAccount;
    }

    public void setThirdParty(ThirdPartyAccount thirdPartyAccount) {
        this.thirdPartyAccount = thirdPartyAccount;
    }
}
