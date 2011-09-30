
package fr.designpattern.zerosumgames.abstractions.immutable.context.game;

import java.util.List;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.annotations.Application;
import fr.designpattern.zerosumgames.abstractions.immutable.annotations.Computation;
import fr.designpattern.zerosumgames.abstractions.immutable.annotations.Value;
import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.referee.RefereeInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.MoveInterface;

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