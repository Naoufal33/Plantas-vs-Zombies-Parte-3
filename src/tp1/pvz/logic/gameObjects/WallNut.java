package tp1.pvz.logic.gameObjects;

import tp1.pvz.logic.GameWorld;
import tp1.pvz.logic.Position;
import tp1.pvz.view.Messages;

public class WallNut extends Plant {
	
	public static final int COST = 50;
	private static final int DAMAGE = 0;
	private static final int INITIAL_HEALTH = 10;

	public WallNut(GameWorld game, Position pos) {
		super(game, pos,INITIAL_HEALTH);
		
	}

	public WallNut() {
		super(null, null, 0); 
	}

	@Override
	public int getCost() {
		return COST; 
	}

	@Override
	public Plant create(Position pos, GameWorld game) {
		return new WallNut(game, pos); 
	}

	@Override
	public void update() {
	}

	@Override
	public String getName() {
		return Messages.WALL_NUT_NAME; 
	}

	@Override
	public String getDescription() {
		return String.format(Messages.WALL_NUT_DESCRIPTION, COST, DAMAGE, INITIAL_HEALTH); 
	}


	@Override
	public String getSymbol() {
		return Messages.WALL_NUT_SYMBOL; 
	}

}
