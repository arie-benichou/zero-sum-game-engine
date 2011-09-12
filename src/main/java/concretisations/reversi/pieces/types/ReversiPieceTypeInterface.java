
package concretisations.reversi.pieces.types;

import abstractions.immutable.ImmutableInterface;
import abstractions.immutable.context.board.BoardInterface;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.board.cell.position.PositionInterface;
import abstractions.immutable.context.board.direction.DirectionInterface;

public interface ReversiPieceTypeInterface extends ImmutableInterface<ReversiPieceTypeInterface> {

    boolean isConnected(final SideInterface side, final BoardInterface board, final PositionInterface position, final DirectionInterface direction);

    boolean isMutable(SideInterface side, BoardInterface board, PositionInterface position);

}