package com.sahillamba.aoc2022;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day6 extends Solution {
    private final List<String> lines;

    public Day6(String inputType) {
        super(6, inputType);
        lines = reader.lines().collect(Collectors.toList());
    }

    public String part1() {
        return lines.stream()
                .map(line -> findPacketMarker(line, 4))
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }
    
    public String part2() {
        return lines.stream()
                .map(line -> findPacketMarker(line, 14))
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    private int findPacketMarker(String signal, int windowSize) {
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
