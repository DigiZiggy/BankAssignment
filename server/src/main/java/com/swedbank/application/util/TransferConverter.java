package com.swedbank.application.util;

import com.posadskiy.currencyconverter.CurrencyConverter;
import com.swedbank.application.model.Currency;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransferConverter {
    CurrencyConverter converter = new CurrencyConverter("60f355a2cd8c1d020200");


    public BigDecimal convertSourceAmountToTarget(Currency from, Currency to, BigDecimal amount) {
        BigDecimal conversionRate = conversionRateFromTo(from, to);
        return conversionRate.multiply(amount);
    }

    public BigDecimal conversionRateFromTo(Currency from, Currency to) {
        return BigDecimal.valueOf(converter.rate(from.toString(), to.toString()));
    }
}
