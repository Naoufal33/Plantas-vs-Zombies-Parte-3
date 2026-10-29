package tp1.pvz.control.commands;

import tp1.pvz.logic.GameModel;
import tp1.pvz.logic.PlantFactory;
import tp1.pvz.logic.gameObjects.Plant;
import tp1.pvz.view.GameView;
import tp1.pvz.view.Messages;

public class ListPlantsCommand extends Command {
	public ListPlantsCommand() {
		super(Messages.COMMAND_LIST_PLANTS_NAME,
		      Messages.COMMAND_LIST_PLANTS_SHORTCUT,
		      Messages.COMMAND_LIST_PLANTS_DETAILS,
		      Messages.COMMAND_LIST_PLANTS_HELP);
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
		StringBuilder buffer = new StringBuilder();
		for (Plant plant : PlantFactory.getAvailablePlants()) {
			buffer.append(plant.getDescription()).append(Messages.LINE_SEPARATOR);
		}
		view.showMessage(buffer.toString());
	}
}
