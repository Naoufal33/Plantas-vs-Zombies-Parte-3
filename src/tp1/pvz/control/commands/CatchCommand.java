package tp1.pvz.control.commands;

import tp1.pvz.logic.GameModel;
import tp1.pvz.logic.Position;
import tp1.pvz.view.GameView;
import tp1.pvz.view.Messages;
import tp1.pvz.exceptions.CommandExecuteException;
import tp1.pvz.exceptions.CommandParseException;
import tp1.pvz.exceptions.NotCatchablePositionException;

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
	public Command create(String[] words) throws CommandParseException {
	    if (words.length < 3) {
	        throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
	    }
	    if (words.length > 3) {
	        throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
	    }
	    try {
	        int c = Integer.parseInt(words[1]);
	        int r = Integer.parseInt(words[2]);
	        return new CatchCommand(c, r);
	    } catch (NumberFormatException e) {
	        throw new CommandParseException(Messages.INVALID_POSITION, e);
	    }
	}

	@Override
	public void execute(GameModel model, GameView view) throws CommandExecuteException {
	    Position pos = new Position(row, col);
	    if (!model.catchSun(pos)) {
	        throw new NotCatchablePositionException(String.format(Messages.NO_CATCHABLE_IN_POSITION, col, row));
	    }
		view.showGame();
	}
}
