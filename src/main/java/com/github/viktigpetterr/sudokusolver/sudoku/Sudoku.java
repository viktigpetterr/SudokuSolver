package com.github.viktigpetterr.sudokusolver.sudoku;

/**
 * Describes a sudoku in shape of a Integer matrix with 9x9 squares. Class also
 * include solving-methods for the sudoku and other relevant methods.
 *
 * @author viktigpetterr
 */
public class Sudoku {

    private final int[][] matrix;

    private static final int DIMENSION = 9;
    private static final int UNSET = 0;

    /**
     * Constructs a sudoku board in shape of a Integer matrix with 9x9 squares.
     */
    public Sudoku() {
        matrix = new int[DIMENSION][DIMENSION];
    }

    /**
     * Returns the  value in the specific place(row, column) of the matrix.
     *
     * @param row - specifies row for the wanted place in the matrix.
     * @param col - specifies column for the wanted place in the matrix.
     * @return The Integer value in the specific place in the matrix.
     */
    public String getValueOf(int row, int col) {
        return Integer.toString(matrix[row][col]);
    }

    /**
     * Sets the value in the specific place(row, column) in the matrix.
     *
     * @param row   - specifies row for which the value shall be set in the
     *              matrix.
     * @param col   - specifies column for which the value shall be set in the
     *              matrix.
     * @param value - The value which is to be set in the matrix.
     */
    public void setValue(int row, int col, int value) {
        matrix[row][col] = value;
    }

    /**
     * @return the dimension of the matrix.
     */
    public int dimension() {
        return DIMENSION;
    }

    /**
     * Clears all Integers in the matrix and replace them with zeros.
     */
    public void clear() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = UNSET;
            }
        }
    }

    /**
     * Checks if the number(num) already exist in the specific column, row or
     * square.
     */
    public boolean numIsLegal(int row, int col, int num) {
        // Check the column and the row.
        for (int i = 0; i < DIMENSION; ++i) {
            if (num == matrix[row][i] || num == matrix[i][col]) {
                return false;
            }
        }
        // Check the square.
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (num == matrix[boxRow + i][boxCol + j]) {
                    return false;
                }
            }
        }
        return true; // No faults, so it's ok.
    }

    /**
     * Checks if the current values in the matrix are
     * legal according to Sudoku rules.
     *
     * @return Returns true if the current values in the matrix are legal
     * according to Sudoku rules. Otherwise false.
     */
    public boolean checkSudoku() {
        return checkSudoku(0, 0);
    }

    /**
     * Backtracking method that checks if the current values in the matrix are
     * legal according to Sudoku rules.
     *
     * @param row - The row where the check will start.
     * @param col - The column where the check will start.
     * @return Returns true if the current values in the matrix are legal
     * according to Sudoku rules. Otherwise false.
     */
    private boolean checkSudoku(int row, int col) {
        if (row == DIMENSION) {
            row = 0;
            col++;
            if (col == DIMENSION) {
                return true; // All places in the matrix has been checked.
            }
        }
        if (matrix[row][col] == UNSET) {
            return checkSudoku(row + 1, col);
        }
        int current = matrix[row][col];
        matrix[row][col] = UNSET;
        if (numIsLegal(row, col, current)) {
            if (checkSudoku(row + 1, col)) {
                matrix[row][col] = current;
                return true;
            }
        }

        matrix[row][col] = current; // reset on backtrack
        return false;
    }

    /**
     * Method for solving the Matrix according to Sudoku rules.
     *
     * @return Returns true if the Sudoku has been solved according to Sudoku
     * rules. Otherwise false.
     */
    public boolean solve() {
        return solve(0, 0);
    }
    /**
     * Backtracking method for solving the Matrix according to Sudoku rules.
     *
     * @param row - The row where the solving shall begin.
     * @param col - The column where the solving shall begin.
     * @return Returns true if the Sudoku has been solved according to Sudoku
     * rules. Otherwise false.
     */
    public boolean solve(int row, int col) {
        if (!checkSudoku(row, col))  {
            return false;
        }

        if (row == DIMENSION) {
            row = 0;
            col++;
            if (col == DIMENSION) {
                return true; // All places in the matrix has been checked.
            }
        }

        if (matrix[row][col] != UNSET) {
            return solve(row + 1, col);
        }

        for (int num = 1; num <= DIMENSION; num++) {
            if (numIsLegal(row, col, num)) {
                matrix[row][col] = num;
                if (solve(row + 1, col)) {
                    return true;
                }
            }
        }
        matrix[row][col] = UNSET; // reset on backtrack
        return false;
    }
}
