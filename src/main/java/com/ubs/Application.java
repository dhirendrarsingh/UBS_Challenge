package com.ubs;
import java.net.URL;
import java.net.URLClassLoader;

import com.ubs.currency.EuroCurrencyAggregator;

/**
 * Application bootstrap entry-point to kick-start application
 * @author Dhirendra Singh
 *
 */
public class Application {
    
    public static void main(String[] args) {
    	
    	ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
        	System.out.println(url.getFile());
        }
        
    	new EuroCurrencyAggregator().process();
    }
}