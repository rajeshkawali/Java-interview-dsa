package com.rajeshkawali.dsa.string;

import java.util.Stack;

public class ReverseString {

    /*
     * Reverse String Variants:
     * ------------------------
     * 1. Char Array (Two-Pointer Swap)
     * 2. StringBuilder / StringBuffer
     * 3. Recursion
     * 4. Stack
     * 5. Backward Loop
     * 6. Reverse Words in String
     */

    // 1. Char Array (Two-Pointer Swap)
    // Time: O(n), Space: O(n)
    public static String reverseByCharArray(String str) {
        char[] chars = str.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    // 2. StringBuilder / StringBuffer
    // Time: O(n), Space: O(n)
    public static String reverseByStringBuilder(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // 3. Recursion
    // Time: O(n^2) (substring creates new string each call)
    // Space: O(n) (recursion stack)
    public static String reverseByRecursion(String str) {
        if (str == null || str.length() <= 1) return str;
        return reverseByRecursion(str.substring(1)) + str.charAt(0);
    }

    // 4. Stack
    // Time: O(n), Space: O(n)
    public static String reverseByStack(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) stack.push(c);

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.pop());
        return sb.toString();
    }

    // 5. Backward Loop
    // Time: O(n), Space: O(n)
    public static String reverseByLoop(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    // 6. Reverse Words in String
    // Time: O(n), Space: O(n)
    public static String reverseWords(String str) {
        String[] words = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i > 0) sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "java is a programming language";
        System.out.println("=================================================================");
        System.out.println("Original String: " + input);
        System.out.println("=================================================================");
        System.out.println("1. Char Array Reverse: " + reverseByCharArray(input));
        System.out.println("=================================================================");
        System.out.println("2. StringBuilder Reverse: " + reverseByStringBuilder(input));
        System.out.println("=================================================================");
        System.out.println("3. Recursion Reverse: " + reverseByRecursion(input));
        System.out.println("=================================================================");
        System.out.println("4. Stack Reverse: " + reverseByStack(input));
        System.out.println("=================================================================");
        System.out.println("5. Backward Loop Reverse: " + reverseByLoop(input));
        System.out.println("=================================================================");
        System.out.println("6. Reverse Words: " + reverseWords(input));
        System.out.println("=================================================================");
    }
}

/*
Reverse String Variants:
- Char Array (Two-Pointer): O(n), space O(n).
- StringBuilder.reverse(): O(n), space O(n).
- Recursion: O(n^2), space O(n).
- Stack: O(n), space O(n).
- Backward Loop: O(n), space O(n).
- Reverse Words: O(n), space O(n).


*/