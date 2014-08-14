package jp.co.supersoftware.map;

import jp.co.supersoftware.gourmet.MainActivity;
import jp.co.supersoftware.gourmet.R;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public abstract class Map {
	static final String CLASS_NAME = "Map";
    private GoogleMap mMap;

    protected int getLayoutId() {
        return R.id.map;
    }

    public Map(){
    	Log.i(CLASS_NAME, "**** constructor START ****");
        setUpMapIfNeeded();
        Log.i(CLASS_NAME, "**** constructor END   ****");
    }

    private void setUpMapIfNeeded() {
        if (mMap != null) {
            return;
        }
        mMap = ((SupportMapFragment) MainActivity.fragmentManager.findFragmentById(R.id.map)).getMap();
        if (mMap != null) {
            start();
        }
    }

    /**
     * Run the specific code.
     */
    protected abstract void start();

    protected GoogleMap getMap() {
        setUpMapIfNeeded();
        return mMap;
    }
}
