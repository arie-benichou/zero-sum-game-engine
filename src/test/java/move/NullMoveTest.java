package move;

import static abstractions.move.API.*;
import static abstractions.piece.API.*;
import static abstractions.position.API.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.move.NullMove;

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