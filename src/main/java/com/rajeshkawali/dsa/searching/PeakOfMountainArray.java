package com.rajeshkawali.dsa.searching;

public class PeakOfMountainArray {

    /*
     * Problem:
     * --------
     * Find the peak element in a mountain array.
     * A mountain array is one that increases strictly
     * then decreases strictly (like a mountain).
     *
     * Logic:
     * 1. Use Binary Search.
     * 2. Compare mid with mid+1.
     *    - If arr[mid] < arr[mid+1] → peak lies on right side.
     *    - Else → peak lies on left side (including mid).
     * 3. Continue until left == right → peak index.
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */

    public static int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid + 1]) {
                // Peak lies on right side
                left = mid + 1;
            } else {
                // Peak lies on left side (including mid)
                right = mid;
            }
        }
        return left; // or right, both are same here
    }

    public static void main(String[] args) {
        int[] mountainArr = {1, 3, 5, 7, 6, 4, 2};

        System.out.println("Mountain Array:");
        for (int val : mountainArr) System.out.print(val + " ");
        System.out.println("\n==============================");

        int peakIndex = peakIndexInMountainArray(mountainArr);
        System.out.println("Peak found at index: " + peakIndex);
        System.out.println("Peak element: " + mountainArr[peakIndex]);
    }
}


/*
Peak of Mountain Array:
- Definition: Index of maximum element in a strictly increasing then decreasing array.
- Approach: Binary Search on mid vs mid+1.
- Time Complexity: O(log n)
- Space Complexity: O(1)

*/