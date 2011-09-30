
package fr.designpattern.zerosumgames.abstractions.immutable.move;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.annotations.Application;
import fr.designpattern.zerosumgames.abstractions.immutable.annotations.Computation;
import fr.designpattern.zerosumgames.abstractions.immutable.annotations.Value;
import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.position.PositionInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.mutation.BoardMutationInterface;

// TODO ?? créer une TypeFactoryInterface générique et utiliser directement les
// types construits par la factory au lieu de devoir appeler value()
// TODO ?? créer une TypeInterface de plus haut niveau
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

    MoveInterface apply(PositionInterface position);

    /*-------------------------------------8<-------------------------------------*/

    @Computation
    boolean isNull();

    /*-------------------------------------8<-------------------------------------*/

    @Computation
    BoardMutationInterface boardMutation(/*SideInterface side, BoardInterface board*/);

    /*-------------------------------------8<-------------------------------------*/

}