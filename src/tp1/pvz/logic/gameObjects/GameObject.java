package tp1.pvz.logic.gameObjects;

import tp1.pvz.logic.GameWorld;
import tp1.pvz.logic.Position;
import tp1.pvz.view.Messages;

public abstract class GameObject implements GameItem {

	protected Position position;
	protected int health;
	protected GameWorld game;

	public GameObject(GameWorld game, Position pos) {
		this.game = game;
		this.position = pos;
	}

	public GameObject(GameWorld game, Position pos, int health) {
		this.game = game;
		this.position = pos;
		this.health = health;
	}

	@Override
	public boolean isInPosition(Position position) {
		return this.position.equals(position);
	}

	@Override
	public boolean isAlive() {
		return health > 0;
	}

	@Override
	public boolean receivePlantAttack(int damage) {
		return false;
	}

	@Override
	public boolean receiveZombieAttack(int damage) {
		return false;
	}

	public void receiveDamage(int damage) {
		this.health = Math.max(0, this.health - damage);
	}

	public Position getPosition() {
		return new Position(position.row(), position.col());
	}

	public String getIcon() {
		return String.format(Messages.GAME_OBJECT_ICON, getSymbol(), this.health);
	}

	public abstract void update();

	public abstract String getName();

	public abstract String getDescription();

	public abstract String getSymbol();

	public void onEnter() {
	}

	public void onExit() {
	}

	public int catchSun() {
		return 0;
	}

	public boolean isSolid() {
		return true;
	}
	public boolean receiveExplosion(int damage) {
		return false; 
	}

}
