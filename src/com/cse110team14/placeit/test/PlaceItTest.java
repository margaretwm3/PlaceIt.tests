package com.cse110team14.placeit.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class PlaceItTest {

	public static Test suite() {
		TestSuite suite = new TestSuite(PlaceItTest.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(LoginTest.class);
		//suite.addTestSuite(MainActivityTest.class);
		//suite.addTestSuite(MultipleUserTest.class);
		suite.addTestSuite(CategoricalPlaceItTest.class);
		//$JUnit-END$
		return suite;
	}

}
