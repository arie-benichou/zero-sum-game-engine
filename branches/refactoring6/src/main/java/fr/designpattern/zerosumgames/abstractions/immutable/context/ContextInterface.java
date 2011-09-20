
package fr.designpattern.zerosumgames.abstractions.immutable.context;

import java.util.List;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.AdversityInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.GameInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.MoveInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public interface ContextInterface extends ImmutableInterface<ContextInterface> {

    /*-------------------------------------8<-------------------------------------*/

    SideInterface side();

    ContextInterface apply(SideInterface side);

    /*-------------------------------------8<-------------------------------------*/

    AdversityInterface adversity();

    ContextInterface apply(AdversityInterface adversity);

    /*-------------------------------------8<-------------------------------------*/

    GameInterface game();

    ContextInterface apply(GameInterface game);

    /*-------------------------------------8<-------------------------------------*/

    boolean isOver();

    List<MoveTypeInterface> playableMoves();

    ContextInterface play(MoveInterface move);

    /*-------------------------------------8<-------------------------------------*/

}