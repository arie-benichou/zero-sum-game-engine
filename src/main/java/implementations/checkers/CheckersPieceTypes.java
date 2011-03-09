package main.java.implementations.checkers;

import main.java.core.interfaces.IGamePieceType;
import main.java.implementations.checkers.pieces.AbstractCheckersPiece;
import main.java.implementations.checkers.pieces.CheckersPieceKing;
import main.java.implementations.checkers.pieces.CheckersPieceMan;

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