package com.okamayana.portfolio_rebalance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.okamayana.portfolio_rebalance.util.PortfolioUtil;

public class PortfolioRebalance {

	public static final String FILENAME = "input.csv";
	public static final String INSTRUCTION_BUY = "Buy %d shares of %s";
	public static final String INSTRUCTION_SELL = "Sell %d shares of %s";

	public static List<String> rebalance(Portfolio portfolio) {
		List<String> advices = new ArrayList<String>();
		double totalInvestment = portfolio.getTotalInvestment();

		List<Investment> investments = portfolio.getInvestments();
		for (Investment investment : investments) {
			String advice = evaluate(investment, totalInvestment);
			if (advice != null) {
				advices.add(advice);
			}
		}

		return advices;
	}

	private static String evaluate(Investment investment, double totalInvestment) {
		double allocationTarget = investment.getAllocationTarget();
		int sharesOwned = investment.getSharesOwned();
		double sharePrice = investment.getSharePrice();

		int sharesDiff = calculateSharesDiff(totalInvestment, allocationTarget,
				sharesOwned, sharePrice);
		
		String ticker = investment.getTicker();
		return generateAdvice(ticker, sharesDiff);
	}

	private static int calculateSharesDiff(double totalInvestment,
			double allocationTarget, int sharesOwned, double sharePrice) {
		int sharesNeeded = (int) Math.round((allocationTarget / 100)
				* totalInvestment / sharePrice);
		int sharesDiff = sharesOwned - sharesNeeded;
		
		return sharesDiff;
	}

	private static String generateAdvice(String ticker, int sharesDiff) {
		String advice = null;
		if (sharesDiff < 0) {
			advice = String.format(INSTRUCTION_BUY, Math.abs(sharesDiff),
					ticker);
		} else if (sharesDiff > 0) {
			advice = String.format(INSTRUCTION_SELL, sharesDiff, ticker);
		}
		
		return advice;
	}

	public static void main(String[] args) {
		Portfolio portfolio = null;
		try {
			portfolio = Portfolio.fromCsv(new File(FILENAME));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PortfolioUtil.validate(portfolio);

		List<String> advices = rebalance(portfolio);
		for (String advice : advices) {
			System.out.println(advice);
		}
	}
}
