
package abstractions.immutable.context.board.cell.piece.type;

import abstractions.immutable.ImmutableInterface;

public final class _Pawn implements ImmutableInterface<_Pawn> {

    /*-------------------------------------8<-------------------------------------*/

    private final static _Pawn INSTANCE = new _Pawn();

    /*-------------------------------------8<-------------------------------------*/

    public static _Pawn from() {
        return INSTANCE;
    }

    private _Pawn() {}

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public _Pawn apply() {
        return this;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    /*-------------------------------------8<-------------------------------------*/

}