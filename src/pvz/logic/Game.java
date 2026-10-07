package pvz.logic;

import java.util.Random;

import pvz.control.Level;

public class Game {

	public static int  NUM_ROWS = 4;
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

	public Game(Long seed, Level level) {

	}

	public int getCycles() {
		return 0;
	} 

	public int getCoins() {
		return INITIAL_COINS;
	}

	public int getRemainingZombies() {
		return 3;
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
		return "";
	}

	public static Position newZombiePosition(int row) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEmpty(Position zombiePosition) {
		// TODO Auto-generated method stub
		return false;
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public boolean isInsideBoard(Position p) {
		return p.row() >= 0 && p.row() < Game.NUM_ROWS && p.col() >= 0 && p.col() < Game.NUM_COLS; 
	}

	public void addGameObject(String plantName, int cost) {
		// TODO Auto-generated method stub
		
	}

	public boolean attackZombie(Position p, int damage) {
		return zombiesManager.damage(p, damage); 
	}
}
