package com.github.mfrey43.day04;

import java.util.ArrayList;
import java.util.List;

public class Day04 {

  static int min = 272091;
  static int max = 815432;

  public static void main(String[] args) {
    List<Integer> validNumbersPart1 = new ArrayList<>();
    for (int i = min; i <= max; i++) {
      if (isValidPart1(i)) {
        validNumbersPart1.add(i);
      }
    }
    System.out.println("part 1: " + validNumbersPart1.size());
    System.out.println("part 2: " + validNumbersPart1.stream().filter(Day04::isValidPart2).count());
  }

  public static boolean isValidPart1(int i) {
    int[] digits = getDigits(i);
    return hasDouble(digits) && digitsOnlyIncreaseLeftToRight(digits);
  }

  public static boolean isValidPart2(int i) {
    int[] digits = getDigits(i);
    return hasDoubleNotInLargerGroup(digits);
  }

  private static boolean hasDouble(int[] digits) {
    for (int i = 1; i < digits.length; i++) {
      if (digits[i - 1] == digits[i]) {
        return true;
      }
    }
    return false;
  }

  private static boolean hasDoubleNotInLargerGroup(int[] digits) {
    for (int i = 1; i < digits.length; i++) {
      if (digits[i - 1] == digits[i] && !(i >= 2 && digits[i - 2] == digits[i]
          || i <= digits.length - 2 && digits[i + 1] == digits[i])) {
        return true;
      }
    }
    return false;
  }

  private static boolean digitsOnlyIncreaseLeftToRight(int[] digits) {
    for (int i = 1; i < digits.length; i++) {
      if (digits[i - 1] > digits[i]) {
        return false;
      }
    }
    return true;
  }

  private static int[] getDigits(int num) {
    int[] digits = new int[(int) (Math.log10(num) + 1)];
    int pos = digits.length - 1;
    while (num > 0) {
      digits[pos] = num % 10;
      num /= 10;
      pos--;
    }
    return digits;
  }
}
