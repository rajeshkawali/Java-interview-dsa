package com.rajeshkawali.dsa.string;

import java.util.*;

public class SubsequencesOfString {

    /*
     * Problem:
     * --------
     * Generate all subsequences of a given string without recursion.
     * Example: input = "abc"
     * Output = ["", "a", "b", "c", "ab", "ac", "bc", "abc"]
     *
     * Variants:
     * 1. Bitmask method
     * 2. Iterative expansion (build subsequences step by step)
     * 3. Queue-based BFS style
     *
     * Time Complexity: O(2^n * n) for all variants
     * Space Complexity: O(2^n * n) to store subsequences
     */

    // 1. Bitmask method
    public static List<String> subsequencesBitmask(String str) {
        List<String> result = new ArrayList<>();
        int n = str.length();
        int total = 1 << n; // 2^n subsequences
        for (int mask = 0; mask < total; mask++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    sb.append(str.charAt(i));
                }
            }
            result.add(sb.toString());
        }
        return result;
    }

    // 2. Iterative expansion
    public static List<String> subsequencesIterative(String str) {
        List<String> result = new ArrayList<>();
        result.add(""); // start with empty subsequence
        for (char c : str.toCharArray()) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                result.add(result.get(i) + c);
            }
        }
        return result;
    }

    // 3. Queue-based BFS style
    public static List<String> subsequencesQueue(String str) {
        List<String> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.add("");
        for (char c : str.toCharArray()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                queue.add(current);          // exclude
                queue.add(current + c);      // include
            }
        }
        result.addAll(queue);
        return result;
    }

    public static void main(String[] args) {
        String input = "abc";
        System.out.println("============================================");
        System.out.println("Subsequences (Bitmask): " + subsequencesBitmask(input));
        System.out.println("============================================");
        System.out.println("Subsequences (Iterative Expansion): " + subsequencesIterative(input));
        System.out.println("============================================");
        System.out.println("Subsequences (Queue BFS): " + subsequencesQueue(input));
        System.out.println("============================================");
    }
}
