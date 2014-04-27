package com.okamayana.portfolio_rebalance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Portfolio {

	private static final double EPSILON = 0.00001;
	private static final String ERROR_ALLOCATIONS_DO_NOT_ADD = "Percentages do not add to 100%";
	private static final String ERROR_ACTUAL_CALCULATED_ALLOC = "Actual and calculated allocated percentages do not match for %s";

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

	public static Portfolio fromCsv(File csvFile) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(csvFile));
		List<Investment> investments = new ArrayList<Investment>();
		double totalPercentageTarget = 0;
		double totalPercentageActual = 0;
		double totalInvestment = 0;

		for (String row; (row = reader.readLine()) != null;) {
			String[] columns = row.split(",");
			String ticker = columns[Investment.COLUMN_TICKER];
			double allocationTarget = Double
					.parseDouble(columns[Investment.COLUMN_ALLOCATION_TARGET]);
			double allocationActual = Double
					.parseDouble(columns[Investment.COLUMN_ALLOCATION_ACTUAL]);
			int sharesOwned = Integer
					.parseInt(columns[Investment.COLUMN_SHARES_OWNED]);
			double sharePrice = Double
					.parseDouble(columns[Investment.COLUMN_SHARE_PRICE]);

			Investment investment = new Investment(ticker, allocationTarget,
					allocationActual, sharesOwned, sharePrice);
			investments.add(investment);

			totalInvestment += sharePrice * sharesOwned;
			totalPercentageTarget += allocationTarget;
			totalPercentageActual += allocationActual;
		}

		reader.close();

		validatePortfolio(investments, totalPercentageTarget,
				totalPercentageActual, totalInvestment);

		return new Portfolio(investments, totalInvestment);
	}

	private static void validatePortfolio(List<Investment> investments,
			double totalPercentageTarget, double totalPercentageActual,
			double totalInvestment) {
		if (totalPercentageActual != 100.0d || totalPercentageTarget != 100.0d) {
			throw new IllegalArgumentException(ERROR_ALLOCATIONS_DO_NOT_ADD);
		}

		for (Investment investment : investments) {
			double sharePrice = investment.getSharePrice();
			int shareOwned = investment.getSharesOwned();
			double allocationActual = investment.getAllocationActual();
			double allocationCalculated = sharePrice * shareOwned
					/ totalInvestment * 100;
			
			if (Math.abs(allocationActual - allocationCalculated - 1.0) <= EPSILON) {
				throw new IllegalArgumentException(
						String.format(ERROR_ACTUAL_CALCULATED_ALLOC, 
								investment.getTicker()));
			}
		}
	}
}
