package tp1.pvz.logic.gameObjects;

import tp1.pvz.logic.GameWorld;
import tp1.pvz.logic.Position;
import tp1.pvz.view.Messages;

public class CommonZombie extends Zombie {
	
	private static final int MOVE_EVERY_CYCLES = 2;
	private static final int INITIAL_HEALTH = 5;


	public CommonZombie(Position position, GameWorld game) {
		super(position, game, INITIAL_HEALTH, MOVE_EVERY_CYCLES);
	}

	public CommonZombie() {
		this(null, null); 
	}

	@Override
	public String getName() {
		return Messages.ZOMBIE_NAME;
	}
	@Override
	public String getSymbol() {
		return Messages.ZOMBIE_SYMBOL;
	}

	@Override
	public Zombie create(Position pos, GameWorld game) {
		return new CommonZombie(pos, game);
	}
}
