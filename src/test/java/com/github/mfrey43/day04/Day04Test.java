package com.github.mfrey43.day04;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

class Day04Test {

  @Test
  void isValidPart1() {
    assertThat(Day04.isValidPart1(111111), is(true));
    assertThat(Day04.isValidPart1(223450), is(false));
    assertThat(Day04.isValidPart1(123789), is(false));
  }

  @Test
  void isValidPart2() {
    assertThat(Day04.isValidPart2(112233), is(true));
    assertThat(Day04.isValidPart2(123444), is(false));
    assertThat(Day04.isValidPart2(111122), is(true));
  }
}
