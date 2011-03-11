package main.java.games.implementations.checkers.pieces;

import java.util.ArrayList;
import java.util.List;

import main.java.games.core.interfaces.IGameBoardCell;
import main.java.games.core.interfaces.IGamePieceType;
import main.java.games.core.types.GameBoardCardinalPosition;
import main.java.games.core.types.GamePlayersEnumeration;

//TODO refactoring
//TODO pouvoir additionner les points cardinaux entre eux
public class CheckersPieceMan extends CheckersPiece {
	// ------------------------------------------------------------
	public CheckersPieceMan(final IGamePieceType type, final GamePlayersEnumeration side) {
		super(type, side);
	}
	// ------------------------------------------------------------
	private GameBoardCardinalPosition getSideDirection() {
		return this.getSide() == GamePlayersEnumeration.FIRST_PLAYER ? GameBoardCardinalPosition.TOP : GameBoardCardinalPosition.BOTTOM;
	}
	// ------------------------------------------------------------
	@Override
	public List<GameBoardCardinalPosition> getWalkOptions(final IGameBoardCell cell) {
		
		// TODO ? utiliser un EnumSet
		final List<GameBoardCardinalPosition> options = new ArrayList<GameBoardCardinalPosition>();
		
		final GameBoardCardinalPosition sideDirection = this.getSideDirection();
		
		final GameBoardCardinalPosition direction1 = GameBoardCardinalPosition.valueOf(sideDirection + "_" + GameBoardCardinalPosition.LEFT.toString());
		if(this.canWalkThrough(cell, direction1)) {
			options.add(direction1);
		}
		
		final GameBoardCardinalPosition direction2 = GameBoardCardinalPosition.valueOf(sideDirection + "_" + GameBoardCardinalPosition.RIGHT.toString());
		if(this.canWalkThrough(cell, direction2)) {
			options.add(direction2);
		}
		
		return options;
	}
	// ------------------------------------------------------------
	@Override
	public List<GameBoardCardinalPosition> getJumpOptions(final IGameBoardCell cell) {
		
		// TODO ? utiliser un EnumSet
		final List<GameBoardCardinalPosition> options = new ArrayList<GameBoardCardinalPosition>();
		
		final GameBoardCardinalPosition sideDirection = this.getSideDirection();
				
		final GameBoardCardinalPosition direction1 = GameBoardCardinalPosition.valueOf(sideDirection + "_" + GameBoardCardinalPosition.LEFT.toString());
		if(this.canJumpOver(cell, direction1)) {
			options.add(direction1);
		}
		
		final GameBoardCardinalPosition direction2 = GameBoardCardinalPosition.valueOf(sideDirection + "_" + GameBoardCardinalPosition.RIGHT.toString());
		if(this.canJumpOver(cell, direction2)) {
			options.add(direction2);
		}
		
		return options;
	}
	// ------------------------------------------------------------
	@Override
	public boolean isPromotable(final IGameBoardCell cell) {
		return cell.getNeighbour(this.getSideDirection()).isNull();
	}
	// ------------------------------------------------------------	
}