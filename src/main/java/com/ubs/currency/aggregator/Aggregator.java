package com.ubs.currency.aggregator;

import java.util.List;
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

@FunctionalInterface
public interface Aggregator<K,V, T> {

    Map<K,V> compute(List<T> data);

}
