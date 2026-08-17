package com.rajeshkawali.dsa.string;
/*
 * Logic:
 * 1. Convert the input string into a char array for easy manipulation.
 * 2. Use two pointers:
 *    - 'left' starts at the beginning, 'right' starts at the end.
 * 3. If left points to '1' and right points to '0', swap them.
 * 4. Move pointers inward until they meet.
 * 5. Result: all zeros shifted to the left, all ones shifted to the right.
 *
 * Complexity:
 * Time: O(n) — single traversal with swaps
 * Space: O(1) — in-place operation
 */

public class MoveZerosAndOnes {

    public static String moveZerosLeftOnesRight(String str) {
        char[] arr = str.toCharArray();
        int left = 0, right = arr.length - 1;

        while (left < right) {
            if (arr[left] == '1' && arr[right] == '0') {
                // Swap
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            } else {
                if (arr[left] == '0') left++;
                if (arr[right] == '1') right--;
            }
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        String input = "10101101";
        System.out.println("Original: " + input);
        String result = moveZerosLeftOnesRight(input);
        System.out.println("Modified: " + result);
    }
}
