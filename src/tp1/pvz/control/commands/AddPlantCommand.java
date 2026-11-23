package tp1.pvz.control.commands;

import tp1.pvz.exceptions.CommandExecuteException;
import tp1.pvz.exceptions.CommandParseException;
import tp1.pvz.exceptions.InvalidPositionException;
import tp1.pvz.exceptions.NotEnoughSuncoinsException;
import tp1.pvz.logic.GameModel;
import tp1.pvz.logic.Position;
import tp1.pvz.view.GameView;
import tp1.pvz.view.Messages;
public class AddPlantCommand extends Command {
	private String plantName;
	private int col;
	private int row;
	public AddPlantCommand() {
		super(Messages.COMMAND_ADD_PLANT_NAME,
		      Messages.COMMAND_ADD_PLANT_SHORTCUT,
		      Messages.COMMAND_ADD_PLANT_DETAILS,
		      Messages.COMMAND_ADD_PLANT_HELP);
	}
	public AddPlantCommand(String plantName, int col, int row) {
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
	        return new AddPlantCommand(name, c, r);
	    } catch (NumberFormatException e) {
	        throw new CommandParseException(Messages.INVALID_POSITION, e);
	    }
	}
	@Override
	public void execute(GameModel model, GameView view) throws CommandExecuteException{
		// 1. Validar si existe la planta
		if (!model.isValidPlant(plantName)) {
			throw new CommandExecuteException(String.format(Messages.INVALID_GAME_OBJECT, plantName));
		}
		// 2. Validar si la posición está dentro del tablero y libre
		Position pos = new Position(row, col);
		if (!model.isValidPosition(pos)) {
	        throw new InvalidPositionException(Messages.INVALID_POSITION);
		}
		// 3. Validar si hay monedas suficientes
		int cost = model.plantCost(plantName);
		if (model.getCoins() < cost) {
			throw new NotEnoughSuncoinsException(Messages.NOT_ENOUGH_COINS);
		}
		// 4. Añadir la planta, avanzar ciclo y repintar
		model.addPlant(plantName, pos);
		model.update();
		view.showGame();
	}
}