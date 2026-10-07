package pvz.control;

import java.util.ArrayList;

import pvz.logic.Game;
import pvz.logic.Position;
import pvz.logic.gameObjects.Peashooter;
import pvz.logic.gameObjects.Sunflower;
import pvz.view.GamePrinter;
import pvz.view.GameView;
import pvz.view.Messages;

/**
 * Input/output coordinator of the game (the C in MVC).
 *
 * <p>
 * Owns the game loop: reads a line from stdin, parses it into a command,
 * validates parameters (plant type, position), delegates state changes to
 * {@link Game}, and triggers a board reprint via {@link tp1.pvz.view.GameView>}
 * when the cycle advances. It holds no game state of its own; the source of
 * truth is always {@link Game}.
 */
public class Controller {

	private final Game game;
	private final GameView view;
	private boolean exit; 

	public Controller(Game game) {
		this.game = game;
		this.view = new GamePrinter(game);
		this.exit = false; 
	}

	/**
	 * Runs the game logic.
	 */
	public void run() {
		view.showGame(); 
		while(!this.exit) {
			view.getPrompt();
		}
		view.showEndMessage();
	}

	private String parseUserInput(String[] words) {
		if(words == null) return null; 
		
		String s = words[0]; 
		s.toLowerCase(); 
		
		if(s.equals("a") || s.equals("add")) {
			return null; 
		}
		else if(s.equals("l") || s.equals("list")) {
			String r = (String) Sunflower.getDescription(); 
			String h = (String) Peashooter.getDescription(); 
			String sb = r + h; 
			return sb; 
		}
		else if(s.equals("r") || s.equals("reset")) {
			return null;
		}
		else if(s.equals("h") || s.equals("help")) {
			String t = Messages.HELP; 
			return t; 
		}
		else if(s == "e" || s =="exit") {
			this.exit = true; 
			
		}
		else if(s == "n" || s =="none") {
			return null;
		}
		return null;
	}

	private boolean isValidPosition(Position position) {
		return false;
	}

	private boolean isValidGameObject(String[] objectName) {
		return false;
	}

}