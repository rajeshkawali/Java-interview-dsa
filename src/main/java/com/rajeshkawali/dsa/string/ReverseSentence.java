package com.rajeshkawali.dsa.string;

import java.util.*;

public class ReverseSentence {

    /*
     * Problem:
     * --------
     * Reverse the order of words in a sentence.
     *
     * Example:
     * Input:  "my child name is laksh"
     * Output: "laksh is name child my"
     *
     * Variants:
     * 1. Split + Loop (basic)
     * 2. Split + StringBuilder
     * 3. Using Stack
     * 4. Using Recursion
     * 5. Using Collections.reverse()
     */

    // 1. Split + Loop
    // Time: O(n), Space: O(n)
    public static String reverseWordsByLoop(String str) {
        String[] words = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i > 0) sb.append(" ");
        }
        return sb.toString();
    }

    // 2. Split + StringBuilder
    // Time: O(n), Space: O(n)
    public static String reverseWordsByStringBuilder(String str) {
        String[] words = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]).append(" ");
        }
        return sb.toString().trim();
    }

    // 3. Using Stack
    // Time: O(n), Space: O(n)
    public static String reverseWordsByStack(String str) {
        Stack<String> stack = new Stack<>();
        for (String word : str.split(" ")) stack.push(word);

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
            if (!stack.isEmpty()) sb.append(" ");
        }
        return sb.toString();
    }

    // 4. Using Recursion
    // Time: O(n^2), Space: O(n) (stack)
    private static String reverseRecursively(String[] words, int index) {
        if (index < 0) return "";
        return words[index] + (index > 0 ? " " : "") + reverseRecursively(words, index - 1);
    }
    public static String reverseWordsByRecursion(String str) {
        String[] words = str.split(" ");
        return reverseRecursively(words, words.length - 1);
    }

    // 5. Using Collections.reverse()
    // Time: O(n), Space: O(n)
    public static String reverseWordsByCollections(String str) {
        List<String> list = Arrays.asList(str.split(" "));
        Collections.reverse(list);
        return String.join(" ", list);
    }
    
    /*
     * Reverse sentence by words using Two-Pointer approach.
     * 
     * Time Complexity: O(n)  (n = number of words)
     * Space Complexity: O(n) (array of words)
     */
    public static String reverseSentenceTwoPointer(String str) {
        String[] words = str.split(" ");
        int left = 0;
        int right = words.length - 1;

        // Swap words using two pointers
        while (left < right) {
            String temp = words[left];
            words[left] = words[right];
            words[right] = temp;
            left++;
            right--;
        }
        // Join back into sentence
        return String.join(" ", words);
    }
    
    public static void main(String[] args) {
        String input = "my child name is laksh";
        System.out.println("=================================================================");
        System.out.println("Original String: " + input);
        System.out.println("=================================================================");
        System.out.println("1. Loop: " + reverseWordsByLoop(input));
        System.out.println("=================================================================");
        System.out.println("2. StringBuilder: " + reverseWordsByStringBuilder(input));
        System.out.println("=================================================================");
        System.out.println("3. Stack: " + reverseWordsByStack(input));
        System.out.println("=================================================================");
        System.out.println("4. Recursion: " + reverseWordsByRecursion(input));
        System.out.println("=================================================================");
        System.out.println("5. Collections.reverse(): " + reverseWordsByCollections(input));
        System.out.println("=================================================================");
        System.out.println("6. Two Pointers: " + reverseSentenceTwoPointer(input));
        System.out.println("=================================================================");
    }
}


/*

Reverse Sentence by Words:
- Loop → O(n), simple and clear.
- StringBuilder → O(n), concise.
- Stack → O(n), uses extra memory.
- Recursion → O(n^2), not optimal but interview-friendly.
- Collections.reverse() → O(n), modern and clean.


*/