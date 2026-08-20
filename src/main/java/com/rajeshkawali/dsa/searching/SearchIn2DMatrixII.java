package com.rajeshkawali.dsa.searching;

public class SearchIn2DMatrixII {

    /*
     * Problem II:
     * -----------
     * Matrix is sorted row-wise and column-wise.
     * Start from top-right corner:
     * - If target == element → found.
     * - If target < element → move left.
     * - If target > element → move down.
     *
     * Time Complexity: O(m + n)
     * Space Complexity: O(1)
     */

    public static boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int row = 0, col = cols - 1;

        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) return true;
            if (matrix[row][col] > target) col--; // move left
            else row++; // move down
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 4, 7, 11},
            {2, 5, 8, 12},
            {3, 6, 9, 16},
            {10, 13, 14, 17}
        };
        int target = 5;

        System.out.println("Search result: " + searchMatrix(matrix, target));
    }
}


/*

Search in 2D Matrix I:
- Matrix behaves like a flattened sorted array.
- Use Binary Search.
- Time: O(log(m*n)), Space: O(1).

Search in 2D Matrix II:
- Matrix sorted row-wise and column-wise.
- Start from top-right corner.
- Eliminate row/column each step.
- Time: O(m+n), Space: O(1).

*/