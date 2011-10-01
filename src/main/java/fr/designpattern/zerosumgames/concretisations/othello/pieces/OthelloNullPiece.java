
package fr.designpattern.zerosumgames.concretisations.othello.pieces;

import fr.designpattern.zerosumgames.abstractions.context.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.BoardCell;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.BoardCellInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.position.PositionInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.direction.Direction;
import fr.designpattern.zerosumgames.abstractions.context.game.board.direction.DirectionInterface;

public final class OthelloNullPiece implements OthelloPieceTypeInterface {

	private final static OthelloNullPiece INSTANCE = new OthelloNullPiece();

	public static OthelloNullPiece from() {
		return INSTANCE;
	}

	private OthelloNullPiece() {}

	@Override
	public OthelloPieceTypeInterface apply() {
		return this;
	}

	@Override
	public boolean isConnected(final SideInterface side, final BoardInterface board, final PositionInterface position, final DirectionInterface direction) {
		return false;
	}

	@Override
	public boolean hasApplication(final SideInterface side, final BoardInterface board, final PositionInterface position) {
		for (final DirectionInterface direction : Direction.ALL_AROUND) {
			final BoardCellInterface nextCell = board.cell(position.apply(direction));
			if (side.opposite().equals(nextCell.value().side())) {
				final BoardCellInterface nextNextCell = board.cell(nextCell.position().apply(direction));
				// TODO utiliser PotentialReversiPawn et NullReversiPawn
				if(!nextNextCell.equals(BoardCell.NULL) && ((OthelloPieceTypeInterface) nextNextCell.value().type().value())
						.isConnected(side, board, nextNextCell.position(), direction))
					return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

}