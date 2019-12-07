package com.github.mfrey43.day03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.io.Resources;

public class Day03 {

  public static final int NEEDLE = 3;
  public static final int MAP_SIZE = 30000; // TODO find a more memory efficient solution, map?

  public static void main(String[] args) throws IOException {
    List<String> lines = Resources.readLines(Resources.getResource("day03.txt"), StandardCharsets.UTF_8)
        .stream()
        .map(String::strip)
        .collect(Collectors.toList());
    System.out.println("distanceToClosestIntersection: " + distanceToClosestIntersection(lines.get(0), lines.get(1)));
    System.out.println("combinedStepsToFirstIntersection: " + fewestCombinedStepsToIntersection(lines.get(0), lines.get(1)));
  }

  public static int distanceToClosestIntersection(String line1, String line2) {
    int[][] map = new int[MAP_SIZE][MAP_SIZE];
    int startPosX = MAP_SIZE / 2, posX = startPosX;
    int startPosY = MAP_SIZE / 2, posY = startPosY;
    map[startPosY][startPosX] = 8;

    // line 1
    String[] line1Steps = line1.split(",");
    for (String line1Step : line1Steps) {
      char direction = line1Step.charAt(0);
      int amount = Integer.parseInt(line1Step.substring(1));
      for (int i = 0; i < amount; i++) {
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
        map[posY][posX] = 1;
      }
    }

    // line 2
    posX = startPosX;
    posY = startPosY;
    String[] line2Steps = line2.split(",");
    for (String line1Step : line2Steps) {
      char direction = line1Step.charAt(0);
      int amount = Integer.parseInt(line1Step.substring(1));
      for (int i = 0; i < amount; i++) {
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
        if (map[posY][posX] == 1) {
          map[posY][posX] = NEEDLE;
        } else {
          map[posY][posX] = 2;
        }
      }
    }

    //printMap(map);
    return getShortestDistanceFromTo(map, startPosY, startPosX, NEEDLE);
  }

  private static int getShortestDistanceFromTo(int[][] map, int startPosY, int startPosX, int needle) {
    int radius = 1;
    int posY;
    int posX;
    int bestDistance = Integer.MAX_VALUE;
    while (radius < bestDistance) {
      for (int i = 0; i < radius; i++) {
        posY = startPosY - radius;
        posX = startPosX + i;
        if (map[posY][posX] == needle) {
          int distance = Math.abs(startPosY - posY) + Math.abs(startPosX - posX);
          if (distance < bestDistance) {
            bestDistance = distance;
            break;
          }
        }
        posX = startPosX - i;
        if (map[posY][posX] == needle) {
          int distance = Math.abs(startPosY - posY) + Math.abs(startPosX - posX);
          if (distance < bestDistance) {
            bestDistance = distance;
            break;
          }
        }

        posY = startPosY + i;
        posX = startPosX + radius;
        if (map[posY][posX] == needle) {
          int distance = Math.abs(startPosY - posY) + Math.abs(startPosX - posX);
          if (distance < bestDistance) {
            bestDistance = distance;
            break;
          }
        }
        posY = startPosY - i;
        if (map[posY][posX] == needle) {
          int distance = Math.abs(startPosY - posY) + Math.abs(startPosX - posX);
          if (distance < bestDistance) {
            bestDistance = distance;
            break;
          }
        }

        posY = startPosY + radius;
        posX = startPosX - i;
        if (map[posY][posX] == needle) {
          int distance = Math.abs(startPosY - posY) + Math.abs(startPosX - posX);
          if (distance < bestDistance) {
            bestDistance = distance;
            break;
          }
        }
        posX = startPosX + i;
        if (map[posY][posX] == needle) {
          int distance = Math.abs(startPosY - posY) + Math.abs(startPosX - posX);
          if (distance < bestDistance) {
            bestDistance = distance;
            break;
          }
        }

        posY = startPosY - i;
        posX = startPosX - radius;
        if (map[posY][posX] == needle) {
          int distance = Math.abs(startPosY - posY) + Math.abs(startPosX - posX);
          if (distance < bestDistance) {
            bestDistance = distance;
            break;
          }
        }
        posY = startPosY + i;
        if (map[posY][posX] == needle) {
          int distance = Math.abs(startPosY - posY) + Math.abs(startPosX - posX);
          if (distance < bestDistance) {
            bestDistance = distance;
            break;
          }
        }
      }
      radius++;
    }
    return bestDistance;
  }

  private static void printMap(int[][] map) {
    for (int[] row : map) {
      for (int i : row) {
        System.out.print(i);
      }
      System.out.println();
    }
  }

  public static int fewestCombinedStepsToIntersection(String line1, String line2) {
    int[][] map = new int[MAP_SIZE][MAP_SIZE];
    int startPosX = MAP_SIZE / 2, posX = startPosX;
    int startPosY = MAP_SIZE / 2, posY = startPosY;
    map[startPosY][startPosX] = 0;

    int fewestSumOfSteps = Integer.MAX_VALUE;

    // line 1
    String[] line1Steps = line1.split(",");
    int numberOfSteps = 0;
    for (String line1Step : line1Steps) {
      char direction = line1Step.charAt(0);
      int amount = Integer.parseInt(line1Step.substring(1));
      for (int i = 0; i < amount; i++) {
        numberOfSteps++;
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
        map[posY][posX] = numberOfSteps;
      }
    }

    // line 2
    posX = startPosX;
    posY = startPosY;
    String[] line2Steps = line2.split(",");
    numberOfSteps = 0;
    for (String line1Step : line2Steps) {
      char direction = line1Step.charAt(0);
      int amount = Integer.parseInt(line1Step.substring(1));
      for (int i = 0; i < amount; i++) {
        numberOfSteps++;
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
        if (map[posY][posX] > 0) {
          int sum = numberOfSteps + map[posY][posX];
          if (sum < fewestSumOfSteps) {
            fewestSumOfSteps = sum;
          }
        }
      }
    }
    return fewestSumOfSteps;
  }
}
