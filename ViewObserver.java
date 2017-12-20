/**
 * Dan Beaupre
 * CS 210
 * Software Design
 * 5/7/17
 * ViewObserver.java
 */

public interface ViewObserver {
	
	void updateView(int row, int column);
	void swapPlayer();
	void blockColumn(int column);
	void reset();
	
	void displayWinner(int winner);
	
}
