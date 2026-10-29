package tp1.pvz.logic.gameObjects;

import tp1.pvz.logic.GameWorld;
import tp1.pvz.logic.Position;

public abstract class Plant extends GameObject {

	public Plant(GameWorld game, Position pos, int health) {
		super(game, pos, health);
	}
	
	@Override
	public boolean receiveZombieAttack(int damage) {
		if(isAlive()) {
			receiveDamage(damage); 
			return true; 
		}
		return false; 
	}
	@Override
	public void onEnter() {
		
	}
	@Override
	public void onExit() {
		
	}
	
	public abstract int getCost(); 
	public abstract Plant create(Position pos, GameWorld game);

}
