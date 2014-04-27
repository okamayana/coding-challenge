package com.okamayana.portfolio_rebalance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.okamayana.portfolio_rebalance.util.MathUtil;
import com.okamayana.portfolio_rebalance.util.PortfolioUtil;

public class PortfolioRebalance {

	public static final String FILENAME = "input.csv";
	public static final String INSTRUCTION_BUY = "Buy %d shares of %s";
	public static final String INSTRUCTION_SELL = "Sell %d shares of %s";

	public static List<String> getAdvice(Portfolio portfolio) {
		List<String> advice = new ArrayList<String>();
		double totalInvestment = portfolio.getTotalInvestment();

		List<Investment> investments = portfolio.getInvestments();
		for (Investment investment : investments) {
			advice.add(evaluate(investment, totalInvestment));
		}

		return advice;
	}
	
	public static Portfolio rebalance(Portfolio portfolio) {
		return rebalance(portfolio, getAdvice(portfolio));
	}

	public static Portfolio rebalance(Portfolio portfolio, List<String> advice) {
		List<Investment> oldInvestments = portfolio.getInvestments();
		List<Investment> newInvestments = new ArrayList<Investment>();

		for (int i = 0; i < advice.size(); i++) {
			String clause = advice.get(i);

			Investment newInvestment = null;
			Investment oldInvestment = oldInvestments.get(i);

			if (clause != null) {
				double allocationTarget = oldInvestment.getAllocationTarget();
				double allocationActual = oldInvestment.getAllocationActual();
				int sharesOwned = oldInvestment.getSharesOwned();
				double sharePrice = oldInvestment.getSharePrice();

				String[] words = clause.split(" ");
				String action = words[0];
				int sharesNeeded = Integer.parseInt(words[1]);
				String ticker = words[4];

				int newSharesOwned = 0;
				if (action.equalsIgnoreCase("buy")) {
					newSharesOwned = sharesOwned + sharesNeeded;
				} else {
					newSharesOwned = sharesOwned - sharesNeeded;
				}

				newInvestment = new Investment(ticker, allocationTarget,
						allocationActual, newSharesOwned, sharePrice);

			} else {
				newInvestment = oldInvestment;
			}

			newInvestments.add(newInvestment);
		}

		double newTotalInvestment = 0;
		for (Investment newInvestment : newInvestments) {
			int sharesOwned = newInvestment.getSharesOwned();
			double sharePrice = newInvestment.getSharePrice();
			newTotalInvestment += sharesOwned * sharePrice;
		}

		List<Investment> newNewInvestments = new ArrayList<Investment>();
		for (Investment newInvestment : newInvestments) {
			int sharesOwned = newInvestment.getSharesOwned();
			double sharePrice = newInvestment.getSharePrice();
			double newAllocationActual = sharesOwned * sharePrice
					/ newTotalInvestment * 100.0d;
			newAllocationActual = MathUtil.round(newAllocationActual, 2);

			int index = newInvestments.indexOf(newInvestment);
			newNewInvestments.add(index,
					newInvestment.setAllocationActual(newAllocationActual));
		}

		return new Portfolio(newNewInvestments, newTotalInvestment);
	}

	private static String evaluate(Investment investment, double totalInvestment) {
		int sharesDiff = calculateSharesDiff(investment, totalInvestment);

		String ticker = investment.getTicker();
		return generateClause(ticker, sharesDiff);
	}

	private static int calculateSharesDiff(Investment investment,
			double totalInvestment) {
		double allocationTarget = investment.getAllocationTarget();
		int sharesOwned = investment.getSharesOwned();
		double sharePrice = investment.getSharePrice();

		int sharesNeeded = (int) Math.round((allocationTarget / 100)
				* totalInvestment / sharePrice);
		int sharesDiff = sharesOwned - sharesNeeded;

		return sharesDiff;
	}

	private static String generateClause(String ticker, int sharesDiff) {
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
		
		System.out.println("unbalanced:");
		PortfolioUtil.print(portfolio);
		System.out.println();

		List<String> advice = getAdvice(portfolio);
		for (String clause : advice) {
			if (clause != null) {
				System.out.println(clause);
			}
		}

		Portfolio rebalancedPortfolio = rebalance(portfolio);
		PortfolioUtil.print(rebalancedPortfolio);
	}
}
