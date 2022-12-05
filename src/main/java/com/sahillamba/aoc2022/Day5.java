package com.sahillamba.aoc2022;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day5 extends Solution {
    private static final String STACKS = "stacks";
    private static final String MOVES = "moves";

    public Day5(String inputType) {
        super(5, inputType);
    }

    public String part1() {
        Map<String, Object> input = parseInput();

        List<Stack<String>> stacks = getStacks(input);
        List<Move> moves = getMoves(input);

        moves.forEach(m -> m.apply(stacks));

        return stacks.stream().map(Stack::peek).collect(Collectors.joining());
    }

    public String part2() {
        Map<String, Object> input = parseInput();

        List<Stack<String>> stacks = getStacks(input);
        List<Move> moves = getMoves(input);

        moves.forEach(m -> m.applyRetainingOrder(stacks));

        return stacks.stream().map(Stack::peek).collect(Collectors.joining());
    }

    private List<Move> getMoves(Map<String, Object> input) {
        List<String> moves = (List<String>) input.get(MOVES);

        Pattern moveRegex = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");

        return moves.stream()
                .map(moveRegex::matcher)
                .filter(Matcher::matches)
                .map(m -> new Move(m.group(1), m.group(2), m.group(3)))
                .collect(Collectors.toList());
    }

    private List<Stack<String>> getStacks(Map<String, Object> input) {
        List<List<String>> stackInput = (List<List<String>>) input.get(STACKS);
        int totalStacks = stackInput.stream()
                .max(Comparator.comparingInt(List::size))
                .orElseThrow()
                .size();

        List<Stack<String>> stacks = new LinkedList<>();
        for (int i = 0; i < totalStacks; i++) {
            stacks.add(new Stack<>());
        }

        Collections.reverse(stackInput);
        stackInput.forEach(l -> {
            for (int i = 0; i < l.size(); i++) {
                String entry = l.get(i);
                // Skip [1, 2, 3] i.e. stack no. line inputs and blank lines
                if (entry.length() <= 1) {
                    continue;
                }
                stacks.get(i).add(entry.substring(1, 2));
            }
        });

        return stacks;
    }

    private Map<String, Object> parseInput() {
        Map<Boolean, List<String>> input = reader.lines()
                .filter(line -> !line.isBlank())
                .collect(Collectors.partitioningBy(line -> line.startsWith("move")));

        Map<String, Object> result = new HashMap<>();
        result.put(MOVES, input.get(true));
        result.put(STACKS, input.get(false).stream()
                .map(line -> chunk(line, 4)
                        .stream()
                        .map(String::trim)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList()));
        return result;
    }

    private List<String> chunk(String str, int chunkSize) {
        List<String> chunks = new LinkedList<>();
        for (int i = 0; i < str.length(); i += chunkSize) {
            chunks.add(str.substring(i, Math.min(str.length(), i + chunkSize)));
        }
        return chunks;
    }

    static class Move {
        int count;
        int from;
        int to;

        public Move(String count, String from, String to) {
            this.count = Integer.parseInt(count);
            this.from = Integer.parseInt(from);
            this.to = Integer.parseInt(to);
        }

        public void apply(List<Stack<String>> stacks) {
            Stack<String> source = stacks.get(from - 1);
            Stack<String> destination = stacks.get(to - 1);

            IntStream.range(0, count).forEach(i -> destination.add(source.pop()));
        }

        public void applyRetainingOrder(List<Stack<String>> stacks) {
            Stack<String> source = stacks.get(from - 1);
            Stack<String> destination = stacks.get(to - 1);

            Stack<String> pitStop = new Stack<>();
            IntStream.range(0, count).forEach(i -> pitStop.add(source.pop()));
            while(!pitStop.isEmpty()) {
                destination.add(pitStop.pop());
            }
        }
    }
}
