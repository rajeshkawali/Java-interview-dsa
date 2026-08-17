package com.rajeshkawali.dsa.string;
/*
 * Logic:
 * 1. Method 1 (reverseStringUsingCharArray):
 *    - Convert string to char array.
 *    - Use two pointers (left, right) and swap characters until they meet.
 *    - Return new string from modified char array.
 *
 * 2. Method 2 (reverseStringUsingStringBuilder):
 *    - Use StringBuilder's built-in reverse() method.
 *    - Simple and efficient.
 *
 * 3. Method 3 (reverseStringUsingLoop):
 *    - Traverse string from end to start.
 *    - Append characters to a new StringBuilder.
 *    - Return reversed string.
 *
 * Complexity:
 * Time: O(n) — each character processed once
 * Space: O(n) for new string/char array (except StringBuilder reverse which is in-place)
 */

public class ReverseString {

    // Method 1: Using char array and two-pointer technique
    public static String reverseStringUsingCharArray(String str) {
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

    // Method 2: Using StringBuilder reverse()
    public static String reverseStringUsingStringBuilder(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // Method 3: Using loop and StringBuilder
    public static String reverseStringUsingLoop(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "HelloWorld";

        System.out.println("Original String: " + input);
        System.out.println("Reversed (Char Array): " + reverseStringUsingCharArray(input));
        System.out.println("Reversed (StringBuilder): " + reverseStringUsingStringBuilder(input));
        System.out.println("Reversed (Loop): " + reverseStringUsingLoop(input));
    }
}
