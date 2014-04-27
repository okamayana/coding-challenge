package com.okamayana.portfolio_rebalance.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtil {
	
	private static final double ERROR_SENSITIVITY = 0.02;
	
	public static boolean approxEqual(double a, double b) {
		return (Math.abs(round(a, 2) - round(b, 2)) <= 0);
	}
	
	public static boolean isAcceptableActual(double actual, double target) {
		return (Math.abs(actual - target)/actual <= ERROR_SENSITIVITY);
	}
	
	public static double round(double value, int places) {
	    if (places < 0) { 
	    	throw new IllegalArgumentException();
	    }

	    BigDecimal decimal = new BigDecimal(value);
	    decimal = decimal.setScale(places, RoundingMode.HALF_UP);
	    return decimal.doubleValue();
	}
}
