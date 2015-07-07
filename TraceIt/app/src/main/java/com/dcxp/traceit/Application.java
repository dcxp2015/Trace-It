package com.dcxp.traceit;

import android.util.Log;

import com.parse.Parse;
import org.json.*;

/**
 * Created by Daniel on 7/7/2015.
 */
public class Application extends android.app.Application {
    public static final String TAG = "com.dcxp.traceit";

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            JSONObject object = new JSONObject(Utils.fullyReadInputStream(getAssets().open("Keys.json")));

            Parse.enableLocalDatastore(this);
            Parse.initialize(this, object.getString("k1"), object.getString("k2"));
        } catch(Exception e) {
            Log.e(TAG, e.toString());
        }
    }

}
