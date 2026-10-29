package tp1.pvz.control.commands;

import tp1.pvz.control.Level;
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
	public Command create(String[] words) {
		if (words.length == 1) {
			return new ResetCommand(null, null);
		} else if (words.length == 3) {
			Level lvl = Level.valueOfIgnoreCase(words[1]);
			if (lvl == null) {
				System.out.println(Messages.error(Messages.ALLOWED_LEVELS));
				return null;
			}
			try {
				long s = Long.parseLong(words[2]);
				return new ResetCommand(lvl, s);
			} catch (NumberFormatException e) {
				System.out.println(Messages.error(Messages.SEED_NOT_A_NUMBER));
				return null;
			}
		} else {
			System.out.println(Messages.error(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER));
			return null;
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
