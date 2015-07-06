package com.dcxp.traceit;

/**
 * Created by Daniel on 7/6/2015.
 */
public final class Vertex {
    private final float x, y;

    public Vertex(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "x: " + x + "\ny: " + y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
