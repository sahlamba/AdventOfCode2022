package com.sahillamba.aoc2022;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day8 extends Solution {
    private static final int[][] directions = {
            {0, -1},    // Left  (y = 0, x = -1)
            {0, 1},     // Right (y = 0, x = 1)
            {-1, 0},    // Up    (y = -1, x = 0)
            {1, 0}      // Down  (y = 1, x = 0)
    };

    private final List<List<Integer>> grid;

    public Day8(String inputType) {
        super(8, inputType);
        grid = reader.lines()
                .map(row -> Stream.of(row.split(""))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public String part1() {
        int n = grid.size(), m = grid.get(0).size();
        int totalVisibleTrees = 2 * ((n - 1) + (m - 1));
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                totalVisibleTrees += isTreeVisible(grid, i, j, n, m) ? 1 : 0;
            }
        }
        return String.valueOf(totalVisibleTrees);
    }
    
    public String part2() {
        int n = grid.size(), m = grid.get(0).size();
        int scenicScore = Integer.MIN_VALUE;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                scenicScore = Math.max(scenicScore(grid, i, j, n, m), scenicScore);
            }
        }
        return String.valueOf(scenicScore);
    }

    private boolean isTreeVisible(List<List<Integer>> grid, int i, int j, int n, int m) {
        for (int[] direction : directions) {
            if (isVisibleFromDirection(grid, i, j, n, m, direction)) {
                return true;
            }
        }
        return false;
    }

    private boolean isVisibleFromDirection(List<List<Integer>> grid, int i, int j, int n, int m, int[] direction) {
        int treeHeight = grid.get(i).get(j);
        int y = i + direction[0], x = j + direction[1];
        while (withinBounds(y, x, n, m)) {
            if (grid.get(y).get(x) >= treeHeight) {
                return false;
            }
            y += direction[0];
            x += direction[1];
        }
        return true;
    }

    private int scenicScore(List<List<Integer>> grid, int i, int j, int n, int m) {
        int product = 1;
        for (int[] direction : directions) {
            product *= scenicScoreInDirection(grid, i, j, n, m, direction);
        }
        return product;
    }

    private int scenicScoreInDirection(List<List<Integer>> grid, int i, int j, int n, int m, int[] direction) {
        int treeHeight = grid.get(i).get(j);
        int totalVisibleTrees = 0, y = i + direction[0], x = j + direction[1];
        while (withinBounds(y, x, n, m)) {
            if (grid.get(y).get(x) <= treeHeight) {
                totalVisibleTrees++;
            }
            if (grid.get(y).get(x) >= treeHeight) {
                break;
            }
            y += direction[0];
            x += direction[1];
        }
        return totalVisibleTrees;
    }

    private boolean withinBounds(int i, int j, int n, int m) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }
}
