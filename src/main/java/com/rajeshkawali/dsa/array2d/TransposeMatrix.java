package com.rajeshkawali.dsa.array2d;



public class TransposeMatrix {

    /*
     * Problem:
     *   Given a matrix (2D array), find its transpose.
     *   Transpose means converting rows into columns and columns into rows.
     *
     * Example:
     *   Input:
     *     1 2 3
     *     4 5 6
     *
     *   Output (Transpose):
     *     1 4
     *     2 5
     *     3 6
     *
     * Logic:
     * 1. For an m x n matrix, the transpose will be n x m.
     * 2. Create a new matrix of size n x m.
     * 3. Copy elements: transpose[j][i] = matrix[i][j].
     *
     * Time Complexity: O(m * n)
     * Space Complexity: O(m * n) for new matrix
     */
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] transpose = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transpose[j][i] = matrix[i][j];
            }
        }
        return transpose;
    }

    /*
     * In-place transpose (only works for square matrices)
     * - Swap elements across the diagonal.
     * - matrix[i][j] ↔ matrix[j][i] for i < j.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static void transposeInPlace(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    // Utility method to print matrix
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6}
        };
        System.out.println("================================");
        System.out.println("Original Matrix:");
        printMatrix(matrix);
        System.out.println("================================");
        System.out.println("Transpose (new matrix):");
        int[][] transposed = transpose(matrix);
        printMatrix(transposed);
        System.out.println("================================");
        int[][] squareMatrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Original Square Matrix:");
        printMatrix(squareMatrix);
        System.out.println("================================");
        transposeInPlace(squareMatrix);
        System.out.println("Transpose (in-place):");
        printMatrix(squareMatrix);
        System.out.println("================================");
    }
}
/*
 * Transpose of Matrix:
 * - For m x n matrix → n x m transpose.
 * - Use new matrix for rectangular arrays.
 * - Use in-place swap for square arrays.
 *
 * Time: O(m*n)
 * Space: O(m*n) for new matrix, O(1) for in-place.
 */
