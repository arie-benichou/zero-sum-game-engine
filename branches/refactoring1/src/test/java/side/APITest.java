package side;

import static side.API.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class APITest {

	@Test
	public final void testIsNull() {
		assertFalse(isNull(FIRST_SIDE));
		assertFalse(isNull(SECOND_SIDE));
		assertTrue(isNull(NULL_SIDE));
	}

	@Test
	public final void testIsFirstSide() {
		assertTrue(isFirstSide(FIRST_SIDE));
		assertFalse(isFirstSide(SECOND_SIDE));
		assertFalse(isFirstSide(NULL_SIDE));
	}

	@Test
	public final void testIsSecondSide() {
		assertTrue(isSecondSide(SECOND_SIDE));
		assertFalse(isSecondSide(FIRST_SIDE));
		assertFalse(isSecondSide(NULL_SIDE));
	}

	@Test
	public final void testIsOneSide() {
		assertTrue(isOneSide(FIRST_SIDE));
		assertTrue(isOneSide(SECOND_SIDE));
		assertFalse(isOneSide(NULL_SIDE));
	}

	@Test
	public final void testNext() {
		assertEquals(next(FIRST_SIDE), SECOND_SIDE);
		assertEquals(next(SECOND_SIDE), FIRST_SIDE);
		assertEquals(next(NULL_SIDE), NULL_SIDE);
		assertEquals(next(FIRST_SIDE.getNegation()), FIRST_SIDE.getNegation());
		assertEquals(next(SECOND_SIDE.getNegation()), SECOND_SIDE.getNegation());
	}

	@Test
	public final void testNot() {
		assertEquals(not(FIRST_SIDE), FIRST_SIDE.getNegation());
		assertEquals(not(SECOND_SIDE), SECOND_SIDE.getNegation());
		assertEquals(not(NULL_SIDE), NULL_SIDE);
	}

}
