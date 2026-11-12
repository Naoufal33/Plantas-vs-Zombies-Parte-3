package tp1.pvz.logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.pvz.logic.gameObjects.*;

public class ZombieFactory {
	
	private static final List<Zombie> AVAILABLE_ZOMBIES = Arrays.asList(
			new CommonZombie(),
			new BucketHeadZombie(),
			new SportyZombie(),
			new ExplosiveZombie() 
			);
	
	public static List<Zombie> getAvailableZombies(){
		return Collections.unmodifiableList(AVAILABLE_ZOMBIES); 
	}

	public static Zombie spawnZombie(String zombieName, GameWorld game, Position pos) {
		for(Zombie z: AVAILABLE_ZOMBIES) {
			if(z.getName().equalsIgnoreCase(zombieName) || z.getSymbol().equalsIgnoreCase(zombieName)) {
				return z.create(pos, game); 
			}
		}
		return null; 
	}
	
	public static Zombie spawnZombie(int zombieType, GameWorld game, Position pos) {
		if(zombieType < 0 || zombieType >= AVAILABLE_ZOMBIES.size()) return null; 
		Zombie z = AVAILABLE_ZOMBIES.get(zombieType); 
		return z.create(pos, game); 
	}
}
