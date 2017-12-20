/**
 * Dan Beaupre
 * CS 210
 * Software Design
 * 5/7/17
 * GameTest.java
 */

public class GameTest {
	
	public static void main(String[] args){
		ModelInterface model = new Connect4Model();
		
		ControllerInterface controller = new Connect4Controller(model);
	}
}
