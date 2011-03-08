package implementations.checkers;

import core.GameBoardMove;
import core.interfaces.IGameBoardPosition;
import core.types.GameBoardCardinalPosition;
import core.types.GamePlayersEnumeration;

// TODO ? implémenter une interface
public class CheckersMove extends GameBoardMove {
	
	private GameBoardCardinalPosition direction;
	private final void setDirection(GameBoardCardinalPosition direction) {
		this.direction = direction;
	}
	public final GameBoardCardinalPosition getDirection() {
		return direction;
	}	

	public CheckersMove(GamePlayersEnumeration side, IGameBoardPosition position, GameBoardCardinalPosition direction) {
		super(side, position);
		this.setDirection(direction);
	}

	@Override
	public String toString() {
		return super.toString() + " " + this.getDirection();
	}

}