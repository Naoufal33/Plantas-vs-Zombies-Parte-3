package tp1.pvz.logic.gameObjects;

import tp1.pvz.logic.GameWorld;
import tp1.pvz.logic.Position;
import tp1.pvz.view.Messages;

public abstract class Zombie extends GameObject {

	protected static final int DAMAGE = 1;
	
	protected int cyclesSinceLastMovement;
	protected int speed;

	public Zombie(Position position, GameWorld game, int health, int speed) {
		super(game, position, health);
		this.speed = speed; 
		cyclesSinceLastMovement = 0;
		
	}
	
	public Zombie() {
		super(null, null, 0); 
	}


	public void update() {
		if (!isAlive()) {
			return;
		}
		GameItem item = game.getGameItemInPosition(this.position.left()); 
		if (item != null && item.receiveZombieAttack(DAMAGE)) {
			cyclesSinceLastMovement = 0;
		} else if (canMove()) {
			if (cyclesSinceLastMovement >= speed) {
				this.position = this.position.left();
				cyclesSinceLastMovement = 1;
				if (this.position.col() < 0) {
					game.zombieArrived();
				}
				item = game.getGameItemInPosition(this.position.left()); 
				if (item != null) {
					item.receiveZombieAttack(DAMAGE);
				}
			} else {
				cyclesSinceLastMovement++;
			}
		} else {
			if (cyclesSinceLastMovement < speed) {
				cyclesSinceLastMovement++;
			}
		}
	}

	private boolean canMove() {
		return game.isEmpty(this.position.left());
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
	public abstract String getName();

	@Override
	public String getDescription() {
		return String.format(Messages.ZOMBIE_DESCRIPTION, getName(), speed, DAMAGE,
				health);
	}

	@Override
	public void onEnter() {}

	@Override
	public void onExit() {
		game.zombieDied();
	}

	@Override
	public abstract String getSymbol(); 
	
	public abstract Zombie create(Position pos, GameWorld game); 

}
