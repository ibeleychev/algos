package com.example.demo.algo;

/**
 * Given a string <b>s</b>, return the longest palindromic substring in <b>s</b>.
 *
 * <p><b>Constraints:</b>
 * <li>1 <= s.length <= 1000
 * <li><b>s</b> consists of only digits and English letters.
 */
public class LongestPalindromicSubstring {

  public static void main(String[] args) {
    var longest = new LongestPalindromicSubstring();
    System.out.println(longest.longestPalindrome("babad"));
    System.out.println(longest.longestPalindrome("cbbd"));
  }

  public String longestPalindrome(String s) {
    if (s == null || s.length() < 1) return "";
    int start = 0;
    int end = 0;
    for (int i = 0; i < s.length(); i++) {
      int len1 = expandAroundCenter(s, i, i);
      int len2 = expandAroundCenter(s, i, i + 1);
      int len = Math.max(len1, len2);
      if (len > end - start) {
        start = i - (len - 1) / 2;
        end = i + len / 2;
      }
    }
    return s.substring(start, end + 1);
  }

  private int expandAroundCenter(String s, int left, int right) {
    int l = left;
    int r = right;
    while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
      l--;
      r++;
    }
    return r - l - 1;
  }
}
