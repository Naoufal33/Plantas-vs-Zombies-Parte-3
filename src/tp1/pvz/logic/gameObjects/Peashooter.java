package tp1.pvz.logic.gameObjects;

import tp1.pvz.logic.GameWorld;
import tp1.pvz.logic.Position;
import tp1.pvz.view.Messages;

public class Peashooter extends Plant{

	public static final int COST = 50;
	private static final int DAMAGE = 1;
	private static final int INITIAL_HEALTH = 3;
	
	public Peashooter(Position position, GameWorld game) {
		super(game, position, INITIAL_HEALTH);
	}
	public Peashooter() {
		super(null, null, 0); 
	}

	@Override
	public String getIcon() {
		return String.format(Messages.PEASHOOTER_ICON, this.health);
	}

	@Override
	public String getDescription() {
		return String.format(Messages.PEASHOOTER_DESCRIPTION, COST, DAMAGE, INITIAL_HEALTH);
	}

	@Override
	public void update() {
		shoot(); 
	}

	private void shoot() {
		boolean hit = false; 
		int c = this.position.col() + 1;
		while(c < GameWorld.NUM_COLS && !hit) {
			Position target = new Position(this.position.row(), c); 
			hit = game.attackZombie(target, DAMAGE); 
			c++; 
		}
	}

	@Override
	public int getCost() {
		return COST; 
	}

	@Override
	public String getName() {
		return Messages.PEASHOOTER_NAME; 
	}

	@Override
	public String getSymbol() {
		return Messages.PEASHOOTER_SYMBOL; 
	}
	
	public static String shortName() { return Messages.PEASHOOTER_SYMBOL; }
	public static String longName() { return Messages.PEASHOOTER_NAME; }
	@Override
	public Plant create(Position pos, GameWorld game) {
		return new Peashooter(pos, game); 
	}
}
