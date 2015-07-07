package com.dcxp.traceit.backend;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 7/7/2015.
 */
public class Leaderboard {
    public static final String TAG = "com.dcxp.backend";
    private static final String USERNAME = "username";
    private static final String HIGHSCORE = "highscore";
    private static final String TABLE_NAME = "_User";
    private static final int TOP_SCORES_LIMIT = 200;

    public static interface ILeaderboardListener {
        void onMyHighScoreLookUpCompleted(float highscore);
        void onTopHighScoreLookupCompleted(List<Float> topScores);
        String getUsername();
    }

    public static void getTopScores(final ILeaderboardListener listener) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_NAME);
        query.orderByDescending(HIGHSCORE);
        query.setLimit(TOP_SCORES_LIMIT);
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> list, ParseException e) {
                List<Float> scores = new ArrayList<Float>();

                for(ParseObject object : list) {
                    scores.add((float)object.getDouble(HIGHSCORE));
                }

                listener.onTopHighScoreLookupCompleted(scores);
            }

        });
    }

    public static void getMyHighScore(final ILeaderboardListener listener) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_NAME);
        query.whereEqualTo(USERNAME, listener.getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    ParseObject user = list.get(0);
                    listener.onMyHighScoreLookUpCompleted((float) user.getDouble(HIGHSCORE));
                } else {
                    Log.e(TAG, e.toString());
                }
            }

        });
    }

    public static void saveScore(ILeaderboardListener listener, float score) {
        ParseObject object = new ParseObject(listener.getUsername());
        object.put(HIGHSCORE, score);
        object.saveInBackground();
    }
}
