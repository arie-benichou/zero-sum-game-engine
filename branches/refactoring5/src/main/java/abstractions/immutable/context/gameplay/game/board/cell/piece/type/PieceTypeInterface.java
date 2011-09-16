
package abstractions.immutable.context.gameplay.game.board.cell.piece.type;

import abstractions.immutable.ImmutableInterface;

public interface PieceTypeInterface extends ImmutableInterface<PieceTypeInterface> {

    /*-------------------------------------8<-------------------------------------*/

    @Override
    PieceTypeInterface apply();

    /*-------------------------------------8<-------------------------------------*/

    ImmutableInterface<?> value();

    PieceTypeInterface apply(ImmutableInterface<?> type);

    PieceTypeInterface apply(Class<? extends ImmutableInterface<?>> typeClass);

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
