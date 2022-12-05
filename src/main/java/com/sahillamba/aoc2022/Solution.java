package com.sahillamba.aoc2022;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public abstract class Solution {
    public static final String EOL = "\n";

    protected final BufferedReader reader;

    public Solution(int day, String inputType) {
        this.reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(
                getClass().getResourceAsStream("/day" + day + "/" + inputType + ".txt")
        )));
    }

    public abstract String part1();

    public abstract String part2();
}
