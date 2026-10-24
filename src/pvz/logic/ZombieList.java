package pvz.logic;

import pvz.logic.gameObjects.Zombie;

public class ZombieList {
	private int numberOfZombies;
	private Zombie[] zList;

	public ZombieList() {
		this.numberOfZombies = 0;
		zList = new Zombie[Game.NUM_COLS * Game.NUM_ROWS];
	}

	public int size() {
		return numberOfZombies;
	}

	public String iconInPosition(Position p) {
		for(int i = 0; i < this.numberOfZombies; i++) {
			if(zList[i].isInPosition(p) && zList[i].isAlive()) {
				return zList[i].getIcon(); 
			}
		}
		return ""; 
		
	}

	public void add(Zombie z) {
		zList[numberOfZombies] = z;
		this.numberOfZombies++; 
	}

	public boolean damage(Position p, int damage) {
		for (int i = 0; i< this.numberOfZombies; i++) {
			if (zList[i].isInPosition(p) && zList[i].isAlive()) {
				zList[i].receiveAttack(damage);
				return true;
			}
		}
		return false;
	}

	public boolean isEmpty(Position p) {
		for (int i = 0; i< this.numberOfZombies; i++) {
			if (zList[i].isInPosition(p) && zList[i].isAlive()) {
				return false;
			}
		}
		return true;
	}

	public void update() {
		for (int i = 0; i< this.numberOfZombies; i++)
			zList[i].update();
		removeDead();
	}

	public void removeDead() {
		int i = 0; 
		while(i < this.numberOfZombies) {
			if(!zList[i].isAlive()) {
				removeFromIndex(i); 
			}
			else {
				i++; 
			}
		}
	}

	public boolean anyInColumn(int column) {
		Position targetCol = new Position(0, column);
		for (int i = 0; i< this.numberOfZombies; i++) {
			if(zList[i].isAlive() && zList[i].isVerticallyAligned(targetCol)) {
				return true; 
			}
		}
		return false; 
	}
	
	private void removeFromIndex(int index) {
		for(int i = index; i < this.numberOfZombies-1; i++) {
			zList[i] = zList[i+1]; 
		}
		zList[this.numberOfZombies- 1] = null; 
		this.numberOfZombies--; 
	}
}
