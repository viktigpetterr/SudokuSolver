package com.github.viktigpetterr.sudokusolver.javafx;

import com.github.viktigpetterr.sudokusolver.sudoku.Sudoku;
import javafx.geometry.Insets;
import javafx.scene.layout.TilePane;

public class SudokuTilePane extends TilePane {

    private final Sudoku sudoku;

    private static final String DEFAULT_VALUE = "0";

    public SudokuTilePane(Sudoku sudoku) {
        this.sudoku = sudoku;

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setHgap(7);
        this.setVgap(7);

        this.refresh(true);
    }

    private boolean styled(int i, int j) {
        return
                (j < 3 && i < 3) ||
                (j < 3 && i > 5) ||
                (j > 2 && j < 6 && i > 2 && i < 6) ||
                (j > 5 && i < 3) ||
                (j > 5 && i > 5);
    }

    public void refresh(boolean clear) {
        this.getChildren().clear();
        for (int i = 0; i < sudoku.dimension(); i++) {
            for (int j = 0; j < sudoku.dimension(); j++) {
                OneNumberTextField numField = new OneNumberTextField(styled(i, j));
                if (!clear) {
                    numField.setNumber(sudoku.getValueOf(i, j));
                }
                int finalI = i;
                int finalJ = j;
                numField.textProperty().addListener((observable, oldValue, newValue) -> {
                    try {
                        Integer.parseInt(newValue);
                    } catch (NumberFormatException e) {
                        newValue = DEFAULT_VALUE;
                    }
                    sudoku.setValue(finalI, finalJ, Integer.parseInt(newValue));
                });

                this.getChildren().add(numField);
            }
        }
    }

}
