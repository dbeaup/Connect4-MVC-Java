/**
 * Dan Beaupre
 * CS 210
 * Software Design
 * 5/7/17
 * Connect4View.java
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Connect4View extends JFrame implements ViewObserver{
	
	ModelInterface model;
	ControllerInterface controller;
	private JPanel gamePanel;
	private JLabel currentPlayer;
	private static JPanel[][] board;
	JButton column1 = new JButton("Column 1");
	JButton column2 = new JButton("Column 2");
	JButton column3 = new JButton("Column 3");
	JButton column4 = new JButton("Column 4");
	JButton column5 = new JButton("Column 5");
	JButton column6 = new JButton("Column 6");
	JButton column7 = new JButton("Column 7");

	/**
	 * Contstructor
	 * @param newController
	 * @param model
	 */
	public Connect4View(ControllerInterface newController, ModelInterface model){
		
		controller = newController;
		
		model.registerObserver((ViewObserver) this);
		
		setTitle("Connect4");
		
		setSize(650, 450);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		createGame();
		
		setVisible(true);
		
	}

	/**
	 * Creates a new game
	 */
	private void createGame(){
		//Creates Panels and Buttons
		gamePanel = new JPanel(new GridLayout(8,7));
		
		
		JButton resetGame = new JButton("Reset");
		JButton exit = new JButton("Exit");
		
		currentPlayer = new JLabel("Player 1");
		currentPlayer.setForeground(Color.BLUE);
		
		//Adds to the panel
		gamePanel.add(column1);
		gamePanel.add(column2);
		gamePanel.add(column3);
		gamePanel.add(column4);
		gamePanel.add(column5);
		gamePanel.add(column6);
		gamePanel.add(column7);
		
		board = new JPanel[6][7];
		
		//Creates board
		createBoard();
		
		gamePanel.add(resetGame);
		gamePanel.add(exit);
		gamePanel.add(currentPlayer);
		
		//Adds the panel
		add(gamePanel);
	
		//Adds action listeners
		column1.addActionListener(new ColumnButtonListener());
		column2.addActionListener(new ColumnButtonListener());
		column3.addActionListener(new ColumnButtonListener());
		column4.addActionListener(new ColumnButtonListener());
		column5.addActionListener(new ColumnButtonListener());
		column6.addActionListener(new ColumnButtonListener());
		column7.addActionListener(new ColumnButtonListener());
		
		resetGame.addActionListener(new ResetGameListener());
		exit.addActionListener(new ExitListener());
		
	}

	/**
	 * Creates the game board
	 */
	private void createBoard() {
		for(int i = 0; i < board.length; i++){
			for (int j = 0; j < board.length + 1; j++){
				
				JPanel boardPanel = board[i][j];
				
				boardPanel = new JPanel();
				boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				board[i][j] = boardPanel;
				
				gamePanel.add(boardPanel);
			}
		}	
	}
	
	/**
	 * Swaps the players and changes the text box
	 */
	public void swapPlayer() {
		if (currentPlayer.getText() == "Player 1") {
			currentPlayer.setText("Player 2");
			currentPlayer.setForeground(Color.RED);
			
		}
		else{
			currentPlayer.setText("Player 1");
			currentPlayer.setForeground(Color.BLUE);
		}
	}
	
	/**
	 * Updates the view, changes the color the piece droped
	 */
	public void updateView(int row, int column) {
		
		if (currentPlayer.getText() == "Player 1"){
			board[row][column].setBackground(Color.BLUE);
		}
		
		else{
			board[row][column].setBackground(Color.RED);
		}
	}
	
	/**
	 * Displays the winner of the game
	 */
	public void displayWinner(int winner) {
		if (winner == 1){
			JOptionPane.showMessageDialog(null, "Player 1 wins!");
		}
		
		else{
			JOptionPane.showMessageDialog(null, "Player 2 wins!");
		}
		
		//Block all columns after game is won
		column1.setEnabled(false);
		column2.setEnabled(false);
		column3.setEnabled(false);
		column4.setEnabled(false);
		column5.setEnabled(false);
		column6.setEnabled(false);
		column7.setEnabled(false);
	}
	
	/**
	 * Resets the board
	 */
	public void reset() {
		for(int i = 0; i < board.length; i++){
			for (int j = 0; j < board.length + 1; j++){
				
				JPanel boardPanel = board[i][j];
				boardPanel.setBackground(Color.WHITE);

			}
		}
		
		//Enables all of the buttons
		column1.setEnabled(true);
		column2.setEnabled(true);
		column3.setEnabled(true);
		column4.setEnabled(true);
		column5.setEnabled(true);
		column6.setEnabled(true);
		column7.setEnabled(true);
		
		//Sets player back to player 1
		currentPlayer.setText("Player 1");
		currentPlayer.setForeground(Color.BLUE);
	}

	/**
	 * Blocks a column when the column is full
	 */
	public void blockColumn(int column) {
		if (column == 0) {
			column1.setEnabled(false);
		}
		
		else if (column == 1) {
			column2.setEnabled(false);
		}
		
		else if (column == 2) {
			column3.setEnabled(false);
		}
		
		else if (column == 3) {
			column4.setEnabled(false);
		}
		
		else if (column == 4) {
			column5.setEnabled(false);
		}
		
		else if (column == 5) {
			column6.setEnabled(false);
		}
		
		else if (column == 6) {
			column7.setEnabled(false);
		}
	}
	
	/**
	 * Action listener for the column buttons
	 *
	 */
	public class ColumnButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			controller.dropPiece(e.getActionCommand());	//Calls controllers drop piece
		}	
	}
	
	/**
	 * Action listener for the reset button
	 *
	 */
	public class ResetGameListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			controller.reset();	//Calls controllers reset
		}
	}
	
	/**
	 * Action listener for the exit button
	 *
	 */
	private class ExitListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			System.exit(0);	//Calls controllers exit
		}	
	}	
}