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
     
    /* Function nameL testCategoricalUI
     * Description: This function is used to test the UI to create categorical placeit
     *              Make sure the information is all providede in the UI
     */
     public void testCategoricalUI(){
    	solo.clickOnButton("Create");
    	solo.enterText(0, "nothing");
    	solo.hideSoftKeyboard();
    	solo.takeScreenshot();
    	boolean ui = solo.searchText("Create Category PlaceIts");
    	assertTrue("The ui is not correct",ui);
    	solo.clickOnButton("Create Category PlaceIts");
    	solo.takeScreenshot();
    	ui = solo.searchText("Title:") 
    	  && solo.searchText("Description:")
          && solo.searchText("Select up to 3 categories");
    	assertTrue("The form to create CPlaceIt is not correct",ui);
     }
    
    /* Function name : testCreateCPlaceIts
     * Description: 
     *   Scenario: 
     *     Given a user has successfully logged into an acount
     *     And there is InterNet access
     *     When the user selects the Create button
     *     Then the user presented with the PlaceIt template
     *     And is able to select up to three categories
     */
    public void testCreateCPlaceIts(){
      solo.clickOnButton("Create");
      solo.enterText(0,"nothing");
      solo.hideSoftKeyboard();
      solo.clickOnButton("Create Category PlaceIts");
      solo.enterText(0,"CPlaceItsTest1");
      solo.enterText(1,"Test CPlaceIts");
      solo.hideSoftKeyboard();
      
      //select up to three categories in total
      solo.pressSpinnerItem(0, 3);
      //assertTrue("aquarium should be selected",solo.isSpinnerTextSelected("aquarium"));
      solo.clickOnButton("Create the PlaceIt");
     
      //look into the active list 
      solo.clickOnButton("Active");
      solo.takeScreenshot();
      boolean inActive = solo.searchText("CPlaceItsTest1");
      assertTrue("The placeit is not in the Active List",inActive);
    }
    
    /* Function name: testCreateWithoutTitle
     * Description: 
     *   Scenario:
     *     Given the user wants to create a Categorical PlaceIt
     *     When the user lefts out the title
     *     And click on the create button
     *     Then there should be error message indicate that the PlaceIt is not created
     */   
     public void testCreateWithoutTitle(){
       solo.clickOnButton("Create");
       solo.enterText(0, "nothing");
       solo.hideSoftKeyboard();
       solo.clickOnButton("Create Category PlaceIts");
       solo.enterText(1,"Without title");
       solo.pressSpinnerItem(0, 0);
       solo.clickOnButton("Create the PlaceIt");
       solo.waitForDialogToOpen();
       solo.takeScreenshot();
       boolean errMsg = solo.searchText("No Title Entered")
        		     && solo.searchText("Please enter a title :)");
       assertTrue("Error message is missing",errMsg);
       solo.clickOnButton("Ok");
       solo.clickOnButton("Active");
       solo.takeScreenshot();
       boolean notInTheActiveList = solo.searchText("Without title");
       assertFalse("The PlaceIt should not be created",notInTheActiveList);
     }
     
     /* Function name: testCreateWithoutDescription
      * Description: 
      *   Scenario:
      *     Given the user wants to create a Categorical PlaceIt
      *     When the user lefts out the description
      *     And click on the create button
      *     Then there should be error message indicate that the PlaceIt is not created
      */   
    public void testCreateWithoutDescription(){
    	solo.clickOnButton("Create");
    	solo.enterText(0, "nothing");
    	solo.hideSoftKeyboard();
    	solo.clickOnButton("Create Category PlaceIts");
    	solo.enterText(0,"Without Description");
    	solo.pressSpinnerItem(0, 6);
    	solo.clickOnButton("Create the PlaceIt");
    	solo.waitForDialogToOpen();
    	solo.takeScreenshot();
        boolean errMsg = solo.searchText("No Description Entered")
        		     && solo.searchText("Please enter a description :)");
        assertTrue("Error message is missing",errMsg);
        solo.clickOnButton("Ok");
     }
    
    /* Function name: testReviewPlaceIts
     * Description: 
     */
    public void testReviewPlaceIts(){
    	solo.clickOnButton("Create");
    	solo.enterText(0,"nothing");
    	solo.hideSoftKeyboard();
    	solo.clickOnButton("Create Category PlaceIts");
    	solo.enterText(0, "Review CPlaceIts");
    	solo.enterText(1,"Test review CPlaceIts");
    	solo.pressSpinnerItem(0, 0);
    	assertTrue("accounting should be selected",solo.isSpinnerTextSelected("accounting"));
    	
    	solo.pressSpinnerItem(0, 3);
    	solo.pressSpinnerItem(0, 2);
        solo.clickOnButton("Create the PlaceIt");
    	
    	solo.clickOnButton("Active");
    	solo.clickOnText("Review CPlaceIts");
    	solo.takeScreenshot();
    	boolean des  = solo.searchText("Description: Test review CPlaceIts");
    	//boolean cat = solo.searchText("Categories: accounting|airport|amusement_park");
    	
    	assertTrue("Description is not correct",des);
    	//assertTrue("Categories are not correct",cat);
    }
    
    
    public void testPlaceItsMovedToPulledDown()
    {
      solo.clickOnButton("Create");
      solo.enterText(0,"nothing");
      solo.hideSoftKeyboard();
      solo.clickOnButton("Create Category PlaceIts");
      solo.enterText(0,"TestPulledDown");
      solo.enterText(1,"Test pull down CPlaceIts");
      solo.pressSpinnerItem(0, 0);
      solo.clickOnButton("Create the PlaceIt");
      
      solo.clickOnButton("Active");
      solo.clickOnText("Title: TestPulledDown");
      solo.clickOnButton("Move To Pulled-Down");
      solo.takeScreenshot();
      boolean notInActive = solo.searchText("Title: TestPulledDown");
      assertFalse("The CPlaceIts should not be in the Active List",notInActive);
       
      solo.clickOnButton("Pulled");
      solo.takeScreenshot();
      boolean inPull = solo.searchText("Title: TestPulledDown");
      assertTrue("The CPlaceIts should be in the pulled down",inPull);
     }

}
