
package concretisations.reversi.pieces.types;

public final class Pawn extends Abstract {

    private final static Pawn INSTANCE = new Pawn();

    public static Pawn from() {
        return INSTANCE;
    }

    private Pawn() {}

}