package com.rm.route.IO;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class InputReader {

    public static List<List<Integer>> readAndParseInput(String filePath) throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return convertToGrid(content);
    }

    private static List<List<Integer>> convertToGrid(String input) {
        String[] rows = input.split("\n");
        Integer numberOfRows = new Integer(rows[0].split(" ")[0]);

        ArrayList grid = new ArrayList<ArrayList>(numberOfRows - 1);
        //Removing first row
        for(int i=1; i< rows.length; i++) {
            List<String> row = asList(rows[i].split(" "));
            List<Integer> integerValues = row.stream().map(s -> new Integer(s)).collect(toList());
            grid.add(integerValues);
        }
        return grid;
    }
}
