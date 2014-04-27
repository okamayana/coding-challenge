package com.okamayana.portfolio_rebalance.test.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TestUtil {
	
	private static final String BASE_PATH_STRING = "test_inputs/";
	public static final File BASE_PATH = new File(BASE_PATH_STRING);
	
	public static final Collection<File[]> buildParameters(String prefix) {
		List<File> inputs = Arrays.asList(TestUtil.BASE_PATH.listFiles());
		List<File[]> parameters = new ArrayList<File[]>();

		for (File input : inputs) {
			if (input.getName().startsWith(prefix)) {
				File[] parameter = new File[] { input };
				parameters.add(parameter);
			}
		}

		return parameters;
	}

}
