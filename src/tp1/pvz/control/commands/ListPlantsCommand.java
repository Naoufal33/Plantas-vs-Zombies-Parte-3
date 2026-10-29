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
	@
