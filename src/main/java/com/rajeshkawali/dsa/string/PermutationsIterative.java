package com.rajeshkawali.dsa.string;
import java.util.*;

/**
 * PermutationsIterative
 *
 * Implements three iterative methods to generate permutations of a string:
 * 1. Lexicographic nextPermutation (requires sorted input)
 * 2. Heap's algorithm iterative version (swap-based)
 * 3. Factoradic (factorial number system) mapping to generate permutations
 *
 * Complexity (n = length of string):
 * - Time: O(n * n!) for all methods (n! permutations, O(n) work per permutation)
 * - Space: O(n! * n) to store results if collecting; O(n) auxiliary (char array, indices)
 */
public class PermutationsIterative {

    /**
     * 1) Lexicographic permutations using nextPermutation
     * - Sort characters first, then repeatedly apply nextPermutation until no next.
     * - Produces permutations in lexicographic order.
     *
     * Time: O(n * n!)  Space: O(n! * n) if collecting results; O(n) auxiliary
     */
    public static List<String> permuteLexicographic(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr); // start from smallest lexicographic permutation
        List<String> result = new ArrayList<>();
        result.add(new String(arr));

        while (nextPermutation(arr)) {
            result.add(new String(arr));
        }
        return result;
    }

    // Produces next lexicographic permutation of arr in-place.
    // Returns true if next permutation exists, false if arr was the last permutation.
    private static boolean nextPermutation(char[] arr) {
        int n = arr.length;
        // 1. Find longest non-increasing suffix
        int i = n - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) i--;
        if (i < 0) return false; // arr is in descending order -> last permutation

        // 2. Find rightmost successor to pivot in suffix
        int j = n - 1;
        while (arr[j] <= arr[i]) j--;

        // 3. Swap pivot with successor
        swap(arr, i, j);

        // 4. Reverse suffix
        reverse(arr, i + 1, n - 1);
        return true;
    }

    private static void reverse(char[] arr, int l, int r) {
        while (l < r) swap(arr, l++, r--);
    }

    private static void swap(char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /**
     * 2) Heap's algorithm iterative version
     * - Uses an integer array 'c' as counters to generate permutations with swaps.
     * - In-place, no sorting required, good for minimal-swap generation.
     *
     * Time: O(n * n!)  Space: O(n! * n) if collecting; O(n) auxiliary
     */
    public static List<String> permuteHeapsIterative(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        List<String> result = new ArrayList<>();
        result.add(new String(arr));

        int[] c = new int[n]; // counters initialized to 0
        int i = 0;
        while (i < n) {
            if (c[i] < i) {
                // if i is even swap 0 and i, else swap c[i] and i
                int swapIndex = (i % 2 == 0) ? 0 : c[i];
                swap(arr, swapIndex, i);
                result.add(new String(arr));
                c[i]++; // increment counter for i
                i = 0;  // reset i
            } else {
                c[i] = 0;
                i++;
            }
        }
        return result;
    }

    /**
     * 3) Factoradic mapping (factorial number system)
     * - Map integers 0..n!-1 to permutations using factoradic representation.
     * - Deterministic, direct access to k-th permutation without recursion.
     *
     * Time: O(n * n!)  Space: O(n! * n) if collecting; O(n) auxiliary
     */
    public static List<String> permuteFactoradic(String s) {
        int n = s.length();
        List<String> result = new ArrayList<>();
        // compute n!
        long fact = 1;
        for (int i = 2; i <= n; i++) fact *= i;
        // characters list (we will copy for each permutation)
        char[] base = s.toCharArray();
        Arrays.sort(base); // optional: start from lexicographic order if desired

        for (long k = 0; k < fact; k++) {
            result.add(kthPermutationFromFactoradic(base, k));
        }
        return result;
    }

    // Build k-th permutation (0-indexed) of sorted base array using factoradic digits
    private static String kthPermutationFromFactoradic(char[] baseSorted, long k) {
        int n = baseSorted.length;
        List<Character> available = new ArrayList<>(n);
        for (char c : baseSorted) available.add(c);

        // compute factoradic digits
        int[] factoradic = new int[n];
        long temp = k;
        for (int i = 1; i <= n; i++) {
            factoradic[n - i] = (int) (temp % i);
            temp /= i;
        }

        // build permutation by selecting and removing indexed elements
        StringBuilder sb = new StringBuilder(n);
        for (int idx : factoradic) {
            sb.append(available.remove(idx));
        }
        return sb.toString();
    }

    /* -------------------------
     * Example usage and simple test
     * -------------------------
     */
    public static void main(String[] args) {
        String input = "abc";
        System.out.println("=================================================================");
        System.out.println("Input: " + input);
        System.out.println("=================================================================");
        System.out.println("Lexicographic permutations:");
        System.out.println(permuteLexicographic(input));
        System.out.println("=================================================================");
        System.out.println("Heap's algorithm iterative permutations:");
        System.out.println(permuteHeapsIterative(input));
        System.out.println("=================================================================");
        System.out.println("Factoradic permutations:");
        System.out.println(permuteFactoradic(input));
        System.out.println("=================================================================");
    }
}
