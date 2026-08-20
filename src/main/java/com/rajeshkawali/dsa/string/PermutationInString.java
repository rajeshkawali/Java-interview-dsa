package com.rajeshkawali.dsa.string;

import java.util.*;

public class PermutationInString {

	/*
     * Problem:
     * --------
     * Given two strings s1 and s2, check if s2 contains a substring
     * that is a permutation of s1.
     *
     * Example:
     * s1 = "abc", s2 = "ridbcaoedo"
     * Output: true (because abc, acb, bac, "bca", cab, cba is a permutations of "abc")
     *
     * Variants:
     * 1. Brute Force (check all substrings)
     * 2. Sorting Approach
     * 3. Sliding Window + Frequency Count (Optimal)
     */

    // 1. Brute Force
    // Time: O(n*m), Space: O(1)
    public static boolean checkByBruteForce(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m > n) return false;

        int[] s1Count = new int[26];
        for (char c : s1.toCharArray()) s1Count[c - 'a']++;

        for (int i = 0; i <= n - m; i++) {
            int[] windowCount = new int[26];
            for (int j = i; j < i + m; j++) {
                windowCount[s2.charAt(j) - 'a']++;
            }
            if (Arrays.equals(s1Count, windowCount)) return true;
        }
        return false;
    }

    // 2. Sorting Approach
    // Time: O(n * m log m), Space: O(m)
    public static boolean checkBySorting(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m > n) return false;

        char[] s1Chars = s1.toCharArray();
        Arrays.sort(s1Chars);
        String sortedS1 = new String(s1Chars);

        for (int i = 0; i <= n - m; i++) {
            char[] subChars = s2.substring(i, i + m).toCharArray();
            Arrays.sort(subChars);
            if (sortedS1.equals(new String(subChars))) return true;
        }
        return false;
    }

    // 3. Sliding Window + Frequency Count (Optimal)
    // Time: O(n), Space: O(26)
    public static boolean checkBySlidingWindow(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] s1Count = new int[26];
        int[] s2Count = new int[26];

        for (char c : s1.toCharArray()) s1Count[c - 'a']++;

        for (int i = 0; i < s2.length(); i++) {
            s2Count[s2.charAt(i) - 'a']++;

            if (i >= s1.length()) {
                s2Count[s2.charAt(i - s1.length()) - 'a']--;
            }

            if (Arrays.equals(s1Count, s2Count)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "ridbcaoedo";
        System.out.println("=================================================================");
        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
        System.out.println("=================================================================");
        System.out.println("1. Brute Force: " + checkByBruteForce(s1, s2));
        System.out.println("=================================================================");
        System.out.println("2. Sorting Approach: " + checkBySorting(s1, s2));
        System.out.println("=================================================================");
        System.out.println("3. Sliding Window (Optimal): " + checkBySlidingWindow(s1, s2));
        System.out.println("=================================================================");
    }
}

/*
Permutation in String:
- Brute Force → O(n*m), simple but slow.
- Sorting → O(n*m log m), better but still heavy.
- Sliding Window + Frequency → O(n), best and interview-ready.

*/