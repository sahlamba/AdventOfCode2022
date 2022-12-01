package com.sahillamba.aoc2022.day1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Part1 {
    public static final String EOL = "\n";

    public void solve() {
        InputStream input = getClass().getResourceAsStream("/day1/sample.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        System.out.println(reader.lines().collect(Collectors.joining(EOL)));
    }
}
