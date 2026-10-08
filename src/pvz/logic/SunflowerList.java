package pvz.logic;

import java.util.ArrayList;

import pvz.logic.gameObjects.Peashooter;
import pvz.logic.gameObjects.Sunflower;

public class SunflowerList {
	
	private int numberOfSunflowers;
	private Sunflower[] sList;
	
	public SunflowerList() {
		this.numberOfSunflowers = 0;
		sList = new Sunflower[Game.NUM_COLS * Game.NUM_ROWS];
	}
	
	public String iconInPosition(Position pos) {
		for (int i = 0; i < numberOfSunflowers; i++) {
			if (sList[i].isInPosition(pos) && sList[i].isAlive()) {
				return sList[i].getIcon();
			}
		}
		return "";
	}
	
	
	public boolean isEmpty(Position pos) {
		boolean empty = true;
		for (int i = 0; i < numberOfSunflowers; i++) {
			if (sList[i].isInPosition(pos) && sList[i].isAlive()) {
				empty = false;
			}
		}
		return empty;	
	}
	
	
	public void removeDead() {
		int i = 0;
		while (i < numberOfSunflowers) {
			if (!sList[i].isAlive()) {
				removeFromIndex(i);
			} else {
				i++;
			}
		}
	}
	
	
	public void update() {
		for(int i = 0; i < numberOfSunflowers; i++) {
			sList[i].update();
		}
	}
	
	
	
	public void add(Sunflower sunflower) {
		if (numberOfSunflowers < sList.length) {
			sList[numberOfSunflowers] = sunflower;
			numberOfSunflowers++;	
		}
	}
	
	
	private void removeFromIndex(int index){
		for (int i = index; i < numberOfSunflowers - 1; i++) {
		sList[i] = sList[i + 1];
	}
	sList[numberOfSunflowers - 1] = null;
	numberOfSunflowers--;
}
	
	public void receiveDamage(Position p, int damage) {
		for	(int i = 0; i < numberOfSunflowers; i++) {
			if (sList[i].isInPosition(p) && sList[i].isAlive()) {
				sList[i].receiveDamage(damage);
				break; 
			}
		}
	}


}

		

	

		
	
	
