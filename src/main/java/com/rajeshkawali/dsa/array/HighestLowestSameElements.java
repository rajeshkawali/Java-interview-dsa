package com.rajeshkawali.dsa.array;

import java.util.HashMap;
import java.util.Map;

public class HighestLowestSameElements {

    /*
     * Logic:
     * 1. Use a HashMap to count frequency of each element in the array.
     * 2. Any element with frequency > 1 is considered a duplicate.
     * 3. Track the minimum and maximum among these duplicates.
     * 4. Also provide a method to find the highest element overall (max).
     * 
     * Complexity:
     * Time: O(n) — single pass through array
     * Space: O(n) — HashMap for frequencies
     */

    public static void main(String[] args) {
        int[] arr = {10, 5, 25, 8, 15, 5, 25, 10, 8};

        findHighestLowestSameElements(arr); // find lowest & highest duplicates
        findHighestElement(arr);            // find highest element overall
    }

    // Method to find lowest and highest duplicate elements
    public static void findHighestLowestSameElements(int[] arr) {
        Map<Integer, Integer> frequency = new HashMap<>(); // store element counts

        // Count frequency of each element
        for (int num : arr) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        Integer minDuplicate = null; // lowest duplicate
        Integer maxDuplicate = null; // highest duplicate

        // Check only elements that appear more than once
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() > 1) { // duplicate check
                int val = entry.getKey();
                if (minDuplicate == null || val < minDuplicate) {
                    minDuplicate = val;
                }
                if (maxDuplicate == null || val > maxDuplicate) {
                    maxDuplicate = val;
                }
            }
        }

        // Print results
        if (minDuplicate != null && maxDuplicate != null) {
            System.out.println("Lowest duplicate element: " + minDuplicate);
            System.out.println("Highest duplicate element: " + maxDuplicate);
        } else {
            System.out.println("No duplicate elements found.");
        }
    }

    // Method to find the highest element overall (not just duplicates)
    public static void findHighestElement(int[] arr) {
        int max = Integer.MIN_VALUE; // start with smallest possible value
        for (int num : arr) {
            if (num > max) {
                max = num; // update max if current element is larger
            }
        }
        System.out.println("Highest element in array: " + max);
    }
}
