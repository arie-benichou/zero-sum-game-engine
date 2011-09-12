
package concretisations.reversi.moves.types;

import abstractions.immutable.ImmutableInterface;
import abstractions.immutable.context.board.BoardInterface;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.board.cell.position.PositionInterface;
import abstractions.immutable.move.mutation.BoardMutationInterface;

public interface ReversiMoveTypeInterface extends ImmutableInterface<ReversiMoveTypeInterface> {

    PositionInterface position();

    //Set<PositionInterface> computeReversiblePositions(final SideInterface side, final BoardInterface board);

    ReversiMoveTypeInterface apply(PositionInterface position);

    @Override
    public String toString();

    BoardMutationInterface computeMutations(SideInterface side, BoardInterface board);

}
