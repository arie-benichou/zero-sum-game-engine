package implementations.checkers;

import core.interfaces.IGamePieceType;
import implementations.checkers.pieces.AbstractCheckersPiece;
import implementations.checkers.pieces.CheckersPieceKing;
import implementations.checkers.pieces.CheckersPieceMan;

public enum CheckersPieceTypes implements IGamePieceType {

	MAN(CheckersPieceMan.class),
	KING(CheckersPieceKing.class);

	private final Class<? extends AbstractCheckersPiece> classObject;

	private CheckersPieceTypes(final Class<? extends AbstractCheckersPiece> classObject) {
		this.classObject = classObject;
	}

	public final Class<? extends AbstractCheckersPiece> getClassObject() {
		return this.classObject;
	}

}