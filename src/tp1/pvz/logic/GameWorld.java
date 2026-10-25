package tp1.pvz.logic;

import tp1.pvz.logic.gameObjects.GameItem;

public interface GameWorld {

	public static final int NUM_ROWS = 4;
	public static final int NUM_COLS = 8;

	public boolean isEmpty(Position position);

	public boolean isInsideBoard(Position position);
	
	public boolean attackZombie(Position p, int damage);
	
	public void attackPlant(Position p, int damage) ; 
	
	public boolean isPlant(Position p); 
	
	public void generateCoins(int amount);
	
	GameItem getGameItemInPosition(Position position);

}
