package com.sahillamba.aoc2022;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2SampleTest {
    private Day2 solution;

    @BeforeEach
    public void beforeEach() {
        solution = new Day2("sample");
    }

    @Test
    public void part1() {
        assertEquals(15, solution.part1());
    }

    @Test
    public void part2() {
        assertEquals(12, solution.part2());
    }
}
