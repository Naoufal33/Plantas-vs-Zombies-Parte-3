package tp1.pvz.logic;

import java.util.Random;

import tp1.pvz.control.Level;
import tp1.pvz.logic.gameObjects.*;

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

	private int zombiesAlived;

	private boolean zombiesArrived;

	
	public ZombiesManager(Game game, Level level, Random rand) {
		this.game = game;
		this.level = level;
		this.rand = rand;
		this.remainingZombies = level.getNumberOfZombies();
		this.zombiesAlived = 0;
		this.zombiesArrived = false;

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
		int type = randomZombieType();
		return addZombie(row, type);
	}

	public boolean addZombie(int row) {
		return addZombie(row, randomZombieType());
	}

	public boolean addZombie(int row, int type) {
		Position zombiePosition = Game.newZombiePosition(row);

		boolean canAdd = this.remainingZombies > 0 && shouldAddZombie();

		if (canAdd) {
			Zombie z = ZombieFactory.spawnZombie(type, game, zombiePosition);
			game.addZombie(z);
			this.remainingZombies--;
			this.zombiesAlived++;
		}
		return canAdd;
	}

	public int getRemainingZombies() {
		return this.remainingZombies;
	}

	public boolean doZombiesReachedTheHouse() {
		return this.zombiesArrived;
	}

	public boolean allZombiesWereKilled() {
		return this.remainingZombies == 0 && this.zombiesAlived == 0;
	}

	public void zombieDead() {
		this.zombiesAlived--;
	}

	public void zombieArrived() {
		this.zombiesArrived = true;
	}
	
	private int randomZombieType() {
		return rand.nextInt(ZombieFactory.getAvailableZombies().size()); 
	}

}
