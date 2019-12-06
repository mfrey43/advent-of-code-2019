package com.github.mfrey43.day02;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

class Day02Test {

  @Test
  void processInput() {
    assertThat(Day02.runProgram("1,0,0,0,99"), equalTo("2,0,0,0,99"));
    assertThat(Day02.runProgram("2,3,0,3,99"), equalTo("2,3,0,6,99"));
    assertThat(Day02.runProgram("2,4,4,5,99,0"), equalTo("2,4,4,5,99,9801"));
    assertThat(Day02.runProgram("1,1,1,4,99,5,6,0,99"), equalTo("30,1,1,4,2,5,6,0,99"));
  }
}
