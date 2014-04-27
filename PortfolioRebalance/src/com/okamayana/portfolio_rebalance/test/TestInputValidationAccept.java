package com.okamayana.portfolio_rebalance.test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.okamayana.portfolio_rebalance.Portfolio;
import com.okamayana.portfolio_rebalance.test.util.TestUtil;
import com.okamayana.portfolio_rebalance.util.PortfolioUtil;

public class TestInputValidationAccept {
	
	private static final String INPUT_PREFIX = "test_input_validation_accept_";
	
	private List<File> inputs;
	
	@Before
	public void setUp() {
		inputs = Arrays.asList(TestUtil.BASE_PATH.listFiles());
	}

	@Test
	public void validateInputs() throws IOException {
		for (File input : inputs) {
			String filename = input.getName();
			
			if (filename.startsWith(INPUT_PREFIX)) {
				Portfolio portfolio = Portfolio.fromCsv(input);
				
				System.out.println(filename);
				PortfolioUtil.printPortfolio(portfolio);
				System.out.println();
				
				PortfolioUtil.validate(portfolio);
			}
		}
	}
}
