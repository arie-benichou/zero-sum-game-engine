
package concretisations.reversi.moves;

import abstractions.immutable.context.gameplay.game.board.BoardInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.gameplay.game.board.cell.position.PositionInterface;
import abstractions.immutable.move.ConcreteMoveTypeInterface;
import abstractions.immutable.move.mutation.BoardMutationInterface;

public interface ReversiMoveTypeInterface extends ConcreteMoveTypeInterface {

    // TODO à mettre dans une interface d'un niveau supérieur commune à tous les types de coups
    @Override
    PositionInterface position();

    // TODO à mettre dans une interface d'un niveau supérieur commune à tous les types de coups    
    @Override
    ReversiMoveTypeInterface apply(PositionInterface position);

    // TODO à mettre dans une interface d'un niveau supérieur commune à tous les types de coups    
    @Override
    BoardMutationInterface computeBoardMutation(SideInterface side, BoardInterface board);

    @Override
    public int hashCode();

    @Override
    public boolean equals(Object obj);

}
