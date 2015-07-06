package com.dcxp.dcxpchallengeproject2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import com.dcxp.dcxpchallengeproject2.loader.LevelLoader;


public class GameActivity extends ActionBarActivity {
    public static final String TAG = "com.dcxp.traceit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        FrameLayout container = (FrameLayout) findViewById(R.id.fl_canvasContainer);
        container.addView(new GameCanvas(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
