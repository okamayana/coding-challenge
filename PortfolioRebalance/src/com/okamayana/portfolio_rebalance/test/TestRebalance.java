package com.okamayana.portfolio_rebalance.test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.okamayana.portfolio_rebalance.Portfolio;
import com.okamayana.portfolio_rebalance.test.util.TestUtil;
import com.okamayana.portfolio_rebalance.util.PortfolioUtil;

@RunWith(Parameterized.class)
public class TestRebalance {

	private static final String INPUT_PREFIX = "test_rebalance_";

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
		System.out.println(input.getName());
		PortfolioUtil.print(portfolio);
		System.out.println();

		PortfolioUtil.validate(portfolio);
	}
	
	@Test
	public void rebalance() {
		
	}
}
