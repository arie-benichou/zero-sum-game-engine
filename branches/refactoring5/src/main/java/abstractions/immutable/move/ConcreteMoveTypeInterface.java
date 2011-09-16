
package abstractions.immutable.move;

import abstractions.immutable.ImmutableInterface;
import abstractions.immutable.context.gameplay.game.board.BoardInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.gameplay.game.board.cell.position.PositionInterface;
import abstractions.immutable.move.mutation.BoardMutationInterface;

public interface ConcreteMoveTypeInterface extends ImmutableInterface<ConcreteMoveTypeInterface> {

    PositionInterface position();

    ConcreteMoveTypeInterface apply(PositionInterface position);

    BoardMutationInterface computeBoardMutation(SideInterface side, BoardInterface board);

}
