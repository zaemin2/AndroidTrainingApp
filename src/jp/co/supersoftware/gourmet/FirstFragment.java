package jp.co.supersoftware.gourmet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import jp.co.supersoftware.clienthttp.HttpClient;
import jp.co.supersoftware.clienthttp.HttpException;
import jp.co.supersoftware.clienthttp.HttpParamsGet;
import jp.co.supersoftware.clienthttp.HttpParamsMultiPart;
import jp.co.supersoftware.clienthttp.HttpRequestGet;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FirstFragment extends Fragment {
	/**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number"; 

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FirstFragment newInstance(int sectionNumber) {
    	FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public FirstFragment() {
    	 
    }
    
    private GoogleMap Map;
    private LocationManager locationManager;
    private String provider;
    private double lat, lng;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	if( container == null ){
    		return null;
    	}
    	
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
      
        setUpMapIfNeeded(); // For setting up the MapFragment
        
        return rootView;
    }
    
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }
    
    private void setUpMapIfNeeded() {
        if (Map != null) {
            return;
        }
        Map = ((SupportMapFragment) MainActivity.fragmentManager.findFragmentById(R.id.map)).getMap();
        if (Map != null) {
            start();
        }
    }
    
    protected void start() {
    	// zoom control button enable or disable.
    	Map.getUiSettings().setZoomControlsEnabled(false);
    	
        locationManager = (LocationManager) getActivity().getSystemService(MainActivity.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);
         
        // Initialize the location fields
        if (location != null) {
        	lat =  location.getLatitude();
            lng = location.getLongitude();
        } else {
        	lat =  51.503186;
            lng = -0.126446;
        }
        
        MyTask task = new MyTask ();
        task.execute();
         
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 15));
    }
    
    protected GoogleMap getMap() {
        setUpMapIfNeeded();
        return Map;
    }
    
    
    /**
     * get gourmet data by HTTP client based on HOT PEPPER
     * API KEY : 55da4e4fa8b53374
     * @throws HttpException 
     */
    private class MyTask extends AsyncTask<Void , Void, Void > {

    	private Exception exception;
    	private String response;
    	
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
			try {
				getGourmetData();
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		 private void getGourmetData() throws HttpException{
		    	
			HttpParamsGet getpart = new HttpParamsGet();
			getpart.add("key", "55da4e4fa8b53374");
		    getpart.add("Latitude", String.valueOf(lat));
		    getpart.add("Longitude",String.valueOf(lng));
		    getpart.add("Range", "3");

		    HttpRequestGet get = new HttpRequestGet("http://api.hotpepper.jp/GourmetSearch/V110/", getpart);

		    response = HttpClient.getStringResponse(get);
		    Log.v("response", response);
		 }
    }

}
