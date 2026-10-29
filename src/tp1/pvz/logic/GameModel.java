package tp1.pvz.logic;

import tp1.pvz.control.Level;

public interface GameModel {
	void reset();
	void reset(Level level, long seed);
	void update();
	void quit();
	boolean isValidPlant(String plantName);
	boolean isValidPosition(Position pos);
	int plantCost(String plantName);
	int getCoins();
	void addPlant(String plantName, Position pos);
}
