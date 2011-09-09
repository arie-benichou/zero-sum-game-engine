
package abstractions.immutable.pieces.types;

import abstractions.immutable.ImmutableInterface;

public interface PieceTypeInterface extends ImmutableInterface<PieceTypeInterface> {

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public PieceTypeInterface apply();

    /*-------------------------------------8<-------------------------------------*/

    ImmutableInterface<?> type();

    /*-------------------------------------8<-------------------------------------*/

}
