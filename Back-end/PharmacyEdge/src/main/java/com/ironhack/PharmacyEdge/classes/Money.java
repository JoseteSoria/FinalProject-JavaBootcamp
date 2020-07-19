package com.ironhack.PharmacyEdge.classes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class Money {
    private static final Currency EUR = Currency.getInstance("EUR");
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;
    //    private final Currency currency;
    private Currency currency;
    private BigDecimal amount;

    public Money() {
    }

    /**
     * Class constructor specifying amount, currency, and rounding
     **/
    public Money(BigDecimal amount, Currency currency, RoundingMode rounding) {
        this.currency = currency;
        setAmount(amount.setScale(currency.getDefaultFractionDigits(), rounding));
    }

    /**
     * Class constructor specifying amount, and currency. Uses default RoundingMode HALF_EVEN.
     **/
    public Money(BigDecimal amount, Currency currency) {
        this(amount, currency, DEFAULT_ROUNDING);
    }

    /**
     * Class constructor specifying amount. Uses default RoundingMode HALF_EVEN and default currency USD.
     **/
    public Money(BigDecimal amount) {
        this(amount, EUR, DEFAULT_ROUNDING);
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    private void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
