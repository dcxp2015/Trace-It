package com.dcxp.traceit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Daniel on 7/7/2015.
 */
public class Utils {

    public static String fullyReadInputStream(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String current;

        while((current = reader.readLine()) != null) {
            builder.append(current);
        }

        return builder.toString();
    }
}
