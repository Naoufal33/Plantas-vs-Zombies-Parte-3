package tp1.pvz.control.commands;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import tp1.pvz.logic.GameModel;
import tp1.pvz.view.GameView;
import tp1.pvz.view.Messages;

public abstract class Command {
	protected final String name;
	protected final String shortcut;
	private final String details;
	private final String help;
	public Command(String name, String shortcut, String details, String help) {
		this.name = name;
		this.shortcut = shortcut;
		this.details = details;
		this.help = help;
	}
	protected boolean matchCommand(String token) {
		return token.equalsIgnoreCase(this.name) || token.equalsIgnoreCase(this.shortcut);
	}
	public abstract Command create(String[] words);
	public abstract void execute(GameModel model, GameView view);
	public String helpText() {
		return details + Messages.HELP_DETAILS_COMMAND_HELP_SEPARATOR + help;
	}
	private static final List<Command> AVAILABLE_COMMANDS = Arrays.asList(
		new AddPlantCommand(),
		new AddZombieCommand(),
		new AddPlantCheatCommand(),
		new CatchCommand(),
		new ListPlantsCommand(),
		new ListZombiesCommand(),
		new ResetCommand(),
		new HelpCommand(),
		new ExitCommand(),
		new NoneCommand()
	);
	public static Iterable<Command> getAvailableCommands() {
		return Collections.unmodifiableList(AVAILABLE_COMMANDS);
	}
	public static Command parse(String[] commandWords) {
		if (commandWords == null || commandWords.length == 0 || commandWords[0].isEmpty()) {
			return new NoneCommand();
		}
		String token = commandWords[0];
		for (Command command : AVAILABLE_COMMANDS) {
			if (command.matchCommand(token)) {
				return command.create(commandWords);
			}
		}
		System.out.println(Messages.error(Messages.UNKNOWN_COMMAND));
		return null;
	}
}
