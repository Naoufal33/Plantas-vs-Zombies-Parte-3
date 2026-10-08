package pvz.logic;

import java.util.ArrayList;

import pvz.logic.gameObjects.Peashooter;
import pvz.logic.gameObjects.Sunflower;

public class SunflowerList {
	
	private int numberOfSunflowers;
	private Sunflower[] sList;
	
	public SunflowerList() {
		this.numberOfSunflowers = 0;
		sList = new ArrayList<>();
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
		return true;
	}

	public void add(Sunflower sunflower) {
		// TODO Auto-generated method stub
		
	}

	


}
