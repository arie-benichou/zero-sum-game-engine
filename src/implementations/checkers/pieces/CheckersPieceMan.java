package implementations.checkers.pieces;

import core.interfaces.IGamePieceType;
import core.types.GamePlayersEnumeration;

public class CheckersPieceMan extends CheckersPiece {
	// ------------------------------------------------------------
	public CheckersPieceMan(IGamePieceType type, GamePlayersEnumeration side) {
		super(type, side);
		//System.out.println("Construction d'une instance de " + this.getClass());
	}
	// ------------------------------------------------------------	
	@Override
	public String toString() {
		// TODO ? utiliser une map dans la factory
		return this.getSide().equals(GamePlayersEnumeration.FIRST_PLAYER) ? "x" : "o";
	}
	// ------------------------------------------------------------		
}