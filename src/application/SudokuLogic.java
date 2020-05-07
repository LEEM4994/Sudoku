package application;

import javafx.scene.control.TextField;

public class SudokuLogic {
	
	private static int answerKey[][] = { 
		{3, 5, 9, 2, 4, 6, 1, 7, 8},
		{7, 2, 4, 5, 8, 1, 3, 9, 6},
		{8, 1, 6, 9, 3, 7, 4, 5, 2},
		{9, 7, 2, 3, 1, 4, 8, 6, 5},
		{6, 8, 3, 7, 5, 9, 2, 4, 1},
		{1, 4, 5, 8, 6, 2, 9, 3, 7},
		{4, 3, 1, 6, 7, 8, 5, 2, 9},
		{5, 9, 7, 1, 2, 3, 6, 8, 4},
		{2, 6, 8, 4, 9, 5, 7, 1, 3}
	};
	
	private int baseGrid[][] = { 
		{3, 5, 0, 0, 0, 0, 0, 0, 0},
		{7, 2, 0, 0, 8, 1, 0, 9, 0},
		{0, 0, 0, 0, 0, 7, 0, 0, 0},
		{9, 7, 0, 3, 0, 0, 0, 6, 5},
		{0, 8, 0, 0, 5, 0, 0, 4, 0},
		{1, 4, 0, 0, 0, 2, 0, 3, 7},
		{0, 0, 0, 6, 0, 0, 0, 0, 0},
		{0, 9, 0, 1, 2, 0, 0, 8, 4},
		{0, 0, 0, 0, 0, 0, 0, 1, 3}
	};
	
	private int userAnswers[][] = { 
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0}
	};
	
	public void initializeGrid(TextField blankGrid[][]) {
		for (int i = 0; i <= 8; i++) {
			for (int j = 0; j <= 8; j++) {
				blankGrid[i][j] = new TextField();
				blankGrid[i][j].setText(Integer.toString(baseGrid[i][j]));
			}
		}
	}
	
	/**public void getUserNumbers(TextField textAnswers) {
		for (int i = 0; i <= 8; i++) {
			for (int j = 0; j <= 8; j++) {
				userAnswers[i][j] = textAnswers[i][j].getText();
			}
		}
	}
	
	public boolean checkUserEntry(int userEntry, int i, int j) {
		if(userEntry != answerKey[i][j]) {
			return false;
		} else { 
			return true;
		}
	}
**/	
}