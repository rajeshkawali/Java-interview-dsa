package com.rajeshkawali.dsa.recursion;



public class InversionCount {

    /*
     * Inversion Count using Merge Sort
     * --------------------------------
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     * Logic: Count inversions during merge step.
     * 
     * Example:
		Array = [2, 4, 1, 3, 5]  
		Inversions = (2,1), (4,1), (4,3) → Count = 3.
     */

    public static int mergeAndCount(int[] arr, int left, int mid, int right) {
        int[] L = java.util.Arrays.copyOfRange(arr, left, mid + 1);
        int[] R = java.util.Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left, swaps = 0;

        while (i < L.length && j < R.length) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
                swaps += (L.length - i); // all remaining L[i...] are inversions
            }
        }

        while (i < L.length) arr[k++] = L[i++];
        while (j < R.length) arr[k++] = R[j++];

        return swaps;
    }

    public static int mergeSortAndCount(int[] arr, int left, int right) {
        int count = 0;
        if (left < right) {
            int mid = (left + right) / 2;
            count += mergeSortAndCount(arr, left, mid);
            count += mergeSortAndCount(arr, mid + 1, right);
            count += mergeAndCount(arr, left, mid, right);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 3, 5};
        int result = mergeSortAndCount(arr, 0, arr.length - 1);
        System.out.println("Inversion Count: " + result);
    }
}
/*
Note
Inversions measure how far an array is from being sorted.
Merge Sort efficiently counts them while sorting.
Useful in problems like minimum swaps to sort or array disorder measurement.
 */
