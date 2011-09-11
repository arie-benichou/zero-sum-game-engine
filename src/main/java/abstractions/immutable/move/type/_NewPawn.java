
package abstractions.immutable.move.type;

import abstractions.immutable.ImmutableInterface;

public final class _NewPawn implements ImmutableInterface<_NewPawn> {

    /*-------------------------------------8<-------------------------------------*/

    private final static _NewPawn INSTANCE = new _NewPawn();

    /*-------------------------------------8<-------------------------------------*/

    public static _NewPawn from() {
        return INSTANCE;
    }

    private _NewPawn() {}

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public _NewPawn apply() {
        return this;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    /*-------------------------------------8<-------------------------------------*/

}