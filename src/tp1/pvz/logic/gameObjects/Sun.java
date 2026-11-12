package tp1.pvz.logic.gameObjects;

import tp1.pvz.logic.GameWorld;
import tp1.pvz.logic.Position;
import tp1.pvz.view.Messages;

public class Sun extends GameObject {
	
	private static final int INITIAL_HEALTH = 10;
	
	private int health; 

	public Sun(GameWorld game, Position pos) {
		super(game, pos, INITIAL_HEALTH);
		health = INITIAL_HEALTH; 
	}
	
	public Sun() {
		super(null, null); 
	}

	@Override
	public void update() {
		health = Math.max(0, health--); 
	}

	@Override
	public String getName() {
		return Messages.SUN_NAME; 
	}

	@Override
	public String getDescription() {
		return Messages.SUN_DESCRIPTION; 
	}

	@Override
	public String getSymbol() {
		return Messages.SUN_SYMBOL; 
	}

}
