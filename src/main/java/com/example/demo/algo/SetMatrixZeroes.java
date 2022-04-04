package com.example.demo.algo;

import java.util.Arrays;

/**
 * Given an m x n integer matrix, if an element is 0, set its entire row and column to 0's.
 *
 * <p>You must do it in place.
 *
 * <p>Constraints:
 *
 * <p>m == matrix.length n == matrix[0].length 1 <= m, n <= 200 -231 <= matrix[i][j] <= 231 - 1 <br>
 * <br>
 *
 * <p>Follow up:
 *
 * <p>
 * <li>A straightforward solution using O(mn) space is probably a bad idea. <br>
 * <li>A simple improvement uses O(m + n) space, but still not the best solution. <br>
 * <li>Could you devise a constant space solution?
 */
public class SetMatrixZeroes {
  public static void main(String[] args) {
    var matrix = new SetMatrixZeroes();
    final var input = new int[][] {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
    matrix.setZeroes(input);
    System.out.println(Arrays.deepToString(input));
  }

  public void setZeroes(int[][] matrix) {
    var zeroColumn = false;
    for (int i = 0; i < matrix.length; i++) {

      if (matrix[i][0] == 0) {
        zeroColumn = true;
      }

      for (int j = 1; j < matrix[i].length; j++) {
        if (matrix[i][j] == 0) {
          matrix[0][j] = 0;
          matrix[i][0] = 0;
        }
      }
    }

    for (int i = 1; i < matrix.length; i++) {
      for (int j = 1; j < matrix[0].length; j++) {
        if (matrix[i][0] == 0 || matrix[0][j] == 0) {
          matrix[i][j] = 0;
        }
      }
    }

    // See if the first row needs to be set to zero as well
    if (matrix[0][0] == 0) {
      for (int j = 0; j < matrix[0].length; j++) {
        matrix[0][j] = 0;
      }
    }

    // See if the first column needs to be set to zero as well
    if (zeroColumn) {
      for (int i = 0; i < matrix.length; i++) {
        matrix[i][0] = 0;
      }
    }
  }
}
