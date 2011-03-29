package game.board.pieces;

import opponents.Side;

public class Pieces {
	
	public final static NullPiece NULL_PIECE = new NullPiece();
	
    public static final class IllegalPieceException extends RuntimeException {
        
        private static final String MESSAGE = "Piece(side=%d) is not a legal piece.";

        private static final long serialVersionUID = 1L;
        
        private Side side;

        public IllegalPieceException(final Side side) {
            super();
            this.side = side;
        }
        
        @Override
        public String getMessage() {
            return String.format(IllegalPieceException.MESSAGE, this.side);
        }
        
    }	
	
	/**
	 * This is the interface for a game piece.
	 */
	public interface Interface {

	    /**
	     * Returns the side related to this piece.
	     * 
	     * @return the side related to this piece
	     */
	    Side getSide();

	    boolean isNull();

	}
	
    /**
     * This is the interface for the pieces factory.
     */
    public static interface FactoryInterface {

        Pieces.Interface piece(final Side side);

        Pieces.Interface nullPiece();

    }
    
    public static final class Factory implements Pieces.FactoryInterface {
    	
        /**
         * Ma convention pour implémenter une interface "statique" en attendant
         * que ce soit un jour possible... me semble être un meilleur compromis
         * que l'abjecte ( et anti object :) convention du singleton et de sa
         * méthode getInstance(). Java devrait permettre la déclaration de
         * méthode statique dans une interface afin de ne pas avoir à créer un
         * singleton pour pouvoir implémenter une interface.
         */    	
    	
        public static Pieces.Interface NullPiece() {
            return Pieces.NULL_PIECE;
        }

        public static Pieces.Interface Piece(Side side) {
            try {            
                return new Piece(side);
            }
            catch (IllegalArgumentException e) {
                throw new IllegalPieceException(side);
            }
        }
        
        /**
         * L'abjecte convention en question :p
         */

        private static final FactoryInterface INSTANCE = new Factory();

        private Factory() {}

        public static FactoryInterface getInstance() {
            return INSTANCE;
        }

        public Pieces.Interface piece(final Side side) {
            return Piece(side);
        }

        public Pieces.Interface nullPiece() {
            return NullPiece();
        }
        
    }    
	
}
