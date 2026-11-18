package tp1.pvz.logic;

public class SunsManager {

	private static final int COOLDOWN = 5;

	private GameWorld game;
	private int cooldown;

	public SunsManager(GameWorld game) {
		this.game = game;
		this.cooldown = 0;
	}

	public void update() {
		if (cooldown >= COOLDOWN) {
			game.addSun();
			cooldown = 0;
		}
		cooldown++;
	}
}
