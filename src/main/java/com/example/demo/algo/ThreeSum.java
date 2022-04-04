package com.example.demo.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i !=
 * j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * <p>Notice that the solution set must not contain duplicate triplets.
 *
 * <p>Constraints:
 *
 * <p>0 <= nums.length <= 3000 <br>
 * -105 <= nums[i] <= 105
 */
public class ThreeSum {
  public static void main(String[] args) {
    ThreeSum threeSum = new ThreeSum();
    System.out.println(threeSum.threeSum(new int[] {-1,0,1,2,-1,-4}));
  }

  public List<List<Integer>> threeSum(int[] num) {
    Arrays.sort(num);
    List<List<Integer>> res = new LinkedList<>();
    for (int i = 0; i < num.length - 2; i++) {
      if (i == 0 || (num[i] != num[i - 1])) {
        int lo = i + 1;
        int hi = num.length - 1;
        int sum = -num[i];
        while (lo < hi) {
          if (num[lo] + num[hi] == sum) {
            res.add(Arrays.asList(num[i], num[lo], num[hi]));
            while (lo < hi && num[lo] == num[lo + 1]) lo++;
            while (lo < hi && num[hi] == num[hi - 1]) hi--;
            lo++;
            hi--;
          } else if (num[lo] + num[hi] < sum) lo++;
          else hi--;
        }
      }
    }
    return res;
  }
}
