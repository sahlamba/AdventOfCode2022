package com.sahillamba.aoc2022;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day1 {
    public static final String EOL = "\n";

    private final BufferedReader reader;

    public Day1(String inputFile) {
        this.reader = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream(inputFile)
        ));
    }

    public int part1() {
        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (String line : reader.lines().collect(Collectors.toList())) {
            if (line.isBlank()) {
                max = Math.max(max, sum);
                sum = 0;
                continue;
            }
            sum += Integer.parseInt(line);
        }

        if (sum > 0) {
            max = Math.max(max, sum);
        }

        return max;
    }

    public int part2() {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(Integer.MIN_VALUE);

        int sum = 0;
        for (String line : reader.lines().collect(Collectors.toList())) {
            if (line.isBlank()) {
                if (sum > minHeap.peek()) {
                    minHeap.offer(sum);
                    if (minHeap.size() > 3) {
                        minHeap.poll();
                    }
                }
                sum = 0;
                continue;
            }
            sum += Integer.parseInt(line);
        }

        if (sum > 0 && sum > minHeap.peek()) {
            minHeap.offer(sum);
            if (minHeap.size() > 3) {
                minHeap.poll();
            }
        }

        sum = 0;
        while (minHeap.size() > 0) {
            sum += minHeap.poll();
        }

        return sum;
    }
}
