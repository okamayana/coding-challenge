package com.okamayana.portfolio_rebalance.test;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.okamayana.portfolio_rebalance.Investment;
import com.okamayana.portfolio_rebalance.Portfolio;
import com.okamayana.portfolio_rebalance.PortfolioRebalance;
import com.okamayana.portfolio_rebalance.test.util.TestUtil;
import com.okamayana.portfolio_rebalance.util.MathUtil;
import com.okamayana.portfolio_rebalance.util.PortfolioUtil;

@RunWith(Parameterized.class)
public class TestRebalance {

	private static final String INPUT_PREFIX = "test_input_valid_";

	private File input;
	private Portfolio portfolio;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	public TestRebalance(File input, Portfolio portfolio) {
		this.input = input;
		this.portfolio = portfolio;
	}

	@Parameters
	public static Collection<Object[]> getParameters() throws IOException {
		return TestUtil.buildPortfolioParameters(INPUT_PREFIX);
	}

	@Test
	public void validateInput() throws IOException {
		System.out.println(input.getName() + ", unbalanced:");
		PortfolioUtil.print(portfolio);
		System.out.println();

		PortfolioUtil.validate(portfolio);
	}

	@Test
	public void validateRebalancedOutput() {
		Portfolio balancedPortfolio = PortfolioRebalance.rebalance(portfolio);

		System.out.println(input.getName() + ", balanced:");
		PortfolioUtil.print(balancedPortfolio);
		System.out.println();

		PortfolioUtil.validate(balancedPortfolio);
	}

	@Test
	public void checkRebalancedOutputIsAcceptable() {
		Portfolio balancedPortfolio = PortfolioRebalance.rebalance(portfolio);
		
		List<Investment> investments = balancedPortfolio.getInvestments();
		for (Investment investment : investments) {
			String ticker = investment.getTicker();
			double allocationTarget = investment.getAllocationTarget();
			double allocationActual = investment.getAllocationActual();

			if (!MathUtil
					.isAcceptableActual(allocationActual, allocationTarget)) {
				fail(String
						.format("Actual for %s is not within acceptable range; target: %f, actual: %f",
								ticker, allocationTarget, allocationActual));
			}
		}
	}
}
