package abstractions.move;





import static abstractions.move.API.*;
import static abstractions.move.API.MoveFactory.*;
import static abstractions.piece.API.*;
import static abstractions.piece.API.PieceFactory.*;
import static abstractions.position.API.*;
import static abstractions.position.API.PositionFactory.*;
import static abstractions.side.API.*;
import static junit.framework.Assert.*;

import org.junit.Test;

import abstractions.move.Move;
import abstractions.move.NullMove;


public class APITest {

    @Test
    public void testNullMove() {
        assertTrue(new NullMove().equals(NULL_MOVE));
        assertFalse(new NullMove() == NULL_MOVE);
    }

    @Test(expected = IllegalMoveException.class)
    public void testIllegalMove1() {
        Move(NULL_POSITION, NULL_PIECE);
    }
    
    @Test(expected = IllegalMoveException.class)
    public void testIllegalMove2() {
        Move(NULL_POSITION, null);
    }
    
    @Test(expected = IllegalMoveException.class)
    public void testIllegalMove3() {
        Move(null, NULL_PIECE);
    }
    
    @Test(expected = IllegalMoveException.class)
    public void testIllegalMove4() {
        Move(null, null);
    } 

    @Test
    public void testMove() {
        assertTrue(new Move(Position(1, 2), Piece(FIRST_SIDE)).equals(Move(Position(1, 2), Piece(FIRST_SIDE))));
        assertFalse(new Move(Position(1, 2), Piece(FIRST_SIDE)) == (Move(Position(1, 2), Piece(FIRST_SIDE))));
    }
    
}
