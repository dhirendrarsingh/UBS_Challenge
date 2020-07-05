package com.ubs.currency.config;

import static com.ubs.currency.config.Constants.TAB;

public enum FileConfig {
    CREDIT_DATA_FILE("FILE.DAT.txt", TAB),
    EXCHANGE_RATES_FILE("CURRENCY.MAPPING.txt", TAB);

    private final String filePath;
    private final String delimiter;


    FileConfig(String filePath, String delimiter) {
        this.filePath = filePath;
        this.delimiter = delimiter;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getDelimiter() {
        return this.delimiter;
    }


}
