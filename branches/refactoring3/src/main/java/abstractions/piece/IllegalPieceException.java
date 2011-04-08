
package abstractions.piece;

import abstractions.side.SideInterface;

/**
 * Class for illegal pieces.
 */
public final class IllegalPieceException extends RuntimeException {

    private static final String MESSAGE = "Piece(side=%s, type=%s) is not a legal piece.";

    private static final long serialVersionUID = 1L;

    private SideInterface side;
    private PieceTypeInterface pieceType;

    public IllegalPieceException(final SideInterface side, final PieceTypeInterface pieceType) {
        super();
        this.side = side;
        this.pieceType = pieceType;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE, this.side, this.pieceType);
    }

}