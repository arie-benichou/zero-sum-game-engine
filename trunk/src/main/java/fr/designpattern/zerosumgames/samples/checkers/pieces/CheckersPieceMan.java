package fr.designpattern.zerosumgames.samples.checkers.pieces;

import java.util.ArrayList;
import java.util.List;

import fr.designpattern.zerosumgames.framework.game.components.board.dimension.BoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.cells.pieces.PieceTypeInterface;
import fr.designpattern.zerosumgames.framework.opponents.OpponentsEnumeration;

//TODO refactoring
//TODO pouvoir additionner les points cardinaux entre eux
public class CheckersPieceMan extends CheckersPiece {
	// ------------------------------------------------------------
	public CheckersPieceMan(final PieceTypeInterface type, final OpponentsEnumeration side) {
		super(type, side);
	}
	// ------------------------------------------------------------
	private BoardCardinalPosition getSideDirection() {
		return this.getSide() == OpponentsEnumeration.FIRST_PLAYER ? BoardCardinalPosition.TOP : BoardCardinalPosition.BOTTOM;
	}
	// ------------------------------------------------------------
	public List<BoardCardinalPosition> getWalkOptions(final CellInterface cell) {
		
		// TODO ? utiliser un EnumSet
		final List<BoardCardinalPosition> options = new ArrayList<BoardCardinalPosition>();
		
		final BoardCardinalPosition sideDirection = this.getSideDirection();
		
		final BoardCardinalPosition direction1 = BoardCardinalPosition.valueOf(sideDirection + "_" + BoardCardinalPosition.LEFT.toString());
		if(this.canWalkThrough(cell, direction1)) {
			options.add(direction1);
		}
		
		final BoardCardinalPosition direction2 = BoardCardinalPosition.valueOf(sideDirection + "_" + BoardCardinalPosition.RIGHT.toString());
		if(this.canWalkThrough(cell, direction2)) {
			options.add(direction2);
		}
		
		return options;
	}
	// ------------------------------------------------------------
	public List<BoardCardinalPosition> getJumpOptions(final CellInterface cell) {
		
		// TODO ? utiliser un EnumSet
		final List<BoardCardinalPosition> options = new ArrayList<BoardCardinalPosition>();
		
		final BoardCardinalPosition sideDirection = this.getSideDirection();
				
		final BoardCardinalPosition direction1 = BoardCardinalPosition.valueOf(sideDirection + "_" + BoardCardinalPosition.LEFT.toString());
		if(this.canJumpOver(cell, direction1)) {
			options.add(direction1);
		}
		
		final BoardCardinalPosition direction2 = BoardCardinalPosition.valueOf(sideDirection + "_" + BoardCardinalPosition.RIGHT.toString());
		if(this.canJumpOver(cell, direction2)) {
			options.add(direction2);
		}
		
		return options;
	}
	// ------------------------------------------------------------
	public boolean isPromotable(final CellInterface cell) {
		return cell.getNeighbour(this.getSideDirection()).isNull();
	}
	// ------------------------------------------------------------	
}