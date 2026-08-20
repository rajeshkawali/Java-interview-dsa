package com.rajeshkawali.dsa.searching;

public class RowWithMaximumOnes {

    /*
     * Problem:
     * --------
     * Given a binary matrix (rows sorted: 0s then 1s),
     * find the row index with the maximum number of 1's.
     *
     * Example:
     * Input:
     * 0 0 1 1
     * 0 1 1 1
     * 0 0 0 1
     *
     * Output: Row 1 (index 1)
     *
     * Logic:
     * 1. Each row is sorted (0s then 1s).
     * 2. Use Binary Search to find the first occurrence of 1 in each row.
     * 3. Count of 1's = total columns - index of first 1.
     * 4. Track row with maximum count.
     *
     * Time Complexity: O(m * log n)  (m = rows, n = cols)
     * Space Complexity: O(1)
     */

    // Binary Search to find first occurrence of 1 in a row
    public static int firstOneIndex(int[] row) {
        int left = 0, right = row.length - 1, result = row.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (row[mid] == 1) {
                result = mid;
                right = mid - 1; // search left for earlier 1
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    // Find row with maximum 1's
    public static int rowWithMaxOnes(int[][] matrix) {
        int maxRow = -1;
        int maxCount = -1;

        for (int i = 0; i < matrix.length; i++) {
            int firstOne = firstOneIndex(matrix[i]);
            int countOnes = matrix[i].length - firstOne;
            if (countOnes > maxCount) {
                maxCount = countOnes;
                maxRow = i;
            }
        }
        return maxRow;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {0, 0, 1, 1},
            {0, 1, 1, 1},
            {0, 0, 0, 1}
        };

        System.out.println("Matrix:");
        for (int[] row : matrix) {
            for (int val : row) System.out.print(val + " ");
            System.out.println();
        }
        System.out.println("==============================");

        int result = rowWithMaxOnes(matrix);
        System.out.println("Row with maximum 1's: " + result);
    }
}

/*

Row with Maximum 1's:
- Each row sorted (0s then 1s).
- Use Binary Search to find first 1 in each row.
- Count = total columns - index of first 1.
- Track row with maximum count.
- Time: O(m * log n), Space: O(1).

*/