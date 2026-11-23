package tp1.pvz.control.commands;

import tp1.pvz.control.Level;
import tp1.pvz.exceptions.CommandParseException;
import tp1.pvz.logic.GameModel;
import tp1.pvz.view.GameView;
import tp1.pvz.view.Messages;
public class ResetCommand extends Command {
	private Level level;
	private Long seed;
	public ResetCommand() {
		super(Messages.COMMAND_RESET_NAME,
		      Messages.COMMAND_RESET_SHORTCUT,
		      Messages.COMMAND_RESET_DETAILS,
		      Messages.COMMAND_RESET_HELP);
	}
	public ResetCommand(Level level, Long seed) {
		this();
		this.level = level;
		this.seed = seed;
	}
	@Override
	public Command create(String[] words) throws CommandParseException {
	    if (words.length == 1) {
	        return new ResetCommand(null, null);
	    } else if (words.length == 3) {
	        Level lvl = Level.valueOfIgnoreCase(words[1]);
	        if (lvl == null) {
	            throw new CommandParseException(Messages.ALLOWED_LEVELS);
	        }
	        try {
	            long s = Long.parseLong(words[2]);
	            return new ResetCommand(lvl, s);
	        } catch (NumberFormatException e) {
	            throw new CommandParseException(String.format(Messages.SEED_NOT_A_NUMBER_ERROR, words[2]), e);
	        }
	    } else {
	        throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
	    }
	}
	@Override
	public void execute(GameModel model, GameView view) {
		if (level != null && seed != null) {
			model.reset(level, seed);
		} else {
			model.reset();
		}
		view.showGame();
	}
}
