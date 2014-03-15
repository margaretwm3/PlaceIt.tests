package com.cse110team14.placeit.test;

import java.util.Calendar;
import com.cse110team14.placeit.MainActivity;
import com.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.cse110team14.placeit.*;

/* 
 * Class name: Tests for implementation of MainActivity
 * Description : Modify the test to meet the requirement of the MS2
 * Date : 3/11/14
 * 
 */

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
	private Solo solo;
 	
	/* Create test fixture */
	public MainActivityTest(){
		super(MainActivity.class);
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
	
	/* Method name: testShowMap
	 * Description: Scenario test for User Story 1
	 *   Scenario 1: Given the GPS is enabled,
	 *               When the application is opened
	 *               Then a map is displayed on the screen 
	 *               without any error message
	 */
	public void testShowMap(){
		// Assumed the Given and When functions correctly
		solo.takeScreenshot();
		/* When the app is correctly opened, the map and the button
		 * should show up on the map */
		boolean buttonThere = solo.searchText("Find")
							&& solo.searchText("Retrack")
							&& solo.searchText("Create")
							&& solo.searchText("Active")
							&& solo.searchText("Pulled");
	    assertTrue("Some button is missing there",buttonThere);
   }
	
	/* Test methods for User Story 2 Place Reminders */
	
	/* Method name: testPlaceItTemplate
	 * Description: Scenario 1
	 *   Given a user has signal
	 *   And wants to set a PlacIt using an address
	 *   When the user enters an address
	 *   Then a template to create a Place-It should presented
	 */
	public void testPlaceItTemplate(){
	  //Assume user's device has signal
	  //When user enters a valid address
	  solo.enterText(0,"UCSD");
	  solo.clickOnButton("Find");  
	  assertTrue("The addreass is not UCSD",solo.getEditText(0).getText().toString().equals("UCSD"));
	  //When the user clicks the create button
	  solo.clickOnButton("Create");
	  solo.hideSoftKeyboard();
	  solo.takeScreenshot();
	  
	  //the template should contain repeat option and text field to enter info.
	  boolean template = solo.searchButton("1 Week");
	  boolean twoWeek = solo.searchButton("2 Weeks");
	  boolean threeWeeks = solo.searchButton("3 Weeks");//it should be three weeks
	  boolean monthly = solo.searchButton("Month");
	  boolean noRepeat = solo.searchButton("None");
	  
	  boolean enter = solo.searchEditText("Title:")
			  	   && solo.searchText("Description:")
			  	   && solo.searchText("Location:")
			  	   && solo.searchText("Color:");
	  assertTrue("Places to enter information is missing",enter);
	  assertTrue("Repeat placeit buttons are missing",template);
	  assertTrue("Two weeks button is not there",twoWeek);
	  assertTrue("Three weeks button is not there",threeWeeks);
	  assertTrue("Monthly button is not there",monthly);
	  
	}
	
	/* Method Name : testMultiplePlaceItSameLocation
	 * Description: Scenario 6 Multiple PlaceIt at same place
	 *   Given a user has signal
	 *   And wants to set PlaceIt at an identical location
	 *   When they enter the address
	 *   Then the user should be presented a PlaceIt template
	 *   And be able to create the PlaceIt
	 *   When the user attempts to review the location
	 *   Then all set PlaceIts are displayed
	 */
	public void testMultiplePlaceItSameLocation(){
		solo.clickOnButton("Create");
		solo.enterText(0, "Firstone");//title
		solo.enterText(1, "First one");//description
		solo.enterText(2,"20,30");//location
		solo.enterText(3,"Red");//color
		solo.clickOnButton("None");//repost period
		solo.clickOnButton("Create the PlaceIt");
		
		//Create the second marker at the same location
		solo.clickOnButton("Create");
		solo.enterText(0, "Secondone");
		solo.enterText(1, "Second one");
		solo.enterText(2,"20,30");
		solo.enterText(3,"Blue");
		solo.clickOnButton("None");
		solo.clickOnButton("Create the PlaceIt");
		
		//Create the third marker at the same location
		solo.clickOnButton("Create");
		solo.enterText(0, "Thirdone");
		solo.enterText(1, "Third one");
		solo.enterText(2,"20,30");
        solo.enterText(3,"Blue");
		solo.clickOnButton("None");
		solo.clickOnButton("Create the PlaceIt");
		//Check the active list
		solo.clickOnButton("Active");
		assertTrue("First marker is not created",solo.searchText("Firstone"));
		assertTrue("Second marker is not created",solo.searchText("Secondone"));
		assertTrue("Third marker is not created",solo.searchText("Thirdone"));
	}
	
	/* Tests for User Story 3 */
	
	/* Method name: testReviewPlaceIt
	 * Description: Scenario 1 
	 *   Given a PlaceIt has been created
	 *   And has not been triggered
	 *   When the posted list is selected
	 *   Then a PlaceIt is available for review
	 */
	public void testReivewPlaceIt(){
		solo.clickOnButton("Create");
		solo.enterText(0, "Review place it");
		solo.enterText(1, "Test marker for review PlaceIt");
		solo.enterText(2,"-20,-70");
		solo.enterText(3,"Blue");
		solo.clickOnButton("None");
		solo.hideSoftKeyboard();
		solo.clickOnButton("Create the PlaceIt");
		solo.clickOnButton("Active");
		solo.scrollToBottom();
		solo.clickOnText("Title: Review place it");
		solo.takeScreenshot();
		boolean review = solo.searchText("Title: Review place it")
				     &&  solo.searchText("Description: Test marker for review PlaceIt")
				     &&  solo.searchText("Date to be Reminded:")
				 	 &&  solo.searchText("Post Date and time:");
		solo.clickOnButton("OK");
		assertTrue("Template for review is missing something",review);	
	}
	
	
	
	
    /* Test for User Story 4 */
	
    /* Scenarios 2,3 to be written ...
	 * maybe using Mock Object
	 */

	/* Method Name: testCreateButton()
	 * Description: Given the user click on the create button
	 * 				Then the template is popped up for user to fill in the PlaceIt info
	 * 				And the user clicks on the Cancel button
	 * 				Then the PlaceIt is not created
	 * 				And the PlaceIt is not in the Active List and PulledDown List
	 */
	public void testCreateButton(){
      //click on the create button should popped out a new page
      solo.clickOnButton("Create");
	  solo.enterText(0, "Marker1");
	  solo.enterText(1,"Create Marker1");
	  solo.enterText(2, "-30.2,20.2");
	  solo.enterText(3,"blue");
	  solo.clickOnButton("None");
	  
	  assertTrue(solo.getEditText(0).getText().toString().equals("Marker1"));
	  assertTrue(solo.getEditText(1).getText().toString().equals("Create Marker1"));
	  assertTrue(solo.getEditText(2).getText().toString().equals("-30.2,20.2"));
	  assertTrue("No date to be entered",solo.searchText("March 2014"));
	  assertTrue(solo.getEditText(3).getText().toString().equals("blue"));
	  assertTrue(solo.isRadioButtonChecked("None"));
	  
	  //test the cancel button in the create template
	  solo.clickOnButton("Cancel");
	  solo.clickOnButton("Active");
	  solo.takeScreenshot();
	  assertFalse("Marker1 should not be in the Active List",solo.searchText("Marker1"));
	  
	  solo.goBackToActivity("MainActivity");
	  solo.clickOnButton("Pulled");
	  assertFalse("Marker1 should not be in the Pulled List",solo.searchText("Marker1"));
	  
	  solo.goBackToActivity("MainActivity");
	  solo.clickOnButton("Create");
	  solo.enterText(0, "Create Button");
	  solo.enterText(1,"Test for create button");
	  solo.enterText(2, "-30.2,20.2");
	  solo.enterText(3,"blue");
	  solo.clickOnButton("None");
	  solo.clickOnButton("Create the PlaceIt");
	  solo.clickOnButton("Active");
	  boolean createPlaceIt = solo.searchText("Title: Create Button");
	  assertTrue(createPlaceIt);	  
	  }
	
	/* Method Name: testActiveList
	 * Description: 
	 *   Given the user create the PlaceIt
	 *   Then the user goes to the Active List
	 *   Then the user should see the PlaceIt showing in the Active List
	 *   And the user click on the PlaceIt to review it
	 *   When the user clicks the OK button after reviewing it
	 *   Then app goes back to Active List page and the PlaceIt is still in the Active List
	 *   And then the user clicks on the Discard Button
	 *   Then the PlaceIt should not showing in both Active List and PulledDown List
	 */
	public void testActiveList(){
		solo.clickOnButton("Create");
		solo.enterText(0, "Test1");
		solo.enterText(1,"Create Test1");
		solo.enterText(2, "-30.2,20.2");
		solo.enterText(3,"Blue");
		solo.clickOnButton("None");
		solo.clickOnButton("Create the PlaceIt");
		
		solo.clickOnButton("Active");
		assertTrue(solo.searchText("Test1"));
	
		solo.clickOnText("Title: Test1");
		solo.clickOnButton("OK");
		solo.takeScreenshot();
		boolean test1 = solo.searchText("Title: Test1");
		assertTrue(test1);
		
		solo.clickOnText("Title: Test1");
		solo.clickOnButton("Discard");
		solo.takeScreenshot();
		test1 = solo.searchText("Title: Test1");
		assertFalse(test1);
		solo.goBack();
		solo.clickOnButton("Pulled");
		test1 = solo.searchText("Title: Test1");
		assertFalse(test1);
   }
}//end of class
