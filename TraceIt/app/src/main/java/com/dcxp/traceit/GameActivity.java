package com.dcxp.traceit;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Chronometer;
import android.widget.FrameLayout;

import com.dcxp.traceit.GameCanvas;
import com.dcxp.traceit.R;
import com.dcxp.traceit.loader.LevelLoader;


public class GameActivity extends Activity {
    public static final String TAG = "com.dcxp.traceit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);

        Level level = LevelLoader.load(this, "test.json");

        FrameLayout container = (FrameLayout) findViewById(R.id.fl_canvasContainer);
        container.addView(new GameCanvas(this, level));

        Chronometer chronometer = (Chronometer) findViewById(R.id.cnm_timer);

        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }
}
