/**
 * Dan Beaupre
 * CS 210
 * Software Design
 * 5/7/17
 * Connect4Model.java
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Connect4Model implements ModelInterface {
	
	int[][] board = new int[6][7];
	ArrayList<ViewObserver> viewObservers = new ArrayList<ViewObserver>();
	int player = 1;

	int row1 = 5;
	int row2 = 5;
	int row3 = 5;
	int row4 = 5;
	int row5 = 5;
	int row6 = 5;
	int row7 = 5;
	
	/**
	 * Resets the board back to its original state, and notifies observers to reset
	 */
	public void reset() {
		//Resets the board
		for(int i = 0; i < board.length; i++){
			for (int j = 0; j < board.length + 1; j++){
				
				board[i][j] = 0;
			}
		}
		//Resets current player
		player = 1;
		//Notifies observers
		for(int i = 0; i < viewObservers.size(); i++) {
			ViewObserver observer = (ViewObserver)viewObservers.get(i);
			observer.reset();
		}
	}

	/**
	 * Drops a piece into the board
	 * @parem the column where the piece is being dropped
	 */
	public void dropPiece(String column) {
		
		if(column == "Column 1") {
			int columnNum = 0;
			findSlot(row1, columnNum);  //Finds next open slot
			getWinner();				//Checks if anyone has won
			changePlayer();				//Swaps players
		}
		
		else if(column == "Column 2"){
			int columnNum = 1;
			findSlot(row2, columnNum);
			getWinner();
			changePlayer();
		}
		
		else if(column == "Column 3"){
			int columnNum = 2;
			findSlot(row3, columnNum);
			getWinner();
			changePlayer();
		}
		
		else if(column == "Column 4"){
			int columnNum = 3;
			findSlot(row4, columnNum);
			getWinner();
			changePlayer();
		}
		
		else if(column == "Column 5"){
			int columnNum = 4;
			findSlot(row5, columnNum);
			getWinner();
			changePlayer();
		}
		
		else if(column == "Column 6"){
			int columnNum = 5;
			findSlot(row6, columnNum);
			getWinner();
			changePlayer();
		}
		
		else if(column == "Column 7"){
			int columnNum = 6;
			findSlot(row7, columnNum);
			getWinner();
			changePlayer();
		}
	}

	/**
	 * Finds next open slot
	 * @param row
	 * @param column
	 */
	public void findSlot(int row, int column) {
		boolean played = true;
		
		while(played){
			
			if (board[row][column] == 0){
				
				board[row][column] = player;
				notifyObserver(row, column);
				played = false;
				row--;
				
				if(row == -1) {
					columnFull(column);
				}
			}
			else {
				row--;
			}
		}
	}
	
	/**
	 * Checks for a winner
	 */
	public void getWinner() {
		//Checks each way of winning
		int Vwinner = verticalWin();
		int Hwinner = horizontalWin();
		int Dwinner = diagonalWin();
		
		//If someone wins, notify observers
		if (Vwinner == 1 || Vwinner == 2) {
			for(int i = 0; i < viewObservers.size(); i++) {
				ViewObserver observer = (ViewObserver)viewObservers.get(i);
				observer.displayWinner(Vwinner);
			}
		}
		//If someone wins, notify observers
		else if (Hwinner != 0) {
			for(int i = 0; i < viewObservers.size(); i++) {
				ViewObserver observer = (ViewObserver)viewObservers.get(i);
				observer.displayWinner(Hwinner);
			}
		}
		//If someone wins, notify observers
		else if (Dwinner == 1 || Dwinner == 2) {
			for(int i = 0; i < viewObservers.size(); i++) {
				ViewObserver observer = (ViewObserver)viewObservers.get(i);
				observer.displayWinner(Dwinner);
			}
		}
	}
	
	/**
	 * Checks for vertical win
	 * @return winning player
	 */
	public int verticalWin() {
		
		for (int row = 0; row < 3; row++) {
		    for (int col = 0; col < 7; col++){
		        if (board[row][col] != 0 && board[row][col] == board[row+1][col] && board[row][col] == board[row+2][col] && board[row][col] == board[row+3][col]) {
		        	return board[row][col];
		        }
		    }
		}
		return 0;
	}
	
	/**
	 * Checks for horizontal win
	 * @return winning player
	 */
	public int horizontalWin() {
		
		for (int row = 0; row < 6; row++) {
		    for (int col = 0; col < 4; col++) {
		        if (board[row][col] != 0 && board[row][col] == board[row][col+1] && board[row][col] == board[row][col+2] && board[row][col] == board[row][col+3]) {
		        	System.out.println(board[row][col]);
		        	return board[row][col];
		        }
		    }
		}
		return 0;        			   
	}
	
	/**
	 * Checks for horizontal win
	 * @return winning player
	 */
	public int diagonalWin() {
		for (int col = 0; col < 4; col++) {
		    for (int row = 0; row < 3; row ++) {
		        if (board[row][col] != 0 && board[row][col] == board[row+1][col+1] && board[row][col] == board[row+2][col+2] && board[row][col] == board[row+3][col+3]) {
		               return board[row][col];
		        }
		    }
		    for (int row = 3; row < 6; row++) {
		        if (board[row][col] != 0 && board[row][col] == board[row-1][col+1] && board[row][col] == board[row-2][col+2] && board[row][col] == board[row-3][col+3]) {
		               return board[row][col];
		    	}
		    }
		}
		return 0;
	}
	
	/**
	 * Checks if a column is full, then notifies observers
	 * @param column
	 */
	private void columnFull(int column) {
		
		for(int i = 0; i < viewObservers.size(); i++) {
			ViewObserver observer = (ViewObserver)viewObservers.get(i);
			observer.blockColumn(column);
		}
	}
	
	/**
	 * Swaps players
	 */
	public void changePlayer() {
		if (player == 1) {
			player = 2;
		}
		else{
			player = 1;
		}
	}
	
	/**
	 * Notifies the observers to update and swap players
	 * @param row
	 * @param column
	 */
	public void notifyObserver(int row, int column){

		for(int i = 0; i < viewObservers.size(); i++) {
			ViewObserver observer = (ViewObserver)viewObservers.get(i);
			observer.updateView(row, column);
			observer.swapPlayer();
		}
	}

	/**
	 * Registers new observers to the model
	 */
	public void registerObserver(ViewObserver o) {
		viewObservers.add(o);	
	}
}