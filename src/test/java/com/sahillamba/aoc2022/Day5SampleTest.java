package com.sahillamba.aoc2022;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day5SampleTest {
    private Day5 solution;

    @BeforeEach
    public void beforeEach() {
        solution = new Day5("sample");
    }

    @Test
    public void part1() {
        assertEquals("CMZ", solution.part1());
    }

    @Test
    public void part2() {
        assertEquals("MCD", solution.part2());
    }
}
