package com.rajeshkawali.dsa.array;

import java.util.*;

public class ThreeSum {

    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        int target = 2;
        System.out.println("Brute Force:");
        System.out.println(findThreeSumBruteForce(arr.clone(), target));
        System.out.println("================================================");

        System.out.println("Sorting + Two Pointer:");
        System.out.println(findThreeSumTwoPointer(arr.clone(), target));
        System.out.println("================================================");
        
        System.out.println("HashMap-based Three Sum:");
        System.out.println(findThreeSumHashMap(arr, target));
        System.out.println("================================================");
    }

    /*
     * Approach 1: Brute Force
     * - Check all triplets (i, j, k).
     * - If arr[i] + arr[j] + arr[k] == 0, add to result.
     *  Time: O(n³) (still brute force, unavoidable here).
	 *	Space: O(m) where m = number of unique triplets.
     */
    public static List<List<Integer>> findThreeSumBruteForce(int[] arr, int target) {
        Set<List<Integer>> result = new HashSet<>(); // use Set to avoid duplicates
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (arr[i] + arr[j] + arr[k] == target) {
                        // Sort triplet once before adding
                        List<Integer> triplet = Arrays.asList(arr[i], arr[j], arr[k]);
                        Collections.sort(triplet);
                        result.add(triplet); // Set ensures uniqueness
                    }
                }
            }
        }
        return new ArrayList<>(result); // convert Set back to List
    }


    /*
     * Approach 2: Sorting + Two Pointer
     * - Sort array.
     * - Fix one element arr[i].
     * - Use two pointers (left, right) to find pairs that sum to -arr[i].
     * - Skip duplicates to avoid repeated triplets.
     * Time: O(n^2), Space: O(1)
     */
    public static List<List<Integer>> findThreeSumTwoPointer(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        int n = arr.length;

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) continue; // skip duplicates
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == target) {
                    result.add(Arrays.asList(arr[i], arr[left], arr[right]));
                    while (left < right && arr[left] == arr[left + 1]) left++; // skip duplicates
                    while (left < right && arr[right] == arr[right - 1]) right--; // skip duplicates
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
    
    public static List<List<Integer>> findThreeSumHashMap(int[] arr, int target) {
        Set<List<Integer>> result = new HashSet<>();
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int newTarget = target - arr[i];
            HashMap<Integer, Integer> map = new HashMap<>();

            for (int j = i + 1; j < n; j++) {
                int complement = newTarget - arr[j];
                if (map.containsKey(complement)) {
                    List<Integer> triplet = Arrays.asList(arr[i], arr[j], complement);
                    Collections.sort(triplet); // normalize order
                    result.add(triplet);       // Set avoids duplicates
                }
                map.put(arr[j], j);
            }
        }
        return new ArrayList<>(result);
    }
}

/*
 * Three Sum Problem:
 * - Find all unique triplets in an array that sum to a target (commonly 0).
 * 
 * Logic:
 * 1. Brute Force:
 *    - Try all triplets (i, j, k).
 *    - Check if sum == 0.
 *    - Time O(n^3), not efficient.
 *
 * 2. Sorting + Two Pointer:
 *    - Sort array.
 *    - Fix one element, use two pointers to find remaining pair.
 *    - Skip duplicates to avoid repeated triplets.
 *    - Time O(n^2), Space O(1) → Best approach.
 *
 * Key Point:
 * - Three Sum is an extension of Two Sum.
 * - Efficient solution uses sorting + two pointers.
*/
