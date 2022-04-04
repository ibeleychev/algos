package com.example.demo.algo;

import org.springframework.util.StopWatch;

public class BinaryString {

  public static void main(String[] args) {
    System.out.println(192 >> 1);
    System.out.println(operations("1".repeat(200_000)));
  }

  private static int operations(String line) {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start("main");
    System.out.println("Started... ");
    int onesCount = 0;
    int[] array = new int[line.length()];
    for (int i = 0; i < line.length(); i++) {
      if (line.charAt(i) == 49) {
        array[i] = 1;
        onesCount++;
      } else {
        array[i] = 0;
      }
    }
    int operationsCount = 0;
    while (onesCount != 0) {
      if (array[line.length() - 1] == 1) {
        array[line.length() - 1] = 0;
        onesCount--;
      } else {
        System.arraycopy(array, 0, array, 1, array.length - 1);
        array[0] = 0;
      }
      operationsCount++;
    }

    stopWatch.stop();
    System.out.println("Finished... " + stopWatch.getTotalTimeSeconds() + " sec");

    return operationsCount;
  }
}
