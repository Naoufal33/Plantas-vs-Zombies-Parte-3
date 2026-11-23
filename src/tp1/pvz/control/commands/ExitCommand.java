package tp1.pvz.control.commands;
import tp1.pvz.exceptions.CommandParseException;

import tp1.pvz.logic.GameModel;
import tp1.pvz.view.GameView;
import tp1.pvz.view.Messages;
public class ExitCommand extends Command {
	public ExitCommand() {
		super(Messages.COMMAND_EXIT_NAME,
		      Messages.COMMAND_EXIT_SHORTCUT,
		      Messages.COMMAND_EXIT_DETAILS,
		      Messages.COMMAND_EXIT_HELP);
	}
	@Override
	public Command create(String[] words) throws CommandParseException{
		if (words.length != 1) {
	        throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		return this;
	}
	@Override
	public void execute(GameModel model, GameView view) {
		model.quit();
	}
}
