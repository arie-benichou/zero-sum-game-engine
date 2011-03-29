package game.piece;

import game.opponents.Side;

public class API {
	
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
	public interface PieceInterface {

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
    public static interface PieceFactoryInterface {

         PieceInterface piece(final Side side);

         PieceInterface nullPiece();

    }
    
    public static final class PieceFactory implements  PieceFactoryInterface {
    	
        /**
         * Ma convention pour implémenter une interface "statique" en attendant
         * que ce soit un jour possible... me semble être un meilleur compromis
         * que l'abjecte ( et anti object :) convention du singleton et de sa
         * méthode getInstance(). Java devrait permettre la déclaration de
         * méthode statique dans une interface afin de ne pas avoir à créer un
         * singleton pour pouvoir implémenter une interface.
         */    	
    	
        public static  PieceInterface NullPiece() {
            return  NULL_PIECE;
        }

        public static  PieceInterface Piece(Side side) {
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

        private static final PieceFactoryInterface INSTANCE = new PieceFactory();

        private PieceFactory() {}

        public static PieceFactoryInterface getInstance() {
            return INSTANCE;
        }

        public  PieceInterface piece(final Side side) {
            return Piece(side);
        }

        public  PieceInterface nullPiece() {
            return NullPiece();
        }
        
    }    
	
}
