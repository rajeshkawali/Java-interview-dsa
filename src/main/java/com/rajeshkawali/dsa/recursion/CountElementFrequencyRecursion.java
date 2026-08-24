package com.rajeshkawali.dsa.recursion;

import java.util.HashMap;
import java.util.Map;

public class CountElementFrequencyRecursion {

    /*
     * Problem:
     * --------
     * Count how many times elements appear in an array using recursion.
     *
     * Variants:
     * 1. Count frequency of a single target element
     * 2. Count frequency of all elements using Map
     * 3. Count frequency using Divide & Conquer
     * 4. Tail recursion style
     */

    // 1. Count frequency of a single target element
    // Time: O(n), Space: O(n)
    public static int countTarget(int[] arr, int index, int target) {
        if (index >= arr.length) return 0;
        int count = (arr[index] == target) ? 1 : 0;
        return count + countTarget(arr, index + 1, target);
    }

    // 2. Count frequency of all elements using Map
    // Time: O(n), Space: O(n)
    public static void countAll(int[] arr, int index, Map<Integer, Integer> freqMap) {
        if (index >= arr.length) return;
        freqMap.put(arr[index], freqMap.getOrDefault(arr[index], 0) + 1);
        countAll(arr, index + 1, freqMap);
    }

    // 3. Divide & Conquer frequency count for a single target
    // Time: O(n), Space: O(log n)
    public static int countTargetDivideConquer(int[] arr, int left, int right, int target) {
        if (left > right) return 0;
        if (left == right) return (arr[left] == target) ? 1 : 0;
        int mid = left + (right - left) / 2;
        int leftCount = countTargetDivideConquer(arr, left, mid, target);
        int rightCount = countTargetDivideConquer(arr, mid + 1, right, target);
        return leftCount + rightCount;
    }

    // 4. Tail recursion style for single target
    // Time: O(n), Space: O(n)
    public static int countTargetTail(int[] arr, int index, int target, int currentCount) {
        if (index >= arr.length) return currentCount;
        if (arr[index] == target) currentCount++;
        return countTargetTail(arr, index + 1, target, currentCount);
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 2, 5, 3, 2, 7, 5};
        System.out.println("============================================");
        // 1. Single target
        System.out.println("Count of 2: " + countTarget(arr, 0, 2));
        System.out.println("============================================");
        // 2. All elements
        Map<Integer, Integer> freqMap = new HashMap<>();
        countAll(arr, 0, freqMap);
        System.out.println("Frequency of all elements: " + freqMap);
        System.out.println("============================================");
        // 3. Divide & Conquer
        System.out.println("Count of 5 (Divide & Conquer): " +
                countTargetDivideConquer(arr, 0, arr.length - 1, 5));
        System.out.println("============================================");
        // 4. Tail recursion
        System.out.println("Count of 3 (Tail Recursion): " +
                countTargetTail(arr, 0, 3, 0));
        System.out.println("============================================");
    }
}
