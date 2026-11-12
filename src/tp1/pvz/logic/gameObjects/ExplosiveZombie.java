package tp1.pvz.logic.gameObjects;

import tp1.pvz.logic.GameWorld;
import tp1.pvz.logic.Position;
import tp1.pvz.view.Messages;

public class ExplosiveZombie extends Zombie {

	private static final int MOVE_EVERY_CYCLES = 2;
	private static final int INITIAL_HEALTH = 5;

	public ExplosiveZombie(Position position, GameWorld game) {
		super(position, game, INITIAL_HEALTH, MOVE_EVERY_CYCLES);
	}

	public ExplosiveZombie() {
		this(null, null); 
	}

	@Override
	public String getName() {
		return Messages.EXPLOSIVE_ZOMBIE_NAME;
	}

	@Override
	public String getSymbol() {
		return Messages.EXPLOSIVE_ZOMBIE_SYMBOL;
	}

	@Override
	public Zombie create(Position pos, GameWorld game) {
		return new ExplosiveZombie(pos, game); 
	}

}
