package com.rm.route;

import com.rm.route.IO.InputReader;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class InputReaderTest {
    @Test
    public void shouldParseInputStringAndReturnGridOfNumbers() throws Exception {
        //TODO: Fix the input file
        String path = ".src/test/java/com/redmart/route/resources/sample_input_1000.txt";
        List<List<Integer>> grid = new InputReader().readAndParseInput(path);
        assertThat(grid.size(), is(4));
        assertThat(grid.get(0).size(), is(4));
    }
}