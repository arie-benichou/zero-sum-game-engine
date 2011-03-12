package fr.designpattern.zerosumgames.implementations.checkers;

import fr.designpattern.zerosumgames.core.GameBoardMove;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardPosition;
import fr.designpattern.zerosumgames.core.types.GameBoardCardinalPosition;
import fr.designpattern.zerosumgames.core.types.GamePlayersEnumeration;

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