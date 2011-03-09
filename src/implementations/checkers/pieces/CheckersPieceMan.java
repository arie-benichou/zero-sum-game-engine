package implementations.checkers.pieces;

import java.util.ArrayList;
import java.util.List;

import core.interfaces.IGameBoardCell;
import core.interfaces.IGamePieceType;
import core.types.GameBoardCardinalPosition;
import core.types.GamePlayersEnumeration;

//TODO refactoring
//TODO pouvoir additionner les points cardinaux entre eux
public class CheckersPieceMan extends CheckersPiece {
	// ------------------------------------------------------------
	public CheckersPieceMan(IGamePieceType type, GamePlayersEnumeration side) {
		super(type, side);
	}
	// ------------------------------------------------------------
	private GameBoardCardinalPosition getSideDirection() {
		return this.getSide() == GamePlayersEnumeration.FIRST_PLAYER ? GameBoardCardinalPosition.TOP : GameBoardCardinalPosition.BOTTOM;
	}
	// ------------------------------------------------------------
	@Override
	public List<GameBoardCardinalPosition> getWalkOptions(IGameBoardCell cell) {
		
		// TODO ? utiliser un EnumSet
		List<GameBoardCardinalPosition> options = new ArrayList<GameBoardCardinalPosition>();
		
		GameBoardCardinalPosition sideDirection = this.getSideDirection();
		
		GameBoardCardinalPosition direction1 = GameBoardCardinalPosition.valueOf(sideDirection + "_" + GameBoardCardinalPosition.LEFT.toString());
		if(this.canWalkThrough(cell, direction1))
			options.add(direction1);
		
		GameBoardCardinalPosition direction2 = GameBoardCardinalPosition.valueOf(sideDirection + "_" + GameBoardCardinalPosition.RIGHT.toString());
		if(this.canWalkThrough(cell, direction2))
			options.add(direction2);
		
		return options;
	}
	// ------------------------------------------------------------
	@Override
	public List<GameBoardCardinalPosition> getJumpOptions(IGameBoardCell cell) {
		
		// TODO ? utiliser un EnumSet
		List<GameBoardCardinalPosition> options = new ArrayList<GameBoardCardinalPosition>();
		
		GameBoardCardinalPosition sideDirection = this.getSideDirection();
				
		GameBoardCardinalPosition direction1 = GameBoardCardinalPosition.valueOf(sideDirection + "_" + GameBoardCardinalPosition.LEFT.toString());
		if(this.canJumpOver(cell, direction1))
			options.add(direction1);
		
		GameBoardCardinalPosition direction2 = GameBoardCardinalPosition.valueOf(sideDirection + "_" + GameBoardCardinalPosition.RIGHT.toString());
		if(this.canJumpOver(cell, direction2))
			options.add(direction2);
		
		return options;
	}
	// ------------------------------------------------------------
}