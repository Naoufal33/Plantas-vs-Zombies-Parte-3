package pvz.logic;

import java.util.ArrayList;
import java.util.Iterator;

import pvz.logic.gameObjects.Peashooter;

public class PeashooterList {
	private int numberOfPeashooters;
	private Peashooter[] pList;

	public PeashooterList() {
		this.numberOfPeashooters = 0;
		pList = new Peashooter[32];
	}

	public String iconInPosition(Position pos) {
		for (Peashooter p : pList) {
			if (p.isInPosition(pos) && p.isAlive()) {
				return p.getIcon();
			}
		}
		return "";
	}

	public void update() {
		for (Peashooter p : pList) {
			p.update();
		}
	}

	public void removeDead() {
		Iterator<Peashooter> it = pList.iterator();
		while (it.hasNext()) {
			if (!it.next().isAlive())
				it.remove();
		}
		this.numberOfPeashooters = pList.size(); 
	}

	public boolean isEmpty(Position pos) {
		boolean empty = true;
		for (Peashooter p : pList) {
			if (p.isInPosition(pos) && p.isAlive()) {
				empty = false;
			}
		}
		return empty;
	}

	private void removeFromIndex(int index) {
		
	}
	
	public void add(Peashooter p) {
		pList.length;
		this.numberOfPeashooters++;
	}

	public void receiveDamage(Position pos, int damage) {
		for(Peashooter p: pList) {
			if(p.isInPosition(pos) && p.isAlive()) {
				p.receiveDamage(damage);
				break; 
			}
		}
	}

}
