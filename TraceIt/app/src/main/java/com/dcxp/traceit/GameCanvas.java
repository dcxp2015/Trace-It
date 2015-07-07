package com.dcxp.traceit;

import android.content.Context;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Daniel on 7/6/2015.
 */
public class GameCanvas extends View implements View.OnTouchListener {
    public static final String TAG = "com.dcxp.traceit";
    private static final int BLUEPRINT_COLOR = Color.GRAY;
    private static final int BLUEPRINT_ALPHA = 50;

    private final Paint paint;
    private final Level level;

    private int[][] edgeMatrix;
    private Vertex[] vertices;

    private List<Line> lines;
    private Line currentLine;
    private Vertex lastVertex;

    public GameCanvas(Context context, Level level) {
        super(context);

        this.level = level;

        vertices = level.getVertices();
        edgeMatrix = level.getEdgeMatrix();
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

        paintBlueprint(canvas);

        for(Vertex vertex : vertices) {
            paintVertex(canvas, vertex);
        }

        for(Vertex vertex : vertices) {
            if(hasConnection(vertex)) {
                paintTouchCircle(canvas, vertex);
            }
        }

        for(Line line : lines) {
            canvas.drawLine(line.getX(), line.getY(), line.getX1(), line.getY1(), paint);
        }
    }

    private void paintBlueprint(Canvas canvas) {
        int oldColor = paint.getColor();
        int oldAlpha = paint.getAlpha();
        paint.setColor(BLUEPRINT_COLOR);
        paint.setAlpha(BLUEPRINT_ALPHA);

        for(int i = 0; i < edgeMatrix.length; i++) {
            for(int j = 0; j < edgeMatrix[i].length; j++) {
                if(edgeMatrix[i][j] == 1) {
                    canvas.drawLine(vertices[i].getX(), vertices[i].getY(), vertices[j].getX(), vertices[j].getY(), paint);
                }
            }
        }

        paint.setAlpha(oldAlpha);
        paint.setColor(oldColor);
    }

    private void connect(Vertex v1, Vertex v2) {
        int v1Index = indexOf(v1);
        int v2Index = indexOf(v2);

        edgeMatrix[v1Index][v2Index] = -1;
        edgeMatrix[v2Index][v1Index] = -1;
    }

    private boolean hasConnection(Vertex vertex) {
        for(int i : edgeMatrix[indexOf(vertex)]) {
            if(i == -1) {
                return true;
            }
        }

        return false;
    }

    private boolean connected(Vertex v1, Vertex v2) {
        return edgeMatrix[indexOf(v1)][indexOf(v2)] == -1;
    }

    private boolean connectable(Vertex v1, Vertex v2) {
        return edgeMatrix[indexOf(v1)][indexOf(v2)] == 1;
    }

    private int indexOf(Vertex vertex) {
        for(int i = 0; i < vertices.length; i++) {
            if(vertices[i].equals(vertex)) {
                return i;
            }
        }

        return -1;
    }

    private void paintVertex(Canvas canvas, Vertex vertex) {
        canvas.drawCircle(vertex.getX(), vertex.getY(), Vertex.RADIUS, paint);
    }

    private void paintTouchCircle(Canvas canvas, Vertex vertex) {
        canvas.drawCircle(vertex.getX(), vertex.getY(), Vertex.VERTEX_SNAP_RANGE, paint);
    }

    private boolean pointInVertex(Vertex vertex, float x, float y) {
        return Math.sqrt(Math.pow(x - vertex.getX(), 2) + Math.pow(y - vertex.getY(), 2)) <= Vertex.VERTEX_SNAP_RANGE;
    }

    private void snapLineToDestinationVertex(Line line, Vertex vertex) {
        line.setX1(vertex.getX());
        line.setY1(vertex.getY());
    }

    private void onTouchUp(float x, float y) {
        // Grab a copy of the original edge matrix
        edgeMatrix = level.getEdgeMatrix();

        currentLine = null;

        lines.clear();

        invalidate();
    }

    private void onTouchDown(float x, float y) {
        for(Vertex vertex : vertices) {
            float vy = vertex.getY();
            float vx = vertex.getX();

            if(pointInVertex(vertex, x, y)) {
                lastVertex = vertex;
                lines.add(currentLine = new Line(vx, vy, vx, vy));
                invalidate();
            }
        }
    }

    private void onTouchMove(float x, float y) {
        for(Vertex vertex : vertices) {
            if(pointInVertex(vertex, x, y)) {
                if(lastVertex != null) {
                    if(connectable(vertex, lastVertex)) {
                        connect(vertex, lastVertex);
                        snapLineToDestinationVertex(currentLine, vertex);

                        lastVertex = vertex;

                        float vx = lastVertex.getX();
                        float vy = lastVertex.getY();

                        lines.add(currentLine = new Line(vx, vy, vx, vy));
                    }
                }

                break;
            }
        }

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
