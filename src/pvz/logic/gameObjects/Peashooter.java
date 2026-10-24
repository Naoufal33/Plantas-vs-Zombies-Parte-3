package pvz.logic.gameObjects;

import pvz.logic.Game;
import pvz.logic.Position;
import pvz.view.Messages;

public class Peashooter {

	public static final int COST = 50;
	private static final int DAMAGE = 1;
	private static final int INITIAL_HEALTH = 3;
	
	private int health;
	private final Position position;
	private Game game;

	
	public Peashooter(Position position, Game game) {
		this.position = position;
		health = INITIAL_HEALTH;
		this.game = game;
	}

	public String getIcon() {
		return String.format(Messages.PEASHOOTER_ICON, this.health);
	}

	public boolean isInPosition(Position p) {
		return this.position.equals(p);
	}

	public static String getDescription() {
		return String.format(Messages.PEASHOOTER_DESCRIPTION, COST, DAMAGE, INITIAL_HEALTH);
	}

	public boolean isAlive() {
		return health > 0; 
	}

	public void update() {
		shoot(); 
	}

	private void shoot() {
		boolean hit = false; 
		int c = this.position.col() + 1;
		while(c < Game.NUM_COLS && !hit) {
			Position target = new Position(this.position.row(), c); 
			hit = game.attackZombie(target, DAMAGE); 
			c++; 
		}
	}

	public void receiveDamage(int damage) {
		this.health = Math.max(0, this.health - damage); 
	}

	public static String shortName() {
		return "P";
	}

	public static String longName() {
		return "Peashooter";
	}
}
