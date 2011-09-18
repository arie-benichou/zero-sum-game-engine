
package fr.designpattern.zerosumgames.concretisations.reversi.context.moves;

import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.position.PositionInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.ConcreteMoveTypeInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.mutation.BoardMutationInterface;

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
