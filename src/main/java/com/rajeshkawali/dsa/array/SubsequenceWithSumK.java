package com.rajeshkawali.dsa.array;

import java.util.*;

public class SubsequenceWithSumK {

    /*
     * Problem:
     * --------
     * Given an array and integer K, find subsequences whose sum = K.
     *
     * Variants:
     * 1. Print all subsequences with sum K
     * 2. Print one subsequence with sum K
     * 3. Count number of subsequences with sum K
     *
     * Approaches:
     * -----------
     * - Bitmasking: generate all subsets using binary representation
     * - Iterative expansion: build subsequences step by step
     *
     * Time Complexity:
     * ----------------
     * O(2^n * n) (each subset generation + sum calculation)
     *
     * Space Complexity:
     * -----------------
     * O(2^n * n) for storing subsequences
     */

    // 1. Print all subsequences with sum K (Bitmasking)
    public static void printAllSubsequences(int[] arr, int K) {
        int n = arr.length;
        int total = 1 << n; // 2^n subsets
        for (int mask = 0; mask < total; mask++) {
            List<Integer> subseq = new ArrayList<>();
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subseq.add(arr[i]);
                    sum += arr[i];
                }
            }
            if (sum == K) {
                System.out.println(subseq);
            }
        }
    }

    // 2. Print one subsequence with sum K (stop early)
    public static void printOneSubsequence(int[] arr, int K) {
        int n = arr.length;
        int total = 1 << n;
        for (int mask = 0; mask < total; mask++) {
            List<Integer> subseq = new ArrayList<>();
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subseq.add(arr[i]);
                    sum += arr[i];
                }
            }
            if (sum == K) {
                System.out.println("One subsequence: " + subseq);
                return; // stop after first
            }
        }
    }

    // 3. Count number of subsequences with sum K
    public static int countSubsequences(int[] arr, int K) {
        int n = arr.length;
        int total = 1 << n;
        int count = 0;
        for (int mask = 0; mask < total; mask++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum += arr[i];
                }
            }
            if (sum == K) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1};
        int K = 2;
        System.out.println("============================================");
        System.out.println("All subsequences with sum " + K + ":");
        printAllSubsequences(arr, K);
        System.out.println("============================================");
        System.out.println("One subsequence with sum " + K + ":");
        printOneSubsequence(arr, K);
        System.out.println("============================================");
        System.out.println("Count of subsequences with sum " + K + ":");
        System.out.println(countSubsequences(arr, K));
        System.out.println("============================================");
    }
}

/*
Short Note
Bitmasking → brute force, generates all subsets using binary representation.
Print all → check every subset, print if sum matches.
Print one → stop after finding the first valid subset.
Count → increment counter instead of storing subsequences.

Time Complexity: O(2^n * n)
Space Complexity: O(2^n * n)
 */
