package com.okamayana.portfolio_rebalance.util;

import java.util.List;

import com.okamayana.portfolio_rebalance.Investment;
import com.okamayana.portfolio_rebalance.Portfolio;

public class PortfolioUtil {

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

			if (!MathUtil.areEqual(allocationActual, allocationCalculated)) {
				throw new IllegalArgumentException(String.format(
						ERROR_ACTUAL_CALCULATED_ALLOC, investment.getTicker(),
						allocationActual, allocationCalculated));
			}
		}

		if (!MathUtil.areEqual(totalAllocationActual, 100.0d)
				|| !MathUtil.areEqual(totalAllocationTarget, 100.0d)) {
			throw new IllegalArgumentException(String.format(
					ERROR_ALLOCATIONS_DO_NOT_ADD, totalAllocationActual,
					totalAllocationTarget));
		}
	}
}
