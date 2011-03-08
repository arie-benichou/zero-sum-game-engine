package implementations.checkers.pieces;

import java.util.ArrayList;
import java.util.List;

import core.GamePiece;
import core.interfaces.IGameBoardCell;
import core.interfaces.IGamePieceType;
import core.types.GameBoardCardinalPosition;
import core.types.GamePlayersEnumeration;

//TODO pouvoir ajouter les points cardinaux entre eux
public class CheckersPiece extends GamePiece implements ICheckersPiece {

	public CheckersPiece(IGamePieceType type, GamePlayersEnumeration side) {
		super(type, side);
	}

	// ------------------------------------------------------------
	
	private GameBoardCardinalPosition getSideDirection() {
		return this.getSide() == GamePlayersEnumeration.FIRST_PLAYER ? GameBoardCardinalPosition.TOP : GameBoardCardinalPosition.BOTTOM;
	}
	
	private GameBoardCardinalPosition getLeftCardinalPosition() {
		return this.getSideDirection() == GameBoardCardinalPosition.TOP ? GameBoardCardinalPosition.TOP_LEFT : GameBoardCardinalPosition.BOTTOM_LEFT;
	}
	
	private GameBoardCardinalPosition getRightCardinalPosition() {
		return this.getSideDirection() == GameBoardCardinalPosition.TOP ? GameBoardCardinalPosition.TOP_RIGHT : GameBoardCardinalPosition.BOTTOM_RIGHT;
	}
	
	// ------------------------------------------------------------
	
	protected boolean canJumpOver(IGameBoardCell cell, GameBoardCardinalPosition cardinalPosition) {
		
		IGameBoardCell neighbourCell = cell.getNeighbour(cardinalPosition);

		if(neighbourCell.isNull() || neighbourCell.isEmpty())
			return false;
		
		if(neighbourCell.getPiece().getSide() == this.getSide())
			return false;
		
		neighbourCell = neighbourCell.getNeighbour(cardinalPosition);
		
		if(neighbourCell.isNull())
			return false;
		
		if(neighbourCell.isEmpty())
			return true;		
		
		return false;
		
	}	

	protected boolean canJumpOnLeft(IGameBoardCell cell) {
		return this.canJumpOver(cell, this.getLeftCardinalPosition());
	}	
	
	protected boolean canJumpOnRight(IGameBoardCell cell) {
		return this.canJumpOver(cell, this.getRightCardinalPosition());
	}	
	
	public boolean canJump(IGameBoardCell cell) {
		return this.canJumpOnLeft(cell) || this.canJumpOnRight(cell);
	}
	
	public List<GameBoardCardinalPosition> getJumpOptions(IGameBoardCell cell) {
		
		// TODO ? utiliser un EnumSet
		List<GameBoardCardinalPosition> options = new ArrayList<GameBoardCardinalPosition>();
		
		GameBoardCardinalPosition sideDirection = this.getSideDirection();
		
		// TODO refactoring
		if(this.canJumpOnLeft(cell)) {
			options.add(GameBoardCardinalPosition.valueOf(sideDirection + "_" + GameBoardCardinalPosition.LEFT.toString()));
		}
		
		if(this.canJumpOnRight(cell)) {
			options.add(GameBoardCardinalPosition.valueOf(sideDirection + "_" + GameBoardCardinalPosition.RIGHT.toString()));
		}
		
		return options;
		
	}
	
	// ------------------------------------------------------------

	protected boolean canWalkThrough(IGameBoardCell cell, GameBoardCardinalPosition cardinalPosition) {
		
		if(cell.getNeighbour(cardinalPosition).isNull())
			return false;
		
		if(cell.getNeighbour(cardinalPosition).isEmpty())
			return true;
		
		return false;
		
	}	
	
	private boolean canWalkOnLeft(IGameBoardCell cell) {
		return this.canWalkThrough(cell, this.getLeftCardinalPosition());
	}
	
	private boolean canWalkOnRight(IGameBoardCell cell) {
		return this.canWalkThrough(cell, this.getRightCardinalPosition());
	}	

	// TODO à virer
	public boolean canWalk(IGameBoardCell cell) {
		return this.canWalkOnLeft(cell) || this.canWalkOnRight(cell);
	}

	public List<GameBoardCardinalPosition> getWalkOptions(IGameBoardCell cell) {
		
		// TODO ? utiliser un EnumSet
		List<GameBoardCardinalPosition> options = new ArrayList<GameBoardCardinalPosition>();
		
		GameBoardCardinalPosition sideDirection = this.getSideDirection();
		
		// TODO refactoring
		if(this.canWalkOnLeft(cell)) {
			options.add(GameBoardCardinalPosition.valueOf(sideDirection + "_" + GameBoardCardinalPosition.LEFT.toString()));
		}
		
		if(this.canWalkOnRight(cell)) {
			options.add(GameBoardCardinalPosition.valueOf(sideDirection + "_" + GameBoardCardinalPosition.RIGHT.toString()));
		}
		
		return options;
		
	}
	
	// ------------------------------------------------------------	
	
}