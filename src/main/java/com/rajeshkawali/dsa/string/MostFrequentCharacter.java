package com.rajeshkawali.dsa.string;

import java.util.*;
import java.util.stream.Collectors;

public class MostFrequentCharacter {

    /*
     * Problem:
     * --------
     * Find the most frequent character in a given string.
     *
     * Example:
     * Input:  "programming"
     * Output: 'g' (appears 2 times)
     *
     * Variants:
     * 1. Using HashMap
     * 2. Using Array (frequency count for ASCII)
     * 3. Using Streams (Java 8+)
     * 4. Using Sorting
     */

    // 1. Using HashMap
    // Time: O(n), Space: O(k) (k = unique chars)
    public static char mostFrequentByHashMap(String str) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : str.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        char result = '\0';
        int max = 0;
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }

    // 2. Using Array (ASCII assumption)
    // Time: O(n), Space: O(256)
    public static char mostFrequentByArray(String str) {
        int[] freq = new int[256]; // ASCII
        for (char c : str.toCharArray()) {
            freq[c]++;
        }
        int max = 0;
        char result = '\0';
        for (int i = 0; i < 256; i++) {
            if (freq[i] > max) {
                max = freq[i];
                result = (char) i;
            }
        }
        return result;
    }

    // 3. Using Streams (Java 8+)
    // Time: O(n), Space: O(k)
    public static char mostFrequentByStreams(String str) {
        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }

    // 4. Using Sorting
    // Time: O(n log n), Space: O(n)
    public static char mostFrequentBySorting(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        int maxCount = 1, count = 1;
        char result = chars[0];
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            if (count > maxCount) {
                maxCount = count;
                result = chars[i];
            }
        }
        return result;
    }
    
    /*
     * Find most frequent character using Two-Pointer approach.
     *
     * Steps:
     * 1. Sort characters of the string.
     * 2. Use two pointers to count consecutive identical chars.
     * 3. Track max frequency and character.
     *
     * Time Complexity: O(n log n) (due to sorting)
     * Space Complexity: O(n) (char array)
     */
    public static char mostFrequentChar(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars); // Step 1: sort

        int left = 0, right = 0;
        int maxCount = 0;
        char result = chars[0];

        while (right < chars.length) {
            // Expand right pointer while same char
            while (right < chars.length && chars[right] == chars[left]) {
                right++;
            }
            int count = right - left; // frequency of current char
            if (count > maxCount) {
                maxCount = count;
                result = chars[left];
            }
            left = right; // move left to next group
        }
        return result;
    }

    public static void main(String[] args) {
        String input = "programming";
        System.out.println("=================================================================");
        System.out.println("Original String: " + input);
        System.out.println("=================================================================");
        System.out.println("1. HashMap: " + mostFrequentByHashMap(input));
        System.out.println("=================================================================");
        System.out.println("2. Array: " + mostFrequentByArray(input));
        System.out.println("=================================================================");
        System.out.println("3. Streams: " + mostFrequentByStreams(input));
        System.out.println("=================================================================");
        System.out.println("4. Sorting: " + mostFrequentBySorting(input));
        System.out.println("=================================================================");
        System.out.println("5. Two-Pointer: " + mostFrequentChar(input));
        System.out.println("=================================================================");
    }
}

/*
Most Frequent Character Approaches:
1. HashMap → O(n), flexible, works for Unicode.
2. Array → O(n), space O(256), fastest for ASCII.
3. Streams → O(n), modern concise style.
4. Sorting → O(n log n), less efficient but simple.

5. Two-Pointer Frequency Count:
- Sort characters.
- Use left/right pointers to count consecutive identical chars.
- Track max frequency.
- Time: O(n log n) (sorting) Space: O(n)

*/