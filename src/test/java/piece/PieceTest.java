
package piece;

import static abstractions.piece.API.*;
import static abstractions.side.API.*;

import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.piece.Piece;

public class PieceTest {

    private PieceInterface piece;

    @Before
    public void setUp() {
    	
        this.piece = new Piece(FIRST_SIDE);
        
    }

    @Test
    public void testPiece() {
    	
        Assert.assertEquals(FIRST_SIDE, this.piece.getSide());
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalPiece1() {
    	
        new Piece(NULL_SIDE);
        
    }
    
    @Test(expected = NullPointerException.class)
    public void testIllegalPiece2() {
    	
        new Piece(null);
        
    }

    @Test
    public void testHashCode() {
    	
        Assert.assertEquals(this.piece.hashCode(), this.piece.hashCode());
        Assert.assertNotSame(this.piece.hashCode(), new Piece(SECOND_SIDE).hashCode());
        Assert.assertEquals(this.piece.hashCode(), new Piece(FIRST_SIDE).hashCode());
        
    }

    @Test
    public void testEquals() {
    	
        Assert.assertTrue(this.piece.equals(this.piece));
        Assert.assertSame(this.piece, this.piece);
        Assert.assertFalse(this.piece.equals(null));
        Assert.assertFalse(this.piece.equals(new Random()));
        Assert.assertFalse(this.piece.equals(new Piece(SECOND_SIDE)));
        Assert.assertTrue(this.piece.equals(new Piece(FIRST_SIDE)));
        Assert.assertNotSame(this.piece, new Piece(FIRST_SIDE));
        
    }

    @After
    public void tearDown() {
        this.piece = null;
    }

}