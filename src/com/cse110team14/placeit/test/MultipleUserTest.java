package com.cse110team14.placeit.test;

import android.test.ActivityInstrumentationTestCase2;
import com.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.cse110team14.placeit.*;

/* This class is used to test multiple user login in */
public class MultipleUserTest extends ActivityInstrumentationTestCase2<LoginActivity> {
  private Solo solo;
 
  /* Create test fixture */
  public MultipleUserTest(){
		super(LoginActivity.class);
  }
  
  @Override
  public void setUp() throws Exception{
    solo = new Solo(getInstrumentation(), getActivity());
  }
	
  @Override
  public void tearDown() throws Exception {
    //tearDown() is run after a test case has finished. 
	//finishOpenedActivities() will finish all the activities that have been opened during the test execution.
	solo.finishOpenedActivities();
  }
  
  /* Function name: testLoginAnotherAccount
   * Description: 
   *   Scenario:
   *     Given the user has already logge in
   *     When the user wants to change the account
   *     Then he can log out and log into another account
   */
  public void testLoginAnotherAccount(){
	//first needs to log out
	solo.clickOnButton("Log Out");
	solo.enterText(0, "aa");
	solo.enterText(1,"aa");
	solo.clickOnButton("Sign In");
	//after log in should be in MainActivity
	solo.assertCurrentActivity("Should be in MainActivity", MainActivity.class);
    solo.clickOnButton("Log Out");
    
    solo.enterText(0, "wms");
    solo.enterText(1,"wm3");
    solo.clickOnButton("Sign In");
    solo.assertCurrentActivity("Should be in MainActivity", MainActivity.class);
    
    solo.clickOnButton("Log Out");
    solo.enterText(0, "raymond");
    solo.enterText(1,"wmsispig");
    solo.clickOnButton("Sign In");
    solo.assertCurrentActivity("Should be in MainActivity", MainActivity.class);
 }

}
