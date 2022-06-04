package com.example.demo.algo;

/** Java program to print all permutations of a given string */
public class StringPermutations {
  public static void main(String[] args) {
    String str = "ABC";
    int n = str.length();
    StringPermutations permutation = new StringPermutations();
    permutation.permute(str, 0, n - 1);
  }

  private void permute(String input, int start, int end) {
    if (start == end) {
      System.out.println(input);
    } else {
      for (int i = start; i <= end; i++) {
        input = swap(input, start, i);
        permute(input, start + 1, end);
        input = swap(input, start, i);
      }
    }
  }

  private String swap(String line, int firstIndex, int secondIndex) {
    final var chars = line.toCharArray();
    char temp = chars[firstIndex];
    chars[firstIndex] = chars[secondIndex];
    chars[secondIndex] = temp;
    return new String(chars);
  }
}
