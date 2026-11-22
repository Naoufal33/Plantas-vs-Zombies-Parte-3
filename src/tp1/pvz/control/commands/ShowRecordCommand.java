package tp1.pvz.control.commands;

import tp1.pvz.logic.GameModel;
import tp1.pvz.view.GameView;
import tp1.pvz.view.Messages;

public class ShowRecordCommand extends Command {

	public ShowRecordCommand() {
		super(Messages.COMMAND_RECORD_NAME, 
				Messages.COMMAND_RECORD_SHORTCUT, 
				Messages.COMMAND_RECORD_DETAILS,
				Messages.COMMAND_RECORD_HELP);
	}

	@Override
	public Command create(String[] words) {
		if (words.length != 1) {
			System.out.println(Messages.error(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER));
			return null;
		}
		return this;
	}

	@Override
	public void execute(GameModel model, GameView view) {
		view.showMessage(String.format(Messages.RECORD, model.getLevel().name(), model.getRecord()));
	}

}
