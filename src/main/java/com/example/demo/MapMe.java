package com.example.demo;

import java.util.List;

public class MapMe {
//  public static void main(String[] args) {
//    String toParse = "has( asd, ) )  AND ( has(sss,,5)";
//    Pattern pattern = Pattern.compile("has\\([^)]+\\)");
//    Matcher matcher = pattern.matcher(toParse);
//    while (matcher.find()) {
//      System.out.println(matcher.group());
//    }
//  }

  public static void main(String[] args) {
    System.out.println(minX(List.of(-5, 4, -2, 3, 1, -1, -6, -1, 0, 5)));
  }

  public static int minX(List<Integer> arr) {
    // Write your code here
    // according to constraints
    int initialX = - arr.get(0) + 1;
    int sum = initialX;
    for (int i = 0; i < arr.size(); i++) {
      sum += arr.get(i);
      if (sum < 1) {
        int intermediateX  = initialX + arr.get(i);
        if (intermediateX > initialX) {
          initialX = intermediateX;
        } else {
          initialX = initialX - sum + 1;
        }
        sum = initialX;
        i = - 1;
      }
    }
    return initialX;
  }
}
