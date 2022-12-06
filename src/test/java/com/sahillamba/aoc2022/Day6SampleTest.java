package com.sahillamba.aoc2022;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day6SampleTest {
    private Day6 solution;

    @BeforeEach
    public void beforeEach() {
        solution = new Day6("sample");
    }

    @Test
    public void part1() {
        assertEquals("7,5,6,10,11", solution.part1());
    }

    @Test
    public void part2() {
        assertEquals("19,23,23,29,26", solution.part2());
    }
}
