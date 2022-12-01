package com.sahillamba.aoc2022;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Day1Test {
    private Day1 day1;

    @BeforeEach
    public void setUp() {
        day1 = new Day1("/day1/input.txt");
    }

    @Test
    public void part1() {
        System.out.println(day1.part1());
    }

    @Test
    public void part2() {
        System.out.println(day1.part2());
    }
}
