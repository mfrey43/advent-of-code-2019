package com.github.mfrey43.day02;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.io.Resources;

public class Day02 {

  public static final int SECOND_PART_TARGET = 19690720;

  public static void main(String[] args) throws IOException {
    String rawInput = Resources.toString(Resources.getResource("day02.txt"), StandardCharsets.UTF_8).strip();
    String preparedProgram = prepareFirstInput(rawInput);
    System.out.println(runProgram(preparedProgram));
    System.out.println(bruteForceFind(rawInput));
  }

  private static String prepareFirstInput(String input) {
    List<Integer> program = Arrays.stream(input.split(",")).map(Integer::valueOf).collect(Collectors.toList());
    program.set(1, 12);
    program.set(2, 2);
    return program.stream().map(Object::toString).collect(Collectors.joining(","));
  }

  public static String runProgram(String input) {
    int[] program = Arrays.stream(input.split(",")).mapToInt(Integer::valueOf).toArray();
    runProgram(program);
    return Arrays.stream(program).boxed().map(Object::toString).collect(Collectors.joining(","));
  }

  private static void runProgram(int[] program) {
    int pos = 0;
    while (program[pos] != 99) {
      int opCode = program[pos];
      int firstValuePos = program[pos + 1];
      int secondValuePos = program[pos + 2];
      int result;
      if (opCode == 1) {
        result = program[firstValuePos] + program[secondValuePos];
      } else if (opCode == 2) {
        result = program[firstValuePos] * program[secondValuePos];
      } else {
        throw new RuntimeException("unknown opCode " + opCode);
      }
      program[program[pos + 3]] = result;
      pos += 4;
    }
  }

  private static long bruteForceFind(String input) {
    int[] originalProgram = Arrays.stream(input.split(",")).mapToInt(Integer::valueOf).toArray();
    int noun = 0;
    int verb = 0;
    int[] program;
    while (true) {
      program = Arrays.copyOf(originalProgram, originalProgram.length);
      program[1] = noun;
      program[2] = verb;
      try {
        runProgram(program);
      } catch (Exception e) {
        // dont care
      }
      if (program[0] == SECOND_PART_TARGET)
        break;
      verb++;
      if (verb > 99) {
        noun++;
        verb = 0;
      }
      if (noun > 99) {
        throw new RuntimeException("no solution found");
      }
    }
    return 100 * noun + verb;
  }
}
