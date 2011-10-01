
package definitions.pieces;

import util.interfaces.ImmutableInterface;
import context.entity.game.board.BoardInterface;
import context.entity.game.board.cell.piece.side.SideInterface;
import context.entity.game.board.cell.position.PositionInterface;
import context.entity.game.board.direction.DirectionInterface;

public interface OthelloPieceTypeInterface extends ImmutableInterface<OthelloPieceTypeInterface> {

	boolean hasApplication(SideInterface side, BoardInterface board, PositionInterface position); // TODO ajouter à une ConcretePieceTypeInterface

	boolean isConnected(final SideInterface side, final BoardInterface board, final PositionInterface position, final DirectionInterface direction);

}