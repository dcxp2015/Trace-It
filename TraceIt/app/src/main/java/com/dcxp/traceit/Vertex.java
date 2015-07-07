package com.dcxp.traceit;

/**
 * Created by Daniel on 7/6/2015.
 */
public final class Vertex {
    public static final float RADIUS = 10;
    public static final float VERTEX_SNAP_RANGE = RADIUS * 4;
    private static int parentWidth, parentHeight;

    // Percent of the width and height that this vertex is placed at
    private final float relativeX, relativeY;

    public Vertex(final float relativeX, final float relativeY) {
        this.relativeX = relativeX;
        this.relativeY = relativeY;
    }

    public float getRelativeX() {
        return relativeX;
    }

    public float getRelativeY() {
        return relativeY;
    }

    public float getX() {
        return relativeX * parentWidth;
    }

    public float getY() {
        return relativeY * parentHeight;
    }

    public static void setParentWidth(int parentWidth) {
        Vertex.parentWidth = parentWidth;
    }

    public static void setParentHeight(int parentHeight) {
        Vertex.parentHeight = parentHeight;
    }

    public static int getParentHeight() {
        return parentHeight;
    }

    public static int getParentWidth() {
        return parentWidth;
    }
}
