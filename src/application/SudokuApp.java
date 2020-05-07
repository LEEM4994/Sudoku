/**
 * 
 */
package application;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
	private Font font1;
	private Text text;
	private FlowPane pane;
	private GridPane gridPane;
	private Scene scene1;
	private Group root;
	private File file;
	private AudioInputStream audioInput;
	private AudioFormat format;
	private Clip soundClip;
	private DataLine.Info data;
	private BackgroundFill redFill;
	private Background incorrectFill;
	private BackgroundFill whiteFill;
	private Background correctFill;

	@Override
	public void start(Stage window) throws Exception {
		font = new Font("Arial", 40);
		font1 = Font.font(20);
		text = new Text(
				"Welcome to Sudoku!\n\nPress enter to play.\n\nClick on spaces and enter\nnumbers.\n\nPress escape to quit.");
		text.setFont(font);
		pane = new FlowPane(text);
		scene1 = new Scene(pane, 600, 400, Color.WHITESMOKE);
		this.window = window;
		scene1.setOnKeyPressed(this::processKeyPress);
		this.window.setTitle("Sudoku");
		this.window.setScene(scene1);
		this.window.show();

	}

	private void processKeyPress(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			sudokuGrid = new TextField[9][9];
			SudokuLogic.initializeGrid(sudokuGrid);
			gridPane = new GridPane();

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sudokuGrid[i][j].setPrefWidth(75);
					sudokuGrid[i][j].setPrefHeight(75);
					sudokuGrid[i][j].setAlignment(Pos.CENTER);
					sudokuGrid[i][j].setFont(font1);
					gridPane.add(sudokuGrid[i][j], i, j);

				}
			}

			//lines to separate grids
			Line line1 = new Line(675, 225, 0, 225);
			line1.setStrokeWidth(6);
			line1.setStroke(Color.LIGHTBLUE);
			Line line2 = new Line(675, 450, 0, 450);
			line2.setStrokeWidth(6);
			line2.setStroke(Color.LIGHTBLUE);
			Line line3 = new Line(225, 675, 225, 0);
			line3.setStrokeWidth(6);
			line3.setStroke(Color.LIGHTBLUE);
			Line line4 = new Line(450, 675, 450, 0);
			line4.setStrokeWidth(6);
			line4.setStroke(Color.LIGHTBLUE);

			root = new Group(gridPane, line1, line2, line3, line4);

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sudokuGrid[i][j].setOnMouseClicked(this::processMouseClick);
				}
			}

			Scene scene2 = new Scene(root, 750, 750, Color.GHOSTWHITE);
			this.window.setScene(scene2);

			scene2.setOnKeyPressed(this::processKeyPress);

		} else if (event.getCode() == KeyCode.ESCAPE) {
			text.setText("Thanks for playing!");
			this.window.setScene(scene1);
			soundClip.stop();
		}
	}

	void processMouseClick(MouseEvent event) {
		boolean correctEntries = false;
		redFill = new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY);
		incorrectFill = new Background(redFill);
		
		whiteFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
		correctFill = new Background(whiteFill);

		SudokuLogic.getUserNumbers(sudokuGrid);
		for (int i = 0; i <= 8; i++) {
			for (int j = 0; j <= 8; j++) {
				correctEntries = SudokuLogic.checkUserEntry(i, j);
				if (Integer.valueOf(sudokuGrid[i][j].getText()) == 0 || correctEntries == true) {
					sudokuGrid[i][j].setBackground(correctFill);
				} else if (correctEntries == false) {
					sudokuGrid[i][j].setBackground(incorrectFill);
					//incorrect sound
					try {
						file = new File("incorrect.wav");
						audioInput = AudioSystem.getAudioInputStream(file);
						format = audioInput.getFormat();
						data = new DataLine.Info(Clip.class, format);
						soundClip = (Clip) AudioSystem.getLine(data);
						soundClip.open(audioInput);
						soundClip.start();
					} catch (Exception e) {
						System.out.println("Exception thrown");

					}
				}
			}
		}
	}

	public static void main(String[] args) {
		launch(args);

	}

}
