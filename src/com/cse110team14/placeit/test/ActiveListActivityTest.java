package com.cse110team14.placeit.test;

import com.cse110team14.placeit.*;
import com.google.android.gms.maps.model.LatLng;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class ActiveListActivityTest extends ActivityInstrumentationTestCase2<ActiveListActivity>{
	private Solo solo;

	public ActiveListActivityTest() {
		super("com.cse110team14.placeit",ActiveListActivity.class);
		// TODO Auto-generated constructor stub
	}
	
	private ActiveListActivity mActivity;
	//private TextView mapTextview;
	//private TextView listTextview;
	//private TextView mapTextview;
	
	public void setUp() throws Exception{
		super.setUp();
		ActiveListActivity activity = getActivity();
		solo = new Solo(getInstrumentation(),getActivity());
	}
	
	/*
	//set up the testing environment
	public void setUp(){
		super.setUp();
		mActivity = this.getActivity();
		mapTextview = (TextView)mActivity.findViewById(com.cse110team14.placeit.R.id.map);
	}*/
	
	//write the function for given
    public void addMarker(){
      MainActivity act = new MainActivity();
      
    	
    	
    }
	
	//write the function for when
	
	
	//write the function for then
   
	
}

