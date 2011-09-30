
package fr.designpattern.zerosumgames.concretisations.othello.pieces;

import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.BoardCell;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.BoardCellInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.position.PositionInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.direction.DirectionInterface;

public final class OthelloPawn implements OthelloPieceTypeInterface {

	private final static OthelloPawn INSTANCE = new OthelloPawn();

	public static OthelloPawn from() {
		return INSTANCE;
	}

	private OthelloPawn() {}

	@Override
	public OthelloPieceTypeInterface apply() {
		return this;
	}

	@Override
	public boolean isConnected(final SideInterface side, final BoardInterface board, final PositionInterface position, final DirectionInterface direction) {
		if (board.cell(position).value().side().equals(side)) return true;
		final BoardCellInterface nextCell = board.cell(position.apply(direction));
		if(nextCell.equals(BoardCell.NULL)) return false; // TODO utiliser PotentialReversiPawn et NullReversiPawn
		return ((OthelloPieceTypeInterface) nextCell.value().type().value()).isConnected(side, board, nextCell.position(), direction);
	}

	@Override
	public boolean hasApplication(final SideInterface side, final BoardInterface board, final PositionInterface position) {
		return false;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

}