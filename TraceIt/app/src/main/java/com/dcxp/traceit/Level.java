package com.dcxp.traceit;

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
        int[][] shallow = new int[edgeMatrix.length][];

        for(int i = 0; i < shallow.length; i++) {
            shallow[i] = new int[edgeMatrix[i].length];

            for(int j = 0; j < edgeMatrix[i].length; j++) {
                shallow[i][j] = edgeMatrix[i][j];
            }
        }

        return shallow;
    }

    public Vertex[] getVertices() {
        return vertexArray;
    }
}
