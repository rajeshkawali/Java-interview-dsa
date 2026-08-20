package com.rajeshkawali.dsa.string;

import java.util.Stack;

public class ReverseWords {

    /*
     * Problem:
     * --------
     * Reverse each word in a string while keeping
     * the word positions the same.
     *
     * Example:
     * Input:  "java is a programming language"
     * Output: "avaj si a gnimmargorp egaugnal"
     *
     * Variants:
     * 1. Using StringBuilder.reverse()
     * 2. Manual Two-Pointer Swap
     * 3. Using Stack
     * 4. Using Recursion (per word)
     * 5. Using Streams (Java 8+)
     */

    // 1. Using StringBuilder.reverse()
    // Time: O(n), Space: O(n)
    public static String reverseWordsByStringBuilder(String str) {
        String[] words = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            sb.append(new StringBuilder(words[i]).reverse());
            if (i < words.length - 1) sb.append(" ");
        }
        return sb.toString();
    }

    // 2. Manual Two-Pointer Swap
    // Time: O(n), Space: O(n)
    public static String reverseWordsByTwoPointer(String str) {
        String[] words = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            int left = 0, right = chars.length - 1;
            while (left < right) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
            sb.append(new String(chars));
            if (i < words.length - 1) sb.append(" ");
        }
        return sb.toString();
    }

    // 3. Using Stack
    // Time: O(n), Space: O(n)
    public static String reverseWordsByStack(String str) {
        String[] words = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            Stack<Character> stack = new Stack<>();
            for (char c : words[i].toCharArray()) stack.push(c);
            while (!stack.isEmpty()) sb.append(stack.pop());
            if (i < words.length - 1) sb.append(" ");
        }
        return sb.toString();
    }

    // 4. Using Recursion (per word)
    // Time: O(n^2), Space: O(n) (stack)
    private static String reverseWordRecursively(String word) {
        if (word.length() <= 1) return word;
        return reverseWordRecursively(word.substring(1)) + word.charAt(0);
    }
    public static String reverseWordsByRecursion(String str) {
        String[] words = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            sb.append(reverseWordRecursively(words[i]));
            if (i < words.length - 1) sb.append(" ");
        }
        return sb.toString();
    }

    // 5. Using Streams (Java 8+)
    // Time: O(n), Space: O(n)
    public static String reverseWordsByStreams(String str) {
        return java.util.Arrays.stream(str.split(" "))
                .map(word -> new StringBuilder(word).reverse().toString())
                .reduce((a, b) -> a + " " + b)
                .orElse("");
    }

    public static void main(String[] args) {
        String input = "java is a programming language";
        System.out.println("=================================================================");
        System.out.println("Original String: " + input);
        System.out.println("=================================================================");
        System.out.println("1. StringBuilder.reverse(): " + reverseWordsByStringBuilder(input));
        System.out.println("=================================================================");
        System.out.println("2. Two-Pointer Swap: " + reverseWordsByTwoPointer(input));
        System.out.println("=================================================================");
        System.out.println("3. Stack: " + reverseWordsByStack(input));
        System.out.println("=================================================================");
        System.out.println("4. Recursion: " + reverseWordsByRecursion(input));
        System.out.println("=================================================================");
        System.out.println("5. Streams: " + reverseWordsByStreams(input));
        System.out.println("=================================================================");
    }
}

/*

Reverse Each Word (same place):
- StringBuilder.reverse(): O(n), easiest.
- Two-Pointer Swap: O(n), manual control.
- Stack: O(n), extra memory.
- Recursion: O(n^2), not optimal but interview-friendly.
- Streams: O(n), modern concise style.


*/