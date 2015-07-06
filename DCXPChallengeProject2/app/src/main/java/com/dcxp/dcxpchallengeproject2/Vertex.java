package com.dcxp.dcxpchallengeproject2;

/**
 * Created by Daniel on 7/6/2015.
 */
public class Vertex {
    private final int x, y;
    private final int[] edges;

    public Vertex(final int x, final int y, final int[] edges) {
        this.x = x;
        this.y = y;
        this.edges = edges;
    }

    public int[] getEdges() {
        return edges;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
