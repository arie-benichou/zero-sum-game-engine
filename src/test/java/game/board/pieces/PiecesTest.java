
package game.board.pieces;

import static game.board.pieces.Pieces.*;
import static game.board.pieces.Pieces.Factory.*;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import game.board.pieces.Pieces.IllegalPieceException;

import opponents.Side;

import org.junit.Test;

public class PiecesTest {

    @Test
    public void testNullPiece() {
        
        assertTrue(new NullPiece().equals(NULL_PIECE));
        assertTrue(new NullPiece().equals(NullPiece()));
        assertTrue(new NullPiece().equals(getInstance().nullPiece()));
        
        assertFalse(new NullPiece() == NULL_PIECE);
        assertFalse(new NullPiece() == NullPiece());
        assertFalse(new NullPiece() == getInstance().nullPiece());
        
        assertTrue(NULL_PIECE == NullPiece());
        assertTrue(NULL_PIECE == getInstance().nullPiece());
        
    }

    @Test(expected = IllegalPieceException.class)
    public void testIllegalPiece1() {
        
        Piece(Side.NO_ONE);
        
    }
    
    @Test(expected = IllegalPieceException.class)
    public void testIllegalPiece2() {
        
        Piece(Side.NOT_FIRST_PLAYER);
        
    }
    
    @Test(expected = IllegalPieceException.class)
    public void testIllegalPiece3() {
        
    	Piece(Side.NOT_SECOND_PLAYER);
        
    }        

    @Test
    public void testLegalPiece() {
        
        assertTrue(Piece(Side.FIRST_PLAYER).equals(new Piece(Side.FIRST_PLAYER)));
        assertFalse(Piece(Side.FIRST_PLAYER) == new Piece(Side.FIRST_PLAYER));
        
    }

}
