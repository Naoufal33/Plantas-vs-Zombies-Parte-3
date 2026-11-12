package tp1.pvz.logic;

import java.util.Random;

import tp1.pvz.logic.actions.GameAction;
import tp1.pvz.logic.gameObjects.GameItem;
import tp1.pvz.logic.gameObjects.Plant;
import tp1.pvz.logic.gameObjects.Zombie;
import tp1.pvz.control.Level;


public class Game implements GameStatus, GameWorld, GameModel {

	public static int NUM_ROWS = 4;
	public static int NUM_COLS = 8;
	public static int INITIAL_COINS = 50;
	private int cycles;
	private int coins;
	private Long longSeed;
	private Random rand;
	private boolean playerQuit;
	private GameObjectContainer objects;
	private ZombiesManager zombiesManager;
	private Level level;

	public Game(Long seed, Level level) {
		this.longSeed = seed;
		this.level = level;
		this.rand = new Random(seed);
		this.cycles = 0;
		this.coins = INITIAL_COINS;
		this.objects = new GameObjectContainer();
		this.zombiesManager = new ZombiesManager(this, level, rand);

	}

	public String positionToString(Position position) {
		return this.objects.positionToString(position);
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

	public boolean hasGameFinished() {
		return playerQuits() || playerWins() || zombiesManager.doZombiesReachedTheHouse();
	}

	public boolean playerWins() {
		return zombiesManager.allZombiesWereKilled();
	}

	public boolean playerQuits() {
		return this.playerQuit;
	}

	public void quit() {
		this.playerQuit = true;
	}

	public void update() {
		this.cycles++;
		this.zombiesManager.addZombie();
		this.objects.update();
	}

	public void reset() {
		this.rand = new Random(this.longSeed);
		this.cycles = 0;
		this.coins = INITIAL_COINS;
		this.playerQuit = false;
		this.objects = new GameObjectContainer();
		this.zombiesManager = new ZombiesManager(this, this.level, this.rand);
	}

	public void generateCoins(int amount) {
		this.coins += amount;
	}

	private void buyWithCoins(int cost) {
		this.coins -= cost;
	}

	public boolean isEmpty(Position pos) {
		return this.objects.isEmpty(pos);
	}

	public static Position newZombiePosition(int row) {
		return new Position(row, NUM_COLS);
	}

	public boolean isInsideBoard(Position p) {
		return p.row() >= 0 && p.row() < Game.NUM_ROWS && p.col() >= 0 && p.col() < Game.NUM_COLS;
	}

	public int plantCost(String name) {
		for (Plant p : PlantFactory.getAvailablePlants()) {
			if (p.getName().equalsIgnoreCase(name) || p.getSymbol().equalsIgnoreCase(name)) {
				return p.getCost();
			}
		}
		return 0;
	}

	@Override
	public GameItem getGameItemInPosition(Position position) {
		return this.objects.getGameItemInPosition(position);
	}

	@Override
	public boolean isValidPlant(String plantName) {
		for (Plant p : PlantFactory.getAvailablePlants()) {
			if (p.getName().equalsIgnoreCase(plantName) || p.getSymbol().equalsIgnoreCase(plantName)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isValidPosition(Position pos) {
		return isInsideBoard(pos) && isEmpty(pos);
	}

	@Override
	public void addPlant(String plantName, Position pos) {
		Plant plant = PlantFactory.spawnPlant(plantName, this, pos);
		if (plant != null) {
			this.objects.add(plant);
			buyWithCoins(plant.getCost());
		}
	}

	@Override
	public void reset(Level level, long seed) {
		this.level = level;
		this.longSeed = seed;
		reset();
	}

	public void addZombie(Zombie z) {
		this.objects.add(z);

	}

	@Override
	public void zombieDied() {
		this.zombiesManager.zombieDead();

	}

	@Override
	public void zombieArrived() {
		this.zombiesManager.zombieArrived();

	}

	@Override
	public boolean isValidZombie(String zombieName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addZombie(String zombieName, Position pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPlantCheat(String plantName, Position pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean catchSun(Position pos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pushAction(GameAction gameAction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSun(Position pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getGeneratedSuns() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCaughtSuns() {
		// TODO Auto-generated method stub
		return 0;
	}
}
