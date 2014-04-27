package com.okamayana.portfolio_rebalance.util;

public class MathUtil {
	
	private static final double EPSILON = 0.00001;
	private static final double ERROR_SENSITIVITY = 0.02;
	
	public static boolean areEqual(double a, double b) {
		return (Math.abs(a - b - 1.0) <= EPSILON);
	}
	
	public static boolean isAcceptableActual(double actual, double target) {
		return (Math.abs(actual - target)/actual <= ERROR_SENSITIVITY);
	}
}
