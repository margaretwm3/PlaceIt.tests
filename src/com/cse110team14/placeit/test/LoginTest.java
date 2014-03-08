package com.cse110team14.placeit.test;

//import junit.framework.TestCase;
import com.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import com.cse110team14.placeit.*;

/* Class name: LoginTest
 * Description: 
 *   This class is used to test the Login and Register UI 
 *   and basic functionality of the login */
/*   Login is in the MainActivity right now */
public class LoginTest extends ActivityInstrumentationTestCase2<LoginActivity> {
	private Solo solo;

	/* Create test fixture */
	public LoginTest(){
		//CHANGE THE CLASS NAME HERE
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
	
	/* Function name : testRegisterUI
	 * Description: To test the UI of the register form
	 */
	public void testRegisterUI(){
	    solo.clickOnButton("Register Now");
		solo.takeScreenshot();
		boolean registerUI = solo.searchText("Username")
							  && solo.searchText("Password")
							  && solo.searchText("repeat password");
		assertTrue("Register UI is not as expected",registerUI);
	}
	
	/* Function name: testLoginUI
	 * Description : This method is to test the UI of Login form
	 */
	public void testLoginUI(){
		solo.takeScreenshot();
		boolean loginUI = solo.searchText("Username")
					   && solo.searchText("Password")
		  			   && solo.searchText("Sign In")
		  			   && solo.searchText("Register Now");
		assertTrue("The login UI is not as expected",loginUI);
	}
	
	/* Function name: testLoginPage 
	 * Description: Login should be the first view of the application 
	 *   Scenario 1: 
	 *     Given a user has Internet Access
	 *     And opens the app
	 *     When entering a valid combination of username and password
	 *     Then the user is logged into their Place_It account
	 */
	public void testRegister(){
		solo.clickOnButton("Register Now");
		solo.enterText(0, "wms");
		solo.enterText(1, "wm3");
		solo.enterText(2, "wm3");
		solo.clickOnButton("Sign Up");
		//solo.waitForDialogToOpen();
	    //After registeration, should go to the sign up page
		solo.takeScreenshot();
		boolean success = solo.searchText("Sign In")
				       && solo.searchText("Register Now");
		assertTrue("After signup should go to login page",success);
		
		/* After the first time regsiter test, it should appear user has already registered */
		solo.waitForDialogToOpen();
		boolean errMsg = solo.searchText("Username has already registered!");
		assertTrue("Error message is wrong",errMsg);
	}
	
	/* Function name testSuccessfulLogin
	 * Description: 
	 *   Scenario 1 : 
	 *    Given a user has Internet access
     *    And opens the app
     *    When entering a valid combination of username and password
     *    Then the user is logged into their Place-It account
     */
	public void testSuccessfulLogin(){
		solo.enterText(0, "wms");
		solo.enterText(1,"wm3");
		solo.clickOnButton("Sign In");
	    solo.takeScreenshot();
		boolean err = solo.searchText("Sign In")
				   && solo.searchText("Register Now");
		assertFalse("The app should start after log in",err);
		
		boolean appStart = solo.searchText("Find")
				          && solo.searchText("Active")
		    			 && solo.searchText("Create");
		assertTrue("The app is not correctly start",appStart);
	}
	
	/* Function name: testInvalidPwd
	 * Description:
	 *   Scenario 1 :
	 *     Given the user has already registered
	 *     When the user inputs the wrong password for his account
	 *     Then error msg should appear and the user can not start the application
	 */
	public void testInvalidPwd(){
		solo.enterText(0, "wms");
		solo.enterText(1,"abcd1234");
		solo.clickOnButton("Sign In");
		solo.waitForDialogToOpen();
		solo.takeScreenshot();
		boolean errorMsg = solo.searchText("Error")
						 && solo.searchText("User name or Password is incorrect");
		solo.clickOnButton("Ok");
		boolean notStart = solo.searchText("Find")
						&& solo.searchText("Create")
						&& solo.searchText("Active");
		assertFalse("The application should not be starrt",notStart);
	}
	
	/* Function name: testDuplicateName
	 * Description: 
	 *   Scenario:
	 *     Given the username is registering an account
	 *     When the user input a username that has already been register
	 *     Then the account should not be created
	 */
	public void testDuplicateUserName(){
	  solo.clickOnButton("Register Now");
	  solo.enterText(0, "wms");
	  solo.enterText(1, "abcdefg");
	  solo.enterText(2,"abcdefg");
	  solo.clickOnButton("Sign Up");
	  solo.takeScreenshot();
	  solo.waitForDialogToOpen();
	  solo.takeScreenshot();
	  boolean errMsg = solo.searchText("Username has already registered!");
	  assertTrue("Error message is not showing",errMsg);
	 
	  boolean notStart = solo.searchText("Sign In")
			  		 &&  solo.searchText("Register Now");
	  assertFalse("Duplicate account register is not allowed",notStart);
	}
	
	/* Function name: testRegisterMissingAllInfo
	 * Description:
	 *   Scenario :
	 *     Given the user wants to register an account
	 *     When he does not fill in any information (username and password)
	 *     Then the user should not be able to register the account
	 */
	public void testRegisterMissingAllInfo(){
		  solo.clickOnButton("Register Now");
		  solo.clickOnButton("Sign Up");
		  solo.waitForDialogToOpen();
		  solo.takeScreenshot();
		  boolean errorMsg = solo.searchText("Error")
				  		&&   solo.searchText("User name and password can't be empty");
		  boolean error = solo.searchText("Sign In")
				       && solo.searchText("Register Now");
		  assertFalse("Missing username should not be able to sign up",error);
	}
	
	/* Function name : testRegisterMissingUsername
	 * Description: 
	 *   Scenario:
	 *     Given the user wants to register an account
	 *     When he leaves the password field empty
	 *     Then he should not be able to register an account
	 */
	public void testRegisterMissingUsername(){
	  solo.	clickOnButton("Register Now");
	  solo.enterText(0,"wmswmswms");
	  solo.clickOnButton("Sign Up");
	  solo.takeScreenshot();
	  boolean errorMsg = solo.searchText("Error")
		  		&&   solo.searchText("Password can't be empty");
	  assertTrue("Erro message for missing username is not as expected",errorMsg);
	 
	  boolean error = solo.searchText("Sign In")
			       && solo.searchText("Register Now");
	  assertFalse("Missing username should not be able to sign up",error);
	 }
	
	/* Function name : testRegisterMissingPwd
	 * Description: 
	 *   Scenario :
	 *     Given the user wants to register an account
	 *     When he leaves the username field empty
	 *     Then he should not be able to register an account
	 */
	public void testRegisterMissingPwd(){
		  solo.	clickOnButton("Register Now");
		  solo.enterText(1,"test1");
		  solo.enterText(2, "test1");
		  solo.clickOnButton("Sign Up");
		  solo.takeScreenshot();
		  boolean errorMsg = solo.searchText("Error")
			  		&&   solo.searchText("User name can't be empty");
		  assertTrue("Error message for missing password is not as expected",errorMsg);
		 
		  boolean error = solo.searchText("Sign In")
				       && solo.searchText("Register Now");
		  assertFalse("Missing username should not be able to sign up",error);
		 }
	
	/* Function name: testRegisterWrongRepeatPwd 
	 * Description: Scenario: 
	 *   Given the user wants to register an account
	 *   When the user inputs wrong password in the second time
	 *   Then the user should not be able to sign up
	 */
	public void testRegisterWrongRepeatPwd(){
		solo.clickOnButton("Register Now");
		solo.enterText(0, "test1");
		solo.enterText(1,"test123");
		solo.enterText(2,"wrongpwd");
		solo.clickOnButton("Sign Up");
		solo.takeScreenshot();
		boolean errorMsg = solo.searchText("Error")
			  		&&   solo.searchText("Repeat password is not consistent");
		
		 boolean noLogin = solo.searchText("Sign In");
		assertFalse("Wrong pwd should not let user register",noLogin);
	}
	
	/* Function name: testInvalidUsernameLogin
	 * Description: 
	 *   Scenario :
	 *     Given a user has Internet access
  	 *     And opens the app
     *     When the user enters an invalid username
     *     Then the user will receive an error message indicating an invalid username
     *     sAnd given an option to register a new Place-It account
	 */
	public void testInvalidUsernameLogin(){
		solo.enterText(0, "UsernameNotRegistered");
		//password is the correct password for username Margaretwm3
		solo.enterText(1,"1234abc!!!");
		solo.clickOnButton("Sign In");
		solo.waitForDialogToOpen();
		solo.takeScreenshot();
		boolean errMsg = solo.searchText("User name or Password is incorrect");
		assertTrue("Error message should appear",errMsg);
	}
	
	public void testRememberUser(){
		solo.enterText(0, "Margaretwm3");
		solo.enterText(1, "1234abc!!!");
		solo.clickOnButton("Sign In");
		
		solo.takeScreenshot();
		boolean openApp = solo.searchText("Find")
				        && solo.searchText("Active")
				        && solo.searchText("Create");
		
		assertTrue("The application is not start when inputing correct username and pwd",openApp);
		
		solo.sleep(1000);
		solo.finishOpenedActivities();
		this.launchActivity("com.cse110team14.placeit",LoginActivity.class,null);
		solo.sleep(1000);
		solo.takeScreenshot();
		boolean rememberUser = solo.searchText("Active")
				           && solo.searchText("Create")
				           && solo.searchText("Find");
		boolean failToRemember = solo.searchText("Sign In")
				             &&  solo.searchText("Register Now");
		assertTrue("The app correctly remember the user",rememberUser);
		assertFalse("The app does not remember the user",failToRemember);
	}
}
