package com.rajeshkawali.dsa.searching;

public class SearchIn2DMatrixI {

    /*
     * Problem I:
     * ----------
     * Matrix is sorted row-wise, and each row's first element
     * is greater than the last element of the previous row.
     * Treat matrix as a flattened sorted array.
     *
     * Time Complexity: O(log(m*n))
     * Space Complexity: O(1)
     */

    public static boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int left = 0, right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / cols][mid % cols];

            if (midValue == target) return true;
            if (midValue < target) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        int target = 3;

        System.out.println("Search result: " + searchMatrix(matrix, target));
    }
}
/*

Search in 2D Matrix I:
- Matrix behaves like a flattened sorted array.
- Use Binary Search.
- Time: O(log(m*n)), Space: O(1).

*/