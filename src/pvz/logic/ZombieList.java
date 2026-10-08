package pvz.logic;

import java.util.ArrayList;
import java.util.Iterator;

import pvz.logic.gameObjects.Zombie;

public class ZombieList {
	private int numberOfZombies;
	private Zombie[] zList;

	public ZombieList() {
		this.numberOfZombies = 0;
		zList = new Zombie[32];
	}

	public int size() {
		return numberOfZombies;
	}

	public String iconInPosition(Position p) {
		return null;
	}

	public void add(Zombie z) {
		zList[numberOfZombies] = z;
		this.numberOfZombies++; 
	}

	public boolean damage(Position p, int damage) {
		for (Zombie z : zList) {
			if (z.isInPosition(p) && z.isAlive()) {
				z.receiveAttack(damage);
				return true;
			}
		}
		return false;
	}

	public boolean isEmpty(Position p) {
		for (Zombie z : zList) {
			if (z.isInPosition(p) && z.isAlive()) {
				return false;
			}
		}
		return true;
	}

	public void update() {
		for (Zombie z : zList)
			z.update();
		removeDead();
	}

	public void removeDead() {
		
	}

	public boolean anyInColumn(int column) {
		return false;
	}
}
