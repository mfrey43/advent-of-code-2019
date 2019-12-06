package com.github.mfrey43.day01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.io.Resources;

public class Day01 {

  public static void main(String[] args) throws IOException {
    List<Long> masses = Resources.readLines(Resources.getResource("day01.txt"), StandardCharsets.UTF_8)
        .stream()
        .map(Long::valueOf)
        .collect(Collectors.toList());

    System.out.println("Simple sum: " + getSimpleSum(masses));
    System.out.println("Complex sum: " + getComplexSum(masses));
  }

  private static long getSimpleSum(List<Long> masses) {
    return masses.stream().mapToLong(Day01::calculateRequiredFuelForMass).sum();
  }

  private static long getComplexSum(List<Long> masses) {
    long totalFuelNeeded = 0;
    for (Long mass : masses) {
      long neededFuel = calculateRequiredFuelForMass(mass);
      while (neededFuel > 0) {
        totalFuelNeeded += neededFuel;
        neededFuel = calculateRequiredFuelForMass(neededFuel);
      }
    }
    return totalFuelNeeded;
  }

  private static long calculateRequiredFuelForMass(Long value) {
    return value / 3 - 2;
  }
}
