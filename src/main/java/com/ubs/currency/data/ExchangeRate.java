package com.ubs.currency.data;

import com.ubs.currency.Currency;
import com.ubs.currency.config.Constants;

import org.apache.commons.lang.StringUtils;

/**
###########################################################################
##
# Copyright (c) 2020 UBS Private Ltd. All Rights Reserved
#
# Please do not share these tests or data
# Use pursuant to company directives
##
###########################################################################
autor : Dhirendra Singh , date :05-Jul-2020
**/

public class ExchangeRate {

    private String fromCurrency ;
    private String toCurrency ;
    private Double rate;

    private static final String USD = Currency.USD.toString();

    public ExchangeRate(String[] columns) {

        int counter = 0;
        this.fromCurrency = StringUtils.defaultIfEmpty(StringUtils.trimToEmpty(columns[counter++]), Constants.USD);
        this.toCurrency = StringUtils.defaultIfEmpty(StringUtils.trimToEmpty(columns[counter++]), Constants.USD);

        this.rate = Double.valueOf(StringUtils.defaultIfEmpty(StringUtils.trimToEmpty(columns[counter]), Constants.DEFAULT_RATE));
    }

    public ExchangeRate(){
        this.fromCurrency = Constants.USD;
        this.toCurrency = Constants.USD;
        this.rate = 1.0;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public Double getRate() {
        return rate;
    }
}
