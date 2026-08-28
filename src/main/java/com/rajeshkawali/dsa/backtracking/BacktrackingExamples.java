package com.rajeshkawali.dsa.backtracking;

public class BacktrackingExamples {

    /*
     * Backtracking:
     * -------------
     * A general algorithmic technique that tries possible solutions
     * and abandons ("backtracks") when a solution path fails.
     *
     * Key Points:
     * - Explore all possibilities.
     * - Use recursion with decision + undo step.
     * - Common in puzzles, pathfinding, and combinatorial problems.
     */

    // 1. N-Queens Problem
    // Place N queens on an NxN chessboard so that no two queens attack each other.
    // Time: O(N!), Space: O(N) (recursion stack + board state)
    public static boolean solveNQueens(int board[][], int row, int n) {
        if (row == n) return true; // Base case: all queens placed

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 1; // Place queen
                if (solveNQueens(board, row + 1, n)) return true;
                board[row][col] = 0; // Backtrack (remove queen)
            }
        }
        return false;
    }

    private static boolean isSafe(int[][] board, int row, int col, int n) {
        // Check column
        for (int i = 0; i < row; i++) if (board[i][col] == 1) return false;
        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1) return false;
        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++)
            if (board[i][j] == 1) return false;
        return true;
    }

    // 2. Rat in a Maze
    // Find a path from top-left to bottom-right in a maze (1 = open, 0 = blocked).
    // Time: O(2^(n*m)), Space: O(n*m) (path storage + recursion stack)
    public static boolean solveMaze(int[][] maze, int x, int y, int[][] sol) {
        int n = maze.length;
        if (x == n - 1 && y == n - 1 && maze[x][y] == 1) {
            sol[x][y] = 1;
            return true;
        }
        if (isSafeMaze(maze, x, y)) {
            sol[x][y] = 1;
            if (solveMaze(maze, x + 1, y, sol)) return true; // Move down
            if (solveMaze(maze, x, y + 1, sol)) return true; // Move right
            sol[x][y] = 0; // Backtrack
        }
        return false;
    }

    private static boolean isSafeMaze(int[][] maze, int x, int y) {
        return (x >= 0 && y >= 0 && x < maze.length && y < maze.length && maze[x][y] == 1);
    }

    // 3. Generate All Subsets (Power Set)
    // Time: O(2^n), Space: O(n) (recursion stack)
    public static void generateSubsets(String str, String current, int index) {
        if (index == str.length()) {
            System.out.println(current);
            return;
        }
        // Include current character
        generateSubsets(str, current + str.charAt(index), index + 1);
        // Exclude current character (backtrack)
        generateSubsets(str, current, index + 1);
    }

    public static void main(String[] args) {
        // N-Queens Example
        int n = 4;
        int[][] board = new int[n][n];
        if (solveNQueens(board, 0, n)) {
            System.out.println("N-Queens solution:");
            for (int[] row : board) {
                for (int cell : row) System.out.print(cell + " ");
                System.out.println();
            }
        }

        // Rat in a Maze Example
        int[][] maze = {{1, 0, 0, 0},
                        {1, 1, 0, 0},
                        {0, 1, 0, 0},
                        {1, 1, 1, 1}};
        int[][] sol = new int[4][4];
        if (solveMaze(maze, 0, 0, sol)) {
            System.out.println("Maze solution:");
            for (int[] row : sol) {
                for (int cell : row) System.out.print(cell + " ");
                System.out.println();
            }
        }

        // Subset Generation Example
        System.out.println("Subsets of 'abc':");
        generateSubsets("abc", "", 0);
    }
}

/*
Backtracking is a problem-solving technique in computer science where we build solutions step by step,
and whenever we realize a step leads to a dead end, we undo (or “backtrack”) that step and try another path.

Think of it like exploring a maze:
You move forward until you hit a wall.
Instead of giving up, you step back to the last decision point and try a different direction.
Eventually, you either find a valid path or confirm no solution exists.
*/
