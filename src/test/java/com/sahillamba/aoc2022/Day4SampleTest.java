package com.sahillamba.aoc2022;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day4SampleTest {
    private Day4 solution;

    @BeforeEach
    public void beforeEach() {
        solution = new Day4("sample");
    }

    @Test
    public void part1() {
        assertEquals(2, solution.part1());
    }

    @Test
    public void part2() {
        assertEquals(4, solution.part2());
    }
}
