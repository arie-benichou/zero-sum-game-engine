package move;

import static position.API.*;
import static piece.API.*;
import static side.API.*;

import static position.API.PositionFactory.*;
import static piece.API.PieceFactory.*;

import move.API.MoveInterface;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NullMoveTest {

	private MoveInterface move;

	@Before
	public void setUp() {
		this.move = new NullMove();
	}
	
	@Test
	public final void testGetPosition() {
		assertTrue(this.move.getPosition().equals(NULL_POSITION));
	}

	@Test
	public final void testGetPiece() {
		assertTrue(this.move.getPiece().equals(NULL_PIECE));
	}

	@Test
	public final void testIsNull() {
		assertTrue(this.move.isNull());
	}
	
	@After
	public void tearDown() {
		this.move = null;
	}

}