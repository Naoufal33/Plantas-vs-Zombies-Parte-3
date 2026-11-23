package tp1.pvz.control.commands;
import tp1.pvz.exceptions.CommandExecuteException;
import tp1.pvz.exceptions.CommandParseException;
import tp1.pvz.exceptions.InvalidPositionException;
import tp1.pvz.logic.GameModel;
import tp1.pvz.logic.Position;
import tp1.pvz.view.GameView;
import tp1.pvz.view.Messages;
public class AddZombieCommand extends Command {
	private String zombieName;
	private int col;
	private int row;
	public AddZombieCommand() {
		super(Messages.COMMAND_ADD_ZOMBIE_NAME,
		      Messages.COMMAND_ADD_ZOMBIE_SHORTCUT,
		      Messages.COMMAND_ADD_ZOMBIE_DETAILS,
		      Messages.COMMAND_ADD_ZOMBIE_HELP);
	}
	public AddZombieCommand(String zombieName, int col, int row) {
		this();
		this.zombieName = zombieName;
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
			return new AddZombieCommand(name, c, r);
		} catch (NumberFormatException e) {
			throw new CommandParseException(Messages.INVALID_POSITION, e);
		}
	}
	@Override
	public void execute(GameModel model, GameView view) throws CommandExecuteException {
		if (!model.isValidZombie(zombieName)) {
			throw new CommandExecuteException(String.format(Messages.INVALID_GAME_OBJECT, zombieName));
		}
		Position pos = new Position(row, col);
		if (!model.isValidPosition(pos)) {
			throw new InvalidPositionException(Messages.INVALID_POSITION);
		}
		model.addZombie(zombieName, pos);
		model.update();
		view.showGame();
	}
}
