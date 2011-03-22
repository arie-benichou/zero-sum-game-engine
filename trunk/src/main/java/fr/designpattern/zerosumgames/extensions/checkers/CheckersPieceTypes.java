package fr.designpattern.zerosumgames.extensions.checkers;

import fr.designpattern.zerosumgames.extensions.checkers.pieces.CheckersPiece;
import fr.designpattern.zerosumgames.extensions.checkers.pieces.CheckersPieceKing;
import fr.designpattern.zerosumgames.extensions.checkers.pieces.CheckersPieceMan;
import fr.designpattern.zerosumgames.framework.build.game.components.board.cell.piece.IGamePieceType;

public enum CheckersPieceTypes implements IGamePieceType {

	MAN(CheckersPieceMan.class),
	KING(CheckersPieceKing.class);

	private final Class<? extends CheckersPiece> classObject;

	private CheckersPieceTypes(final Class<? extends CheckersPiece> classObject) {
		this.classObject = classObject;
	}

	public final Class<? extends CheckersPiece> getClassObject() {
		return this.classObject;
	}

}