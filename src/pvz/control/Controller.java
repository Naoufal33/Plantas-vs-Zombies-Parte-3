package pvz.control;

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
		while (!this.exit) {
			String words[] = view.getPrompt();
			String msg = parseUserInput(words);

			if (msg != null)
				view.showMessage(msg);

		}
		view.showEndMessage();
	}

	private String parseUserInput(String[] words) {
		if (words == null || words.length == 0)
			return null;
		if (words.length > 0) {
			String s = words[0];

			if (s.equals("a") || s.equals("add")) {
				// Comprobar que tiene los parametros [add, planta, columna, fila]
				if (words.length < 4) {
					view.showError(Messages.COMMAND_PARAMETERS_MISSING);
					return null;
				}
				// Comprobar que existe la planta
				String plantName = words[1];
				if (!isValidGameObject(plantName)) {
					view.showError(Messages.INVALID_GAME_OBJECT);
					return null;
				}
				// Crear la posicion
				int col = Integer.parseInt(words[2]);
				int row = Integer.parseInt(words[3]);

				Position pos = new Position(row, col);
				if (!isValidPosition(pos)) {
					view.showError(Messages.INVALID_POSITION);
					return null;
				}
				//Comprobamos si podemos comprar la planta
				int cost = plantCost(plantName);
				if (game.getCoins() < cost) {
					view.showError(Messages.NOT_ENOUGH_COINS);
					return null;
				}
				//Crear la planta y repintamos el tablero
				game.addGameObject(plantName, pos);
				view.showGame();
				return null;
			} else if (s.equals("l") || s.equals("list")) {
				return Messages.LIST;
			} else if (s.equals("r") || s.equals("reset")) {
				game.reset();
				view.showGame();
				return null;
			} else if (s.equals("h") || s.equals("help")) {
				return Messages.HELP;
			} else if (s.equals("e") || s.equals("exit")) {
				this.exit = true;

			} else if (s.isEmpty() || s.equals("n") || s.equals("none")) {
				game.update();
				view.showGame();
				return null;
			}
			else {
				view.showError(Messages.UNKNOWN_COMMAND);
				return null; 
			}

		}
		return null;
	}

	private boolean isValidPosition(Position position) {
		return game.isInsideBoard(position) && game.isEmpty(position);
	}

	private boolean isValidGameObject(String objectName) {
		return game.checkGameObject(objectName); 
	}

	private int plantCost(String name) {
		if (name.equals("s") || name.equals("sunflower")) {
			return Sunflower.COST;
		} else {
			return Peashooter.COST;
		}
	}

}