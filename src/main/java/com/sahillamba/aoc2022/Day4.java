package com.sahillamba.aoc2022;

public class Day4 extends Solution {
    public Day4(String inputType) {
        super(4, inputType);
    }

    public int part1() {
        return (int) reader
                .lines()
                .map(Range::of)
                .filter(p -> p.left.fullyContains(p.right) || p.right.fullyContains(p.left))
                .count();
    }
    
    public int part2() {
        return (int) reader
                .lines()
                .map(Range::of)
                .filter(p -> p.left.intersects(p.right) || p.right.intersects(p.left))
                .count();
    }

    static class Range {
        int start;
        int end;

        public Range(int start, int end) {
            assert start <= end;

            this.start = start;
            this.end = end;
        }

        public static Pair<Range> of(String line) {
            String[] ranges = line.split(",");

            String[] rangeLeftStr = ranges[0].split("-");
            Range rangeLeft = new Range(Integer.parseInt(rangeLeftStr[0]), Integer.parseInt(rangeLeftStr[1]));

            String[] rangeRightStr = ranges[1].split("-");
            Range rangeRight = new Range(Integer.parseInt(rangeRightStr[0]), Integer.parseInt(rangeRightStr[1]));

            return new Pair<>(rangeLeft, rangeRight);
        }

        public boolean fullyContains(Range range) {
            return range.start >= this.start && range.end <= this.end;
        }

        public boolean intersects(Range range) {
            return !(this.end < range.start || range.end < this.start);
        }
    }

    static class Pair<T> {
        T left;
        T right;

        public Pair(T left, T right) {
            this.left = left;
            this.right = right;
        }
    }
}
