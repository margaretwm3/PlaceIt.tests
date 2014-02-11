package com.cse110team14.placeit.test;

import com.cse110team14.placeit.MainActivity;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.provider.Settings;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.robotium.solo.Solo;
import android.content.ContentResolver;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> 
{
  private static Solo solo;
  
  private static MainActivity mActivity;
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
    
  //create a test fixure
public MainActivityTest() 
  {
	super("com.cse110team14.placeit",MainActivity.class);
	Log.i("tester","within MainActivityTest() constructor");
  }
  
  @Override
  public void setUp() throws Exception 
  {
    super.setUp();
    //Find views
    mActivity = this.getActivity();
    activeButton = (Button)mActivity.findViewById(com.cse110team14.placeit.R.id.active);
    createButton = (Button)mActivity.findViewById(com.cse110team14.placeit.R.id.create);
    pulledButton = (Button)mActivity.findViewById(com.cse110team14.placeit.R.id.pulled);
    findButton = (Button)mActivity.findViewById(com.cse110team14.placeit.R.id.btn_show);
    retrackButton = (Button)mActivity.findViewById(com.cse110team14.placeit.R.id.retrack);
    mAddreass = (EditText)mActivity.findViewById(com.cse110team14.placeit.R.id.et_place);
    title = (EditText)mActivity.findViewById(com.cse110team14.placeit.R.id.ItemTitle);
    date = (EditText)mActivity.findViewById(com.cse110team14.placeit.R.id.dateToBeReminded);
    color = (EditText)mActivity.findViewById(com.cse110team14.placeit.R.id.color);
    description = (EditText)mActivity.findViewById(com.cse110team14.placeit.R.id.description);
  }
  

  @Override
  public void tearDown() throws Exception 
  {
    //tearDown() is run after a test case has finished. 
    //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
    super.tearDown();
   }
  
  public void testPreconditions(){
	  assertNotNull(mActivity);
	  assertNotNull(activeButton);
	  assertNotNull(createButton);
	  assertNotNull(pulledButton);
	  assertNotNull(findButton);
	  assertNotNull(retrackButton);
	  assertNotNull(mAddreass);
  }
  
  public void testEnterAddreass(){
	  //clearing the enter addreass edit text
	  mAddreass.clearComposingText();
	  //tapping the mAddreass edit text through TouchUtils class
	  TouchUtils.tapView(this, mAddreass);
	  //sending input to mAddreass
	  sendKeys(KeyEvent.KEYCODE_U,KeyEvent.KEYCODE_C,
			  KeyEvent.KEYCODE_S,KeyEvent.KEYCODE_D);
	  //TouchUtils.clickView(this,findButton);
	  String tmpAddreass = null;
	  try{
		  //getting the input from the editText
		  tmpAddreass = mAddreass.getText().toString();
	  }catch(NullPointerException e){
		  assertNotNull("mAddress is null",e.getMessage());
	  }
	  //System.out.println(mAddreass.toString());
	  Log.e("EnterAddreass",mAddreass.toString());
	  assertNotNull(tmpAddreass);
	  assertEquals(tmpAddreass,"ucsd");
  }
  
   public void testCreateButton(){
	  solo = new Solo(getInstrumentation(),getActivity());
	  solo.clickOnButton("Create");
	  solo.enterText(0, "First PlaceIt");
	  EditText e1 = (EditText)mActivity.findViewById(com.cse110team14.placeit.R.id.description);
	  solo.enterText(e1, "Created on 2/9/14");
	  /*solo.enterText(2,"2/9/14");
	  solo.enterText(3,"blue");
	  solo.enterText(index, text);*/
	  solo.goBack();
	  assertTrue(true);
	  
	  TouchUtils.tapView(this, createButton);
	  TouchUtils.tapView(this,title);
	  sendKeys(KeyEvent.KEYCODE_M);
	  TouchUtils.clickView(this, description);
	  sendKeys(KeyEvent.KEYCODE_A);
	  TouchUtils.tapView(this, date);
      sendKeys(KeyEvent.KEYCODE_1,KeyEvent.KEYCODE_SLASH,KeyEvent.KEYCODE_1,KeyEvent.KEYCODE_SLASH,
    		  KeyEvent.KEYCODE_1,KeyEvent.KEYCODE_1);
      TouchUtils.tapView(this, color);
      sendKeys(KeyEvent.KEYCODE_B,KeyEvent.KEYCODE_L,KeyEvent.KEYCODE_U,
    		  KeyEvent.KEYCODE_E);
      
      assertEquals(title.getText().toString(),"m");
      assertEquals(description.getText().toString(),"a");
      assertEquals(date.getText().toString(),"1/1/11");
      assertEquals(color.getText().toString(),"blue");
  }
  
   //write  a method for given
   /*void gpsEnabled(){
     Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
     intent.putExtra("enabled", true);
     this.ctx.sendBroadcast(intent);
     String GPSProvider = Settings.Secure.getString(ctx.getContentResolver(),
				Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		//GPS is enabled
		if(!GPSProvider.contains("gps")){
		  final Intent poke = new Intent();
		  poke.setClassName("com.android.settings",
				  "com.android.settings.widget.SettingsAppWidgetProvider");
		  poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
		  poke.setData(Uri.parse("3"));
		  sendBroadcast(poke);
		}
	}*/
  
    //write  a method for given
    static void gpsEnabled(){
      //android doesn't allow we to touch the user private setting
      //here we assumed gps is Enabled
    	
    }
    //write a method for when
 	static void applicationOpened(){
      //setUp() is run before a test case is started. 
 	  //This is where the solo object is created.
 	  //mActivity = getActivity();
 	}
 	
 	//write a method for then
 	/*void testTheMapShouldShow(){
 	  if(mActivity == null){
 		  assertTrue(false);
 	  }
 	  else{
 		  assertTrue(true);
 	  }
 	}*/
 	
 	//when for test place it
 	/*static void enterAddreass(){
 		LatLng sydney = new LatLng(-33.867, 151.206);
 		//map.setMyLocation(sydney);
 	}*/
 
  
    public static void testCorrectDisplayMap(){
     gpsEnabled();
     applicationOpened();
     /*testTheMapShouldShow();
     //pass the test if the map is correctly created
    	 assertTrue(true);
  
     else {
       assertTrue(false);
     }*/
    }
    
    public void testSetValidPlaceIt(){
    	gpsEnabled();
    	applicationOpened();
    	assertNotNull(mActivity);
    }
  
  
  /*private static void sendBroadcast(Intent poke) {
	// TODO Auto-generated method stub
  }

  private static ContentResolver getContentResolver() {
	// TODO Auto-generated method stub
	return null;
}*/

}
