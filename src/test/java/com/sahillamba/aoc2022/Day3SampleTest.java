package com.sahillamba.aoc2022;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3SampleTest {
    private Day3 solution;

    @BeforeEach
    public void beforeEach() {
        solution = new Day3("sample");
    }

    @Test
    public void part1() {
        assertEquals("157", solution.part1());
    }

    @Test
    public void part2() {
        assertEquals("70", solution.part2());
    }
}
