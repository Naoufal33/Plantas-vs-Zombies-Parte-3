package tp1.pvz.logic.gameObjects;

import tp1.pvz.logic.Position;

public interface GameItem {
	
	public boolean isAlive();
	
	public boolean isInPosition(Position position); 
	
	public boolean receivePlantAttack(int damage); 
	
	public boolean receiveZombieAttack(int damage); 

}
