
package concretisations.reversi.moves;

import abstractions.immutable.ImmutableInterface;
import abstractions.immutable.context.board.BoardInterface;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.board.cell.position.PositionInterface;
import abstractions.immutable.move.mutation.BoardMutationInterface;

public interface ReversiMoveTypeInterface extends ImmutableInterface<ReversiMoveTypeInterface> {

    // TODO à mettre dans une interface d'un niveau supérieur commune à tous les types de coups
    PositionInterface position();

    // TODO à mettre dans une interface d'un niveau supérieur commune à tous les types de coups    
    ReversiMoveTypeInterface apply(PositionInterface position);

    // TODO à mettre dans une interface d'un niveau supérieur commune à tous les types de coups    
    BoardMutationInterface computeBoardMutation(SideInterface side, BoardInterface board);

    @Override
    public int hashCode();

    @Override
    public boolean equals(Object obj);

}
