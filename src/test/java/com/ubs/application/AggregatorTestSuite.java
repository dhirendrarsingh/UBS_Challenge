package com.ubs.application;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import com.ubs.currency.Currency;
import com.ubs.currency.aggregator.Aggregator;
import com.ubs.currency.aggregator.CreditRatingAverageAggregator;
import com.ubs.currency.config.ExchangeRatesHolder;
import com.ubs.currency.data.CreditData;
import com.ubs.currency.data.GroupingKey;
import com.ubs.utils.FileLoader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.ubs.currency.config.FileConfig.CREDIT_DATA_FILE;

public class AggregatorTestSuite extends TestCase {

    private ExchangeRatesHolder exchangeRatesHolder;

    @Before
    public void setup() throws IOException {

    }

    @Test
    public void testFileDataLoad() throws IOException {
        FileLoader<CreditData> dataFileLoader = new FileLoader<>(CREDIT_DATA_FILE.getFilePath());
        List<CreditData> creditDataList = dataFileLoader.loadData(CreditData.mapToCreditData);

        assertEquals(19, creditDataList.size());

    }

    @Test
    public void testCreditDataAggregator() throws IOException {
        FileLoader<CreditData> dataFileLoader = new FileLoader<>(CREDIT_DATA_FILE.getFilePath());
        List<CreditData> creditDataList = dataFileLoader.loadData(CreditData.mapToCreditData);

        Aggregator<GroupingKey, Double, CreditData> creditRatingAggregator = new CreditRatingAverageAggregator(Currency.EUR);

        Map<GroupingKey, Double> result = creditRatingAggregator.compute(creditDataList);

        assertFalse(result.isEmpty());

    }
}
