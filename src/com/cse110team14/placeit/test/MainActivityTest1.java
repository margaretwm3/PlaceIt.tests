package com.cse110team14.placeit.test;

import com.cse110team14.placeit.MainActivity;
import com.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.cse110team14.placeit.*;


public class MainActivityTest1 extends ActivityInstrumentationTestCase2<MainActivity> {
	private Solo solo;
	
	MainActivity activity;
	/*private Button activeButton;
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
	
	public void setUp() throws Exception{
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	@Override
	public void tearDown() throws Exception {
		//tearDown() is run after a test case has finished. 
		//finishOpenedActivities() will finish all the activities that have been opened during the test execution.
		solo.finishOpenedActivities();
	}
	
	public void testEnterEddreass(){
		//0 is the index of the EditText field
		solo.enterText(0,"UCSD");	
		solo.clickOnButton("Find");
		assertTrue("The addreass is not UCSD",solo.getEditText(0).getText().toString().equals("UCSD"));
	}
	
	public void testButtonThere(){
		solo.takeScreenshot();
		boolean buttonThere = solo.searchText("Find")
							&& solo.searchText("Retrack")
							&& solo.searchText("Create")
							&& solo.searchText("Active")
							&& solo.searchText("Pulled");
		assertTrue("Some button is missing there",buttonThere);
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
	
	public void testPulled(){
		solo.clickOnButton("Active");
		solo.clickOnText("Test1");
		//try to delete the Test1 marker from the active list
		solo.clickOnButton("Delete");
		//try to go to the main menu and check the pulled down list
		solo.goBack();
		solo.clickOnButton("Pulled");
		solo.takeScreenshot();
		boolean isThere = solo.searchText("Test1");
		assertTrue("Test1 should be in the pulled down list",isThere);
	}
	
	/* create a marker and then check if it in the active list
	 * check if the title,description,blue,date,color are correct
	 * 
	 * */
	
	
	
}//end of class
