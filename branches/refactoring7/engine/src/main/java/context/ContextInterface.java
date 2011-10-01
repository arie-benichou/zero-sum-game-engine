
package context;

import java.util.List;

import scala.collection.immutable.Stack;
import util.annotations.Application;
import util.annotations.Computation;
import util.annotations.Value;
import util.interfaces.ImmutableInterface;
import context.entity.adversity.AdversityInterface;
import context.entity.game.GameInterface;
import context.entity.game.board.cell.piece.side.SideInterface;
import context.event.MoveInterface;

public interface ContextInterface extends ImmutableInterface<ContextInterface> {

    /*-------------------------------------8<-------------------------------------*/

    @Value
    SideInterface side();

    @Application
    ContextInterface apply(SideInterface side);

    /*-------------------------------------8<-------------------------------------*/

    @Value
    AdversityInterface adversity();

    @Application
    ContextInterface apply(AdversityInterface adversity);

    /*-------------------------------------8<-------------------------------------*/

    @Value
    GameInterface game();

    @Application
    ContextInterface apply(GameInterface game);

    /*-------------------------------------8<-------------------------------------*/

    @Value
    Stack<MoveInterface> history();

    //TODO ? @Application ContextInterface apply(Stack<MoveInterface> history);    

    /*-------------------------------------8<-------------------------------------*/

    @Application
    ContextInterface apply(MoveInterface choice);

    @Application
    ContextInterface apply(GameInterface game, Stack<MoveInterface> history);

    /*-------------------------------------8<-------------------------------------*/

    @Computation
    boolean isOver();

    @Computation
    List<MoveInterface> options();

    @Computation
    Double evaluation();

    @Computation
    Double estimation();

    /*-------------------------------------8<-------------------------------------*/

}