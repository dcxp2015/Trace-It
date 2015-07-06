package com.dpc.dcxpchallengeproject2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Daniel on 7/6/2015.
 */
public class GameCanvas extends View {
    private Paint paint;

    public GameCanvas(Context context) {
        super(context);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
