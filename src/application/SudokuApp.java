/**
 * 
 */
package application;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author CastilloCanedoMichel
 *
 */
public class SudokuApp extends Application {

	private TextField sudokuGrid[][];
	private Stage window;
	private Font font;
	private Text text;
	private FlowPane pane;
	private Scene scene1;
	private Group root;
	private File file;
	private AudioInputStream audioInput;
	private AudioFormat format;
	private Clip soundClip;
	private DataLine.Info data;
	private ChangeListener box;

	@Override
	public void start(Stage window) throws Exception {
		font = new Font("Arial", 40);
		text = new Text(
				"Welcome to Sudoku!\n\nPress the S key to play.\n\nClick on spaces and enter\nnumbers.\n\nPress escape to quit.");
		text.setFont(font);
		pane = new FlowPane(text);
		scene1 = new Scene(pane, 600, 400, Color.WHITESMOKE);
		this.window = window;
		scene1.setOnKeyPressed(this::processKeyPress);
		this.window.setTitle("Wack-A-Mole");
		this.window.setScene(scene1);
		this.window.show();

	}

	private void processKeyPress(KeyEvent event) {
		if (event.getCode() == KeyCode.S) {
			sudokuGrid = new TextField[9][9];
			initializeGrid(sudokuGrid);
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					root = new Group(sudokuGrid[i][j]);
				}
			}

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sudokuGrid[i][j].setOnMouseClicked(this::processMouseClick);
				}
			}

			Scene scene2 = new Scene(root, 750, 750, Color.GHOSTWHITE);
			this.window.setScene(scene2);
		} else if (event.getCode() == KeyCode.ESCAPE) {
			text.setText("Thanks for playing!");
			this.window.setScene(scene1);
			// soundClip.stop();
		}
	}

	void processMouseClick(MouseEvent event) {
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
