package com.cse110team14.placeit.test;

import com.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

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
     *              Make sure the information is all provideded in the UI
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
      
      View view1 = solo.getView(Spinner.class,0);
  	  solo.clickOnView(view1);
  	  solo.scrollToTop();
  	  solo.clickOnView(solo.getView(TextView.class,3));
  	  solo.goBack();
  	  solo.clickOnButton("Create the PlaceIt");
      
      //select up to three categories in total
      //solo.pressSpinnerItem(0, 3);
      //assertTrue("aquarium should be selected",solo.isSpinnerTextSelected("aquarium"));
      //solo.clickOnButton("Create the PlaceIt");
     
      //look into the active list and the CPlaceIts should be there
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
       
       View view1 = solo.getView(Spinner.class,0);
	   solo.clickOnView(view1);
	   solo.scrollToTop();
	   solo.clickOnView(solo.getView(TextView.class,5));
	   solo.goBack();
	   solo.clickOnButton("Create the PlaceIt");
       
       //solo.pressSpinnerItem(0, 0);
       //solo.clickOnButton("Create the PlaceIt");
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
    	
    	View view1 = solo.getView(Spinner.class,0);
    	solo.clickOnView(view1);
    	solo.scrollToTop();
    	solo.clickOnView(solo.getView(TextView.class,6));
    	solo.goBack();
    	solo.clickOnButton("Create the PlaceIt");
    	
    	//solo.pressSpinnerItem(0, 6);
    	//solo.clickOnButton("Create the PlaceIt");
    	solo.waitForDialogToOpen();
    	solo.takeScreenshot();
        boolean errMsg = solo.searchText("No Description Entered")
        		     && solo.searchText("Please enter a description :)");
        assertTrue("Error message is missing",errMsg);
        solo.clickOnButton("Ok");
     }
    
    /* Function name: testNoCategorySelected
     * Description: 
     *   Scenario:
     *     Given the user 
     *     When he wants to create a categorical placeit
     *     And he select no category
     *     Then the placeit should not be created 
     *     And the error message should appear
     */
    public void testNoCategorySelected(){
    	solo.clickOnButton("Create");
    	solo.enterText(0,"nothing");
        solo.hideSoftKeyboard();
        solo.clickOnButton("Create Category PlaceIts");
        solo.enterText(0,"TestNoCategorySelected");
        solo.enterText(1,"Test no category selected");
        solo.clickOnButton("Create the PlaceIt");
        solo.waitForDialogToOpen();
        solo.takeScreenshot();
        boolean errMsg = solo.searchText("No category has been selected");
        assertTrue("Error message is not appeared",errMsg);
   }
    
    /* Function name: testMoreThanLimitedSelected
     * Description: 
     *   Scenario:
     *     Given the user 
     *     When he wants to create a categorical placeit
     *     And he select more than 3 categories
     *     Then the placeit should not be created 
     *     And the error message should appear
     */
    public void testMoreThanLimitedSelected(){
    	solo.clickOnButton("Create");
    	solo.enterText(0,"nothing");
        solo.hideSoftKeyboard();
        solo.clickOnButton("Create Category PlaceIts");
        solo.enterText(0,"TestMoreSelected");
        solo.enterText(1,"Test More categories selected");
        
        View view1 = solo.getView(Spinner.class,0);
    	solo.clickOnView(view1);
    	solo.scrollToTop();
    	solo.clickOnView(solo.getView(TextView.class,5));
    	//solo.goBack();
    	solo.clickOnView(solo.getView(TextView.class,0));
    	//solo.goBack();
    	solo.clickOnView(solo.getView(TextView.class,10));
    	//solo.goBack();
    	solo.clickOnView(solo.getView(TextView.class,11));
    	solo.goBack();
    	solo.clickOnButton("Create the PlaceIt");
        
        
        /*solo.pressSpinnerItem(0, 0);
        solo.pressSpinnerItem(0, 5);
        solo.pressSpinnerItem(0, 10);
        solo.pressSpinnerItem(0, 7);
        solo.clickOnButton("Create the PlaceIt");*/
        solo.waitForDialogToOpen();
        solo.takeScreenshot();
        boolean errMsg = solo.searchText("You can not select more than 3 categories");
        assertTrue("Error message is not appeared",errMsg);
   }
    
   
   /* Function name: testNotification
    * Description:
    *   Scenario:
    *     Given the user has already logged in
    *     When he create a CPlaceIt with one specific category
    *     Then when he approaches one of the places in that category
    *     And he will receive the notification including the address of the place 
    */
    public void testNotification(){
	   solo.clickOnButton("Create");
   	   solo.enterText(0,"nothing");
       solo.hideSoftKeyboard();
       solo.clickOnButton("Create Category PlaceIts");
       solo.enterText(0,"TestNotification");
       solo.enterText(1,"Test when one category matched, notification will appear");
       
       View view1 = solo.getView(Spinner.class,0);
   	   solo.clickOnView(view1);
       solo.scrollToTop();
   	   solo.clickOnView(solo.getView(TextView.class,5));
   	   solo.goBack();
   	   solo.clickOnButton("Create the PlaceIt");
   	
       solo.clickOnButton("Active");
       solo.takeScreenshot();
       boolean inActive = solo.searchText("Title: TestNotification");
       assertTrue("CPlaceIts should appear in the Active List",inActive);
       
       solo.goBack();
       solo.clickOnButton("Refresh");
       solo.sleep(1000);
       solo.clickOnButton("Active");
       solo.takeScreenshot();
       boolean notInActive = solo.searchText("Title: TestNotification");
       assertFalse("The CPlaceIts is still in Active List",notInActive);
       
       solo.goBack();
       solo.clickOnButton("Pulled");
       solo.takeScreenshot();
       boolean inPulledDown = solo.searchText("Title: TestNotification");
       assertTrue("CPlaceIts is not in the PulledDown",inPulledDown);
       //go back for the next test
       solo.goBack();
   }
 }
