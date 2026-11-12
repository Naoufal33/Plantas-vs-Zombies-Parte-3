package tp1.pvz.logic.gameObjects;

import tp1.pvz.logic.GameWorld;
import tp1.pvz.logic.Position;
import tp1.pvz.view.Messages;

public class Sun extends GameObject {
	
	private static final int INITIAL_HEALTH = 11;
	private static final int SUNCOINS_VALUE = 10; 

	public Sun(GameWorld game, Position pos) {
		super(game, pos, INITIAL_HEALTH);
	}
	
	public Sun() {
		super(null, null); 
	}

	@Override
	public void update() {
		receiveDamage(1);  
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
	@Override
	public int catchSun() {
		this.health = 0; 
		return SUNCOINS_VALUE; 
	}
	
	@Override
	public boolean isSolid() {
		return false;
	}

}
