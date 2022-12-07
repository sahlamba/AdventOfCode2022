package com.sahillamba.aoc2022;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day7SampleTest {
    private Day7 solution;

    @BeforeEach
    public void beforeEach() {
        solution = new Day7("sample", true);
    }

    @Test
    public void part1() {
        assertEquals("95437", solution.part1());
    }

    @Test
    public void part2() {
        assertEquals("24933642", solution.part2());
    }
}
