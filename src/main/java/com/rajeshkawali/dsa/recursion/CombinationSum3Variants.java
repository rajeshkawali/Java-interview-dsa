package com.rajeshkawali.dsa.recursion;

import java.util.*;

/**
 * CombinationSumVariants
 *
 * Variants implemented:
 * 1. Combination Sum (unlimited use of each candidate)
 * 2. Combination Sum II (each candidate used at most once; input may contain duplicates)
 * 3. Combination Sum III (choose k numbers from 1..9, each used at most once, sum to n)
 *
 * General approach:
 * - All variants use recursion + backtracking (include/exclude style or controlled iteration).
 * - We build a current combination, recurse, then backtrack (remove last element).
 *
 * Complexity notes (n = number of candidates, k = average length of a combination, target = T):
 * - Combination Sum (unlimited):
 *   Time: O(number_of_combinations * k) — worst-case exponential; often approximated O(2^n) in structure.
 *   Space: O(k) recursion stack + O(number_of_combinations * k) for result storage.
 *
 * - Combination Sum II (each used once, duplicates handled):
 *   Time: O(number_of_combinations * k) — exponential in worst case.
 *   Space: O(k) recursion stack + O(number_of_combinations * k) for results.
 *
 * - Combination Sum III (k numbers from 1..9):
 *   Time: O(C(9, k) * k) — bounded because candidates are 1..9.
 *   Space: O(k) recursion stack + O(C(9, k) * k) for results.
 */
public class CombinationSum3Variants {

    /* -------------------------
     * 1. Combination Sum (unlimited use)
     * -------------------------
     * Given candidates (distinct) and target, return all unique combinations
     * where candidates can be chosen unlimited times.
     *
     * Strategy:
     * - Sort candidates (optional but helps pruning).
     * - For each index i, either pick candidates[i] and stay at i (allow reuse),
     *   or move to i+1 to try next candidate.
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        backtrackUnlimited(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackUnlimited(int[] candidates, int remain, int start,
                                           List<Integer> current, List<List<Integer>> result) {
        if (remain == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (remain < 0) return;

        for (int i = start; i < candidates.length; i++) {
            int val = candidates[i];
            if (val > remain) break; // pruning because array is sorted
            current.add(val);
            // allow reuse of i -> pass i again
            backtrackUnlimited(candidates, remain - val, i, current, result);
            current.remove(current.size() - 1); // backtrack
        }
    }

    /* -------------------------
     * 2. Combination Sum II (each candidate used at most once)
     * -------------------------
     * Given candidates (may contain duplicates) and target, return unique combinations
     * where each candidate may be used at most once.
     *
     * Strategy:
     * - Sort candidates to group duplicates.
     * - At each recursion level skip duplicates by checking previous value.
     * - When including candidate at i, recurse with start = i+1 (no reuse).
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        backtrackOnce(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackOnce(int[] candidates, int remain, int start,
                                      List<Integer> current, List<List<Integer>> result) {
        if (remain == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (remain < 0) return;

        for (int i = start; i < candidates.length; i++) {
            // skip duplicates at the same recursion level
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            int val = candidates[i];
            if (val > remain) break; // pruning
            current.add(val);
            // move to i+1 because each element can be used once
            backtrackOnce(candidates, remain - val, i + 1, current, result);
            current.remove(current.size() - 1); // backtrack
        }
    }

    /* -------------------------
     * 3. Combination Sum III (k numbers from 1..9)
     * -------------------------
     * Find all combinations of k distinct numbers from 1..9 that sum to n.
     *
     * Strategy:
     * - Use numbers 1..9 as candidates.
     * - Recurse with next start = i+1 to ensure distinctness.
     * - Prune when sum exceeds target or when remaining slots cannot be filled.
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackKDistinct(1, k, n, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackKDistinct(int start, int k, int remain,
                                           List<Integer> current, List<List<Integer>> result) {
        if (remain == 0 && current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (remain < 0) return;
        if (current.size() > k) return;

        // numbers are 1..9
        for (int i = start; i <= 9; i++) {
            // pruning: if remaining numbers cannot fill required slots, break
            int maxPossibleCount = 9 - i + 1;
            if (current.size() + maxPossibleCount < k) break;

            current.add(i);
            backtrackKDistinct(i + 1, k, remain - i, current, result);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        // Variant 1: unlimited use
        int[] candidates1 = {2, 3, 6, 7};
        int target1 = 7;
        System.out.println("============================================");
        System.out.println("Combination Sum (unlimited) for target 7:");
        System.out.println(combinationSum(candidates1, target1));
        // Expected: [[2,2,3], [7]]
        System.out.println("============================================");
        // Variant 2: each used once, duplicates in input
        int[] candidates2 = {10,1,2,7,6,1,5};
        int target2 = 8;
        System.out.println("Combination Sum II (each once) for target 8:");
        System.out.println(combinationSum2(candidates2, target2));
        // Expected: [[1,1,6],[1,2,5],[1,7],[2,6]]
        System.out.println("============================================");
        // Variant 3: k numbers from 1..9
        int k = 3, n = 7;
        System.out.println("Combination Sum III (k=3, n=7):");
        System.out.println(combinationSum3(k, n));
        // Expected: [[1,2,4]]
        System.out.println("============================================");
    }
}

/*

Quick Notes:-
Backtracking pattern: add element → recurse → remove element (backtrack). Sorting helps pruning and duplicate handling.
Pruning: stop recursion early when the running sum exceeds the target or when remaining candidates cannot satisfy constraints.
Complexity: all variants are exponential in the worst case; memoization is not typically applied because we need to enumerate combinations (not just count or check existence).

Use cases:
Combination Sum → coin‑change style combinations with unlimited coins.
Combination Sum II → subset combinations from a multiset (handle duplicates).
Combination Sum III → constrained combinations from a fixed small set (1..9), often used in interview problems.

*/
