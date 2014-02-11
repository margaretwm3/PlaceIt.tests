package com.cse110team14.placeit.test;

import com.cse110team14.placeit.MainActivity;
import com.robotium.solo.Solo;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.test.ActivityInstrumentationTestCase2;

/*public class ActiveButtonTests extends
		ActivityInstrumentationTestCase2<MainActivity> {
	
	private Solo solo;
	
	//write  a method for given
	void gpsEnabled(){
		String GPSProvider = Settings.Secure.getString(getContentResolver(),
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
	}
	
	void turnGPSOff(){
		String provider = Settings.Secure.getString(getContentResolver(),
				Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		if(provider.contains("gps")){
			
			
		}
		
	}
	
	//write a method for when
	void applicationOpened(){
		
		
	}
	
	//write a method for then
	void theMapShouldFunction(){
		
		
	}
	
	@Test
	public void openMap(){
		gpsEnabled();
		applicationOpened();
		theMapShouldFunction();
		
	}
	
	
	//we actually want a default constructor without arguments
	@SuppressWarnings("deprecation")
	public ActiveButtonTests()
	{
		//we want to type in (package name, class to launch)
		super("com.cse110team14.placeit.MainActivity",MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(),getActivity());
	}
	
	public void testActiveButtonClicked()
	{
		solo.assertCurrentActivity("Check on the first activity", MainActivity.class);
		
	}

}*/
