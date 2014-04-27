package com.okamayana.portfolio_rebalance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

	public static Portfolio fromCsv(File csvFile) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(csvFile));
		List<Investment> investments = new ArrayList<Investment>();
		double totalInvestment = 0;

		for (String row; (row = reader.readLine()) != null;) {
			if (!row.startsWith("##")) {
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

				Investment investment = new Investment(ticker,
						allocationTarget, allocationActual, sharesOwned,
						sharePrice);
				investments.add(investment);

				totalInvestment += sharePrice * sharesOwned;
			}
		}

		reader.close();

		return new Portfolio(investments, totalInvestment);
	}
}
