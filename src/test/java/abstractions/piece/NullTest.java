
package abstractions.piece;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.piece.mocks.Null;
import abstractions.piece.mocks.Pawn;
import abstractions.side.Sides;

public class NullTest {

    private Null piece;

    @Before
    public void setUp() throws Exception {
        this.piece = new Null(Sides.NULL_SIDE);
    }

    @Test
    public final void testHashCode() {

    }

    @Test
    public final void testAbstractPiece() {
        assertTrue(new Null(Sides.NULL_SIDE).hashCode() == this.piece.hashCode());
        assertFalse(new Pawn(Sides.FIRST_SIDE).hashCode() == this.piece.hashCode());
        assertFalse(new Pawn(Sides.SECOND_SIDE).hashCode() == this.piece.hashCode());
    }

    @Test
    public final void testGetSide() {
        assertTrue(this.piece.getSide().equals(Sides.NULL_SIDE));
    }

    @Test
    public final void testEqualsObject() {
        assertTrue(this.piece.equals(this.piece));
        assertFalse(this.piece.equals(null));
        assertFalse(this.piece.equals(new Random()));
        assertFalse(this.piece.equals(new Pawn(Sides.FIRST_SIDE)));
        assertFalse(this.piece.equals(new Pawn(Sides.SECOND_SIDE)));
        assertFalse(this.piece == new Null(Sides.NULL_SIDE));
    }

    @After
    public void tearDown() throws Exception {
        this.piece = null;
    }

}
