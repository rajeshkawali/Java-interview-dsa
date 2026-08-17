package com.rajeshkawali.dsa.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ArrayExample {

    public static void main(String[] args) {

        // 1. Find Second Largest Element
        int[] arr1 = { 10, 5, 25, 8, 15 };
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        for (int num : arr1) {
            if (num > largest) {
                secondLargest = largest; // update second largest
                largest = num;           // update largest
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }
        System.out.println("Second Largest Element: " + secondLargest);
        System.out.println("==================================================================");

        // 2. Move Zeroes to End (while maintaining order of non-zero elements)
        int[] arr2 = { 0, 1, 0, 3, 12 };
        int j = 0; // pointer for non-zero placement
        for (int i = 0; i < arr2.length; i++) {
            if (arr2[i] != 0) {
                int temp = arr2[i];
                arr2[i] = arr2[j];
                arr2[j] = temp;
                j++;
            }
        }
        System.out.println("Array after moving zeroes: " + Arrays.toString(arr2));
        System.out.println("==================================================================");

        // 3. Find First Duplicate Element
        int[] arr3 = { 1, 3, 4, 2, 3 };
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr3) {
            if (!set.add(num)) { // if add fails, duplicate found
                System.out.println("Duplicate Element: " + num);
                break;
            }
        }
        System.out.println("==================================================================");

        // 4. Frequency of Each Element
        int[] arr4 = { 1, 2, 2, 3, 1, 1 };
        HashMap<Integer, Integer> frequency = new HashMap<>();
        for (int num : arr4) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        System.out.println("Frequency Map: " + frequency);
        System.out.println("==================================================================");

        // 5. Sum of Array Elements
        int[] arr5 = { 1, 3, 4, 2, 3 };
        int sum = 0;
        for (int num : arr5) {
            sum += num;
        }
        System.out.println("Sum of Elements: " + sum);
        System.out.println("==================================================================");

        // 6. Count Even Numbers
        int[] arr6 = { 1, 2, 3, 4, 6, 7 };
        int evenCount = 0;
        for (int num : arr6) {
            if (num % 2 == 0) {
                evenCount++;
            }
        }
        System.out.println("Count of Even Numbers: " + evenCount);
        System.out.println("==================================================================");

        // 7. Find Minimum Element
        int[] arr7 = { 15, 3, 27, 8, 12 };
        int min = arr7[0];
        for (int i = 1; i < arr7.length; i++) {
            if (arr7[i] < min) {
                min = arr7[i];
            }
        }
        System.out.println("Minimum Element: " + min);
        System.out.println("==================================================================");

        // 8. Linear Search for Target
        int[] arr8 = { 5, 10, 15, 20, 25 };
        int target = 20;
        for (int i = 0; i < arr8.length; i++) {
            if (arr8[i] == target) {
                System.out.println("Target " + target + " found at index: " + i);
                break;
            }
        }
        System.out.println("==================================================================");

        // 9. Reverse Array (Two-Pointer Swap)
        int[] arr9 = { 1, 2, 3, 4, 5 };
        int left = 0;
        int right = arr9.length - 1;
        while (left < right) {
            int temp = arr9[left];
            arr9[left] = arr9[right];
            arr9[right] = temp;
            left++;
            right--;
        }
        System.out.println("Reversed Array: " + Arrays.toString(arr9));
        System.out.println("==================================================================");
    }
}
