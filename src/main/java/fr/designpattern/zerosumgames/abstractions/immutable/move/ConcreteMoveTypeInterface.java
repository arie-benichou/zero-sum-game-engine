
package fr.designpattern.zerosumgames.abstractions.immutable.move;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.position.PositionInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.mutation.BoardMutationInterface;

public interface ConcreteMoveTypeInterface extends ImmutableInterface<ConcreteMoveTypeInterface>, Comparable<ConcreteMoveTypeInterface> {

    ContextInterface context();

    PositionInterface position();

    ConcreteMoveTypeInterface apply(PositionInterface position);

    BoardMutationInterface boardMutation(/*SideInterface side, BoardInterface board*/);

    boolean isNull();

}
