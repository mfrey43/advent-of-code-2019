package com.github.mfrey43.day03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.io.Resources;

public class Day03 {

  public static void main(String[] args) throws IOException {
    List<String> lines = Resources.readLines(Resources.getResource("day03.txt"), StandardCharsets.UTF_8)
        .stream()
        .map(String::strip)
        .collect(Collectors.toList());
    System.out.println("distanceToClosestIntersection: " + distanceToClosestIntersection(lines.get(0), lines.get(1)));
    System.out.println(
        "combinedStepsToFirstIntersection: " + fewestCombinedStepsToIntersection(lines.get(0), lines.get(1)));
  }

  public static int distanceToClosestIntersection(String line1, String line2) {
    Set<Point> dataPoints1 = getDataPoints(line1);
    Set<Point> dataPoints2 = getDataPoints(line2);

    dataPoints1.retainAll(dataPoints2);

    return getShortestDistanceToIntersection(dataPoints1);
  }

  public static int fewestCombinedStepsToIntersection(String line1, String line2) {
    Set<Point> dataPoints1 = getDataPoints(line1);
    Set<Point> dataPoints2 = getDataPoints(line2);

    dataPoints1.retainAll(dataPoints2);
    dataPoints2.retainAll(dataPoints1);

    return getFewestStepsToIntersection(dataPoints1, dataPoints2);
  }

  private static int getShortestDistanceToIntersection(Set<Point> points) {
    int minimumDistance = Integer.MAX_VALUE;
    for (Point point : points) {
      int distance = Math.abs(point.getX()) + Math.abs(point.getY());
      if (distance < minimumDistance) {
        minimumDistance = distance;
      }
    }
    return minimumDistance;
  }

  private static Set<Point> getDataPoints(String input) {
    Set<Point> points = new HashSet<>();
    int posX = 0;
    int posY = 0;
    int steps = 0;
    String[] line1Steps = input.split(",");
    for (String line1Step : line1Steps) {
      char direction = line1Step.charAt(0);
      int amount = Integer.parseInt(line1Step.substring(1));
      for (int i = 0; i < amount; i++) {
        steps++;
        switch (direction) {
          case 'U':
            posY--;
            break;
          case 'R':
            posX++;
            break;
          case 'D':
            posY++;
            break;
          case 'L':
            posX--;
            break;
        }
        points.add(new Point(posX, posY, steps));
      }
    }
    return points;
  }

  private static int getFewestStepsToIntersection(Set<Point> dataPoints1, Set<Point> dataPoints2) {
    Map<Point, Integer> map = dataPoints1.stream().collect(Collectors.toMap(point -> point, Point::getSteps));
    dataPoints2.forEach(point -> map.merge(point, point.getSteps(), Integer::sum));

    return map.values().stream().mapToInt(Integer::intValue).sorted().findFirst().getAsInt();
  }
}
