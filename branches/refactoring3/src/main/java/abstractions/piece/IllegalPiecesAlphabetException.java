package abstractions.piece;


/**
 * Class for illegal pieces alphabet.
 */
public final class IllegalPiecesAlphabetException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IllegalPiecesAlphabetException(String message) {
        super(message);
    }

}