
package move;

import piece.API.PieceInterface;
import position.API.PositionInterface;

/**
 * API related to moves.
 */
public class API {

    public final static MoveInterface NULL_MOVE = new NullMove();

    public final static class IllegalMoveException extends RuntimeException {

        private static final String MESSAGE = "Move(position=%s, piece=%s) is not a legal move.";

        private static final long serialVersionUID = 1L;

        private final PositionInterface position;
        private final PieceInterface piece;

        public IllegalMoveException(final PositionInterface position, final PieceInterface piece) {
            super();
            this.position = position;
            this.piece = piece;
        }

        @Override
        public String getMessage() {
            return String.format(MESSAGE, this.position, this.piece.getSide());
        }

    }

    /**
     * This is the interface for a move.
     */
    public static interface MoveInterface {

        /**
         * Returns the position related to this move.
         * 
         * @return the position related to this move
         */
        PositionInterface getPosition();

        /**
         * Returns the piece related to this move.
         * 
         * @return the piece related to this move
         */
        PieceInterface getPiece();

        /**
         * Returns true if this move is the null object, false otherwise.
         * 
         * @return true if this move is the null object, false otherwise
         */
        boolean isNull();

    }

    public final static class MoveFactory {

        public final static MoveInterface Move(PositionInterface position, PieceInterface piece) {
            try {
                return new Move(position, piece);
            }
            catch (IllegalArgumentException e) {
                throw new IllegalMoveException(position, piece);
            }
            catch (NullPointerException e) {
                throw new IllegalMoveException(position, piece);
            }
        }

    }

}
