package com.example.demo.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any
 * order.
 *
 * <p>An Anagram is a word or phrase formed by rearranging the letters of a different word or
 * phrase, typically using all the original letters exactly once.
 *
 * <p><b>Constraints:</b>
 * <li>1 <= strs.length <= 104
 * <li>0 <= strs[i].length <= 100 strs[i] consists of lowercase English letters.
 */
public class GroupAnagrams {
  public static void main(String[] args) {
    GroupAnagrams gr = new GroupAnagrams();
    System.out.println(gr.groupAnagrams(new String[] {"", ""}));
  }

  public List<List<String>> groupAnagrams(String[] strs) {
    var result = new ArrayList<List<String>>();
    Map<String, List<String>> anagrams = new HashMap<>();
    for (String str : strs) {
      final var unsortedStr = str.toCharArray();
      Arrays.sort(unsortedStr);
      var sortedStr = new String(unsortedStr);
      if (anagrams.containsKey(sortedStr)) {
        anagrams.get(sortedStr).add(str);
      } else {
        var set = new ArrayList<String>();
        set.add(str);
        anagrams.put(sortedStr, set);
      }
    }

    for (List<String> value : anagrams.values()) {
      result.add(new ArrayList<>(value));
    }

    return result;
  }

  public List<List<String>> notMine(String[] strs) {
    if (strs.length == 0) return new ArrayList<>();
    Map<String, List<String>> ans = new HashMap<>();
    int[] count = new int[26];
    for (String s : strs) {
      Arrays.fill(count, 0);
      for (char c : s.toCharArray()) count[c - 'a']++;

      StringBuilder sb = new StringBuilder("");
      for (int i = 0; i < 26; i++) {
        sb.append('#');
        sb.append(count[i]);
      }
      String key = sb.toString();
      ans.computeIfAbsent(key, k -> new ArrayList<>());
      ans.get(key).add(s);
    }
    return new ArrayList<>(ans.values());
  }
}
