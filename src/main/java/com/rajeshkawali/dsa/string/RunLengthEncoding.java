package com.rajeshkawali.dsa.string;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

public class RunLengthEncoding {

	/*
     * Problem:
     * --------
     * Compress a char array by replacing consecutive duplicates
     * with character + count.
     *
     * Example:
     * Input:  ['a','c','a','a','c','b','b','b','d','b','e','b','e','a']
     * Output: "aca2cb3dbebea"
     *
     * Variants:
     * 1. Simple Loop
     * 2. Two-Pointer
     * 3. Recursion
     * 4. Using Stack Simulation
     * 5. Using StringBuilder Counters
     * 6. Using LinkedHashMap (for grouped runs)
     */

    // 1. Simple Loop
    // Time: O(n), Space: O(1)
    public static String compressByLoop(char[] arr) {
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (int i = 1; i <= arr.length; i++) {
            if (i < arr.length && arr[i] == arr[i - 1]) {
                count++;
            } else {
                result.append(arr[i - 1]);
                if (count > 1) result.append(count);
                count = 1;
            }
        }
        return result.toString();
    }

    // 2. Two-Pointer
    // Time: O(n), Space: O(1)
    public static String compressByTwoPointer(char[] arr) {
        StringBuilder result = new StringBuilder();
        int left = 0;
        while (left < arr.length) {
            int right = left;
            while (right < arr.length && arr[right] == arr[left]) {
                right++;
            }
            int count = right - left;
            result.append(arr[left]);
            if (count > 1) result.append(count);
            left = right;
        }
        return result.toString();
    }

    // 3. Recursion
    // Time: O(n^2), Space: O(n)
    public static String compressByRecursion(char[] arr) {
        return compressHelper(arr, 0);
    }

    private static String compressHelper(char[] arr, int index) {
        if (index >= arr.length) return "";
        int count = 1;
        while (index + count < arr.length && arr[index + count] == arr[index]) {
            count++;
        }
        String part = "" + arr[index];
        if (count > 1) part += count;
        return part + compressHelper(arr, index + count);
    }
    
    // 4. Using Stack Simulation
    // Time: O(n), Space: O(n)
    public static String compressByStack(char[] arr) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        int count = 0;

        for (char c : arr) {
            if (!stack.isEmpty() && stack.peek() == c) {
                count++;
            } else {
                if (!stack.isEmpty()) {
                    result.append(stack.pop());
                    if (count > 1) result.append(count);
                }
                stack.push(c);
                count = 1;
            }
        }
        if (!stack.isEmpty()) {
            result.append(stack.pop());
            if (count > 1) result.append(count);
        }
        return result.toString();
    }

    // 5. Using StringBuilder Counters
    // Time: O(n), Space: O(1)
    public static String compressByStringBuilder(char[] arr) {
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (int i = 1; i <= arr.length; i++) {
            if (i < arr.length && arr[i] == arr[i - 1]) {
                count++;
            } else {
                result.append(arr[i - 1]);
                if (count > 1) result.append(count);
                count = 1;
            }
        }
        return result.toString();
    }

    // 6. Using LinkedHashMap (for grouped runs)
    // Time: O(n), Space: O(k) (k = unique consecutive groups)
    public static String compressByLinkedHashMap(char[] arr) {
        LinkedHashMap<String, Integer> groups = new LinkedHashMap<>();
        int count = 1;
        for (int i = 1; i <= arr.length; i++) {
            if (i < arr.length && arr[i] == arr[i - 1]) {
                count++;
            } else {
                String key = String.valueOf(arr[i - 1]);
                groups.put(key + groups.size(), count); // unique key per run
                count = 1;
            }
        }
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : groups.entrySet()) {
            char c = entry.getKey().charAt(0);
            result.append(c);
            if (entry.getValue() > 1) result.append(entry.getValue());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        char[] input = {'a','c','a','a','c','b','b','b','d','b','e','b','e','a'};
        System.out.println("=================================================================");
        System.out.println("Original Array: " + new String(input));
        System.out.println("=================================================================");
        System.out.println("1. Using Loop: " + compressByLoop(input));
        System.out.println("=================================================================");
        System.out.println("2. Using Two-Pointer: " + compressByTwoPointer(input));
        System.out.println("=================================================================");
        System.out.println("3. Using Recursion: " + compressByRecursion(input));
        System.out.println("=================================================================");
        System.out.println("4. Stack: " + compressByStack(input.clone()));
        System.out.println("=================================================================");
        System.out.println("5. StringBuilder: " + compressByStringBuilder(input.clone()));
        System.out.println("=================================================================");
        System.out.println("6. LinkedHashMap: " + compressByLinkedHashMap(input.clone()));
        System.out.println("=================================================================");
    }
}

/*
You are given a character array. The task is to compress the array by replacing consecutive 
duplicate characters with the character followed by its count.
If a character appears only once consecutively, you just keep the character without a count.

Run Length Encoding (RLE) technique, widely used in data compression.
*/