package com.rajeshkawali.dsa.array2d;

public class RotateMatrix {

    /*
     * Problem:
     *   Rotate a square matrix (n x n) by 90°, 180°, 270°.
     *   Rotations can be clockwise or anticlockwise.
     *
     * Key Idea:
     * - Rotation is achieved using transpose + reverse operations.
     * - For 90° clockwise: transpose + reverse each row.
     * - For 90° anticlockwise: transpose + reverse each column.
     * - For 180°: reverse rows + reverse columns.
     * - For 270° clockwise: same as 90° anticlockwise.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1) (in-place)
     */

    // Utility method to print matrix
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Transpose of matrix (swap across diagonal)
    public static void transpose(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    // Reverse each row
    public static void reverseRows(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }

    // Reverse each column
    public static void reverseColumns(int[][] matrix) {
        int n = matrix.length;
        for (int j = 0; j < n; j++) {
            int top = 0, bottom = n - 1;
            while (top < bottom) {
                int temp = matrix[top][j];
                matrix[top][j] = matrix[bottom][j];
                matrix[bottom][j] = temp;
                top++;
                bottom--;
            }
        }
    }

    // Rotate 90° clockwise
    public static void rotate90Clockwise(int[][] matrix) {
        transpose(matrix);
        reverseRows(matrix);
    }

    // Rotate 90° anticlockwise
    public static void rotate90AntiClockwise(int[][] matrix) {
        transpose(matrix);
        reverseColumns(matrix);
    }

    // Rotate 180°
    public static void rotate180(int[][] matrix) {
        reverseRows(matrix);
        reverseColumns(matrix);
    }

    // Rotate 270° clockwise (same as 90° anticlockwise)
    public static void rotate270Clockwise(int[][] matrix) {
        rotate90AntiClockwise(matrix);
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("Original Matrix:");
        printMatrix(matrix);
        System.out.println("================================");
        rotate90Clockwise(matrix);
        System.out.println("90° Clockwise:");
        printMatrix(matrix);
        System.out.println("================================");
        rotate90AntiClockwise(matrix);
        System.out.println("90° AntiClockwise (back to original):");
        printMatrix(matrix);
        System.out.println("================================");
        rotate180(matrix);
        System.out.println("180° Rotation:");
        printMatrix(matrix);
        System.out.println("================================");
        rotate270Clockwise(matrix);
        System.out.println("270° Clockwise:");
        printMatrix(matrix);
        System.out.println("================================");
    }
}
/*
 * Matrix Rotation Summary:
 * - 90° Clockwise → transpose + reverse rows
 * - 90° AntiClockwise → transpose + reverse columns
 * - 180° → reverse rows + reverse columns
 * - 270° Clockwise → same as 90° AntiClockwise
 *
 * Time: O(n^2)
 * Space: O(1)
 */
