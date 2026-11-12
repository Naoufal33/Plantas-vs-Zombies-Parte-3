package tp1.pvz.logic;

import java.util.Collections;
import java.util.List;

import tp1.pvz.logic.gameObjects.Zombie;

public class ZombieFactory {
	
	public static List<Zombie> getAvailableZombies(){
		return Collections.emptyList(); 
	}

	public static Zombie spawnZombie(String zombieName, GameWorld game, Position pos) {
		return null; 
	}
}
