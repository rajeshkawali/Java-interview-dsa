package com.rajeshkawali.dsa.string;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class FrequencyCompressionAndCount {

    /*
     * Problem:
     * --------
     * Compress a char array by replacing each character with
     * character + total frequency (not just consecutive).
     *
     * Example:
     * Input:  ['a','c','a','a','c','b','b','b','d','b','e','b','e','a']
     * Output: "a4b5c2de2"
     *
     * Variants:
     * 1. Using HashMap
     * 2. Using Array (for lowercase letters)
     * 3. Using Recursion
     * 4. Sorting + Counting
     * 5. Streams (Java 8+)
     * 6. LinkedHashMap (preserve order)
     */

    // 1. Using HashMap
    // Time: O(n), Space: O(k) (k = unique chars)
    public static String compressByHashMap(char[] arr) {
        Map<Character, Integer> freq = new LinkedHashMap<>(); // preserve order
        for (char c : arr) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            result.append(entry.getKey());
            if (entry.getValue() > 1) result.append(entry.getValue());
        }
        return result.toString();
    }

    // 2. Using Array (assuming lowercase a-z)
    // Time: O(n), Space: O(26)
    public static String compressByArray(char[] arr) {
        int[] freq = new int[26];
        for (char c : arr) {
            freq[c - 'a']++;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                result.append((char)(i + 'a'));
                if (freq[i] > 1) result.append(freq[i]);
            }
        }
        return result.toString();
    }

    // 3. Recursion
    // Time: O(n^2), Space: O(n)
    public static String compressByRecursion(char[] arr) {
        return compressHelper(arr, 0, new LinkedHashMap<>());
    }

    private static String compressHelper(char[] arr, int index, Map<Character,Integer> freq) {
        if (index == arr.length) {
            StringBuilder result = new StringBuilder();
            for (Map.Entry<Character,Integer> entry : freq.entrySet()) {
                result.append(entry.getKey());
                if (entry.getValue() > 1) result.append(entry.getValue());
            }
            return result.toString();
        }
        freq.put(arr[index], freq.getOrDefault(arr[index], 0) + 1);
        return compressHelper(arr, index + 1, freq);
    }
    
    // 4. Sorting + Counting
    // Time: O(n log n), Space: O(1) extra
    public static String compressBySorting(char[] arr) {
        Arrays.sort(arr); // sort characters
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

    // 5. Using Java Streams (Java 8+)
    // Time: O(n), Space: O(k) (k = unique chars)
    public static String compressByStreams(char[] arr) {
        Map<Character, Long> freq = new LinkedHashMap<>();
        for (char c : arr) {
            freq.put(c, freq.getOrDefault(c, 0L) + 1);
        }
        return freq.entrySet().stream()
                .map(e -> e.getKey() + (e.getValue() > 1 ? String.valueOf(e.getValue()) : ""))
                .reduce("", String::concat);
    }

    // 6. Using LinkedHashMap explicitly (preserve order)
    // Time: O(n), Space: O(k)
    public static String compressByLinkedHashMap(char[] arr) {
        LinkedHashMap<Character, Integer> freq = new LinkedHashMap<>();
        for (char c : arr) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            result.append(entry.getKey());
            if (entry.getValue() > 1) result.append(entry.getValue());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        char[] input = {'a','c','a','a','c','b','b','b','d','b','e','b','e','a'};
        System.out.println("=================================================================");
        System.out.println("Original Array: " + new String(input));
        System.out.println("=================================================================");
        System.out.println("HashMap: " + compressByHashMap(input));
        System.out.println("=================================================================");
        System.out.println("Array: " + compressByArray(input));
        System.out.println("=================================================================");
        System.out.println("Recursion: " + compressByRecursion(input));
        System.out.println("=================================================================");
        System.out.println("Sorting + Counting: " + compressBySorting(input.clone()));
        System.out.println("=================================================================");
        System.out.println("Streams: " + compressByStreams(input.clone()));
        System.out.println("=================================================================");
        System.out.println("LinkedHashMap: " + compressByLinkedHashMap(input.clone()));
        System.out.println("=================================================================");
    }
}