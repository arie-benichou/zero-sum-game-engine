
package context.event;

import util.annotations.Application;
import util.annotations.Computation;
import util.annotations.Value;
import util.interfaces.ImmutableInterface;
import context.ContextInterface;
import context.entity.game.board.cell.position.PositionInterface;
import context.entity.game.board.mutation.BoardMutationInterface;

public interface MoveInterface extends ImmutableInterface<MoveInterface>, Comparable<MoveInterface> {

    /*-------------------------------------8<-------------------------------------*/

    @Value
    ContextInterface context();

    @Value
    MoveInterface value();

    @Value
    PositionInterface position();

    /*-------------------------------------8<-------------------------------------*/

    @Application
    MoveInterface apply(MoveInterface value);

    @Application
    MoveInterface apply(Class<? extends MoveInterface> valueClass);

    @Application
    MoveInterface apply(PositionInterface position);

    /*-------------------------------------8<-------------------------------------*/

    @Computation
    boolean isNull();

    @Computation
    BoardMutationInterface boardMutation();

    /*-------------------------------------8<-------------------------------------*/

}