/**
 * Dan Beaupre
 * CS 210
 * Software Design
 * 5/7/17
 * Connect4Controller.java
 */

public class Connect4Controller implements ControllerInterface{
	
	ModelInterface model;
	Connect4View view;
	
	/**
	 * Constructor
	 * @param model
	 */
	public Connect4Controller(ModelInterface model){
		this.model = model;
		view = new Connect4View(this, model);
	}

	/**
	 * Drops the piece, calls models drop piece
	 */
	public void dropPiece(String column) {
		model.dropPiece(column);
	}

	/**
	 * Resets the game
	 */
	public void reset() {
		model.reset();
		
	}
	
}
