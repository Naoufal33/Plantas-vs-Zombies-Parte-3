package tp1.pvz.control.commands;

import tp1.pvz.logic.GameModel;
import tp1.pvz.logic.ZombieFactory;
import tp1.pvz.logic.gameObjects.Zombie;
import tp1.pvz.view.GameView;
import tp1.pvz.view.Messages;
import tp1.pvz.exceptions.CommandParseException;


public class ListZombiesCommand extends Command {

	public ListZombiesCommand() {
		super(Messages.COMMAND_LIST_ZOMBIES_NAME,
		      Messages.COMMAND_LIST_ZOMBIES_SHORTCUT,
		      Messages.COMMAND_LIST_ZOMBIES_DETAILS,
		      Messages.COMMAND_LIST_ZOMBIES_HELP);
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
		StringBuilder buffer = new StringBuilder();
		for (Zombie zombie : ZombieFactory.getAvailableZombies()) {
			buffer.append(zombie.getDescription()).append(Messages.LINE_SEPARATOR);
		}
		view.showMessage(buffer.toString());
	}
}
