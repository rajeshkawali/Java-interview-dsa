package com.rajeshkawali.dsa.recursion;

public class MaxMinRecursion {

    /*
     * Problem:
     * --------
     * Find maximum and minimum element in an array using recursion.
     *
     * Variants:
     * 1. Linear recursion
     * 2. Divide & Conquer recursion
     * 3. Tail recursion style
     * 4. Recursion with accumulator
     */

    // 1. Linear recursion (Max)
    public static int findMaxLinear(int[] arr, int index) {
        if (index == arr.length - 1) return arr[index];
        int maxRest = findMaxLinear(arr, index + 1);
        return Math.max(arr[index], maxRest);
    }

    // 1. Linear recursion (Min)
    public static int findMinLinear(int[] arr, int index) {
        if (index == arr.length - 1) return arr[index];
        int minRest = findMinLinear(arr, index + 1);
        return Math.min(arr[index], minRest);
    }

    // 2. Divide & Conquer recursion (Max)
    public static int findMaxDivideConquer(int[] arr, int left, int right) {
        if (left == right) return arr[left];
        int mid = left + (right - left) / 2;
        int maxLeft = findMaxDivideConquer(arr, left, mid);
        int maxRight = findMaxDivideConquer(arr, mid + 1, right);
        return Math.max(maxLeft, maxRight);
    }

    // 2. Divide & Conquer recursion (Min)
    public static int findMinDivideConquer(int[] arr, int left, int right) {
        if (left == right) return arr[left];
        int mid = left + (right - left) / 2;
        int minLeft = findMinDivideConquer(arr, left, mid);
        int minRight = findMinDivideConquer(arr, mid + 1, right);
        return Math.min(minLeft, minRight);
    }

    // 3. Tail recursion style (Max)
    public static int findMaxTail(int[] arr, int index, int currentMax) {
        if (index == arr.length) return currentMax;
        currentMax = Math.max(currentMax, arr[index]);
        return findMaxTail(arr, index + 1, currentMax);
    }

    // 3. Tail recursion style (Min)
    public static int findMinTail(int[] arr, int index, int currentMin) {
        if (index == arr.length) return currentMin;
        currentMin = Math.min(currentMin, arr[index]);
        return findMinTail(arr, index + 1, currentMin);
    }

    // 4. Accumulator wrapper (Max)
    public static int findMaxAccumulator(int[] arr) {
        return findMaxAccumulatorHelper(arr, 0, Integer.MIN_VALUE);
    }

    private static int findMaxAccumulatorHelper(int[] arr, int index, int currentMax) {
        if (index == arr.length) return currentMax;
        currentMax = Math.max(currentMax, arr[index]);
        return findMaxAccumulatorHelper(arr, index + 1, currentMax);
    }

    // 4. Accumulator wrapper (Min)
    public static int findMinAccumulator(int[] arr) {
        return findMinAccumulatorHelper(arr, 0, Integer.MAX_VALUE);
    }

    private static int findMinAccumulatorHelper(int[] arr, int index, int currentMin) {
        if (index == arr.length) return currentMin;
        currentMin = Math.min(currentMin, arr[index]);
        return findMinAccumulatorHelper(arr, index + 1, currentMin);
    }

    public static void main(String[] args) {
        int[] arr = {12, 45, 7, 89, 23, 56};
        System.out.println("============================================");
        System.out.println("Array elements: ");
        for (int num : arr) System.out.print(num + " ");
        System.out.println();
        System.out.println("============================================");
        System.out.println("Max (Linear): " + findMaxLinear(arr, 0));
        System.out.println("Min (Linear): " + findMinLinear(arr, 0));
        System.out.println("============================================");
        System.out.println("Max (Divide & Conquer): " + findMaxDivideConquer(arr, 0, arr.length - 1));
        System.out.println("Min (Divide & Conquer): " + findMinDivideConquer(arr, 0, arr.length - 1));
        System.out.println("============================================");
        System.out.println("Max (Tail Recursion): " + findMaxTail(arr, 0, Integer.MIN_VALUE));
        System.out.println("Min (Tail Recursion): " + findMinTail(arr, 0, Integer.MAX_VALUE));
        System.out.println("============================================");
        System.out.println("Max (Accumulator): " + findMaxAccumulator(arr));
        System.out.println("Min (Accumulator): " + findMinAccumulator(arr));
        System.out.println("============================================");
    }
}
