package implementations.checkers;

import core.interfaces.IGamePieceType;
import implementations.checkers.pieces.CheckersPiece;
import implementations.checkers.pieces.CheckersPieceKing;
import implementations.checkers.pieces.CheckersPieceMan;

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