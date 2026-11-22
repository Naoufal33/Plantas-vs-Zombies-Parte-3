package tp1.pvz.logic.actions;

import tp1.pvz.logic.GameWorld;
import tp1.pvz.logic.Position;
import tp1.pvz.logic.gameObjects.GameItem;

public class ExplosionAction implements GameAction {

	private Position pos;
	private int damage;
	private boolean attacksZombie;

	private static final int SIZE = 9;

	private static final int[] COL = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
	private static final int[] ROW = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };

	public ExplosionAction(Position position, int damage, boolean attacksZombie) {
		this.pos = position;
		this.damage = damage;
		this.attacksZombie = attacksZombie;
	}

	@Override
	public void execute(GameWorld game) {
		for (int i = 0; i < SIZE; i++) {
			Position p = new Position(this.pos.row() + ROW[i], this.pos.col() + COL[i]);
			if (game.isInsideBoard(p)) {
				GameItem item = game.getGameItemInPosition(p);
				if (item != null) {
					if (attacksZombie) {
						item.receiveExplosion(damage);
					} else {
						item.receiveZombieAttack(damage);
					}
				}
			}
		}
	}

}
