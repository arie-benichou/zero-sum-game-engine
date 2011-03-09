package main.java.implementations.checkers;

import main.java.core.GameBoardMove;
import main.java.core.interfaces.IGameBoardPosition;
import main.java.core.types.GameBoardCardinalPosition;
import main.java.core.types.GamePlayersEnumeration;

// TODO ? implémenter une interface
public class CheckersMove extends GameBoardMove {
	
	private GameBoardCardinalPosition direction;
	private final void setDirection(final GameBoardCardinalPosition direction) {
		this.direction = direction;
	}
	public final GameBoardCardinalPosition getDirection() {
		return direction;
	}	

	public CheckersMove(final GamePlayersEnumeration side, final IGameBoardPosition position, final GameBoardCardinalPosition direction) {
		super(side, position);
		this.setDirection(direction);
	}

	@Override
	public String toString() {
		return super.toString() + " " + this.getDirection();
	}

}