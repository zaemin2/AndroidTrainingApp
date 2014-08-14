package jp.co.supersoftware.map;

//import android.location.LocationManager;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;


public class ClusteringMarker extends Map {
	static final String CLASS_NAME = "ClusteringMarker";
	public String test = "test";
	
	//private LocationManager locationManager;
    //private String provider;
    //private double lat, lng;
	
	public LatLng position;
	
	public void CusteringMarker(){
		Log.i(CLASS_NAME, "**** constructor START ****");
		//this.test = "test20";
		Log.i(CLASS_NAME, "**** constructor END   ****");
	}
	
	
	@Override
	protected void start() {
		Log.i(CLASS_NAME, "**** start() START ****");
		//Log.i(CLASS_NAME, this.test);
		getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 10));
		Log.i(CLASS_NAME, "**** start() END   ****");
	}

}
