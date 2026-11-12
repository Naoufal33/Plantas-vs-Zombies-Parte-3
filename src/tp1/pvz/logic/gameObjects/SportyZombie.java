package tp1.pvz.logic.gameObjects;

import tp1.pvz.logic.GameWorld;
import tp1.pvz.logic.Position;
import tp1.pvz.view.Messages;

public class SportyZombie extends Zombie {
	
	private static final int MOVE_EVERY_CYCLES = 1;
	private static final int INITIAL_HEALTH = 2;
	
	public SportyZombie(Position position, GameWorld game) {
		super(position, game, INITIAL_HEALTH, MOVE_EVERY_CYCLES); 
	}
	public SportyZombie() {
		this(null, null); 
	}

	@Override
	public String getName() {
		return Messages.SPORTY_ZOMBIE_NAME; 
	}

	@Override
	public String getSymbol() {
		return Messages.SPORTY_ZOMBIE_SYMBOL; 
	}

	@Override
	public Zombie create(Position pos, GameWorld game) {
		return new SportyZombie(pos, game); 
	}

}
