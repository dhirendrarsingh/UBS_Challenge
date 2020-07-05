package com.ubs.currency.data;
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

public class CreditRatingGroupingKey implements GroupingKey {

    private String countryCriteria ;
    private String creditRatingCriteria;

    public CreditRatingGroupingKey(String countryCriteria, String creditRatingCriteria) {
        this.countryCriteria = countryCriteria;
        this.creditRatingCriteria = creditRatingCriteria;
    }

    public String getCountryCriteria() {
        return countryCriteria;
    }

    public String getCreditRatingCriteria() {
        return creditRatingCriteria;
    }

	@Override
	public String toString() {
		return "CreditRatingGroupingKey [countryCriteria=" + countryCriteria + ", creditRatingCriteria="
				+ creditRatingCriteria + "]";
	}
}
