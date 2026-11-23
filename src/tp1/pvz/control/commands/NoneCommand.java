package tp1.pvz.control.commands;

import tp1.pvz.logic.GameModel;
import tp1.pvz.view.GameView;
import tp1.pvz.view.Messages;
import tp1.pvz.exceptions.CommandParseException;
public class NoneCommand extends Command {
	public NoneCommand() {
		super(Messages.COMMAND_UPDATE_NAME,
		      Messages.COMMAND_UPDATE_SHORTCUT,
		      Messages.COMMAND_UPDATE_DETAILS,
		      Messages.COMMAND_UPDATE_HELP);
	}
	@Override
	protected boolean matchCommand(String token) {
		return token.isEmpty() || super.matchCommand(token);
	}
	@Override
	public Command create(String[] words) throws CommandParseException {
	    if (words.length > 1) {
	        throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
	    }
	    return this;
	}
	@Override
	public void execute(GameModel model, GameView view) {
		model.update();
		view.showGame();
	}
}
