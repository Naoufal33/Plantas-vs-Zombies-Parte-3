package pvz.logic.gameObjects;

import pvz.logic.Game;
import pvz.logic.Position;
import pvz.view.Messages;

public class Sunflower {
	
	public static final int COST = 20;
	private static final int DAMAGE = 0;
	private static final int GENERETAD_SUNCOINS = 10;
	private static final int INITIAL_HEALTH = 1;
	private static final int COOLDOWN = 3; 
	
	private int cyclesSinceLastCoinGeneration; 
	private int health;
	private Position position;
	private Game game;
	
	public Sunflower(Position position, Game game) {
		this.position = position; 
		cyclesSinceLastCoinGeneration = 0; 
		this.health = INITIAL_HEALTH;
		this.game = game;
	}
	public String getIcon() {
		return String.format(Messages.SUNFLOWER_ICON, this.health);
	}
	
	public boolean isInPosition(Position p) {
		return this.position.equals(p);
	}


	public static Object getDescription() {
		return String.format(Messages.SUNFLOWER_DESCRIPTION, COST, DAMAGE, INITIAL_HEALTH);
	}
	
	public boolean isAlive() {
		return health > 0;
	}

	public void update() {
	}
	
	public void receiveDamage(int damage) {
		
	}
	
	public String shortName() {
		return "S";
	}

	public String longName() {
		return "[S]unflower";
	}

}
