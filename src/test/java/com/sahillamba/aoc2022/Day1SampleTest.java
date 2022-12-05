package com.sahillamba.aoc2022;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1SampleTest {
    private Day1 solution;

    @BeforeEach
    public void beforeEach() {
        solution = new Day1("sample");
    }

    @Test
    public void part1() {
        assertEquals("24000", solution.part1());
    }

    @Test
    public void part2() {
        assertEquals("45000", solution.part2());
    }
}
