package pvz.logic.gameObjects;

import pvz.logic.Game;
import pvz.logic.Position;
import pvz.view.Messages;

public class Zombie {

	public static final int MOVE_EVERY_CYCLES = 1;
	private static final int DAMAGE = 1;
	private static final int INITIAL_HEALTH = 5;

	private int cyclesSinceLastCoinMovement;
	private int health;
	private Position position;
	private Game game;
	
	public Zombie(Position position, Game game) {
		this.position = position;
		this.health = INITIAL_HEALTH;
		cyclesSinceLastCoinMovement = 0; 
		this.game = game;
	}

	public String getIcon() {
		return String.format(Messages.ZOMBIE_ICON, this.health);
	}
	
	public boolean isInPosition(Position p) {
		return this.position.equals(p);
	}
	
	public boolean isHorizontallyAligned(Position p) {
		return position.row() == p.row();
	}

	public boolean isVerticallyAligned(Position p) {
		return position.col() == p.col();
	}
	
	public void receiveAttack(int damage) {}
	
	public void update() {
	}
	
	private boolean canMove() { return false; }
	
	private void move() {}
	
	
	public boolean isAlive() {
		return health > 0;
	}
	
	private void attack() {}
	
}
