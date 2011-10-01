
package fr.designpattern.zerosumgames.abstractions.context.game;

import java.util.List;

import fr.designpattern.zerosumgames.abstractions.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.annotations.Application;
import fr.designpattern.zerosumgames.abstractions.annotations.Computation;
import fr.designpattern.zerosumgames.abstractions.annotations.Value;
import fr.designpattern.zerosumgames.abstractions.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.referee.RefereeInterface;
import fr.designpattern.zerosumgames.abstractions.move.MoveInterface;

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