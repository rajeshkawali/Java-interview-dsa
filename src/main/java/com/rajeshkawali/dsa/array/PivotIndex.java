package com.rajeshkawali.dsa.array;

public class PivotIndex {

    /*
     * Approach:
     * 1. Compute total sum of array.
     * 2. Traverse array, keep track of left sum.
     * 3. At each index i:
     *    - Right sum = totalSum - leftSum - arr[i]
     *    - If leftSum == rightSum → pivot found.
     * 4. Return index if found, else -1.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int findPivotIndex(int[] arr) {
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        int leftSum = 0;
        for (int i = 0; i < arr.length; i++) {
            int rightSum = totalSum - leftSum - arr[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += arr[i];
        }
        return -1;
    }
    
    /*
     * Approach: Prefix + Suffix Arrays
     * 1. Build prefixSum[] where prefixSum[i] = sum of elements from 0..i.
     * 2. Build suffixSum[] where suffixSum[i] = sum of elements from i..n-1.
     * 3. Traverse array:
     *    - At index i, leftSum = prefixSum[i-1], rightSum = suffixSum[i+1].
     *    - If leftSum == rightSum → pivot index found.
     * 4. Return first pivot index or -1 if none.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n) (two extra arrays)
     */
    public static int findPivotIndexTwo(int[] arr) {
        int n = arr.length;
        int[] prefixSum = new int[n];
        int[] suffixSum = new int[n];

        // Build prefix sum
        prefixSum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        // Build suffix sum
        suffixSum[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + arr[i];
        }

        // Find pivot index
        for (int i = 0; i < n; i++) {
            int leftSum = (i == 0) ? 0 : prefixSum[i - 1];
            int rightSum = (i == n - 1) ? 0 : suffixSum[i + 1];
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 7, 3, 6, 5, 6};
        int[] arr2 = {2, 1, -1};
        int[] arr3 = {1, 2, 3};

        System.out.println("Pivot Index of arr1: " + findPivotIndex(arr1)); // 3
        System.out.println("Pivot Index of arr2: " + findPivotIndex(arr2)); // 0
        System.out.println("Pivot Index of arr3: " + findPivotIndex(arr3)); // -1
        System.out.println("================================================");
        System.out.println("Pivot Index of arr1: " + findPivotIndexTwo(arr1)); // 3
        System.out.println("Pivot Index of arr2: " + findPivotIndexTwo(arr2)); // 0
        System.out.println("Pivot Index of arr3: " + findPivotIndexTwo(arr3)); // -1
    }
}
/*
 * Pivot Index Problem:
 * - Find index where left sum == right sum.
 * - Compute total sum, then iterate with left sum.
 *
 * Complexity:
 * - Time: O(n) → single pass
 * - Space: O(1) → no extra memory
 *
 * Key Point:
 * - Return leftmost pivot if multiple exist.
 * - Return -1 if no pivot index found.
 */
