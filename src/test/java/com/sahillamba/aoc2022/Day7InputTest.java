package com.sahillamba.aoc2022;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Day7InputTest {
    private Day7 solution;

    @BeforeEach
    public void beforeEach() {
        solution = new Day7("input", false);
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
