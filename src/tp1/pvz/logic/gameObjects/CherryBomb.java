package tp1.pvz.logic.gameObjects;

import tp1.pvz.logic.GameWorld;
import tp1.pvz.logic.Position;
import tp1.pvz.logic.actions.ExplosionAction;
import tp1.pvz.view.Messages;

public class CherryBomb extends Plant {

	public static final int COST = 50;
	private static final int DAMAGE = 10;
	private static final int INITIAL_HEALTH = 2;

	private int cyclesUntilExplosion;

	public CherryBomb(GameWorld game, Position pos) {
		super(game, pos, INITIAL_HEALTH);
		this.cyclesUntilExplosion = 2;
	}

	public CherryBomb() {
		super(null, null, 0);
	}

	@Override
	public int getCost() {
		return COST;
	}

	@Override
	public Plant create(Position pos, GameWorld game) {
		return new CherryBomb(game, pos);
	}

	@Override
	public void update() {
		if (isAlive()) {
			this.cyclesUntilExplosion--;
			if (cyclesUntilExplosion == 0)
				explode();
		}
	}

	public void explode() {
		this.health = 0;
		game.pushAction(new ExplosionAction(position, DAMAGE, true));
	}

	@Override
	public String getName() {
		return Messages.CHERRY_BOMB_NAME;
	}

	@Override
	public String getDescription() {
		return String.format(Messages.CHERRY_BOMB_DESCRIPTION, COST, DAMAGE, INITIAL_HEALTH);
	}

	@Override
	public String getIcon() {
		if (this.cyclesUntilExplosion <= 1) {
			return String.format(Messages.CHERRY_BOMB_ABOUT_TO_EXPLODE_ICON, this.cyclesUntilExplosion);

		}
		return String.format(Messages.CHERRY_BOMB_ICON, this.cyclesUntilExplosion);
	}

	@Override
	public String getSymbol() {
		return Messages.CHERRY_BOMB_SYMBOL;
	}

}
