
package abstractions.piece;

import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.piece.mocks.Pawn;
import abstractions.piece.mocks.PieceSet;
import abstractions.side.Sides;

public class PawnTest {

    private Pawn piece;

    @Before
    public void setUp() throws Exception {
        this.piece = new Pawn(Sides.FIRST, PieceSet.PAWN);
    }

    @Test
    public final void testGetSide() {
        Assert.assertTrue(this.piece.getSide().equals(Sides.FIRST));
    }

    @Test
    public final void testHashCode() {
        Assert.assertTrue(new Pawn(Sides.FIRST, PieceSet.PAWN).hashCode() == this.piece.hashCode());
        Assert.assertFalse(new Pawn(Sides.SECOND, PieceSet.PAWN).hashCode() == this.piece.hashCode());
    }

    @Test
    public final void testEqualsObject() {
        Assert.assertTrue(this.piece.equals(this.piece));
        Assert.assertFalse(this.piece.equals(null));
        Assert.assertFalse(this.piece.equals(new Random()));
        Assert.assertFalse(this.piece.equals(new Pawn(Sides.SECOND, PieceSet.PAWN)));
        Assert.assertTrue(this.piece.equals(new Pawn(Sides.FIRST, PieceSet.PAWN)));
        Assert.assertFalse(this.piece == new Pawn(Sides.FIRST, PieceSet.PAWN));
    }

    @After
    public void tearDown() throws Exception {
        this.piece = null;
    }

}
