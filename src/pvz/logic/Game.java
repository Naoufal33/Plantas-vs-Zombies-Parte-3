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
}
