package com.sahillamba.aoc2022;

import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Day1 extends Solution {
    public Day1(String inputType) {
        super(1, inputType);
    }

    public String part1() {
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

        return String.valueOf(max);
    }

    public String part2() {
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

        return String.valueOf(sum);
    }
}
