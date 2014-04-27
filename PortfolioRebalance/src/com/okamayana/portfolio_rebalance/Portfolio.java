package com.okamayana.portfolio_rebalance;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {

	private List<Investment> investments = new ArrayList<Investment>();
	private double totalInvestment;

	public Portfolio(List<Investment> investments, double totalInvestment) {
		this.investments.addAll(investments);
		this.totalInvestment = totalInvestment;
	}

	public List<Investment> getInvestments() {
		return investments;
	}

	public double getTotalInvestment() {
		return totalInvestment;
	}

	public int getInvestmentsCount() {
		return investments.size();
	}

	public void appendInvestment(Investment investment) {
		investments.add(investment);
	}
}
