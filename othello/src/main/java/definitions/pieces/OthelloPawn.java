
package definitions.pieces;

import context.entity.game.board.BoardInterface;
import context.entity.game.board.cell.BoardCell;
import context.entity.game.board.cell.BoardCellInterface;
import context.entity.game.board.cell.piece.side.SideInterface;
import context.entity.game.board.cell.position.PositionInterface;
import context.entity.game.board.direction.DirectionInterface;

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