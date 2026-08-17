package com.rajeshkawali.dsa.array2d;

public class SpiralPrintMatrix {

    /*
     * Problem:
     *   Print elements of a matrix in spiral order.
     *
     * Example:
     *   Input:
     *     1  2  3
     *     4  5  6
     *     7  8  9
     *
     *   Output (Spiral Print):
     *     1 2 3 6 9 8 7 4 5
     *
     * Logic:
     * 1. Maintain four boundaries: top, bottom, left, right.
     * 2. Traverse:
     *    - Left → Right (top row)
     *    - Top → Bottom (right column)
     *    - Right → Left (bottom row, if still valid)
     *    - Bottom → Top (left column, if still valid)
     * 3. Shrink boundaries after each traversal.
     * 4. Continue until all elements are printed.
     *
     * Time Complexity: O(m * n)
     * Space Complexity: O(1)
     */
    public static void spiralPrint(int[][] matrix) {
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

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

            // Traverse bottom row (if still valid)
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    System.out.print(matrix[bottom][j] + " ");
                }
                bottom--;
            }

            // Traverse left column (if still valid)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    System.out.print(matrix[i][left] + " ");
                }
                left++;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("Spiral Print of Matrix:");
        spiralPrint(matrix);
    }
}
/*
 * Spiral Print Matrix:
 * - Use four boundaries: top, bottom, left, right.
 * - Traverse in four directions and shrink boundaries.
 * - Continue until all elements are covered.
 *
 * Time: O(m*n)
 * Space: O(1)
 */
