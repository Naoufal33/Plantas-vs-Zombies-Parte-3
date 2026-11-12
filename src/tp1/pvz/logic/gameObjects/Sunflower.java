package tp1.pvz.logic.gameObjects;

import tp1.pvz.logic.GameWorld;
import tp1.pvz.logic.Position;
import tp1.pvz.view.Messages;

public class Sunflower extends Plant {

	public static final int COST = 20;
	private static final int DAMAGE = 0;
	private static final int GENERATED_SUNCOINS = 10;
	private static final int INITIAL_HEALTH = 1;
	private static final int COOLDOWN = 3;

	private int cyclesSinceLastCoinGeneration;
	private boolean justPlanted;

	public Sunflower(Position position, GameWorld game) {
		super(game, position, INITIAL_HEALTH);
		this.cyclesSinceLastCoinGeneration = 0;
		this.justPlanted = true;
	}

	public Sunflower() {
		super(null, null, 0);
	}

	@Override
	public String getDescription() {
		return String.format(Messages.SUNFLOWER_DESCRIPTION, COST, DAMAGE, INITIAL_HEALTH);
	}

	@Override
	public void update() {
		if (justPlanted) {
			justPlanted = false;
			return;
		}
		cyclesSinceLastCoinGeneration++;
		if (cyclesSinceLastCoinGeneration == COOLDOWN) {
			game.generateCoins(GENERATED_SUNCOINS);
			cyclesSinceLastCoinGeneration = 0;
		}
	}

	@Override
	public int getCost() {
		return COST;
	}

	@Override
	public String getName() {
		return Messages.SUNFLOWER_NAME;
	}

	@Override
	public String getSymbol() {
		return Messages.SUNFLOWER_SYMBOL;
	}

	public static String shortName() {
		return Messages.SUNFLOWER_NAME;
	}

	@Override
	public Plant create(Position pos, GameWorld game) {
		return new Sunflower(pos, game);
	}

}
