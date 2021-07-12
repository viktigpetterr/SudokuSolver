package com.github.viktigpetterr.sudokusolver.sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuTest {

    private Sudoku sudoku;

    @BeforeEach
    public void setUp() {
        sudoku = new Sudoku();
    }

    @Test
    public void testSize() {
        assertEquals(9, sudoku.dimension());
    }

    @Test
    public void testGetValueOf() {
        int row = 0;
        int col = 0;
        int val = 1;

        sudoku.setValue(row, col, val);
        assertEquals(Integer.toString(val), sudoku.getValueOf(col, row));
    }

    @Test
    public void testClear() {
        int row = 0;
        int col = 0;
        int val = 1;

        sudoku.setValue(row, col, val);
        assertEquals(Integer.toString(val), sudoku.getValueOf(col, row));

        sudoku.clear();
        int dimension = sudoku.dimension();
        for (col = 0; col < dimension; col++) {
            for (row = 0; row < dimension; row++) {
                assertEquals(Integer.toString(0), sudoku.getValueOf(row, col));
            }
        }
    }

    @Test
    public void testNumIsLegal() {
        int row = 0;
        int col = 0;

        sudoku.setValue(row, col, 1);
        sudoku.setValue(row, sudoku.dimension() - 1, 1);
        assertFalse(sudoku.numIsLegal(row, col, 1));

        sudoku.setValue(row, sudoku.dimension() - 1, 0);
        sudoku.setValue(sudoku.dimension() - 1, col, 1);
        assertFalse(sudoku.numIsLegal(row, col, 1));

        sudoku.setValue(sudoku.dimension() - 1, col, 0);
        sudoku.setValue(row, col, 1);
        assertFalse(sudoku.numIsLegal(row, col, 1));

        assertTrue(sudoku.numIsLegal(row, col , 2));
    }

    @Test
    public void testCheckSudoku() {
        int row = 0;
        int col = 0;

        sudoku.setValue(row, col, 1);
        sudoku.setValue(row, sudoku.dimension() - 1, 1);
        assertFalse(sudoku.checkSudoku());
    }

    @Test
    public void testSolve() {
        int row = 0;
        int col = 0;

        sudoku.setValue(row, col, 1);
        assertTrue(sudoku.solve());

        sudoku.clear();
        sudoku.setValue(row, col, 1);
        sudoku.setValue(row, sudoku.dimension() - 1, 1);
        assertFalse(sudoku.solve());
    }

}