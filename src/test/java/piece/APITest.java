
package piece;

import static abstractions.piece.API.*;
import static abstractions.piece.API.PieceFactory.*;
import static abstractions.side.API.*;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


import org.junit.Test;

import abstractions.piece.NullPiece;
import abstractions.piece.AbstractPiece;

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
        
        assertTrue(Piece(FIRST_SIDE).equals(new AbstractPiece(FIRST_SIDE)));
        assertFalse(Piece(FIRST_SIDE) == new AbstractPiece(FIRST_SIDE));
        
    }

}
