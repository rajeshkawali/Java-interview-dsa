package com.rajeshkawali.dsa.generic;

import java.util.*;

/**
 * Different ways to remove duplicates from a sorted array.
 * 
 * Approaches:
 * 1. Two Pointer (Optimal, in-place)
 * 2. HashSet (Simple, but extra space)
 * 3. LinkedHashSet (Preserves order)
 * 4. Sorting + Linear Scan (works even if unsorted)
 * 5. Java 8 Stream (Concise modern style)
 *
 * Key Point:
 * - For interviews: Two Pointer is the expected optimal solution.
 * - For practice: Set or Stream-based approaches are handy.
 */
public class RemoveDuplicates {

    // 1. Two Pointer (Optimal, in-place)
    public static int removeDuplicatesTwoPointer(int[] arr) {
        if (arr.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < arr.length; j++) {
            if (arr[j] != arr[i]) {
                i++;
                arr[i] = arr[j];
            }
        }
        return i + 1; // length of unique array
    }

    // 2. HashSet (does not guarantee order)
    public static int[] removeDuplicatesHashSet(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) set.add(num);
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    // 3. LinkedHashSet (preserves insertion order)
    public static int[] removeDuplicatesLinkedHashSet(int[] arr) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : arr) set.add(num);
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    // 4. Sorting + Linear Scan (works even if input unsorted)
    public static int[] removeDuplicatesSorting(int[] arr) {
        Arrays.sort(arr);
        int j = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[j]) {
                j++;
                arr[j] = arr[i];
            }
        }
        return Arrays.copyOfRange(arr, 0, j + 1);
    }

    // 5. Java 8 Stream (modern concise style)
    public static int[] removeDuplicatesStream(int[] arr) {
        return Arrays.stream(arr).distinct().toArray();
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 3, 4, 4, 5};

        // Method 1: Two Pointer
        int[] arr1 = arr.clone();
        int newLength = removeDuplicatesTwoPointer(arr1);
        System.out.println("Two Pointer → New length: " + newLength);
        System.out.println("Unique elements: " + Arrays.toString(Arrays.copyOfRange(arr1, 0, newLength)));
        System.out.println("================================================");

        // Method 2: HashSet
        System.out.println("HashSet → " + Arrays.toString(removeDuplicatesHashSet(arr.clone())));
        System.out.println("================================================");

        // Method 3: LinkedHashSet
        System.out.println("LinkedHashSet → " + Arrays.toString(removeDuplicatesLinkedHashSet(arr.clone())));
        System.out.println("================================================");

        // Method 4: Sorting + Linear Scan
        System.out.println("Sorting + Linear Scan → " + Arrays.toString(removeDuplicatesSorting(arr.clone())));
        System.out.println("================================================");

        // Method 5: Stream
        System.out.println("Stream distinct() → " + Arrays.toString(removeDuplicatesStream(arr.clone())));
        System.out.println("================================================");
    }
}
