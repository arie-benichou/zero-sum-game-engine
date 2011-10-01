
package context.entity.game;

import java.util.List;

import util.annotations.Application;
import util.annotations.Computation;
import util.annotations.Value;
import util.interfaces.ImmutableInterface;
import context.ContextInterface;
import context.entity.game.board.BoardInterface;
import context.entity.game.referee.RefereeInterface;
import context.event.MoveInterface;

public interface GameInterface extends ImmutableInterface<GameInterface> {

    /*-------------------------------------8<-------------------------------------*/

    @Value
    BoardInterface board();

    @Application
    GameInterface apply(BoardInterface board);

    /*-------------------------------------8<-------------------------------------*/

    @Value
    RefereeInterface referee();

    @Application
    GameInterface apply(RefereeInterface referee);

    /*-------------------------------------8<-------------------------------------*/

    @Application
    @Computation
    GameInterface apply(final MoveInterface move);

    /*-------------------------------------8<-------------------------------------*/

    @Computation
    boolean isOver(ContextInterface context);

    @Computation
    List<MoveInterface> options(ContextInterface context);

    /*-------------------------------------8<-------------------------------------*/

}