package com.dcxp.dcxpchallengeproject2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Daniel on 7/6/2015.
 */
public class GameCanvas extends View implements View.OnTouchListener {
    private static final String TAG = "com.dcxp.dcxpchallengeproject2";
    private Paint paint;

    public GameCanvas(Context context) {
        super(context);
        paint = new Paint();

        setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void onTouchUp() {
        Log.d(TAG, "onTouchUp");
    }

    private void onTouchDown() {
        Log.d(TAG, "onTouchDown");
    }

    private void onTouchMove() {
        Log.d(TAG, "onTouchMove");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_UP:
                onTouchUp();
                break;
            case MotionEvent.ACTION_DOWN:
                onTouchDown();
                break;
            case MotionEvent.ACTION_MOVE:
                onTouchMove();
                break;
        }

        return true;
    }
}
