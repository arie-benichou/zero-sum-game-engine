
package abstractions.immutable.pieces.types;

import abstractions.immutable.ImmutableInterface;

public final class Pawn implements ImmutableInterface<Pawn> {

    /*-------------------------------------8<-------------------------------------*/

    private final static Pawn INSTANCE = new Pawn();

    /*-------------------------------------8<-------------------------------------*/

    public static Pawn from() {
        return INSTANCE;
    }

    private Pawn() {}

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public Pawn apply() {
        return this;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    /*-------------------------------------8<-------------------------------------*/

}