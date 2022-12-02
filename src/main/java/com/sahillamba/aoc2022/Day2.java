package com.sahillamba.aoc2022;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Day2 extends Solution {
    public Day2(String inputType) {
        super(2, inputType);
    }

    public int part1() {
        Map<String, Shape> decryptedMoves = Map.of(
                "A", Shape.Rock,
                "X", Shape.Rock,
                "B", Shape.Paper,
                "Y", Shape.Paper,
                "C", Shape.Scissors,
                "Z", Shape.Scissors
        );

        int total = 0;
        for (String line : reader.lines().filter(Predicate.not(String::isBlank)).collect(Collectors.toList())) {
            String[] moves = line.split(" ");
            Shape opponent = decryptedMoves.get(moves[0]);
            Shape me = decryptedMoves.get(moves[1]);
            total += Shape.play(me, opponent);
        }
        return total;
    }

    public int part2() {
        Map<String, Shape> decryptedMoves = Map.of(
                "A", Shape.Rock,
                "B", Shape.Paper,
                "C", Shape.Scissors
        );
        Map<String, String> outcomes = Map.of(
                "X", "Lose",
                "Y", "Draw",
                "Z", "Win"
        );

        int total = 0;
        for (String line : reader.lines().filter(Predicate.not(String::isBlank)).collect(Collectors.toList())) {
            String[] moves = line.split(" ");
            Shape opponent = decryptedMoves.get(moves[0]);
            String outcome = outcomes.get(moves[1]);
            total += Shape.playForOutcome(outcome, opponent);
        }
        return total;
    }

    enum Shape {
        Rock(1),
        Paper(2),
        Scissors(3);

        private final int score;

        Shape(int score) {
            this.score = score;
        }

        // Plays P1 against P2 and return win/draw/lost score for P1
        public static int play(Shape p1, Shape p2) {
            Map<Shape, Shape> beats = Map.of(
                    Rock, Scissors,     // Rock beats Scissors
                    Paper, Rock,        // Paper beats Rock
                    Scissors, Paper     // Scissors beats Paper
            );
            // Draw
            if (p1 == p2) {
                return 3 + p1.score;
            }
            // Won
            if (beats.get(p1) == p2) {
                return 6 + p1.score;
            }
            // Lost
            return p1.score;
        }

        public static int playForOutcome(String outcome, Shape opponent) {
            Map<Shape, Shape> beats = Map.of(
                    Rock, Scissors,     // Rock beats Scissors
                    Paper, Rock,        // Paper beats Rock
                    Scissors, Paper     // Scissors beats Paper
            );
            Map<Shape, Shape> losesTo = Map.of(
                    Scissors, Rock,     // Scissors loses to Rock
                    Rock, Paper,        // Rock loses to Paper
                    Paper, Scissors     // Paper loses to Scissors
            );
            switch (outcome) {
                case "Lose": {
                    return beats.get(opponent).score;
                }
                case "Draw": {
                    return 3 + opponent.score;
                }
                case "Win": {
                    return 6 + losesTo.get(opponent).score;
                }
            }
            return 0;
        }
    }
}
