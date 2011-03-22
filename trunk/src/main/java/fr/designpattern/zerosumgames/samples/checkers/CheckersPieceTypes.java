package fr.designpattern.zerosumgames.samples.checkers;

import fr.designpattern.zerosumgames.framework.game.components.board.cell.piece.IGamePieceType;
import fr.designpattern.zerosumgames.samples.checkers.pieces.CheckersPiece;
import fr.designpattern.zerosumgames.samples.checkers.pieces.CheckersPieceKing;
import fr.designpattern.zerosumgames.samples.checkers.pieces.CheckersPieceMan;

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