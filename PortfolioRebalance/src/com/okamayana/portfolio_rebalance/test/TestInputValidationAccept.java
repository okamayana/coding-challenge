package com.okamayana.portfolio_rebalance.test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.okamayana.portfolio_rebalance.test.util.TestUtil;

public class TestInputValidationAccept {
	
	private static final String INPUT_PREFIX = "test_input_validation_accept_";
	
	private List<File> inputs;
	
	@Before
	public void setUp() {
		inputs = Arrays.asList(TestUtil.BASE_PATH.listFiles());
	}

	@Test
	public void validateInputs() {
		for (File input : inputs) {
			String filename = input.getName();
			
			if (filename.startsWith(INPUT_PREFIX)) {
				
			}
		}
	}
}
