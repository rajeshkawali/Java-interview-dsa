package com.rajeshkawali.dsa.array2d;



public class MatrixSumProblems {

    /*
     * Problem Set: Common Interview Questions on 2D Arrays
     * ----------------------------------------------------
     * 1. Print sum of each row
     * 2. Print sum of each column
     * 3. Print sum of main diagonal (top-left to bottom-right)
     * 4. Print sum of secondary diagonal (top-right to bottom-left)
     * 5. Print total sum of matrix
     *
     * Time Complexity: O(m * n) for row/column sums
     * Space Complexity: O(1) → only a few variables
     */
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        printRowSums(matrix);
        System.out.println("================================");
        printColumnSums(matrix);
        System.out.println("================================");
        printMainDiagonalSum(matrix);
        System.out.println("================================");
        printSecondaryDiagonalSum(matrix);
        System.out.println("================================");
        printTotalSum(matrix);
    }

    // 1. Sum of each row
    public static void printRowSums(int[][] arr) {
    		int rows = arr.length;
        int cols = arr[0].length;
        for (int i = 0; i < rows; i++) {
            int sum = 0;
            for (int j = 0; j < cols; j++) {
                sum += arr[i][j];
            }
            System.out.println("Sum of row " + i + " = " + sum);
        }
    }

    // 2. Sum of each column
    public static void printColumnSums(int[][] arr) {
        int rows = arr.length;
        int cols = arr[0].length;
        for (int j = 0; j < cols; j++) {
            int sum = 0;
            for (int i = 0; i < rows; i++) {
                sum += arr[i][j];
            }
            System.out.println("Sum of column " + j + " = " + sum);
        }
    }

    // 3. Sum of main diagonal (i == j)
    public static void printMainDiagonalSum(int[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i][i];
        }
        System.out.println("Sum of main diagonal = " + sum);
    }

    // 4. Sum of secondary diagonal (i + j == n-1)
    public static void printSecondaryDiagonalSum(int[][] arr) {
        int sum = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            sum += arr[i][n - 1 - i];
        }
        System.out.println("Sum of secondary diagonal = " + sum);
    }

    // 5. Total sum of matrix
    public static void printTotalSum(int[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sum += arr[i][j];
            }
        }
        System.out.println("Total sum of matrix = " + sum);
    }

}

/*
 * Interview Variations on Matrix Sum:
 * - Row sums → O(m*n)
 * - Column sums → O(m*n)
 * - Diagonal sums → O(n)
 * - Total sum → O(m*n)
 *
 * Key Point:
 * - These are building blocks for more advanced matrix problems
 *   like spiral traversal, boundary sum, or saddle point.
 */
