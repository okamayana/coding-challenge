package com.okamayana.portfolio_rebalance;

public class Investment {
	
	public static final int COLUMN_TICKER = 0;
	public static final int COLUMN_ALLOCATION_TARGET = 1;
	public static final int COLUMN_ALLOCATION_ACTUAL = 2;
	public static final int COLUMN_SHARES_OWNED = 3;
	public static final int COLUMN_SHARE_PRICE = 4;

	private String ticker;
	private double allocationTarget;
	private double allocationActual;
	private int sharesOwned;
	private double sharePrice;
	
	public Investment(String ticker, double allocationTarget,
			double allocationActual, int sharesOwned, double sharePrice) {
		this.ticker = ticker;
		this.allocationTarget = allocationTarget;
		this.allocationActual = allocationActual;
		this.sharePrice = sharePrice;
		this.sharesOwned = sharesOwned;
	}
	
	public String getTicker() {
		return ticker;
	}
	
	public double getAllocationTarget() {
		return allocationTarget;
	}
	
	public double getAllocationActual() {
		return allocationActual;
	}
	
	public double getSharePrice() {
		return sharePrice;
	}
	
	public int getSharesOwned() {
		return sharesOwned;
	}
}
