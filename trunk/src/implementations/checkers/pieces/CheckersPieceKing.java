package implementations.checkers.pieces;

import java.util.ArrayList;
import java.util.List;

import core.interfaces.IGameBoardCell;
import core.interfaces.IGamePieceType;
import core.types.GameBoardCardinalPosition;
import core.types.GamePlayersEnumeration;

public class CheckersPieceKing extends CheckersPiece {
	
	private static final GameBoardCardinalPosition[] POSITIONS = {
		GameBoardCardinalPosition.TOP_RIGHT,
		GameBoardCardinalPosition.BOTTOM_RIGHT,
		GameBoardCardinalPosition.TOP_LEFT,
		GameBoardCardinalPosition.BOTTOM_LEFT
	};  

	public CheckersPieceKing(IGamePieceType type, GamePlayersEnumeration side) {
		super(type, side);
		//System.out.println("Construction d'une instance de " + this.getClass());
	}
	// ------------------------------------------------------------	
	@Override
	public String toString() {
		// TODO ? utiliser une map dans la factory
		return this.getSide().equals(GamePlayersEnumeration.FIRST_PLAYER) ? "X" : "O";
	}
	// ------------------------------------------------------------
			
	public List<GameBoardCardinalPosition> getJumpOptions(IGameBoardCell cell) {
		
		//System.out.println("tu as affaire à un roi!");
		
		// TODO ? utiliser un EnumSet
		List<GameBoardCardinalPosition> options = new ArrayList<GameBoardCardinalPosition>();
	
		for(GameBoardCardinalPosition direction : POSITIONS) {
			if(this.canJumpOver(cell, direction)) {
				options.add(direction);
			}
		}
		
		return options;
		
	}
	
	// ------------------------------------------------------------

	public List<GameBoardCardinalPosition> getWalkOptions(IGameBoardCell cell) {
		
		//System.out.println("tu as affaire à un roi!");
		
		// TODO ? utiliser un EnumSet
		List<GameBoardCardinalPosition> options = new ArrayList<GameBoardCardinalPosition>();
		
		for(GameBoardCardinalPosition direction : POSITIONS) {
			if(this.canWalkThrough(cell, direction)) {
				options.add(direction);
			}
		}
		
		return options;
		
	}
	
	// ------------------------------------------------------------		
	
}