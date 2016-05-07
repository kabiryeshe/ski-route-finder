package com.redmart.route;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class InputReader {

    public static ArrayList<ArrayList<Integer>> convertToGrid(String input) {
        String[] rows = input.split("\n");
        Integer gridSize = new Integer(rows[0].split(" ")[0]);

        ArrayList grid = new ArrayList<ArrayList>(gridSize);
        //Removing first row
        for(int i=1; i< rows.length; i++) {
            List<String> row = asList(rows[i].split(" "));
            List<Integer> integerValues = row.stream().map(s -> new Integer(s)).collect(toList());
            grid.add(integerValues);
        }
        return grid;
    }
}
