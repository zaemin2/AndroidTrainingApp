package jp.co.supersoftware.gourmet;

import java.util.ArrayList;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

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
    
    private static GoogleMap objMap;
    private static Double dbLatitude, dbLongitude;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	if( container == null ){
    		return null;
    	}
    	
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        dbLatitude = 29.027167;
        dbLongitude = 76.466904;
        
        setUpMapIfNeeded(); // For setting up the MapFragment
        
        return rootView;
    }
    
    
    /***** Sets up the map if it is possible to do so *****/
    public static void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (objMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            objMap = ((SupportMapFragment) MainActivity.fragmentManager
                    .findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (objMap != null)
                setUpMap();
        }
    }
/*
    @Override
    public void setMenuVisibility(boolean menuVisible) {
    		// TODO Auto-generated method stub
    		super.setMenuVisibility(menuVisible);
    		if(menuVisible)
    		{
    		//System.out.println("VISIBLE");
    			/*
    			 * for refeshing the markers on map based on zip list selction
    			 */
    /*
    	      if (objMap != null)
    	            setUpMap();
    		}

    	}
*/
    /**
     * This is where we can add markers or lines, add listeners or move the
     * camera.
     * <p>
     * This should only be called once and when we are sure that {@link #objMap}
     * is not null.
     */
    private static void setUpMap() {
        // For showing a move to my loction button
        objMap.setMyLocationEnabled(true);
        // For dropping a marker at a point on the Map
        objMap.clear();
        
        // For zooming automatically to the Dropped PIN Location
        objMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dbLatitude,
                dbLongitude), 5.0f));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
       // if (objMap != null)
            //setUpMap();

       // if (objMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            objMap = ((SupportMapFragment) MainActivity.fragmentManager
                    .findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (objMap != null)
                setUpMap();
        //}
    }

    /**** The mapfragment's id must be removed from the FragmentManager
     **** or else if the same it is passed on the next time then 
     **** app will crash ****/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (objMap != null) {
            MainActivity.fragmentManager.beginTransaction()
                .remove(MainActivity.fragmentManager.findFragmentById(R.id.map)).commit();
            objMap = null;
        }
    }

}
