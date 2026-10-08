package pvz.logic;


import pvz.logic.gameObjects.Peashooter;

public class PeashooterList {
	private int numberOfPeashooters;
	private Peashooter[] pList;

	public PeashooterList() {
		this.numberOfPeashooters = 0;
		pList = new Peashooter[Game.NUM_COLS * Game.NUM_ROWS];
	}

	public String iconInPosition(Position pos) {
		for (int i = 0; i < this.numberOfPeashooters; i++ ) {
			if (this.pList[i].isInPosition(pos) && pList[i].isAlive()) {
				return pList[i].getIcon();
			}
		}
		return "";
	}

	public void update() {
		for (int i = 0; i < this.numberOfPeashooters; i++) {
			pList[i].update();
		}
	}

	public void removeDead() {
		int i = 0; 
		while(i < this.numberOfPeashooters) {
			if(!pList[i].isAlive()) {
				removeFromIndex(i); 
			}
			else {
				i++; 
			}
		}
	}

	public boolean isEmpty(Position pos) {
		for (int i = 0; i < this.numberOfPeashooters; i++) {
			if (pList[i].isInPosition(pos) && pList[i].isAlive()) {
				return false; 
			}
		}
		return true;
	}
	
	public void add(Peashooter p) {
		pList[this.numberOfPeashooters] = p; 
		this.numberOfPeashooters++;
	}

	public void receiveDamage(Position pos, int damage) {
		for(int i = 0; i < this.numberOfPeashooters; i++ ) {
			if(pList[i].isInPosition(pos) && pList[i].isAlive()) {
				pList[i].receiveDamage(damage);
				break; 
			}
		}
	}
	
	private void removeFromIndex(int index) {
		for(int i = index; i < this.numberOfPeashooters-1; i++) {
			pList[i] = pList[i+1]; 
		}
		pList[this.numberOfPeashooters- 1] = null; 
		this.numberOfPeashooters--; 
	}

}
