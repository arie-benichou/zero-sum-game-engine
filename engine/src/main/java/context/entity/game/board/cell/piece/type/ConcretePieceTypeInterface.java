
package context.entity.game.board.cell.piece.type;

import util.interfaces.ImmutableInterface;
import util.interfaces.NullObjectInterface;
import context.entity.game.board.BoardInterface;
import context.entity.game.board.cell.piece.side.SideInterface;
import context.entity.game.board.cell.position.PositionInterface;

public interface ConcretePieceTypeInterface extends ImmutableInterface<ConcretePieceTypeInterface>, NullObjectInterface {

    boolean hasApplication(SideInterface side, BoardInterface board, PositionInterface position);

}
