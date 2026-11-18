package tp1.pvz.control.commands;

import tp1.pvz.logic.GameModel;
import tp1.pvz.logic.Position;
import tp1.pvz.view.GameView;
import tp1.pvz.view.Messages;

public class CatchCommand extends Command {

	private int col;
	private int row;

	public CatchCommand() {
		super(Messages.COMMAND_CATCH_NAME,
		      Messages.COMMAND_CATCH_SHORTCUT,
		      Messages.COMMAND_CATCH_DETAILS,
		      Messages.COMMAND_CATCH_HELP);
	}

	public CatchCommand(int col, int row) {
		this();
		this.col = col;
		this.row = row;
	}

	@Override
	public Command create(String[] words) {
		if (words.length < 3) {
			System.out.println(Messages.error(Messages.COMMAND_PARAMETERS_MISSING));
			return null;
		}
		if (words.length > 3) {
			System.out.println(Messages.error(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER));
			return null;
		}
		try {
			int c = Integer.parseInt(words[1]);
			int r = Integer.parseInt(words[2]);
			return new CatchCommand(c, r);
		} catch (NumberFormatException e) {
			System.out.println(Messages.error(Messages.INVALID_POSITION));
			return null;
		}
	}

	@Override
	public void execute(GameModel model, GameView view) {
		Position pos = new Position(row, col);
		if (!model.catchSun(pos)) {
			view.showError(String.format(Messages.NO_CATCHABLE_IN_POSITION, col, row));
			return;
		}
		view.showGame();
	}
}
