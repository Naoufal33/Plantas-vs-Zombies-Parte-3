package tp1.pvz.logic.gameObjects;

import tp1.pvz.logic.GameWorld;
import tp1.pvz.logic.Position;
import tp1.pvz.view.Messages;

public class BucketHeadZombie extends Zombie {
	
	private static final int MOVE_EVERY_CYCLES = 4;
	private static final int INITIAL_HEALTH = 8;
	
	public BucketHeadZombie(Position position, GameWorld game) {
		super(position, game, INITIAL_HEALTH, MOVE_EVERY_CYCLES); 
	}
	public BucketHeadZombie() {
		this(null, null); 
	}

	@Override
	public String getName() {
		return Messages.BUCKET_HEAD_ZOMBIE_NAME; 
	}

	@Override
	public String getSymbol() {
		return Messages.BUCKET_HEAD_ZOMBIE_SYMBOL; 
	}

	@Override
	public Zombie create(Position pos, GameWorld game) {
		return new BucketHeadZombie(pos, game); 
	}

}
