package com.github.viktigpetterr.sudokusolver.javafx;

import com.github.viktigpetterr.sudokusolver.sudoku.Sudoku;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * Graphics describes an UI for a sudoku class. It includes the possibility to
 * solve different sudoku games.
 *
 * @author viktigpetterr
 */
public class App extends Application {

    private final Sudoku sudoku = new Sudoku();

    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #e5e5e5;");

        SudokuTilePane sudokuTilePane = new SudokuTilePane(sudoku);
        root.setCenter(sudokuTilePane);

        Button clear = new Button("Clear");
        clear.setStyle("-fx-base: #f27474");
        clear.setMaxSize(80, 40);
        clear.setMinSize(80, 40);
        clear.setOnAction(event -> {
            sudoku.clear();
            sudokuTilePane.refresh(true);
        });

        Button solve = new Button("Solve");
        solve.setMaxSize(80, 40);
        solve.setMinSize(80, 40);
        solve.setDefaultButton(true);
        solve.setStyle("-fx-base: #82e584;");
        solve.setOnAction(event -> {
            if (sudoku.solve()) {
                sudokuTilePane.refresh(false);
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText(null);
                alert.setContentText("Sudoku is unsolvable!");
                alert.showAndWait();
            }
        });

        HBox hBoxBottom = new HBox();
        hBoxBottom.getChildren().addAll(solve, clear);
        hBoxBottom.setSpacing(30);
        hBoxBottom.setPadding(new Insets(20));
        hBoxBottom.setAlignment(Pos.BASELINE_CENTER);

        root.setBottom(hBoxBottom);

        Scene scene = new Scene(root);
        stage.setHeight(550);
        stage.setWidth(445);
        stage.setResizable(false);

        stage.setTitle("Sudoku Solver");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        App.launch(args);
    }
}
