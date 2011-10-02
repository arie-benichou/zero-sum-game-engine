
package definitions.pieces;

import context.entity.game.board.BoardInterface;
import context.entity.game.board.cell.piece.side.SideInterface;
import context.entity.game.board.cell.piece.type.ConcretePieceTypeInterface;
import context.entity.game.board.cell.position.PositionInterface;
import context.entity.game.board.direction.DirectionInterface;

public interface Connect4PieceTypeInterface extends ConcretePieceTypeInterface {

    boolean isConnected(final SideInterface side, final BoardInterface board, final PositionInterface position, final DirectionInterface direction);

}