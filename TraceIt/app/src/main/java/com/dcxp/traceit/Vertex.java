package com.dcxp.traceit;

/**
 * Created by Daniel on 7/6/2015.
 */
public final class Vertex {
    private static final float BASE_RADIUS = 5; // Radius as defined on our VIRTUAL SIZE
    private static final int TOUCH_CIRCLE_SIZE_SCALE = 4;
    private static final float BASE_VERTEX_SNAP_RANGE = BASE_RADIUS * TOUCH_CIRCLE_SIZE_SCALE;
    private static float parentWidth, parentHeight;
    private static float radius, vertexSnapRange;

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

    private static void update() {
        radius = BASE_RADIUS * ((parentWidth * parentHeight) / (480 * 800));
        vertexSnapRange = radius * TOUCH_CIRCLE_SIZE_SCALE;
    }

    public static void setParentWidth(int parentWidth) {
        Vertex.parentWidth = parentWidth;
        update();
    }

    public static void setParentHeight(int parentHeight) {
        Vertex.parentHeight = parentHeight;
        update();
    }

    public static float getParentHeight() {
        return parentHeight;
    }

    public static float getParentWidth() {
        return parentWidth;
    }

    public static void setVertexSnapRange(float vertexSnapRange) {
        Vertex.vertexSnapRange = vertexSnapRange;
    }

    public static void setRadius(float radius) {
        Vertex.radius = radius;
    }

    public static float getVertexSnapRange() {
        return vertexSnapRange;
    }

    public static float getRadius() {
        return radius;
    }
}
