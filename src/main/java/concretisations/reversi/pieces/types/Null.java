
package concretisations.reversi.pieces.types;

public final class Null extends Abstract {

    private final static Null INSTANCE = new Null();

    public static Null from() {
        return INSTANCE;
    }

    private Null() {}

}