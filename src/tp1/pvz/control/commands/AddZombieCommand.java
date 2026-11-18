package tp1.pvz.control.commands;

import tp1.pvz.logic.GameModel;
import tp1.pvz.logic.Position;
import tp1.pvz.view.GameView;
import tp1.pvz.view.Messages;

public class AddZombieCommand extends Command {

	private String zombieName;
	private int col;
	private int row;

	public AddZombieCommand() {
		super(Messages.COMMAND_ADD_ZOMBIE_NAME,
		      Messages.COMMAND_ADD_ZOMBIE_SHORTCUT,
		      Messages.COMMAND_ADD_ZOMBIE_DETAILS,
		      Messages.COMMAND_ADD_ZOMBIE_HELP);
	}

	public AddZombieCommand(String zombieName, int col, int row) {
		this();
		this.zombieName = zombieName;
		this.col = col;
		this.row = row;
	}

	@Override
	public Command create(String[] words) {
		if (words.length < 4) {
			System.out.println(Messages.error(Messages.COMMAND_PARAMETERS_MISSING));
			return null;
		}
		if (words.length > 4) {
			System.out.println(Messages.error(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER));
			return null;
		}
		String name = words[1];
		try {
			int c = Integer.parseInt(words[2]);
			int r = Integer.parseInt(words[3]);
			return new AddZombieCommand(name, c, r);
		} catch (NumberFormatException e) {
			System.out.println(Messages.error(Messages.INVALID_POSITION));
			return null;
		}
	}

	@Override
	public void execute(GameModel model, GameView view) {
		if (!model.isValidZombie(zombieName)) {
			view.showError(Messages.INVALID_GAME_OBJECT);
			return;
		}
		Position pos = new Position(row, col);
		if (!model.isValidPosition(pos)) {
			view.showError(Messages.INVALID_POSITION);
			return;
		}
		model.addZombie(zombieName, pos);
		model.update();
		view.showGame();
	}
}
