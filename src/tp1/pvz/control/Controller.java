package tp1.pvz.control;
import tp1.pvz.control.commands.Command;
import tp1.pvz.exceptions.RecordException;
import tp1.pvz.logic.Game;
import tp1.pvz.logic.GameModel;
import tp1.pvz.view.GamePrinter;
import tp1.pvz.view.GameView;
/**
 * Input/output coordinator of the game (the C in MVC).
 */
public class Controller {
	private final Game game;
	private final GameView view;
	public Controller(Game game) {
		this.game = game;
		this.view = new GamePrinter(game);
	}
	/**
	 * Runs the game loop delegating actions to Commands.
	 */
	public void run() {
		view.showGame();
		while (!game.hasGameFinished()) {
			String[] words = view.getPrompt();
			Command command = Command.parse(words);
			if (command != null) {
				command.execute(game, view);
			}
		}
		try {
			game.updateRecord();
		}
		catch(RecordException e) {
			view.showError(e.getMessage());
		}
		
		view.showEndMessage();
	}
}