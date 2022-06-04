package com.example.demo.algo;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class AllCharactersUnique {
  public static void main(String[] args) {
    System.out.println(stringContainsOnlyUnique("alphabet+$".repeat(10_000)));
    System.out.println(stringContainsOnlyUniqueWithDataStructure("alphabet+$".repeat(10_000)));
  }

  private static boolean stringContainsOnlyUnique(String line) {
    long start = System.currentTimeMillis();
    if (line == null || line.isEmpty()) {
      return false;
    }
    char[] chars = line.toCharArray();
    Arrays.sort(chars);
    for(int i = 0; i < chars.length - 1; i++) {
      if(chars[i] == chars[i + 1]) {
        System.out.println((System.currentTimeMillis() - start) + "ms");
        return false;
      }
    }
    return true;
  }

  private static boolean stringContainsOnlyUniqueWithDataStructure(String line) {
    long start = System.currentTimeMillis();
    if (line == null || line.isEmpty()) {
      return false;
    }
    Set<Integer> uniqueChars = line.chars().boxed().collect(Collectors.toSet());

    System.out.println((System.currentTimeMillis() - start) + "ms");
    return line.length() == uniqueChars.size();
  }
}
