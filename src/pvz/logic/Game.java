package pvz.logic;

import java.util.Random;

import pvz.control.Level;
import pvz.logic.gameObjects.Peashooter;
import pvz.logic.gameObjects.Sunflower;

public class Game {

	public static int NUM_ROWS = 4;
	public static int NUM_COLS = 8;
	public static int INITIAL_COINS = 50;
	private int cycles;
	private int coins;
	private Long longSeed;
	private Random rand;
	private boolean playerQuit;
	private SunflowerList sunflowerList;
	private PeashooterList peashooterList;
	private ZombiesManager zombiesManager;
	private Level level;

	public Game(Long seed, Level level) {
		this.longSeed = seed;
		this.level = level;
		this.rand = new Random(seed);
		this.cycles = 0;
		this.coins = INITIAL_COINS;
		this.peashooterList = new PeashooterList();
		this.sunflowerList = new SunflowerList();
		this.zombiesManager = new ZombiesManager(this, level, rand);

	}

	public int getCycles() {
		return cycles;
	}

	public int getCoins() {
		return this.coins;
	}

	public int getRemainingZombies() {
		return this.zombiesManager.getRemainingZombies();
	}

	public int hasGameFinished() {
		return 0;
	}

	public boolean playerWins() {
		return false;
	}

	public boolean playerQuits() {
		return false;
	}

	public void quit() {
	}

	public void update() {
	}

	public String positionToString(Position position) {
		if (this.peashooterList != null) {
			String icon = this.peashooterList.iconInPosition(position);
			if (icon != null && !icon.isEmpty()) {
				return icon;
			}
		}
		if (this.sunflowerList != null) {
			String icon = this.sunflowerList.iconInPosition(position);
			if (icon != null && !icon.isEmpty()) {
				return icon;
			}
		}
		if (this.zombiesManager != null) {
			String icon = this.zombiesManager.iconInPosition(position);
			if (icon != null && !icon.isEmpty()) {
				return icon;
			}
		}
		return "";
	}

	public static Position newZombiePosition(int row) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEmpty(Position pos) {
		return (peashooterList == null || peashooterList.isEmpty(pos))
				&& (sunflowerList == null || sunflowerList.isEmpty(pos))
				&& (zombiesManager == null || zombiesManager.isEmpty(pos));
	}

	public void reset() {
		// TODO Auto-generated method stub

	}

	public boolean isInsideBoard(Position p) {
		return p.row() >= 0 && p.row() < Game.NUM_ROWS && p.col() >= 0 && p.col() < Game.NUM_COLS;
	}

	public boolean attackZombie(Position p, int damage) {
		return zombiesManager.damage(p, damage);
	}

	public void addGameObject(String plantName, Position pos) {
		if (plantName.equals("p") || plantName.equals("peashooter")) {
			this.peashooterList.add(new Peashooter(pos, this));
			this.coins -= Peashooter.COST;
		} else if (plantName.equals("s") || plantName.equals("sunflower")) {
			this.sunflowerList.add(new Sunflower(pos, this));
			this.coins -= Sunflower.COST;
		}

	}
}
