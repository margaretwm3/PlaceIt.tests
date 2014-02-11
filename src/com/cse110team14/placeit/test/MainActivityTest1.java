package com.cse110team14.placeit.test;

import com.cse110team14.placeit.MainActivity;
import com.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.cse110team14.placeit.*;

/* 
 * Class name: Tests for implementation of MainActivity
 * 
 */

public class MainActivityTest1 extends ActivityInstrumentationTestCase2<MainActivity> {
	private Solo solo;
	
	/*MainActivity activity;
	private Button activeButton;
	private Button createButton;
	private Button pulledButton;
	private Button findButton;
	private Button retrackButton;
	private EditText mAddreass;
	private EditText description;
	private EditText title;
	private EditText date;
	private EditText color; 
	activeButton = (Button)activity.findViewById(com.cse110team14.placeit.R.id.active);
	createButton = (Button)activity.findViewById(com.cse110team14.placeit.R.id.create);
	pulledButton = (Button)activity.findViewById(com.cse110team14.placeit.R.id.pulled);
	findButton = (Button)activity.findViewById(com.cse110team14.placeit.R.id.btn_show);
	retrackButton = (Button)activity.findViewById(com.cse110team14.placeit.R.id.retrack);
	mAddreass = (EditText)activity.findViewById(com.cse110team14.placeit.R.id.et_place);
	title = (EditText)activity.findViewById(com.cse110team14.placeit.R.id.ItemTitle);
	date = (EditText)activity.findViewById(com.cse110team14.placeit.R.id.dateToBeReminded);
	color = (EditText)activity.findViewById(com.cse110team14.placeit.R.id.color);
	description = (EditText)activity.findViewById(com.cse110team14.placeit.R.id.description);
	*/
 	
	/* Create test fixture */
	public MainActivityTest1(){
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
	 *                 without any error message
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
	  solo.takeScreenshot();
	  //the template should contain repeat option and text field to enter info.
	  boolean template = solo.searchButton("Weekly");
	  //repost should be weekly or multiple of week , no less than 4
	  boolean enter = solo.searchEditText("Title")
			  && solo.searchText("Description")
			  && solo.searchText("Date")
			  && solo.searchText("Location")
			  && solo.searchText("Color");
	  assertTrue("Places to enter information is missing",enter);
	  assertTrue("Repeat placeit buttons are missing",template);
	}
	
	/* Method name: testInvalidAddress
	 * Description: Scenario 2
	 *   Given a user has signal
	 *   And wants to set a PlaceIt using an address
	 *   When the user enters an invalid address
	 *   Then an error message should be presented
	 */
	public void testInvalidAddreass(){
	  solo.clickOnButton("Create");
	  solo.enterText(0, "Marker2");
	  solo.enterText(1,"Invalid Address");
	  solo.enterText(2, "-360,360");
	  solo.enterText(3,"1/11/11");
	  solo.enterText(4, "Red");
	  solo.clickOnButton("Create the PlaceIt");
	  //solo.takeScreenshot();
	  //boolean errMsg = solo.waitForText("(?i).*?invalid address");
	  solo.getViews();
	  assertTrue(solo.waitForView(0));
	  //assertTrue("Error message is not showing for invalid address",
			  //solo.waitForText("(?i).*?invalid"));
	}
	
	/* Method name: testClickOnMap
	 * Description: Scenario 3
	 *   Given the user has signal
	 *   When the user locates their desired location
	 *   Then the tapping on the area will retrieve the nearest valid address
	 *   And a template to create a PlaceIt should be presented 
	 */
	/*
	public void testClickOnMapCreation(){
		solo.clickLongOnScreen(1,1,8);
		solo.clickOnEditText(0);
		solo.enterText(0, "Marker3");
		solo.enterText(1, "Create from map");
		solo.enterText(2,"-180,90");
		solo.enterText(3,"1/12/16");
		solo.enterText(4,"Orange");
		solo.clickOnButton("Create the PlaceIt");
		solo.clickOnButton("Active");
		solo.scrollToBottom();
		solo.takeScreenshot();
		boolean createFromMap = solo.searchText("Marker3");
		assertTrue("Create marker from map failed",createFromMap);
	}*/
	
	
	/* Test create marker without signal Scenario 4
	 * TO BE WRITTEN ... */
	
	/* Method Name : testMultiplePlaceItSameLocation
	 * Description: Senario 6 Multiple placeit at same place
	 *   Given a user has signal
	 *   And wants to set PlaceIt at an identical location
	 *   When they enter the address
	 *   Then the user should be presented a PlaceIt template
	 *   And be albe to create the PlaceIt
	 *   When the user attempts to review the location
	 *   Then all set PlaceIts are displayed
	 */
	public void testMultiplePlaceItSameLocation(){
		solo.clickOnButton("Create");
		solo.enterText(0, "Firstone");
		solo.enterText(1, "First one");
		solo.enterText(2,"20,30");
		solo.enterText(3,"2/10/14");
		solo.enterText(4,"Red");
		solo.clickOnButton("Weekly");
		solo.clickOnButton("Create the PlaceIt");
		
		//Create the second marker at the same location
		solo.clickOnButton("Create");
		solo.enterText(0, "Secondone");
		solo.enterText(1, "Second one");
		solo.enterText(2,"20,30");
		solo.enterText(3,"2/09/14");
		solo.enterText(4,"Blue");
		solo.clickOnButton("Weekly");
		solo.clickOnButton("Create the PlaceIt");
		
		//Create the third marker at the same location
		solo.clickOnButton("Create");
		solo.enterText(0, "Thirdone");
		solo.enterText(1, "Third one");
		solo.enterText(2,"20,30");
		solo.enterText(3,"2/08/14");
		solo.enterText(4,"Blue");
		solo.clickOnButton("Weekly");
		solo.clickOnButton("Create the PlaceIt");
		//Check the active list
		solo.clickOnButton("Active");
		assertTrue(solo.searchText("Firstone"));
		assertTrue(solo.searchText("Secondone"));
		assertTrue(solo.searchText("Thirdone"));
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
		solo.enterText(0, "Marker4");
		solo.enterText(1, "User Story 3");
		solo.enterText(2,"-20,-70");
		solo.enterText(3,"2/08/14");
		solo.enterText(4,"Blue");
		solo.clickOnButton("Weekly");
		solo.clickOnButton("Create the PlaceIt");
		
		solo.clickOnButton("Active");
		solo.clickOnMenuItem("Title:Marker4");
		solo.takeScreenshot();
		boolean review = solo.searchText("Title")
				     &&  solo.searchText("Description")
				     &&  solo.searchText("Date to be reminded");
		assertTrue("Template for review is missing something",review);	
	}
	
	/* Scenario 2,3 : within 1/2 miles
	 * TO BE WRITTEN ...
	 */
	
	/* Method Name: testPulled()
	 * 
	 */
	public void testPulled(){
		solo.clickOnButton("Active");
		solo.clickOnText("Test1");
		//try to delete the Test1 marker from the active list
		solo.clickOnButton(1); //Moved to pulled down list
		//try to go to the main menu and check the pulled down list
		solo.goBack();
		solo.clickOnButton("Pulled");
		solo.takeScreenshot();
		boolean isThere = solo.searchText("Test1");
		assertTrue("Test1 should be in the pulled down list",isThere);
	}

	public void testCreateButton(){
      //click on the create button should popped out a new page
      solo.clickOnButton("Create");
	  solo.enterText(0, "Marker1");
	  solo.enterText(1,"Create Marker1");
	  solo.enterText(2, "-30.2,20.2");
	  solo.enterText(3,"1/11/12");
	  solo.enterText(4,"blue");
	  solo.clickOnButton("Weekly");
	  //solo.clickOnButton("Create the PlaceIt");
	  
	  assertTrue(solo.getEditText(0).getText().toString().equals("Marker1"));
	  assertTrue(solo.getEditText(1).getText().toString().equals("Create Marker1"));
	  assertTrue(solo.getEditText(2).getText().toString().equals("-30.2,20.2"));
	  assertTrue(solo.getEditText(3).getText().toString().equals("1/11/12"));
	  assertTrue(solo.getEditText(4).getText().toString().equals("blue"));
	  assertTrue(solo.isRadioButtonChecked("Weekly"));
	 }
	
	public void testActive(){
		solo.clickOnButton("Create");
		solo.enterText(0, "Test1");
		solo.enterText(1,"Create Test1");
		solo.enterText(2, "-30.2,20.2");
		solo.enterText(3,"1/11/12");
		solo.enterText(4,"blue");
		solo.clickOnButton("Weekly");
		solo.clickOnButton("Create the PlaceIt");
		
		solo.clickOnButton("Active");
		assertTrue(solo.searchText("Test1"));
   }
	
	
	
	/* create a marker and then check if it in the active list
	 * check if the title,description,blue,date,color are correct
	 */
	
	
	
}//end of class
