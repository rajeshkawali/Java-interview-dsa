package com.rajeshkawali.dsa.array;

/*
 * Logic (Two Pointer):
 * 1. Use one pointer `j` to track the position of the last unique element.
 * 2. Traverse with another pointer `i`.
 * 3. If arr[i] != arr[j], increment j and copy arr[i] to arr[j].
 * 4. After loop, the array from index 0..j contains unique elements.
 *
 * Complexity:
 * Time: O(n) — single pass
 * Space: O(1) — in-place, no extra memory
 */

import java.util.Arrays;

public class RemoveDuplicatesSortedArray {

    // Method 1: Standard two-pointer approach
    public static int removeDuplicates(int[] arr) {
        if (arr.length == 0) return 0;

        int j = 0; // pointer for unique position
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[j]) {
                j++;
                arr[j] = arr[i];
            }
        }
        return j + 1; // length of unique array
    }

    // Method 2: Alternative two-pointer style
    public static int removeDuplicateFindNewLength(int[] arr) {
        int i = 0;
        for (int j = 1; j < arr.length; j++) {
            if (arr[i] != arr[j]) {
                i++;
                arr[i] = arr[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 3, 4, 4, 5};
        System.out.println("Original length of array: " + arr.length);
        System.out.println("================================================");
        // Using Method 1
        int[] arr1 = arr.clone();
        int newLength1 = removeDuplicates(arr1);
        System.out.println("New length (Method 1): " + newLength1);
        System.out.println("Unique elements: " + Arrays.toString(Arrays.copyOfRange(arr1, 0, newLength1)));
        System.out.println("================================================");

        // Using Method 2
        int[] arr2 = arr.clone();
        int newLength2 = removeDuplicateFindNewLength(arr2);
        System.out.println("New length (Method 2): " + newLength2);
        System.out.println("Unique elements: " + Arrays.toString(Arrays.copyOfRange(arr2, 0, newLength2)));
        System.out.println("================================================");
    }
}
