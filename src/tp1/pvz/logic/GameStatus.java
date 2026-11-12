package tp1.pvz.logic;

public interface GameStatus {
	public int getCycles();

	public int getCoins();

	public int getRemainingZombies();

	public String positionToString(Position position);

	public boolean playerQuits();

	public boolean playerWins();
	
	public int getGeneratedSuns();
	
	public int getCaughtSuns();

}
