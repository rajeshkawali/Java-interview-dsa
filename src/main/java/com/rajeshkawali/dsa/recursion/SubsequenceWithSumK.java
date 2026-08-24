package com.rajeshkawali.dsa.recursion;

import java.util.*;

public class SubsequenceWithSumK {

    /*
     * Problem:
     * --------
     * Given an array and an integer K, print all subsequences whose sum equals K.
     *
     * Example:
     * arr = [1,2,1], K = 2
     * Output = [ [1,1], [2] ]
     *
     * Variants:
     * 1. Print all subsequences with sum K
     * 2. Print only one subsequence with sum K
     * 3. Count number of subsequences with sum K
     *
     * Time Complexity:
     * ----------------
     * O(2^n) (each element has two choices: include/exclude)
     *
     * Space Complexity:
     * -----------------
     * O(n) recursion stack + storage for subsequences
     */

    // 1. Print all subsequences with sum K
    public static void printAllSubsequences(int[] arr, int index, List<Integer> current, int sum, int K) {
        if (index == arr.length) {
            if (sum == K) {
                System.out.println(current);
            }
            return;
        }

        // Include current element
        current.add(arr[index]);
        printAllSubsequences(arr, index + 1, current, sum + arr[index], K);

        // Exclude current element (backtrack)
        current.remove(current.size() - 1);
        printAllSubsequences(arr, index + 1, current, sum, K);
    }

    // 2. Print only one subsequence with sum K
    public static boolean printOneSubsequence(int[] arr, int index, List<Integer> current, int sum, int K) {
        if (index == arr.length) {
            if (sum == K) {
                System.out.println("One subsequence: " + current);
                return true;
            }
            return false;
        }

        // Include
        current.add(arr[index]);
        if (printOneSubsequence(arr, index + 1, current, sum + arr[index], K)) return true;

        // Exclude
        current.remove(current.size() - 1);
        if (printOneSubsequence(arr, index + 1, current, sum, K)) return true;

        return false;
    }

    // 3. Count number of subsequences with sum K
    public static int countSubsequences(int[] arr, int index, int sum, int K) {
        if (index == arr.length) {
            return (sum == K) ? 1 : 0;
        }

        // Include
        int include = countSubsequences(arr, index + 1, sum + arr[index], K);

        // Exclude
        int exclude = countSubsequences(arr, index + 1, sum, K);

        return include + exclude;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1};
        int K = 2;
        System.out.println("============================================");
        System.out.println("All subsequences with sum " + K + ":");
        printAllSubsequences(arr, 0, new ArrayList<>(), 0, K);
        System.out.println("============================================");
        System.out.println("One subsequence with sum " + K + ":");
        printOneSubsequence(arr, 0, new ArrayList<>(), 0, K);
        System.out.println("============================================");
        System.out.println("Count of subsequences with sum " + K + ":");
        System.out.println(countSubsequences(arr, 0, 0, K));
        System.out.println("============================================");
    }
}

/*
Short Note:
Print all subsequences → explores all include/exclude paths.
Print one subsequence → stops recursion once a valid subsequence is found.
Count subsequences → returns integer count instead of storing subsequences.
Time Complexity: O(2^n)
Space Complexity: O(n) recursion stack
*/