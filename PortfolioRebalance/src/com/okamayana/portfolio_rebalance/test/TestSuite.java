package com.okamayana.portfolio_rebalance.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestInputValidationAccept.class,
		TestInputValidationReject.class, TestRebalance.class })
public class TestSuite {

}
