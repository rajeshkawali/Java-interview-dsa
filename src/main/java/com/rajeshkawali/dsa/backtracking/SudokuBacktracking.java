package com.rajeshkawali.dsa.backtracking;
public class SudokuBacktracking {

    // Time Complexity: ~O(9^(n*n)) worst case (huge search space)
    // Space Complexity: O(n*n) for board + recursion stack
    public static boolean solveSudoku(int[][] board) {
        int n = board.length;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == 0) { // empty cell
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num; // place number
                            if (solveSudoku(board)) return true;
                            board[row][col] = 0; // backtrack
                        }
                    }
                    return false; // no valid number found
                }
            }
        }
        return true; // solved
    }

    private static boolean isSafe(int[][] board, int row, int col, int num) {
        // check row & column
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) return false;
        }
        // check 3x3 subgrid
        int startRow = row - row % 3, startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) return false;
            }
        }
        return true;
    }

    public static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) System.out.print(cell + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = {
            {5,3,0,0,7,0,0,0,0},
            {6,0,0,1,9,5,0,0,0},
            {0,9,8,0,0,0,0,6,0},
            {8,0,0,0,6,0,0,0,3},
            {4,0,0,8,0,3,0,0,1},
            {7,0,0,0,2,0,0,0,6},
            {0,6,0,0,0,0,2,8,0},
            {0,0,0,4,1,9,0,0,5},
            {0,0,0,0,8,0,0,7,9}
        };

        if (solveSudoku(board)) {
            System.out.println("Sudoku solved:");
            printBoard(board);
        } else {
            System.out.println("No solution exists!");
        }
    }
}


/*
Sudoku Solver (Backtracking):
Sudoku is a 9×9 grid divided into 3×3 subgrids.
Fill empty cells (0) with digits 1–9.
Each row, column, and 3×3 box must contain all digits 1–9 without repetition.

Backtracking tries numbers one by one:
Place a number if it’s valid.
Move forward.
If stuck, undo and try another number.

Short Note:
Backtracking tries filling each empty cell with digits 1–9.
If a number violates Sudoku rules, it undoes and tries another.
This shows how backtracking can solve constraint-heavy problems.

*/