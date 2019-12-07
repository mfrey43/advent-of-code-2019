package com.github.mfrey43.day03;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

class Day03Test {

  @Test
  void distanceToClosestIntersection() {
    assertThat(Day03.distanceToClosestIntersection("R75,D30,R83,U83,L12,D49,R71,U7,L72","U62,R66,U55,R34,D71,R55,D58,R83"), equalTo(159));
    assertThat(Day03.distanceToClosestIntersection("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51","U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"), equalTo(135));
  }

  @Test
  void fewestCombinedStepsToIntersection() {
    assertThat(Day03.fewestCombinedStepsToIntersection("R75,D30,R83,U83,L12,D49,R71,U7,L72","U62,R66,U55,R34,D71,R55,D58,R83"), equalTo(610));
    assertThat(Day03.fewestCombinedStepsToIntersection("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51","U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"), equalTo(410));
  }
}