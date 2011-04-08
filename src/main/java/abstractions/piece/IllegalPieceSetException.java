package abstractions.piece;


/**
 * Class for illegal pieces alphabet.
 */
public final class IllegalPieceSetException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IllegalPieceSetException(String message) {
        super(message);
    }

}