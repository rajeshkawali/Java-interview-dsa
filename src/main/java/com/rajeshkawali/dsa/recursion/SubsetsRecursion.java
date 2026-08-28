package com.rajeshkawali.dsa.recursion;
import java.util.*;

/*
 * SubsetsRecursion
 *
 * Implements:
 *  - Subsets I  : all subsets of distinct elements
 *  - Subsets II : all unique subsets when input may contain duplicates
 *
 * Complexity notes are provided below each method.
 */
public class SubsetsRecursion {

    // Subsets I
    // Generate all subsets using include/exclude recursion.
    // Time Complexity: O(2^n * n)  -> 2^n subsets, O(n) to copy each subset into result
    // Space Complexity: O(2^n * n) for result + O(n) recursion stack and current list
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        backtrackSubsets(nums, 0, current, result);
        return result;
    }

    private static void backtrackSubsets(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        // Add a copy of current subset to result
        result.add(new ArrayList<>(current));

        // Explore further elements to include
        for (int i = index; i < nums.length; i++) {
            // Include nums[i]
            current.add(nums[i]);
            backtrackSubsets(nums, i + 1, current, result);
            // Backtrack: remove last added element
            current.remove(current.size() - 1);
        }
    }

    // Subsets II
    // Generate unique subsets when nums may contain duplicates.
    // Sort first to group duplicates, then skip duplicates at same recursion level.
    // Time Complexity: O(2^n * n) worst case; duplicates reduce number of unique subsets
    // Space Complexity: O(2^n * n) for result + O(n) recursion stack and current list
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums); // important for duplicate skipping
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        backtrackSubsetsWithDup(nums, 0, current, result);
        return result;
    }

    private static void backtrackSubsetsWithDup(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));

        for (int i = index; i < nums.length; i++) {
            // Skip duplicates at the same recursion level
            if (i > index && nums[i] == nums[i - 1]) continue;

            current.add(nums[i]);
            backtrackSubsetsWithDup(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    // Simple demonstration
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        System.out.println("=================================================================");
        System.out.println("Subsets I for [1,2,3]:");
        System.out.println(subsets(nums1));
        System.out.println("=================================================================");
        int[] nums2 = {1, 2, 2};
        System.out.println("Subsets II for [1,2,2]:");
        System.out.println(subsetsWithDup(nums2));
        System.out.println("=================================================================");
    }
}
