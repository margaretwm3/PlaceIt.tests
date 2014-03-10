package com.cse110team14.placeit.test;

import com.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.cse110team14.placeit.*;
import com.cse110team14.placeit.model.CPlaceIts;


public class CategoricalPlaceItTest extends ActivityInstrumentationTestCase2<MainActivity> {
	private Solo solo;

	/* Create test fixture */
	public CategoricalPlaceItTest(){
		//CHANGE THE CLASS NAME HERE
		super(MainActivity.class);
	}
	
    public void setUp() throws Exception{
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
     public void tearDown() throws Exception {
		//tearDown() is run after a test case has finished. 
		//finishOpenedActivities() will finish all the activities that have been opened during the test execution.
		solo.finishOpenedActivities();
	}
     
    public void testCategoricalUI(){
    	solo.clickOnButton("Create");
    	solo.hideSoftKeyboard();
    	solo.takeScreenshot();
    	boolean ui = solo.searchText("Create Category PlaceIts");
    	assertTrue("The ui is not correct",ui);
   }

}
