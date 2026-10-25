package tp1.pvz.logic;

import java.util.Random;

import tp1.pvz.control.Level;
import tp1.pvz.logic.gameObjects.Zombie;

/**
 * Manages the full lifecycle of zombies for a game session.
 *
 * <p>
 * Responsibilities: deciding each cycle whether to spawn a new zombie
 * (probabilistically, subject to the remaining quota from {@link Level}),
 * delegating per-cycle updates and dead-removal to the underlying
 * {@link ZombieList}, and answering win/loss queries
 *
 */
public class ZombiesManager {

	private Game game;

	private Level level;

	private Random rand;

	private int remainingZombies;

	private ZombieList zombies;

	public ZombiesManager(Game game, Level level, Random rand) {
		this.game = game;
		this.level = level;
		this.rand = rand;
		this.remainingZombies = level.getNumberOfZombies();
		this.zombies = new ZombieList();
	}

	/**
	 * Checks if the game should add (if possible) a zombie to the game.
	 * 
	 * @return <code>true</code> if a zombie should be added to the game.
	 */
	private boolean shouldAddZombie() {
		return rand.nextDouble() < level.getZombieFrequency();
	}

	/**
	 * Return a random row within the board limits.
	 * 
	 * @return a random row.
	 */
	private int randomZombieRow() {
		return rand.nextInt(Game.NUM_ROWS);
	}

	public boolean addZombie() {
		int row = randomZombieRow();
		return addZombie(row);
	}

	public boolean addZombie(int row) {
		Position zombiePosition = Game.newZombiePosition(row);
		
		boolean shouldAdd = shouldAddZombie(); 
		boolean canAdd = this.remainingZombies > 0 && shouldAdd && game.isEmpty(zombiePosition);

		if (canAdd) {
			Zombie z = new Zombie(zombiePosition, game);
			zombies.add(z);
			this.remainingZombies--; 
		}
		return canAdd;
	}

	public boolean damageZombie(Position p, int damage) {
		return this.zombies.damage(p, damage);
	}

	public int getRemainingZombies() {
		return this.remainingZombies; 
	}
	public boolean doZombiesReachedTheHouse() {
		return zombies.anyInColumn(-1); 
	}
	public String iconInPosition(Position position) {
		return zombies.iconInPosition(position); 
	}
	
	public boolean isEmpty(Position pos) {
		return zombies.isEmpty(pos);
	}
	public void update() {
		zombies.update();
	}
	public boolean allZombiesWereKilled() {
		return this.remainingZombies == 0 && zombies.size() == 0; 
	}
	public void removeDead() {
		zombies.removeDead();
	}

}
