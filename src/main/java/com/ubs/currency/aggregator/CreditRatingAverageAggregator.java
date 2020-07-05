package com.ubs.currency.aggregator;

import com.ubs.currency.Currency;
import com.ubs.currency.data.CreditData;
import com.ubs.currency.data.CreditRatingGroupingKey;
import com.ubs.currency.data.GroupingKey;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

public class CreditRatingAverageAggregator implements Aggregator<GroupingKey, Double, CreditData> {

	private Currency type;
	
	public CreditRatingAverageAggregator(Currency currency){
		type = currency;
	}
	
	//grouping functionality implemented using java functions.
	public static Function<CreditData, GroupingKey> groupingCriteria = (data) -> {
        String countryCriteria = data.getCountry();
        String creditRatingCriteria = data.getCreditRating();
        if (StringUtils.isEmpty(countryCriteria)) {
            countryCriteria = data.getCity();
        }

        return new CreditRatingGroupingKey(countryCriteria, creditRatingCriteria);
    };

    
    @Override
    public Map<GroupingKey, Double> compute(List<CreditData> creditDataList) {
        return creditDataList.stream().collect(Collectors.groupingBy(groupingCriteria,
                Collectors.averagingDouble(cd -> cd.getAmountIn(type))));

    }
}
