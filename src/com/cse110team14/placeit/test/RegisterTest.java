package com.cse110team14.placeit.test;

import android.test.ActivityInstrumentationTestCase2;
import com.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.cse110team14.placeit.*;

public class RegisterTest extends ActivityInstrumentationTestCase2<RegisterActivity> {
  private Solo solo;
 
  /* Create test fixture */
  public RegisterTest(){
		super(RegisterActivity.class);
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
  
  public void testRegisterUI(){
	solo.takeScreenshot();
	solo.clickOnButton("Register Now");
	solo.takeScreenshot();
	boolean registerUI = solo.searchText("Username")
						&& solo.searchText("Password")
						&& solo.searchText("repeat password");
	assertTrue("Register UI is not as expected",registerUI);
	}

}
