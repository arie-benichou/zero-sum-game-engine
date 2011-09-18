
package fr.designpattern.zerosumgames.abstractions.immutable.move;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.mutation.BoardMutationInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public interface MoveInterface extends ImmutableInterface<MoveInterface> {

    /*-------------------------------------8<-------------------------------------*/

    /*
    SideInterface side();

    MoveInterface apply(SideInterface side);
    */

    /*-------------------------------------8<-------------------------------------*/

    MoveTypeInterface type();

    MoveInterface apply(MoveTypeInterface type);

    /*-------------------------------------8<-------------------------------------*/

    BoardMutationInterface mutation();

    MoveInterface apply(BoardMutationInterface mutation);

    /*-------------------------------------8<-------------------------------------*/

    MoveInterface apply(MoveTypeInterface type, BoardMutationInterface mutation);

    /*-------------------------------------8<-------------------------------------*/

    /*
    Map<PositionInterface, PieceInterface> mutations();

    MoveInterface apply(Map<PositionInterface, PieceInterface> mutations);
    */

    /*-------------------------------------8<-------------------------------------*/

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);

    /*-------------------------------------8<-------------------------------------*/

    @Override
    String toString();

    /*-------------------------------------8<-------------------------------------*/

}
