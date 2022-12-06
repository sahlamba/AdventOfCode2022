package com.sahillamba.aoc2022;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Day6InputTest {
    private Day6 solution;

    @BeforeEach
    public void beforeEach() {
        solution = new Day6("input");
    }

    @Test
    public void part1() {
        System.out.println(solution.part1());
    }

    @Test
    public void part2() {
        System.out.println(solution.part2());
    }
}
