package com.rajeshkawali.dsa.recursion;

import java.util.*;

/**
 * PermutationsRecursionVariants
 *
 * Variants included:
 * 1. Swap-based recursion (in-place) — good for distinct characters
 * 2. Prefix/backtracking with used[] and duplicate skipping — handles duplicates
 * 3. Heap's algorithm (recursive) — alternative swap-based method
 *
 * Complexity notes (n = length of string, k = average permutation length = n):
 * - Time: O(n * n!) for all variants that generate all permutations
 *   (there are n! permutations and each permutation costs O(n) to build/copy)
 * - Space:
 *   - Output storage: O(n! * n)
 *   - Recursion stack: O(n)
 *   - Additional aux arrays: O(n) for used[] or temporary buffers
 */
public class PermutationsRecursion {

    /* -------------------------
     * Variant 1: Swap-based recursion (in-place)
     * - Classic approach: swap current index with each index >= current,
     *   recurse for next index, then swap back (backtrack).
     * - Works well when characters are distinct.
     * Time: O(n * n!)
     * Space: O(n) recursion stack + O(n! * n) output
     */
    public static List<String> permuteSwap(String s) {
        List<String> result = new ArrayList<>();
        char[] arr = s.toCharArray();
        permuteSwapHelper(arr, 0, result);
        return result;
    }

    private static void permuteSwapHelper(char[] arr, int index, List<String> result) {
        if (index == arr.length) {
            result.add(new String(arr));
            return;
        }
        for (int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            permuteSwapHelper(arr, index + 1, result);
            swap(arr, index, i); // backtrack
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /* -------------------------
     * Variant 2: Prefix/backtracking with used[] (handles duplicates)
     * - Sort input first to allow skipping duplicates at the same recursion level.
     * - Build current prefix (StringBuilder or List), mark used positions.
     * - Skip a candidate if it's the same as previous and previous was not used
     *   (prevents generating duplicate permutations).
     * Time: O(n * n!) (but fewer outputs when duplicates exist)
     * Space: O(n) recursion stack + O(n) used[] + O(n! * n) output
     */
    public static List<String> permuteWithDuplicates(String s) {
        List<String> result = new ArrayList<>();
        char[] arr = s.toCharArray();
        Arrays.sort(arr); // sort to group duplicates
        boolean[] used = new boolean[arr.length];
        StringBuilder current = new StringBuilder();
        permuteDupHelper(arr, used, current, result);
        return result;
    }

    private static void permuteDupHelper(char[] arr, boolean[] used,
                                        StringBuilder current, List<String> result) {
        if (current.length() == arr.length) {
            result.add(current.toString());
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            // skip used positions
            if (used[i]) continue;
            // skip duplicates: if same char as previous and previous not used in this branch
            if (i > 0 && arr[i] == arr[i - 1] && !used[i - 1]) continue;

            used[i] = true;
            current.append(arr[i]);
            permuteDupHelper(arr, used, current, result);
            // backtrack
            current.deleteCharAt(current.length() - 1);
            used[i] = false;
        }
    }

    /* -------------------------
     * Variant 3: Heap's algorithm (recursive)
     * - Another swap-based recursive algorithm that generates permutations
     *   with minimal swaps; often used for in-place generation.
     * - Useful when you want a different generation order or fewer swaps.
     * Time: O(n * n!)
     * Space: O(n) recursion stack + O(n! * n) output
     */
    public static List<String> heapPermute(String s) {
        List<String> result = new ArrayList<>();
        char[] arr = s.toCharArray();
        heapPermuteHelper(arr.length, arr, result);
        return result;
    }

    private static void heapPermuteHelper(int k, char[] arr, List<String> result) {
        if (k == 1) {
            result.add(new String(arr));
            return;
        }
        heapPermuteHelper(k - 1, arr, result);
        for (int i = 0; i < k - 1; i++) {
            if (k % 2 == 0) {
                swap(arr, i, k - 1);
            } else {
                swap(arr, 0, k - 1);
            }
            heapPermuteHelper(k - 1, arr, result);
        }
    }

    /* -------------------------
     * Example main to demonstrate all variants
     * -------------------------
     */
    public static void main(String[] args) {
        String input = "abc";
        System.out.println("============================================");
        System.out.println("Input: " + input);
        System.out.println("============================================");
        List<String> p1 = permuteSwap(input);
        System.out.println("Permutations (Swap-based):");
        System.out.println(p1);
        System.out.println("============================================");
        List<String> p2 = permuteWithDuplicates(input);
        System.out.println("Permutations (With duplicate handling):");
        System.out.println(p2);
        System.out.println("============================================");
        List<String> p3 = heapPermute(input);
        System.out.println("Permutations (Heap's algorithm):");
        System.out.println(p3);
        System.out.println("============================================");
    }
}
