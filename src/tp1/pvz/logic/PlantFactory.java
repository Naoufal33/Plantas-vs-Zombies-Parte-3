package tp1.pvz.logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.pvz.logic.gameObjects.CherryBomb;
import tp1.pvz.logic.gameObjects.Peashooter;
import tp1.pvz.logic.gameObjects.Plant;
import tp1.pvz.logic.gameObjects.Sunflower;
import tp1.pvz.logic.gameObjects.WallNut;

public class PlantFactory {

	private static final List<Plant> AVAILABLE_PLANTS = Arrays.asList(
			new Sunflower(),
			new Peashooter(),
			new WallNut(),
			new CherryBomb()
			); 
	
	public static List <Plant> getAvailablePlants(){
		return Collections.unmodifiableList(AVAILABLE_PLANTS); 
	}
	
	public static Plant spawnPlant(String plantName, GameWorld game, Position pos) {
		for(Plant p: AVAILABLE_PLANTS) {
			if(p.getName().equalsIgnoreCase(plantName) || p.getSymbol().equalsIgnoreCase(plantName) ) {
				return p.create(pos, game); 
			}
		}
		return null; 
	}
}
