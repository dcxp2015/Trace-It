package com.dcxp.traceit.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;

import com.dcxp.traceit.R;
import com.dcxp.traceit.TraceItApplication;

public class LevelActivity extends ActionBarActivity {
    private GridView gridView;

    private class GridViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return NUMBER_OF_LEVELS;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View inflatedView = convertView;

            final int level = position + 1;

            if(inflatedView == null) {
                if(app.isLevelUnlocked(level)) {
                    inflatedView = getLayoutInflater().inflate(R.layout.level_layout_unlocked, gridView, false);
                }
                else {
                    inflatedView = getLayoutInflater().inflate(R.layout.level_layout_locked, gridView, false);
                }
            }

            ImageButton levelBtn = ((ImageButton) inflatedView.findViewById(R.id.btn_level));

            if(app.isLevelUnlocked(level)) {
                levelBtn.setBackgroundResource(R.drawable.unlock);
            }
            else {
                levelBtn.setBackgroundResource(R.drawable.lock);
            }


            levelBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // Then the level is unlockef
                    if (app.isLevelUnlocked(level)) {
                        Intent intent = new Intent(LevelActivity.this, GameActivity.class);
                        intent.putExtra("level", level);
                        startActivity(intent);
                    }
                }

            });

            return inflatedView;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }
    }

    private TraceItApplication app;
    public static final int NUMBER_OF_LEVELS = 17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        app = (TraceItApplication) getApplication();

        // Level 1 always unlocked
        app.unlockLevel(1);

        gridView = (GridView) findViewById(R.id.gv_levels);
        gridView.setAdapter(new GridViewAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_level, menu);
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
