package com.rajeshkawali.dsa.array2d;

public class WavePrintMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("================================");
        wavePrintColumnWise(matrix);
        System.out.println("================================");
        wavePrintRowWise(matrix);
        System.out.println("================================");
        spiralPrint(matrix);
        System.out.println("================================");
    }
    
    
    /*
     * Problem:
     *   Print a matrix in wave form (column-wise).
     *
     * Example:
     *   Input:
     *     1  2  3
     *     4  5  6
     *     7  8  9
     *
     *   Output (Wave Print):
     *     1 4 7 8 5 2 3 6 9
     *
     * Logic:
     * 1. Traverse column by column.
     * 2. If column index is even → print top to bottom.
     * 3. If column index is odd → print bottom to top.
     *
     * Time Complexity: O(m * n) → m rows, n columns
     * Space Complexity: O(1) → no extra memory
     */
    // 1. Wave print (column-wise)
    public static void wavePrintColumnWise(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        System.out.print("Column-wise Wave Print: ");
        for (int j = 0; j < cols; j++) {
            if (j % 2 == 0) {
            		// Even column → top to bottom
                for (int i = 0; i < rows; i++) {
                    System.out.print(matrix[i][j] + " ");
                }
            } else {
            		// Odd column → bottom to top
                for (int i = rows - 1; i >= 0; i--) {
                    System.out.print(matrix[i][j] + " ");
                }
            }
        }
        System.out.println();
    }

    // 2. Wave print (row-wise)
    public static void wavePrintRowWise(int[][] matrix) {
        int rows = matrix.length;
        System.out.print("Row-wise Wave Print: ");
        for (int i = 0; i < rows; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < matrix[i].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
            } else {
                for (int j = matrix[i].length - 1; j >= 0; j--) {
                    System.out.print(matrix[i][j] + " ");
                }
            }
        }
        System.out.println();
    }

    // 3. Spiral print
    public static void spiralPrint(int[][] matrix) {
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;
        System.out.print("Spiral Print: ");
        while (top <= bottom && left <= right) {
            // Traverse top row
            for (int j = left; j <= right; j++) {
                System.out.print(matrix[top][j] + " ");
            }
            top++;
            // Traverse right column
            for (int i = top; i <= bottom; i++) {
                System.out.print(matrix[i][right] + " ");
            }
            right--;
            // Traverse bottom row
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    System.out.print(matrix[bottom][j] + " ");
                }
                bottom--;
            }
            // Traverse left column
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    System.out.print(matrix[i][left] + " ");
                }
                left++;
            }
        }
        System.out.println();
    }
    
    
    
    
    
    
}
/*
 * Wave Print Matrix: (column-wise)
 * - Traverse column by column.
 * - Even column → top to bottom.
 * - Odd column → bottom to top.
 *
 * Time: O(m*n)
 * Space: O(1)
 *
 * Key Point:
 * - This is a variation of matrix traversal questions (spiral, zig-zag, boundary).
 */
