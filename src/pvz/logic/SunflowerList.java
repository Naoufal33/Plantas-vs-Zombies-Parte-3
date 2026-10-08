package pvz.logic;

import java.util.ArrayList;

import pvz.logic.gameObjects.Peashooter;
import pvz.logic.gameObjects.Sunflower;

public class SunflowerList {
	
	private int numberOfSunflowers;
	private Sunflower[] sList;
	
	public SunflowerList() {
		this.numberOfSunflowers = 0;
		sList = new Sunflower[32];
	}
	
	public String iconInPosition(Position pos) {
		for (Sunflower s : sList) {
			if (s.isInPosition(pos) && s.isAlive()) {
				return s.getIcon();
			}
		}
		return "";
	}
	
	public boolean isEmpty(Position pos) {
		boolean empty = true;
		for (Sunflower s : sList) {
			if (s.isInPosition(pos) && s.isAlive()) {
				empty = false;
			}
		}
		return empty;	
		}

	public void removeDead() {
		
	}
	
	public void update() {
		
	}
	
	public void add(Sunflower sunflower) {
		
		
	}

	private void removeFromIndex(int index){
		
	}
	
	public void receiveDamage(Position p, int damage) {
		for(Sunflower s: sList) {
			if(s.isInPosition(p) && s.isAlive()) {
				s.receiveDamage(damage);
				break; 
			}
		}
	}


}
