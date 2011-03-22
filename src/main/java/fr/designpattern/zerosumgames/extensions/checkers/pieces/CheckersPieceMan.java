package fr.designpattern.zerosumgames.extensions.checkers.pieces;

import java.util.ArrayList;
import java.util.List;

import fr.designpattern.zerosumgames.game.components.board.GameBoardCardinalPosition;
import fr.designpattern.zerosumgames.game.components.board.cell.IGameBoardCell;
import fr.designpattern.zerosumgames.game.components.board.cell.piece.IGamePieceType;
import fr.designpattern.zerosumgames.game.components.opponents.GamePlayersEnumeration;

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
	public boolean isPromotable(final IGameBoardCell cell) {
		return cell.getNeighbour(this.getSideDirection()).isNull();
	}
	// ------------------------------------------------------------	
}