package com.dcxp.traceit.loader;

import android.content.Context;
import android.util.Log;

import com.dcxp.traceit.Level;
import com.dcxp.traceit.Vertex;

import org.json.JSONArray;

import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Daniel on 7/6/2015.
 */
public class LevelLoader {
    public static final String TAG = "com.dcxp.traceit.loader";
    private static final String BASE = "levels/puzzle";
    private static final String TYPE = ".JSON";

    public static Level load(Context context, int levelNumber) {
        Vertex[] vertices = null;
        int[][] edges = null;

        try {
            JSONObject json = new JSONObject(read(context.getAssets().open(BASE + levelNumber + TYPE)));
            JSONArray vertexArray = json.getJSONArray("vertices");
            JSONArray edgeMatrix = json.getJSONArray("edges");

            vertices = parseVertexArray(vertexArray);
            edges = parseEdgeMatrix(edgeMatrix, vertexArray.length());
        } catch(Exception e) {
            Log.e(TAG, e.toString());
        }

        return new Level(vertices, edges, levelNumber);
    }

    private static Vertex[] parseVertexArray(JSONArray jsonArray) {
        int length = jsonArray.length();
        Vertex[] vertices = new Vertex[length];

        try {
            for (int i = 0; i < length; i++) {
                JSONObject vertex = jsonArray.getJSONObject(i);

                vertices[i] = new Vertex((float) vertex.getDouble("x"), (float) vertex.getDouble("y"));
            }
        } catch(Exception e) {
            Log.e(TAG, e.toString());
        }

        return vertices;
    }

    private static int[][] parseEdgeMatrix(JSONArray edgeMatrix, int vertexCount) {
        int rows = edgeMatrix.length();
        int[][] matrix;

        matrix = new int[rows][vertexCount];

        try {
            for (int i = 0; i < rows; i++) {
                JSONArray row = edgeMatrix.getJSONArray(i);

                int[] data = new int[vertexCount];
                for (int j = 0; j < vertexCount; j++) {
                    data[j] = row.getInt(j);
                }

                matrix[i] = data;
            }
        } catch(Exception e) {
            Log.e(TAG, e.toString());
        }

        return matrix;
    }

    private static String read(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String current;

        while((current = reader.readLine()) != null) {
            builder.append(current);
        }

        return builder.toString();
    }

}
