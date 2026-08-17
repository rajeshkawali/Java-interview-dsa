package com.rajeshkawali.dsa.array2d;

public class ReverseMatrixAndArray {

    /*
     * Problem Set:
     * 1. Print 2D matrix row-wise in reverse order.
     * 2. Print 2D matrix column-wise in reverse order.
     * 3. Print 1D array in reverse order.
     *
     * Time Complexity: O(m*n) for matrix, O(n) for array
     * Space Complexity: O(1)
     */

    // 1. Print 2D matrix row-wise in reverse order
    public static void printMatrixRowReverse(int[][] matrix) {
        System.out.println("Matrix Row-wise Reverse:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = matrix[i].length - 1; j >= 0; j--) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 2. Print 2D matrix column-wise in reverse order
    public static void printMatrixColumnReverse(int[][] matrix) {
        System.out.println("Matrix Column-wise Reverse:");
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int j = 0; j < cols; j++) {
            for (int i = rows - 1; i >= 0; i--) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 3. Print 1D array in reverse order
    public static void printArrayReverse(int[] arr) {
        System.out.println("Array Reverse:");
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[] array = {10, 20, 30, 40, 50};
        System.out.println("================================");
        printMatrixRowReverse(matrix);
        System.out.println("================================");
        printMatrixColumnReverse(matrix);
        System.out.println("================================");
        printArrayReverse(array);
        System.out.println("================================");
    }
}
/*
 * Reverse Printing:
 * - Row-wise reverse → iterate rows, print each row backwards.
 * - Column-wise reverse → iterate columns, print each column bottom to top.
 * - Array reverse → iterate array backwards.
 *
 * Time: O(m*n) for matrix, O(n) for array
 * Space: O(1)
 */
