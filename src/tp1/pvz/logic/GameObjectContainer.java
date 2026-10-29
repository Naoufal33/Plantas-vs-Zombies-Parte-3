package tp1.pvz.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tp1.pvz.logic.gameObjects.GameItem;
import tp1.pvz.logic.gameObjects.GameObject;

public class GameObjectContainer {

	private List<GameObject> gameObjects; 
	
	public GameObjectContainer() {
		gameObjects = new ArrayList<>(); 
	}
	
	
	public void add(GameObject o) {
		gameObjects.add(o); 
		o.onEnter();
	}
	
	public void update() {
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i).isAlive()) {
				gameObjects.get(i).update();
			}
		}
		removeDead(); 
	}
	
	public void removeDead() {
		Iterator <GameObject> it = gameObjects.iterator(); 
		while(it.hasNext()) {
			GameObject obj = it.next(); 
			if(!obj.isAlive()) {
				obj.onExit();
				it.remove();
			}
		}
	}
	
	public boolean isEmpty(Position pos) {
		for(GameObject o: gameObjects) {
			if(o.isAlive() && o.isInPosition(pos)) {
				return false; 
			}
		}
		return true; 
		
	}
	
	public GameItem getGameItemInPosition(Position pos) {
		for(GameObject o: gameObjects) {
			if(o.isAlive() && o.isInPosition(pos)) {
				return o; 
			}
		}
		return null; 
	}
	
	public String positionToString(Position pos) {
		for(GameObject o: gameObjects) {
			if(o.isAlive() && o.isInPosition(pos)) {
				return o.getIcon();  
			}
		}
		return ""; 
	}
}
