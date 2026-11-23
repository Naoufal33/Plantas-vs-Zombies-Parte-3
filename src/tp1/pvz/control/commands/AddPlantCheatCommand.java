package tp1.pvz.control.commands;
import tp1.pvz.exceptions.CommandExecuteException;
import tp1.pvz.exceptions.CommandParseException;
import tp1.pvz.exceptions.InvalidPositionException;
import tp1.pvz.logic.GameModel;
import tp1.pvz.logic.Position;
import tp1.pvz.view.GameView;
import tp1.pvz.view.Messages;
public class AddPlantCheatCommand extends Command {
	private String plantName;
	private int col;
	private int row;
	public AddPlantCheatCommand() {
		super(Messages.COMMAND_CHEAT_PLANT_NAME,
		      Messages.COMMAND_CHEAT_PLANT_SHORTCUT,
		      Messages.COMMAND_CHEAT_PLANT_DETAILS,
		      Messages.COMMAND_CHEAT_PLANT_HELP);
	}
	public AddPlantCheatCommand(String plantName, int col, int row) {
		this();
		this.plantName = plantName;
		this.col = col;
		this.row = row;
	}
	@Override
	public Command create(String[] words) throws CommandParseException {
		if (words.length < 4) {
			throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
		}
		if (words.length > 4) {
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		String name = words[1];
		try {
			int c = Integer.parseInt(words[2]);
			int r = Integer.parseInt(words[3]);
			return new AddPlantCheatCommand(name, c, r);
		} catch (NumberFormatException e) {
			throw new CommandParseException(Messages.INVALID_POSITION, e);
		}
	}
	@Override
	public void execute(GameModel model, GameView view) throws CommandExecuteException {
		if (!model.isValidPlant(plantName)) {
			throw new CommandExecuteException(String.format(Messages.INVALID_GAME_OBJECT, plantName));
		}
		Position pos = new Position(row, col);
		if (!model.isValidPosition(pos)) {
			throw new InvalidPositionException(Messages.INVALID_POSITION);
		}
		model.addPlantCheat(plantName, pos);
		model.update();
		view.showGame();
	}
}
