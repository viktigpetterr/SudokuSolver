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
 * @author Diocles1
 *
 */
public class Graphics extends Application {

	private Sudoku sudoku = new Sudoku();

	/** Adds TextField type to the specific TilePane in a sudoku-pattern. */
	private void newSudokuField(TilePane tilePane, Sudoku sudoku) {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				TextField numField = new OneNumberTextField();
				numField.setMaxSize(40, 40);
				numField.setMinSize(40, 40);
				numField.setAlignment(Pos.CENTER);

				int i2 = i;
				int j2 = j;
				if (j < 3 && i < 3) {
					numField.setStyle("-fx-background-color: #8c8c8c;");
				}
				if (j < 3 && i > 5) {
					numField.setStyle("-fx-background-color: #8c8c8c;");
				}
				if (j > 2 && j < 6 && i > 2 && i < 6) {
					numField.setStyle("-fx-background-color: #8c8c8c;");

				}
				if (j > 5 && i < 3) {
					numField.setStyle("-fx-background-color: #8c8c8c;");
				}
				if (j > 5 && i > 5) {
					numField.setStyle("-fx-background-color: #8c8c8c;");
				}

				numField.textProperty().addListener((observable, oldValue, newValue) -> {
					try {
						Integer.parseInt(newValue);
					} catch (NumberFormatException e) {
						newValue = "0";
					}
					sudoku.setValue(i2, j2, Integer.parseInt(newValue));
				});
				tilePane.getChildren().add(numField);
			}
		}

	}

	/**
	 * Clears the specific TilePane and then adds TextField type to the specific
	 * TilePane in a sudoku-pattern. Then the TextFields textProperty is filled
	 * with the corresponding value in sudoku.
	 * 
	 */

	private void rebuildSolvedSudokuField(TilePane tilePane, Sudoku sudoku) {
		tilePane.getChildren().clear();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				TextField numField = new OneNumberTextField();
				numField.textProperty().set(sudoku.getValueOf(i, j));
				numField.setMaxSize(40, 40);
				numField.setMinSize(40, 40);
				numField.setAlignment(Pos.CENTER);
				int i2 = i;
				int j2 = j;
				if (j < 3 && i < 3) {
					numField.setStyle("-fx-background-color: #8c8c8c;");
				}
				if (j < 3 && i > 5) {
					numField.setStyle("-fx-background-color: #8c8c8c;");
				}
				if (j > 2 && j < 6 && i > 2 && i < 6) {
					numField.setStyle("-fx-background-color: #8c8c8c;");

				}
				if (j > 5 && i < 3) {
					numField.setStyle("-fx-background-color: #8c8c8c;");
				}
				if (j > 5 && i > 5) {
					numField.setStyle("-fx-background-color: #8c8c8c;");
				}

				numField.textProperty().addListener((observable, oldValue, newValue) -> {
					try {
						Integer.parseInt(newValue);
					} catch (NumberFormatException e) {
						newValue = "0";
					}
					sudoku.setValue(i2, j2, Integer.parseInt(newValue));
				});
				tilePane.getChildren().add(numField);
			}
		}

	}

	public void start(Stage stage) throws Exception {
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: #e5e5e5;");
		TilePane tilePane = new TilePane();
		tilePane.setPadding(new Insets(10, 10, 10, 10));
		tilePane.setHgap(7);
		tilePane.setVgap(7);

		newSudokuField(tilePane, sudoku);
		root.setCenter(tilePane);

		Button clear = new Button("Clear");
		clear.setStyle("-fx-base: #f27474");
		clear.setMaxSize(80, 40);
		clear.setMinSize(80, 40);
		clear.setOnAction(event -> {
			tilePane.getChildren().clear();
			sudoku.clear();
			newSudokuField(tilePane, sudoku);

		});

		Button solve = new Button("Solve");
		solve.setMaxSize(80, 40);
		solve.setMinSize(80, 40);
		solve.setDefaultButton(true);
		solve.setStyle(  "-fx-base: #82e584;");
		solve.setOnAction(event -> {
			if (sudoku.checkSudoku(0, 0)) {

				if (sudoku.solve(0, 0) == false) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Notification");
					alert.setHeaderText(null);
					alert.setContentText("Sudoku is unsolvable!");
					alert.showAndWait();
				} else {
					rebuildSolvedSudokuField(tilePane, sudoku);

				}
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Notification");
				alert.setHeaderText(null);
				alert.setContentText("Sudoku is unsolvable!");
				alert.showAndWait();
				return;

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
		Application.launch(args);
	}
}
