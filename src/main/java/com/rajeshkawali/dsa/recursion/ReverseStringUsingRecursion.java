package com.rajeshkawali.dsa.recursion;

public class ReverseStringUsingRecursion {

    /*
     * Problem:
     * --------
     * Reverse a string using recursion.
     *
     * Variants:
     * 1. Using substring + charAt
     * 2. Using helper with index (forward recursion)
     * 3. Using helper with index (backward recursion)
     * 4. Using character array swap
     * 5. Tail recursion style
     */

    // 1. Using substring + charAt
    // Time: O(n^2) (substring creates new strings), Space: O(n)
    public static String reverseSubstring(String str) {
        if (str.isEmpty()) return "";
        return reverseSubstring(str.substring(1)) + str.charAt(0);
    }

    // 2. Using helper with index (forward recursion)
    // Time: O(n), Space: O(n)
    public static String reverseForward(String str) {
        return reverseForwardHelper(str, 0);
    }

    private static String reverseForwardHelper(String str, int index) {
        if (index == str.length()) return "";
        return reverseForwardHelper(str, index + 1) + str.charAt(index);
    }

    // 3. Using helper with index (backward recursion)
    // Time: O(n), Space: O(n)
    public static String reverseBackward(String str) {
        return reverseBackwardHelper(str, str.length() - 1);
    }

    private static String reverseBackwardHelper(String str, int index) {
        if (index < 0) return "";
        return str.charAt(index) + reverseBackwardHelper(str, index - 1);
    }

    // 4. Using character array swap
    // Time: O(n), Space: O(n) (array + recursion stack)
    public static String reverseCharArray(String str) {
        char[] arr = str.toCharArray();
        reverseArrayHelper(arr, 0, arr.length - 1);
        return new String(arr);
    }

    private static void reverseArrayHelper(char[] arr, int left, int right) {
        if (left >= right) return;
        char temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
        reverseArrayHelper(arr, left + 1, right - 1);
    }

    // 5. Tail recursion style
    // Time: O(n), Space: O(n)
    public static String reverseTail(String str) {
        return reverseTailHelper(str, "", 0);
    }

    private static String reverseTailHelper(String str, String result, int index) {
        if (index == str.length()) return result;
        return reverseTailHelper(str, str.charAt(index) + result, index + 1);
    }

    public static void main(String[] args) {
        String input = "rajesh";
        System.out.println("============================================");
        System.out.println("Original: " + input);
        System.out.println("============================================");
        System.out.println("1. Substring + charAt: " + reverseSubstring(input));
        System.out.println("============================================");
        System.out.println("2. Forward recursion: " + reverseForward(input));
        System.out.println("============================================");
        System.out.println("3. Backward recursion: " + reverseBackward(input));
        System.out.println("============================================");
        System.out.println("4. Char array swap: " + reverseCharArray(input));
        System.out.println("============================================");
        System.out.println("5. Tail recursion: " + reverseTail(input));
        System.out.println("============================================");
    }
}

/*

Short Note:
Substring + charAt → simple but inefficient (O(n²)).
Forward recursion → build result after reaching end.
Backward recursion → build result from last index backward.
Char array swap → in‑place reversal, efficient.
Tail recursion → accumulates result progressively.
*/