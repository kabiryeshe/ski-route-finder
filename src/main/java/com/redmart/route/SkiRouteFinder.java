package com.redmart.route;

import java.util.ArrayList;

public class SkiRouteFinder {

    public static void main(String []args) {

        String input = "4 4\n4 8 7 3\n2 5 9 3\n6 3 2 5\n4 4 1 6\n";
        ArrayList<ArrayList<Integer>> grid = InputReader.convertToGrid(input);
    }
}
