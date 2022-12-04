package com.sahillamba.aoc2022;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Day3InputTest {
    private Day3 solution;

    @BeforeEach
    public void beforeEach() {
        solution = new Day3("input");
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
