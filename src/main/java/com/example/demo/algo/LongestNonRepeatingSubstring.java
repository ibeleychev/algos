package com.example.demo.algo;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string <b>s</b>, find the length of the longest substring without repeating characters.
 *
 * <p><b>Constraints:</b>
 * <li>0 <= s.length <= 5 * 104
 * <li>s consists of English letters, digits, symbols and spaces.
 */
public class LongestNonRepeatingSubstring {
  public static void main(String[] args) {
    LongestNonRepeatingSubstring longest = new LongestNonRepeatingSubstring();
    System.out.println(longest.lengthOfLongestSubstring("kaskj"));
  }

  public int lengthOfLongestSubstring(String s) {
    int n = s.length(), ans = 0;
    Map<Character, Integer> map = new HashMap<>(); // current index of character
    // try to extend the range [i, j]
    for (int j = 0, i = 0; j < n; j++) {
      if (map.containsKey(s.charAt(j))) {
        i = Math.max(map.get(s.charAt(j)), i);
      }
      ans = Math.max(ans, j - i + 1);
      map.put(s.charAt(j), j + 1);
    }
    return ans;
  }
}
