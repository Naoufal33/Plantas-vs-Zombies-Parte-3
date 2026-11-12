package tp1.pvz.logic;

import tp1.pvz.logic.actions.GameAction;
import tp1.pvz.logic.gameObjects.GameItem;

public interface GameWorld {

	public static final int NUM_ROWS = 4;
	public static final int NUM_COLS = 8;

	public boolean isEmpty(Position position);

	public boolean isInsideBoard(Position position);

	public void generateCoins(int amount);

	public GameItem getGameItemInPosition(Position position);

	public void zombieDied();

	public void zombieArrived();
	
	public void pushAction(GameAction gameAction);
	
	public void addSun(Position pos); 

}
