package fr.designpattern.zerosumgames.abstractions.immutable.rendering.symbol;

import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.PieceInterface;

public class PieceAsStringSymbol implements SymbolInterface<PieceInterface, ImmutableString> {

	public static PieceAsStringSymbol from(final PieceInterface piece, final ImmutableString asStringValue) {
		return new PieceAsStringSymbol(piece, asStringValue);
	}

	private final PieceInterface id;
	private final ImmutableString value;

	private PieceAsStringSymbol(final PieceInterface piece, final ImmutableString string) {
		this.id = piece;
		this.value = string;
	}

	@Override
	public PieceAsStringSymbol apply() {
		return this;
	}

	@Override
	public PieceInterface id() {
		return this.id;
	}

	@Override
	public ImmutableString value() {
		return this.value;
	}

	@Override
	public PieceAsStringSymbol apply(final ImmutableString newValue) {
		return new PieceAsStringSymbol(this.id(), newValue);
	}

}