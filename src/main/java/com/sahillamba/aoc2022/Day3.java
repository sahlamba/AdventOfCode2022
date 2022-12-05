package com.sahillamba.aoc2022;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day3 extends Solution {
    public Day3(String inputType) {
        super(3, inputType);
    }

    public String part1() {
        return String.valueOf(reader
                .lines()
                .map(this::prioritizeArrangement)
                .reduce(0, Integer::sum));
    }

    public String part2() {
        List<String> lines = reader.lines().collect(Collectors.toList());

        List<List<String>> lineGroups = new LinkedList<>();
        for (int i = 0; i < lines.size() - 2; i += 3) {
            List<String> group = new LinkedList<>();
            group.add(lines.get(i));
            group.add(lines.get(i + 1));
            group.add(lines.get(i + 2));
            lineGroups.add(group);
        }

        return String.valueOf(lineGroups.stream()
                .map(this::intersectingCharScore)
                .reduce(0, Integer::sum));
    }

    private int score(Character c) {
        int alphabets = 26, A_ASCII = 97, a_ASCII = 65;
        return Character.isLowerCase(c) ? c - A_ASCII + 1 : c - a_ASCII + 1 + alphabets;
    }

    private int prioritizeArrangement(String line) {
        AtomicInteger result = new AtomicInteger();
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();

        int i = 0;
        for (; i < line.length() / 2; i++) {
            set1.add(line.charAt(i));
        }
        for (; i < line.length(); i++) {
            set2.add(line.charAt(i));
        }

        set1.stream().filter(set2::contains).findFirst().ifPresent(c -> {
            result.set(score(c));
        });
        return result.get();
    }

    private int intersectingCharScore(List<String> lineGroup) {
        assert lineGroup.size() > 0 && lineGroup.size() <= 3;

        List<Set<Character>> sets = lineGroup.stream()
                .map(s -> s.chars().mapToObj(c -> (char) c).collect(Collectors.toSet()))
                .collect(Collectors.toList());

        sets.get(1).retainAll(sets.get(2));
        sets.get(0).retainAll(sets.get(1));

        return sets.get(0).stream().map(this::score).reduce(0, Integer::sum);
    }
}
