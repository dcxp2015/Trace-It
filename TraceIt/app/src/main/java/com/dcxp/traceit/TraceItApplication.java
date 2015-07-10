package com.dcxp.traceit;

import android.content.SharedPreferences;

import com.dcxp.traceit.activities.LevelActivity;

/**
 * Created by Daniel on 7/8/2015.
 */
public class TraceItApplication extends android.app.Application {
    private SharedPreferences levelPrefs;
    private SharedPreferences.Editor editor;
    private static final String PREFS_KEY = "lvls";

    @Override
    public void onCreate() {
        super.onCreate();

        levelPrefs = getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        editor = levelPrefs.edit();
    }

    public void unlockLevel(int level) {
        if(level <= LevelActivity.NUMBER_OF_LEVELS) {
            editor.putInt("level max", level);
            editor.commit();
        }
    }

    public boolean isLevelUnlocked(int level) {
        return levelPrefs.getInt("level max", 1) >= level;
    }
}
