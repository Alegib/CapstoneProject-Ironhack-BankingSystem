package Ironhack.CapstoneProject.models.Transactions;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
@Embeddable
public class Money {

    public static final Currency USD = Currency.getInstance("USD");

    private BigDecimal amount;

    public Money(BigDecimal amount, Currency currency, RoundingMode round) {
        setAmount(amount.setScale(currency.getDefaultFractionDigits(), round));
    }

    public Money(BigDecimal amount, Currency currency) {
        setAmount(amount.setScale(currency.getDefaultFractionDigits(), RoundingMode.HALF_EVEN));
    }

    public Money(BigDecimal amount) {
        setAmount(amount.setScale(USD.getDefaultFractionDigits(), RoundingMode.HALF_EVEN));
    }
    public Money() {}

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
