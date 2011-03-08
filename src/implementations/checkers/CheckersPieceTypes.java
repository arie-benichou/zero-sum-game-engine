package implementations.checkers;

import core.interfaces.IGamePieceType;
import implementations.checkers.pieces.CheckersPiece;
import implementations.checkers.pieces.CheckersPieceKing;
import implementations.checkers.pieces.CheckersPieceMan;


/*
 * Remarque:
 * le type de pièce NULL est un artefact correspondant à la non-pièce, il s'agit du NullObject NullPiece  
 */
public enum CheckersPieceTypes implements IGamePieceType {
	
	MAN(CheckersPieceMan.class),
	KING(CheckersPieceKing.class);
	
	private final Class<? extends CheckersPiece> classObject;
	
	
	private CheckersPieceTypes(Class<? extends CheckersPiece> classObject) {
		this.classObject = classObject;
	}
	
	public final Class<? extends CheckersPiece> getClassObject() {
		return this.classObject;
	}

}