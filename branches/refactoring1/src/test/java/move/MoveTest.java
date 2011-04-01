
package move;

import static move.API.*;
import static position.API.*;
import static piece.API.*;
import static side.API.*;

import static position.API.PositionFactory.*;
import static piece.API.PieceFactory.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MoveTest {

    private MoveInterface move;

    @Before
    public void setUp() {
        this.move = new Move(Position(1, 1), Piece(FIRST_SIDE));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testIllegalMove1() {
        new Move(NULL_POSITION, NULL_PIECE);
    }

    @Test(expected = NullPointerException.class)
    public void testIllegalMove2() {
        new Move(null, null);
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