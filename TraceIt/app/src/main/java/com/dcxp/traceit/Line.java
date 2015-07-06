package com.dcxp.traceit;

/**
 * Created by Daniel on 7/6/2015.
 */
public class Line {
    private float x, y, x1, y1;

    public Line(float x, float y, float x1, float y1) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public float getX1() {
        return x1;
    }

    public float getY1() {
        return y1;
    }
}
