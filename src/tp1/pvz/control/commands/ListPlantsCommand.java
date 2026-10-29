package tp1.pvz.control.commands;
import tp1.pvz.logic.GameModel;
import tp1.pvz.logic.gameObjects.Peashooter;
import tp1.pvz.logic.gameObjects.Sunflower;
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
		// Imprime las descripciones de las plantas disponibles:
		buffer.append(Sunflower.getDescription()).append(Messages.LINE_SEPARATOR);
		buffer.append(Peashooter.getDescription()).append(Messages.LINE_SEPARATOR);
		view.showMessage(buffer.toString());
		// Nota: Cuando Persona 2 termine PlantFactory, esto se cambiará por:
		// for (Plant plant : PlantFactory.getAvailablePlants()) { ... }
	}
}
