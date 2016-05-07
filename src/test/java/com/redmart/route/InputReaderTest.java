package com.redmart.route;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class InputReaderTest {
    @Test
    public void shouldParseInputStringAndReturnGridOfNumbers() throws Exception {

        String input = "4 4\n4 8 7 3\n2 5 9 3\n6 3 2 5\n4 4 1 6\n";
        ArrayList<ArrayList<Integer>> grid = new InputReader().convertToGrid(input);
        assertThat(grid.size(), is(4));
        assertThat(grid.get(0).size(), is(4));
    }
}