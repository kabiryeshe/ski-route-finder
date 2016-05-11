package com.rm.route;

import com.rm.route.IO.InputReader;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class InputReaderTest {
    @Test
    public void shouldParseInputStringAndReturnGridOfNumbers() throws Exception {
        List<List<Integer>> grid = new InputReader().readAndParseInput(loadFile("sample_input.txt"));
        assertThat(grid.size(), is(4));
        assertThat(grid.get(0).size(), is(4));
    }

    private String loadFile(String fileName) {
        return this.getClass().getClassLoader().getResource(fileName).getFile();
    }
}