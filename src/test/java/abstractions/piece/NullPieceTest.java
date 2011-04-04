
package abstractions.piece;

import static abstractions.piece.API.*;
import static abstractions.side.API.*;

import java.util.Random;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.piece.AbstractNullPiece;
import abstractions.piece.AbstractPiece;

public class NullPieceTest {

    private PieceInterface piece;

    @Before
    public void setUp() {
    	
        this.piece = new AbstractNullPiece();
        
    }

    @Test
    public void testNew() {
    	
        Assert.assertEquals(NULL_SIDE, this.piece.getSide());
        Assert.assertTrue(this.piece.isNull());
        
    }

    @Test
    public void testHashCode() {
    	
        Assert.assertEquals(this.piece.hashCode(), this.piece.hashCode());
        Assert.assertNotSame(this.piece.hashCode(), new AbstractPiece(FIRST_SIDE).hashCode());
        Assert.assertNotSame(this.piece.hashCode(), new AbstractPiece(SECOND_SIDE).hashCode());
        Assert.assertEquals(this.piece.hashCode(), new AbstractNullPiece().hashCode());
        
    }

    @Test
    public void testEquals() {
    	
        Assert.assertEquals(this.piece, this.piece);
        Assert.assertSame(this.piece, this.piece);
        Assert.assertFalse(this.piece.equals(null));
        Assert.assertFalse(this.piece.equals(new Random()));
        Assert.assertFalse(this.piece.equals(new AbstractPiece(FIRST_SIDE)));
        Assert.assertFalse(this.piece.equals(new AbstractPiece(SECOND_SIDE)));
        Assert.assertEquals(this.piece, new AbstractNullPiece());
        Assert.assertNotSame(this.piece, new AbstractNullPiece());
        
    }

    @After
    public void tearDown() {
        this.piece = null;
    }

}