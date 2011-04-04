
package abstractions.piece;

import abstractions.side.SideInterface;

/**
 * Class for illegal piece exceptions.
 */
public final class IllegalPieceException extends RuntimeException {

    private static final String MESSAGE = "Piece(side=%d) is not a legal piece.";

    private static final long serialVersionUID = 1L;

    private SideInterface side;

    public IllegalPieceException(final SideInterface side) {
        super();
        this.side = side;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE, this.side);
    }

}