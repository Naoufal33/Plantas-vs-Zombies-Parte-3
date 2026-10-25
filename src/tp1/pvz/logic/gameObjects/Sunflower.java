package tp1.pvz.logic.gameObjects;

import tp1.pvz.logic.Game;
import tp1.pvz.logic.Position;
import tp1.pvz.view.Messages;

public class Sunflower {
	
	public static final int COST = 20;
	private static final int DAMAGE = 0;
	private static final int GENERATED_SUNCOINS = 10;
	private static final int INITIAL_HEALTH = 1;
	private static final int COOLDOWN = 3; 
	
	private int cyclesSinceLastCoinGeneration; 
	private boolean justPlanted;
	private int health;
	private final Position position;
	private Game game;
	
	public Sunflower(Position position, Game game) {
		this.position = position; 
		this.cyclesSinceLastCoinGeneration = 0; 
		this.justPlanted = true;
		this.health = INITIAL_HEALTH;
		this.game = game;
	}
	public String getIcon() {
		return String.format(Messages.SUNFLOWER_ICON, this.health);
	}
	
	public boolean isInPosition(Position p) {
		return this.position.equals(p);
	}


	public static String getDescription() {
		return String.format(Messages.SUNFLOWER_DESCRIPTION, COST, DAMAGE, INITIAL_HEALTH);
	}
	
	public boolean isAlive() {
		return health > 0;
	}

	public void update() {
		if (justPlanted) {
			justPlanted = false;
			return;
		}
		cyclesSinceLastCoinGeneration++;
		if (cyclesSinceLastCoinGeneration == COOLDOWN) {
			game.generateCoins(GENERATED_SUNCOINS);
			cyclesSinceLastCoinGeneration  = 0;
		}
	}
	
	public void receiveDamage(int damage) {
		this.health = Math.max(0, this.health - damage); 
	}
	public static String shortName() {
		return "S";
	}

	public static String longName() {
		return "Sunflower";
	}

}
