package tp1.pvz.logic.gameObjects;

import tp1.pvz.logic.GameWorld;
import tp1.pvz.logic.Position;
import tp1.pvz.view.Messages;

public class Zombie extends GameObject {

	public static final int MOVE_EVERY_CYCLES = 2;
	private static final int DAMAGE = 1;
	private static final int INITIAL_HEALTH = 5;

	private int cyclesSinceLastMovement;

	public Zombie(Position position, GameWorld game) {
		super(game, position, INITIAL_HEALTH);
		cyclesSinceLastMovement = 0;
	}

	public Zombie() {
		this(null, null);
	}

	public String getIcon() {
		return String.format(Messages.ZOMBIE_ICON, this.health);
	}

	public boolean isHorizontallyAligned(Position p) {
		return position.row() == p.row();
	}

	public boolean isVerticallyAligned(Position p) {
		return position.col() == p.col();
	}

	public void receiveAttack(int damage) {
		this.health = Math.max(0, this.health - damage);
	}

	public void update() {
		if (!isAlive()) {
			return;
		}
		if (game.isPlant(this.position.left())) {
			attack();
		} else if (canMove()) {
			move();
			if (game.isPlant(this.position.left())) {
				attack();
			}
		}
	}

	private boolean canMove() {
		return game.isEmpty(this.position.left());
	}

	private void move() {
		cyclesSinceLastMovement++;
		if (cyclesSinceLastMovement >= MOVE_EVERY_CYCLES) {
			this.position = this.position.left();
			cyclesSinceLastMovement = 0;
		}
	}

	private void attack() {
		game.attackPlant(this.position.left(), DAMAGE);
	}

	@Override
	public boolean receivePlantAttack(int damage) {
		if (isAlive()) {
			receiveDamage(damage);
			return true;
		}
		return false;
	}

	@Override
	public String getName() {
		return Messages.ZOMBIE_NAME;
	}

	@Override
	public String getDescription() {
		return String.format(Messages.ZOMBIE_DESCRIPTION, Messages.ZOMBIE_NAME, MOVE_EVERY_CYCLES, DAMAGE,
				INITIAL_HEALTH);

	}

	@Override
	public void onEnter() {
	}

	@Override
	public void onExit() {
	}

	@Override
	public String getSymbol() {
		return Messages.ZOMBIE_SYMBOL;
	}

}
