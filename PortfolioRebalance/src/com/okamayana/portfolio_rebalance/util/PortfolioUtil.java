package com.okamayana.portfolio_rebalance.util;

import java.util.List;

import com.okamayana.portfolio_rebalance.Investment;
import com.okamayana.portfolio_rebalance.Portfolio;

public class PortfolioUtil {

	private static final String FORMAT = "%s,%f,%f,%d,%f";
	private static final String ERROR_ALLOCATIONS_DO_NOT_ADD = "Percentages do not add to 100%: %f, $f";
	private static final String ERROR_ACTUAL_CALCULATED_ALLOC = "Actual and calculated allocated percentages do not match for %s: %f, %f";

	public static void validate(Portfolio portfolio) {
		double totalInvestment = portfolio.getTotalInvestment();
		List<Investment> investments = portfolio.getInvestments();

		double totalAllocationActual = 0;
		double totalAllocationTarget = 0;

		for (Investment investment : investments) {
			double sharePrice = investment.getSharePrice();
			int shareOwned = investment.getSharesOwned();
			double allocationTarget = investment.getAllocationTarget();
			double allocationActual = investment.getAllocationActual();
			double allocationCalculated = sharePrice * shareOwned
					/ totalInvestment * 100;

			totalAllocationActual += allocationActual;
			totalAllocationTarget += allocationTarget;

			if (!MathUtil.approxEqual(allocationActual, allocationCalculated)) {
				throw new IllegalArgumentException(String.format(
						ERROR_ACTUAL_CALCULATED_ALLOC, investment.getTicker(),
						allocationActual, allocationCalculated));
			}
		}

		if (!MathUtil.approxEqual(totalAllocationActual, 100.0d)
				|| !MathUtil.approxEqual(totalAllocationTarget, 100.0d)) {
			throw new IllegalArgumentException(String.format(
					ERROR_ALLOCATIONS_DO_NOT_ADD, totalAllocationActual,
					totalAllocationTarget));
		}
	}

	public static void print(Portfolio portfolio) {
		List<Investment> investments = portfolio.getInvestments();
		for (Investment investment : investments) {
			String ticker = investment.getTicker();
			double allocationTarget = investment.getAllocationTarget();
			double allocationActual = investment.getAllocationActual();
			int sharesOwned = investment.getSharesOwned();
			double sharePrice = investment.getSharePrice();

			System.out.println(String.format(FORMAT, ticker, allocationTarget,
					allocationActual, sharesOwned, sharePrice));
		}
	}
}
