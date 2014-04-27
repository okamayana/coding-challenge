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
public class TestInputValidationReject {

	private static final String INPUT_PREFIX = "test_input_invalid_";

	private File input;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	public TestInputValidationReject(File input) {
		this.input = input;
	}

	@Parameters
	public static Collection<File[]> getParameters() {
		return TestUtil.buildParameters(INPUT_PREFIX);
	}

	@Test
	public void invalidateInput() throws IOException {
		Portfolio portfolio = Portfolio.fromCsv(input);

		System.out.println(input.getName());
		PortfolioUtil.print(portfolio);
		System.out.println();
		
		thrown.expect(IllegalArgumentException.class);
		PortfolioUtil.validate(portfolio);
	}
}
