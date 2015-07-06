package com.dcxp.traceit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Daniel on 7/6/2015.
 */
public class GameCanvas extends View implements View.OnTouchListener {
    public static final String TAG = "com.dcxp.traceit";

    private final Paint paint;
    private final Level level;
    private Vertex anchor;
    private List<Line> lines;
    private Line currentLine;

    public GameCanvas(Context context, Level level) {
        super(context);

        this.level = level;
        lines = new LinkedList<Line>();
        paint = new Paint();

        setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Vertex.setParentWidth(getWidth());
        Vertex.setParentHeight(getHeight());

        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(Vertex.RADIUS);

        for(Vertex vertex : level.getVertices()) {
            paintVertex(canvas, vertex);
        }

        if(anchor != null) {
            paintTouchCircle(canvas, anchor);
        }

        if(currentLine != null) {
            canvas.drawLine(currentLine.getX(), currentLine.getY(), currentLine.getX1(), currentLine.getY1(), paint);
        }
    }

    private void paintVertex(Canvas canvas, Vertex vertex) {
        canvas.drawCircle(vertex.getX(), vertex.getY(), Vertex.RADIUS, paint);
    }

    private void paintTouchCircle(Canvas canvas, Vertex vertex) {
        canvas.drawCircle(vertex.getX(), vertex.getY(), Vertex.VERTEX_SNAP_RANGE, paint);
    }

    private void onTouchUp(float x, float y) {
        anchor = null;
        currentLine = null;
        invalidate();
    }

    private void onTouchDown(float x, float y) {
        for(Vertex vertex : level.getVertices()) {
            float vy = vertex.getY();
            float vx = vertex.getX();

            if(Math.sqrt(Math.pow(x - vx, 2) + Math.pow(y - vy, 2)) <= Vertex.VERTEX_SNAP_RANGE) {
                anchor = vertex;
                lines.add(currentLine = new Line(vx, vy, vx, vy));
                invalidate();
            }
        }
    }

    private void onTouchMove(float x, float y) {
        if(currentLine != null) {
            currentLine.setX1(x);
            currentLine.setY1(y);
        }

        invalidate();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_UP:
                onTouchUp(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_DOWN:
                onTouchDown(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                onTouchMove(event.getX(), event.getY());
                break;
        }

        return true;
    }
}
