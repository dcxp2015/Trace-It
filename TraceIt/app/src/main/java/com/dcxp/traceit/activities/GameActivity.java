package com.dcxp.traceit.activities;

import android.app.Activity;
import android.os.SystemClock;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.FrameLayout;

import com.dcxp.traceit.GameCanvas;
import com.dcxp.traceit.Level;
import com.dcxp.traceit.R;
import com.dcxp.traceit.loader.LevelLoader;


public class GameActivity extends Activity {
    public static final String TAG = "com.dcxp.traceit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        Level level = LevelLoader.load(this, "test.json");

        FrameLayout container = (FrameLayout) findViewById(R.id.fl_canvasContainer);
        container.addView(new GameCanvas(this, level));

        Chronometer chronometer = (Chronometer) findViewById(R.id.cnm_timer);

        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }
}
