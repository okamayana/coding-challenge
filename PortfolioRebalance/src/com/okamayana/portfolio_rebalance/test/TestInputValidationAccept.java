package com.okamayana.portfolio_rebalance.test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.okamayana.portfolio_rebalance.Portfolio;
import com.okamayana.portfolio_rebalance.test.util.TestUtil;
import com.okamayana.portfolio_rebalance.util.PortfolioUtil;

@RunWith(Parameterized.class)
public class TestInputValidationAccept {

	private static final String INPUT_PREFIX = "test_input_valid_";

	private File input;

	public TestInputValidationAccept(File input) {
		this.input = input;
	}

	@Parameters
	public static Collection<File[]> getParameters() {
		return TestUtil.buildParameters(INPUT_PREFIX);
	}

	@Test
	public void validateInput() throws IOException {
		Portfolio portfolio = Portfolio.fromCsv(input);
		
		System.out.println(input.getName());
		PortfolioUtil.print(portfolio);
		System.out.println();
		
		PortfolioUtil.validate(portfolio);
	}
}
