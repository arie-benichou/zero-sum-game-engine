
package concretisations.reversi.pieces;

import abstractions.immutable.ImmutableInterface;
import abstractions.immutable.context.gameplay.game.board.BoardInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.gameplay.game.board.cell.position.PositionInterface;
import abstractions.immutable.context.gameplay.game.board.direction.DirectionInterface;

public interface ReversiPieceTypeInterface extends ImmutableInterface<ReversiPieceTypeInterface> {

    boolean isConnected(final SideInterface side, final BoardInterface board, final PositionInterface position, final DirectionInterface direction);

    boolean hasApplication(SideInterface side, BoardInterface board, PositionInterface position);

}