package com.rajeshkawali.dsa.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubsequencesRecursion {

    /*
     * Problem:
     * --------
     * Generate all subsequences of a given string using recursion.
     * Example: input = "abc"
     * Output = ["", "a", "b", "c", "ab", "ac", "bc", "abc"]
     *
     * Variants:
     * 1. Standard recursion (include/exclude choice)
     * 2. Helper with index
     * 3. Tail recursion style
     * 4. Divide & Conquer
     *
     * Time Complexity: O(2^n) (each character has two choices: include/exclude)
     * Space Complexity: O(2^n * n) for storing subsequences + O(n) recursion stack
     */

    // 1. Standard recursion (include/exclude choice)
    public static void subsequences(String str, String current, int index, List<String> result) {
        if (index == str.length()) {
            result.add(current);
            return;
        }
        // Exclude current char
        subsequences(str, current, index + 1, result);
        // Include current char
        subsequences(str, current + str.charAt(index), index + 1, result);
    }

    // 2. Helper with index (cleaner wrapper)
    public static List<String> subsequencesHelper(String str) {
        List<String> result = new ArrayList<>();
        subsequences(str, "", 0, result);
        return result;
    }

    // 3. Tail recursion style (carry result forward)
    public static void subsequencesTail(String str, int index, String current, List<String> result) {
        if (index == str.length()) {
            result.add(current);
            return;
        }
        subsequencesTail(str, index + 1, current, result); // exclude
        subsequencesTail(str, index + 1, current + str.charAt(index), result); // include
    }

    // 4. Divide & Conquer
    public static List<String> subsequencesDivideConquer(String str) {
        if (str.isEmpty()) {
            List<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        char first = str.charAt(0);
        List<String> rest = subsequencesDivideConquer(str.substring(1));
        List<String> result = new ArrayList<>(rest);
        for (String s : rest) {
            result.add(first + s);
        }
        return result;
    }

    // Wrapper method to generate subsequences
    public static List<String> subsequencesOfString(String str) {
        List<String> ans = new ArrayList<>();
        StringBuilder output = new StringBuilder();
        int index = 0;
        printSubsequencesOfString(str, output, index, ans);
        Collections.sort(ans); // optional: sort subsequences lexicographically
        return ans;
    }

    // Recursive method to generate subsequences
    public static void printSubsequencesOfString(String str, StringBuilder output, int index, List<String> result) {
        // Base case: if index reaches end of string, add current subsequence
        if (index >= str.length()) {
            result.add(output.toString());
            return;
        }
        char ch = str.charAt(index);
        // Choice 1: Include current character
        output.append(ch);
        printSubsequencesOfString(str, output, index + 1, result);
        // Backtrack: remove last character before exploring exclusion
        output.deleteCharAt(output.length() - 1);
        // Choice 2: Exclude current character
        printSubsequencesOfString(str, output, index + 1, result);
    }

    public static void main(String[] args) {
        String input = "abc";
        System.out.println("============================================");
        // 1 & 2. Standard recursion with helper
        List<String> result1 = subsequencesHelper(input);
        System.out.println("Subsequences (Standard recursion): " + result1);
        System.out.println("============================================");
        // 3. Tail recursion
        List<String> result2 = new ArrayList<>();
        subsequencesTail(input, 0, "", result2);
        System.out.println("Subsequences (Tail recursion): " + result2);
        System.out.println("============================================");
        // 4. Divide & Conquer
        List<String> result3 = subsequencesDivideConquer(input);
        System.out.println("Subsequences (Divide & Conquer): " + result3);
        System.out.println("============================================");
        List<String> result4 = subsequencesOfString(input);
        System.out.println("Subsequences Of String: " + result4);
        System.out.println("============================================");
    }
}

/*
Short Note:
Standard recursion → classic include/exclude choice at each step.
Helper with index → cleaner wrapper for standard recursion.
Tail recursion → carries current subsequence forward, avoids backtracking.
Divide & Conquer → splits string into first char + rest, then merges results.
*/