package jp.co.supersoftware.gourmet;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
         
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 15));
    }
    
    protected GoogleMap getMap() {
        setUpMapIfNeeded();
        return Map;
    }
}
