
package fr.designpattern.zerosumgames.abstractions.immutable.context.game;

import java.util.List;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.referee.RefereeInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.MoveInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public interface GameInterface extends ImmutableInterface<GameInterface> {

    /*-------------------------------------8<-------------------------------------*/

    BoardInterface board();

    GameInterface apply(BoardInterface board);

    /*-------------------------------------8<-------------------------------------*/

    RefereeInterface referee();

    GameInterface apply(RefereeInterface referee);

    /*-------------------------------------8<-------------------------------------*/

    //boolean isGameOver(final SideInterface side);
    boolean isGameOver(ContextInterface context);

    List<MoveTypeInterface> playableMoves(ContextInterface context);

    GameInterface play(final MoveInterface move);

    /*-------------------------------------8<-------------------------------------*/

}