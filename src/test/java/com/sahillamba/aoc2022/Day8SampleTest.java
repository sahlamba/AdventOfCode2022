package com.sahillamba.aoc2022;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day8SampleTest {
    private Day8 solution;

    @BeforeEach
    public void beforeEach() {
        solution = new Day8("sample");
    }

    @Test
    public void part1() {
        assertEquals("21", solution.part1());
    }

    @Test
    public void part2() {
        assertEquals("4", solution.part2());
    }
}
