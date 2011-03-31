package side;

import static side.API.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SideTest {

	private SideInterface side;

	@Before
	public void setUp() {
		this.side = Side.FIRST_SIDE;
	}

	@Test
	public final void testIsFirstSide() {
		assertTrue(this.side.isFirstSide());
		assertFalse(Side.SECOND_SIDE.isFirstSide());
	}

	@Test
	public final void testIsSecondSide() {
		assertTrue(Side.SECOND_SIDE.isSecondSide());
		assertFalse(Side.SECOND_SIDE.isFirstSide());
	}

	@Test
	public final void testIsOneSide() {
		assertTrue(this.side.isOneSide());
		assertTrue(Side.SECOND_SIDE.isOneSide());
		assertFalse(Side.NULL_SIDE.isOneSide());
	}

	@Test
	public final void testIsNull() {
		assertTrue(Side.NULL_SIDE.isNull());
		assertFalse(this.side.isNull());
	}

	@Test
	public final void testGetNextSide() {
		assertTrue(Side.SECOND_SIDE == this.side.getNextSide());
		assertTrue(this.side == Side.SECOND_SIDE.getNextSide());
	}

	@Test
	public final void testGetNegation() {
		assertTrue(Side.NOT_FIRST_SIDE == this.side.getNegation());
		assertTrue(Side.NOT_SECOND_SIDE == Side.SECOND_SIDE.getNegation());
	}

	@After
	public void tearDown() {
		this.side = null;
	}

}
