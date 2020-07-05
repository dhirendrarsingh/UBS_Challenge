package com.ubs.currency;

import static com.ubs.currency.config.FileConfig.CREDIT_DATA_FILE;
import static com.ubs.currency.config.FileConfig.EXCHANGE_RATES_FILE;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ubs.currency.aggregator.CreditRatingAverageAggregator;
import com.ubs.currency.data.CreditData;
import com.ubs.currency.data.ExchangeRate;
import com.ubs.currency.data.GroupingKey;
import com.ubs.utils.FileLoader;

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
public class EuroCurrencyAggregator implements CurrencyAggregator {

	private FileLoader<CreditData>  fileLoader;
	
	public EuroCurrencyAggregator() {
		fileLoader = new FileLoader<>(CREDIT_DATA_FILE.getFilePath());
	}
	
	@Override
	public void process() {
		try {
			List<CreditData> creditData = fileLoader.loadData((line) -> {
	            String[] row = line.split(CREDIT_DATA_FILE.getDelimiter());
	            return new CreditData(row);
	
	        });
			
			Map<GroupingKey, Double> AvgMap = new CreditRatingAverageAggregator(Currency.EUR)
									.compute(creditData);
			
			System.out.print("********************");
			AvgMap.forEach((k,v)-> System.out.println(k.toString() + "  " + v));
			System.out.print("********************");
	
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	
	    }
		
	}

}
