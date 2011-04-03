
package abstractions.move;

import static abstractions.move.API.*;
import static abstractions.piece.API.*;
import static abstractions.piece.API.PieceFactory.*;
import static abstractions.position.API.*;
import static abstractions.position.API.PositionFactory.*;
import static abstractions.side.API.*;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.move.Move;

public class MoveTest {

    private MoveInterface move;

    @Before
    public void setUp() {
        this.move = new Move(Position(1, 1), Piece(FIRST_SIDE));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testIllegalMove1() {
    	
        new Move(NULL_POSITION, Piece(FIRST_SIDE));
        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public final void testIllegalMove2() {
    	
        new Move(Position(1, 1), NULL_PIECE);
        
    }

    @Test(expected = NullPointerException.class)
    public void testIllegalMove3() {
    	
        new Move(null, Piece(FIRST_SIDE));
        
    }
    
    @Test(expected = NullPointerException.class)
    public void testIllegalMove4() {
    	
        new Move(Position(1, 1), null);
        
    }    

    @Test
    public final void testGetPosition() {
    	
        assertTrue(this.move.getPosition().equals(Position(1, 1)));
        
    }

    @Test
    public final void testGetPiece() {
    	
        assertTrue(this.move.getPiece().equals(Piece(FIRST_SIDE)));
        
    }

    @Test
    public final void testIsNull() {
    	
        assertFalse(this.move.isNull());
        
    }

    @After
    public void tearDown() {
        this.move = null;
    }

}