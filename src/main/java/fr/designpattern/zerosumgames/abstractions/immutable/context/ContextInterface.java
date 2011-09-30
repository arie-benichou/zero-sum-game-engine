
package fr.designpattern.zerosumgames.abstractions.immutable.context;

import java.util.List;

import scala.collection.immutable.Stack;
import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.annotations.Application;
import fr.designpattern.zerosumgames.abstractions.immutable.annotations.Computation;
import fr.designpattern.zerosumgames.abstractions.immutable.annotations.Value;
import fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.AdversityInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.GameInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.MoveInterface;

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

    //TODO ? @Computation List<MoveTypeInterface> options(SideInterface side);

    @Computation
    Double evaluate();

    @Computation
    Double estimate();

    /*-------------------------------------8<-------------------------------------*/

}