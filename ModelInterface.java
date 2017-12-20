/**
 * Dan Beaupre
 * CS 210
 * Software Design
 * 5/7/17
 * ModelInterface.java
 */

public interface ModelInterface {
	
	void reset();
	void changePlayer();
	void dropPiece(String column);
	void registerObserver(ViewObserver o);
	
	void getWinner();
}
