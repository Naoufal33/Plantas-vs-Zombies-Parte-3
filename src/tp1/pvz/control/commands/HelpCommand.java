package tp1.pvz.control.commands;
import tp1.pvz.logic.GameModel;
import tp1.pvz.view.GameView;
import tp1.pvz.view.Messages;
import tp1.pvz.exceptions.CommandParseException;

public class HelpCommand extends Command {
	public HelpCommand() {
		super(Messages.COMMAND_HELP_NAME,
		      Messages.COMMAND_HELP_SHORTCUT,
		      Messages.COMMAND_HELP_DETAILS,
		      Messages.COMMAND_HELP_HELP);
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
		StringBuilder buffer = new StringBuilder(Messages.HELP_AVAILABLE_COMMANDS);
		for (Command c : Command.getAvailableCommands()) {
			buffer.append(Messages.LINE_SEPARATOR);
			buffer.append(c.helpText());
		}
		view.showMessage(buffer.toString());
	}
}
