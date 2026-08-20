package com.rajeshkawali.dsa.string;

import java.util.*;

public class RemoveAdjacentDuplicates {

    /*
     * Problem:
     * --------
     * Remove adjacent duplicate characters from a string.
     *
     * Example:
     * Input:  "axxacsvvccdc"
     * Output: "csdc"
     *
     * Variants:
     * 1. Using Stack
     * 2. Using StringBuilder (simulate stack)
     * 3. Using Recursion
     * 4. Using Manual Stack (array simulation)
     * 5. Using Two-Pointer (write index)
     * 6. Using Recursion (manual char array)
     */

    // 1. Using Stack
    // Time: O(n), Space: O(n)
    public static String removeByStack(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop(); // remove duplicate
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : stack) sb.append(c);
        return sb.toString();
    }

    // 2. Using StringBuilder (simulate stack)
    // Time: O(n), Space: O(n)
    public static String removeByStringBuilder(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            int len = sb.length();
            if (len > 0 && sb.charAt(len - 1) == c) {
                sb.deleteCharAt(len - 1); // remove duplicate
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // 3. Using Recursion
    // Time: O(n^2) (due to substring operations), Space: O(n) (recursion stack)
    public static String removeByRecursion(String str) {
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                return removeByRecursion(str.substring(0, i - 1) + str.substring(i + 1));
            }
        }
        return str;
    }
    
    // 4. Using Manual Stack (simulate stack with char array)
    // Time: O(n), Space: O(n)
    public static String removeByManualStack(String str) {
        char[] stack = new char[str.length()];
        int top = -1; // stack pointer

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (top >= 0 && stack[top] == c) {
                top--; // pop duplicate
            } else {
                stack[++top] = c; // push
            }
        }
        // build result
        String result = "";
        for (int i = 0; i <= top; i++) {
            result += stack[i];
        }
        return result;
    }

    // 5. Using Two-Pointer (write index)
    // Time: O(n), Space: O(n)
    public static String removeByTwoPointer(String str) {
        char[] arr = str.toCharArray();
        int write = 0; // position to write

        for (int read = 0; read < arr.length; read++) {
            if (write > 0 && arr[write - 1] == arr[read]) {
                write--; // remove duplicate by overwriting
            } else {
                arr[write++] = arr[read];
            }
        }
        // build result
        String result = "";
        for (int i = 0; i < write; i++) {
            result += arr[i];
        }
        return result;
    }

    // 6. Using Recursion (manual char array)
    // Time: O(n^2) (due to repeated rebuilds), Space: O(n) recursion stack
    public static String removeByManualRecursion(String str) {
        char[] arr = str.toCharArray();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                // manually rebuild string without using substring
                String result = "";
                for (int j = 0; j < i - 1; j++) result += arr[j];
                for (int j = i + 1; j < arr.length; j++) result += arr[j];
                return removeByRecursion(result);
            }
        }
        return str;
    }

    public static void main(String[] args) {
        String input = "axxacsvvccdc";
        System.out.println("=================================================================");
        System.out.println("Original String: " + input);
        System.out.println("=================================================================");
        System.out.println("1. Using Stack: " + removeByStack(input));
        System.out.println("=================================================================");
        System.out.println("2. Using StringBuilder: " + removeByStringBuilder(input));
        System.out.println("=================================================================");
        System.out.println("3. Using Recursion: " + removeByRecursion(input));
        System.out.println("=================================================================");
        System.out.println("4. Using Manual Stack: " + removeByManualStack(input));
        System.out.println("=================================================================");
        System.out.println("5. Using Two-Pointer: " + removeByTwoPointer(input));
        System.out.println("=================================================================");
        System.out.println("6. Using Recursion: " + removeByManualRecursion(input));
        System.out.println("=================================================================");
    }
}

/*
Remove Adjacent Duplicates:
- Stack → O(n), clean and intuitive.
- StringBuilder → O(n), efficient and simple.
- Recursion → O(n^2), less efficient but interview-friendly.

Remove Adjacent Duplicates (no inbuilt methods):
- Manual Stack → O(n), intuitive push/pop simulation.
- Two-Pointer → O(n), efficient in-place overwrite.
- Recursion → O(n^2), less efficient but good for interviews.

*/
