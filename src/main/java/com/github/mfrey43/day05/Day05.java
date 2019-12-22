package com.github.mfrey43.day05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import com.google.common.io.Resources;

public class Day05 {

  public static void main(String[] args) throws IOException {
    String rawInput = Resources.toString(Resources.getResource("day05.txt"), StandardCharsets.UTF_8).strip();
    System.out.println(runProgram(rawInput));
  }

  public static int runProgram(String input) {
    int[] program = Arrays.stream(input.split(",")).mapToInt(Integer::valueOf).toArray();
    return runProgram(program, 1);
  }

  private static int runProgram(int[] program, int input) {
    int pos = 0;
    while (program[pos] != 99) {
      int firstValue = program[pos];
      int opCode = firstValue % 100;
      int param1Mode = (firstValue / 100) % 10;
      int param2Mode = (firstValue / 1000) % 10;
      int param1 = (param1Mode == 1) ? program[pos + 1] : program[program[pos + 1]];
      if (opCode == 1) {
        int param2 = (param2Mode == 1) ? program[pos + 2] : program[program[pos + 2]];
        program[program[pos + 3]] = param1 + param2;
        pos += 4;
      } else if (opCode == 2) {
        int param2 = (param2Mode == 1) ? program[pos + 2] : program[program[pos + 2]];
        program[program[pos + 3]] = param1 * param2;
        pos += 4;
      } else if (opCode == 3) {
        program[pos + 1] = input;
        pos += 2;
      } else if (opCode == 4) {
        System.out.println(program[pos + 1]);
        pos += 2;
      } else {
        pos += 4;
        //throw new RuntimeException("unknown opCode " + opCode);
      }
    }
    return -1;
  }
}
