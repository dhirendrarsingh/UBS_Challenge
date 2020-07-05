package com.ubs.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
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

 * File loader utility.  
 * @author Dhirendra Singh
 *
 * @param <T>
 */
public class FileLoader<T> {

	
	private String inputFile = null;

	public FileLoader(String inputFile) {
		this.inputFile = inputFile;
	}
	

	public List<T> loadData(final Function<String, T> transform) throws  IOException {
		InputStream inputDataFileStream = FileLoader.class.getClassLoader().getResourceAsStream(inputFile);

		try(BufferedReader br = new BufferedReader(new InputStreamReader(inputDataFileStream))){
			// skip the header of the file
			return br.lines().skip(1).map(transform).collect(Collectors.toList());

		}

	}

}
