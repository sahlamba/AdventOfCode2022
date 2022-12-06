package com.sahillamba.aoc2022;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Day6 extends Solution {
    public Day6(String inputType) {
        super(6, inputType);
    }

    public String part1() {
        return reader.lines()
                .map(line -> findStartOfPacketMarker(line, 4))
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }
    
    public String part2() {
        return reader.lines()
                .map(line -> findStartOfPacketMarker(line, 14))
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    private int findStartOfPacketMarker(String signal, int windowSize) {
        for (int start = 0, end = windowSize; end <= signal.length(); start++, end++) {
            if (allUnique(signal.substring(start, end))) {
                return end;
            }
        }
        return 0;
    }

    private boolean allUnique(String str) {
        Set<Character> chars = new HashSet<>();
        for (char c: str.toCharArray()) {
            chars.add(c);
        }
        return chars.size() == str.length();
    }
}
