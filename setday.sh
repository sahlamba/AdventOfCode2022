#!/bin/bash

# Directories
SRC_DIR=./src/main/java/com/sahillamba/aoc2022
TST_DIR=./src/test/java/com/sahillamba/aoc2022
RES_DIR="./src/main/resources/day${1}"

# Classes
SOL_SRC="${SRC_DIR}/Day${1}.java"
TST_SAMPLE="${TST_DIR}/Day${1}SampleTest.java"
TST_INPUT="${TST_DIR}/Day${1}InputTest.java"

# Inputs
TXT_SAMPLE="${RES_DIR}/sample.txt"
TXT_INPUT="${RES_DIR}/input.txt"

# Create problem input files
mkdir "${RES_DIR}"
touch "${TXT_SAMPLE}"
touch "${TXT_INPUT}"

# Create solution template
touch "${SOL_SRC}" &&
  echo "package com.sahillamba.aoc2022;

public class Day${1} extends Solution {
    public Day${1}(String inputType) {
        super(${1}, inputType);
    }

    public String part1() {
        return \"0\";
    }
    
    public String part2() {
        return \"0\";
    }
}" >"${SOL_SRC}"

# Create sample test class
touch "${TST_SAMPLE}" &&
  echo "package com.sahillamba.aoc2022;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day${1}SampleTest {
    private Day${1} solution;

    @BeforeEach
    public void beforeEach() {
        solution = new Day${1}(\"sample\");
    }

    @Test
    public void part1() {
        assertEquals(\"0\", solution.part1());
    }

    @Test
    public void part2() {
        assertEquals(\"0\", solution.part2());
    }
}" >"${TST_SAMPLE}"

# Create input test class
touch "${TST_INPUT}" &&
  echo "package com.sahillamba.aoc2022;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Day${1}InputTest {
    private Day${1} solution;

    @BeforeEach
    public void beforeEach() {
        solution = new Day${1}(\"input\");
    }

    @Test
    public void part1() {
        System.out.println(solution.part1());
    }

    @Test
    public void part2() {
        System.out.println(solution.part2());
    }
}" >"${TST_INPUT}"
