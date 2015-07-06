package com.dcxp.traceit;

import com.dcxp.traceit.Vertex;

/**
 * Created by Daniel on 7/6/2015.
 */
public class Level {
    private Vertex[] vertexArray;
    private int[][] edgeMatrix;

    public Level(Vertex[] vertexArray, int[][] edgeMatrix) {
        this.vertexArray = vertexArray;
        this.edgeMatrix = edgeMatrix;
    }

    public int[][] getEdgeMatrix() {
        return edgeMatrix;
    }

    public Vertex[] getVertexArray() {
        return vertexArray;
    }
}
