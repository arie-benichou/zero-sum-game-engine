
package piece;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import static side.API.*;
import static piece.API.*;
import static piece.API.PieceFactory.*;

import org.junit.Test;

public class APITest {

    @Test
    public void testNullPiece() {
        
        assertTrue(new NullPiece().equals(NULL_PIECE));
        assertTrue(new NullPiece().equals(NullPiece()));
        
        assertFalse(new NullPiece() == NULL_PIECE);
        assertFalse(new NullPiece() == NullPiece());
        
        assertTrue(NULL_PIECE == NullPiece());
        
    }

    @Test(expected = IllegalPieceException.class)
    public void testIllegalPiece1() {
        
        Piece(NULL_SIDE);
        
    }
    
    @Test(expected = IllegalPieceException.class)
    public void testIllegalPiece2() {
    	
        Piece(null);
        
    }    
    
    @Test
    public void testLegalPiece() {
        
        assertTrue(Piece(FIRST_SIDE).equals(new Piece(FIRST_SIDE)));
        assertFalse(Piece(FIRST_SIDE) == new Piece(FIRST_SIDE));
        
    }

}
