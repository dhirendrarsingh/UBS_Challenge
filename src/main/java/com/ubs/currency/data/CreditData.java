package com.ubs.currency.data;

import com.ubs.currency.Currency;
import com.ubs.currency.config.Constants;
import com.ubs.currency.config.ExchangeRatesHolder;

import org.apache.commons.lang.StringUtils;

import static com.ubs.currency.config.FileConfig.CREDIT_DATA_FILE;

import java.util.function.Function;

public class CreditData {
    private String companyCode;
    private String account;
    private String city;
    private String country;
    private String creditRating;
    private Currency currency;
    private Double amount;

    public CreditData(String[] columns) {
        int counter = 0;
        this.companyCode = StringUtils.trimToEmpty(columns[counter++]);
        this.account = StringUtils.trimToEmpty(columns[counter++]);
        this.city = StringUtils.trimToEmpty(columns[counter++]);
        this.country = StringUtils.trimToEmpty(columns[counter++]);
        this.creditRating = StringUtils.trimToEmpty(columns[counter++]);
        this.currency = Currency.valueOf(StringUtils.trimToEmpty(columns[counter++]));
        try {
            this.amount = Double.parseDouble(columns[counter]);
        } catch (NumberFormatException nfe) {
            this.amount = 0.0;

        }
    }


    public String getCompanyCode() {
        return companyCode;
    }

    public String getAccount() {
        return account;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public String getCurrency() {
        return currency.toString();
    }

    public Double getAmount() {
        return amount;
    }

    public Double getAmountIn(Currency tc ) {
        return ExchangeRatesHolder.INSTANCE.convert(this.currency, tc, this.amount);
    }

    public static Function<String, CreditData> mapToCreditData = (line) -> {
        String[] row = line.split(CREDIT_DATA_FILE.getDelimiter());// a CSV has comma separated lines
        return new CreditData(row);

    };
}
