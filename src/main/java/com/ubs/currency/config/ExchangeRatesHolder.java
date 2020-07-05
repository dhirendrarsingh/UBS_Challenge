package com.ubs.currency.config;

import com.ubs.currency.Currency;
import com.ubs.currency.data.ExchangeRate;
import com.ubs.utils.FileLoader;

import static com.ubs.currency.config.FileConfig.EXCHANGE_RATES_FILE;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

public enum ExchangeRatesHolder {
    INSTANCE(EXCHANGE_RATES_FILE);

    private Map<Currency, ExchangeRate> exchangeRatesMap = new HashMap<>();

    ExchangeRatesHolder(FileConfig exchangeRatesFileConfig) {
        FileLoader<ExchangeRate> exchangeRatesLoader = new FileLoader<>(EXCHANGE_RATES_FILE.getFilePath());
        try {
            exchangeRatesLoader.loadData((line) -> {
                String[] row = line.split(EXCHANGE_RATES_FILE.getDelimiter());// a CSV has comma separated lines
                return new ExchangeRate(row);

            }).forEach(exchangeRate -> exchangeRatesMap.put(Currency.valueOf(exchangeRate.getFromCurrency()), exchangeRate));

        } catch (IOException ioe) {
            ioe.printStackTrace();

        }

    }

    public Double convert(Currency fc, Currency tc, Double amount) {
        ExchangeRate toUSDRate = this.exchangeRatesMap.computeIfAbsent(fc, (from)->new ExchangeRate());
        Double amountInUSD = amount * toUSDRate.getRate();
        ExchangeRate toCurrencyRate = this.exchangeRatesMap.computeIfAbsent(tc, (to)->new ExchangeRate());
        return amountInUSD / toCurrencyRate.getRate();

    }

}
