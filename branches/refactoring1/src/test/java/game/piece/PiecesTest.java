
package game.piece;

import static game.piece.API.*;
import static game.piece.API.PieceFactory.*;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import game.piece.NullPiece;
import game.piece.Piece;
import game.piece.API.IllegalPieceException;
import game.side.Side;


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
