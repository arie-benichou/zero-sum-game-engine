package abstractions.immutable.side;

import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;

import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.piece.side.Side;

public class SideTest {

	@Test
	public final void testFromInt() {
		Assert.assertTrue(Side.from(0).value() == Side.NULL.value());
		Assert.assertTrue(Side.from(1).value() == 1);
		Assert.assertTrue(Side.from(Integer.MAX_VALUE).value() == Integer.MAX_VALUE);
		Assert.assertTrue(Side.from(Integer.MIN_VALUE).value() == Integer.MIN_VALUE);
	}

	@Test
	public final void testFromInteger() {
		Assert.assertTrue(Side.from(null).value() == Side.NULL.value());
		Assert.assertTrue(Side.from(Integer.valueOf(0)).value() == Side.NULL.value());
		Assert.assertTrue(Side.from(Integer.valueOf(0)).value() == Side.from(0).value());
		Assert.assertTrue(Side.from(Integer.valueOf(1)).value() == Side.from(1).value());
		Assert.assertTrue(Side.from(Integer.valueOf(Integer.MAX_VALUE)).value() == Side.from(Integer.MAX_VALUE).value());
		Assert.assertTrue(Side.from(Integer.valueOf(Integer.MIN_VALUE)).value() == Side.from(Integer.MIN_VALUE).value());
	}

	@Test
	public final void testValue() {
		Assert.assertTrue(Side.NULL.value() == 0);
		Assert.assertTrue(Side.from(1).value() == 1);
		Assert.assertTrue(Side.from(-1).value() == -1);
		Assert.assertTrue(Side.from(Integer.MAX_VALUE).value() == Integer.MAX_VALUE);
		Assert.assertTrue(Side.from(Integer.MIN_VALUE).value() == Integer.MIN_VALUE);
	}

	@Test
	public final void testOpposite() {
		Assert.assertTrue(Side.NULL.opposite().value() == 0);
		Assert.assertTrue(Side.from(1).opposite().value() == -1);
		Assert.assertTrue(Side.from(-1).opposite().value() == 1);
		Assert.assertTrue(Side.from(Integer.MAX_VALUE).opposite().value() == -Integer.MAX_VALUE);
		Assert.assertTrue(Side.from(Integer.MIN_VALUE).opposite().value() == -Integer.MIN_VALUE);
	}

	@Test
	public final void testIsNull() {
		Assert.assertTrue(Side.NULL.isNull());
		Assert.assertTrue(!Side.from(1).isNull());
		Assert.assertTrue(!Side.from(-1).isNull());
		Assert.assertTrue(!Side.from(Integer.MAX_VALUE).isNull());
		Assert.assertTrue(!Side.from(Integer.MIN_VALUE).isNull());
	}

	@Test
	public final void testApply() {
		Assert.assertTrue(Side.NULL.apply() == Side.NULL);
		Assert.assertTrue(Side.from(1).apply() == Side.from(1));
		Assert.assertTrue(Side.from(-1).apply() == Side.from(-1));
		Assert.assertTrue(Side.from(Integer.MAX_VALUE).apply() == Side.from(Integer.MAX_VALUE));
		Assert.assertTrue(Side.from(Integer.MIN_VALUE).apply() == Side.from(Integer.MIN_VALUE));
	}

	@Test
	public final void testApplyInt() {
		Assert.assertTrue(Side.NULL.apply(Side.NULL.value()).value() == Side.NULL.value());
		Assert.assertTrue(Side.NULL.apply(1).value() == 1);
		Assert.assertTrue(Side.NULL.apply(-1).value() == -1);
		Assert.assertTrue(Side.NULL.apply(Integer.MAX_VALUE).value() == Integer.MAX_VALUE);
		Assert.assertTrue(Side.NULL.apply(Integer.MIN_VALUE).value() == Integer.MIN_VALUE);
		Assert.assertTrue(Side.from(1).apply(-1).value() == -1);
	}

	@Test
	public final void testApplyInteger() {
		Assert.assertTrue(Side.NULL.apply(null).value() == Side.NULL.value());
		Assert.assertTrue(Side.NULL.apply(Integer.valueOf(0)).value() == Side.NULL.value());
		Assert.assertTrue(Side.NULL.apply(Integer.valueOf(1)).value() == 1);
		Assert.assertTrue(Side.NULL.apply(Integer.valueOf(-1)).value() == -1);
		Assert.assertTrue(Side.NULL.apply(Integer.valueOf(Integer.MAX_VALUE)).value() == Integer.MAX_VALUE);
		Assert.assertTrue(Side.NULL.apply(Integer.valueOf(Integer.MIN_VALUE)).value() == Integer.MIN_VALUE);
		Assert.assertTrue(Side.from(1).apply(Integer.valueOf(0)).value() == Side.NULL.value());
	}

	@Test
	public final void testHashCode() {
		Assert.assertTrue(Side.NULL.hashCode() == Side.from(0).hashCode());
		Assert.assertTrue(Side.from(1).hashCode() != Side.NULL.hashCode());
		Assert.assertTrue(Side.from(1).hashCode() == Side.from(1).hashCode());
		Assert.assertTrue(Side.from(-1).hashCode() != Side.from(1).hashCode());
		Assert.assertTrue(Side.from(-1).hashCode() == Side.from(-1).hashCode());
		Assert.assertTrue(Side.from(Integer.MAX_VALUE).hashCode() != Side.from(Integer.MIN_VALUE).hashCode());
		Assert.assertTrue(Side.from(Integer.MAX_VALUE).hashCode() == Side.from(Integer.MAX_VALUE).hashCode());
		Assert.assertTrue(Side.from(Integer.MIN_VALUE).hashCode() == Side.from(Integer.MIN_VALUE).hashCode());
	}

	@Test
	public final void testEqualsObject() {
		Assert.assertTrue(!Side.NULL.equals(null));
		Assert.assertTrue(Side.NULL.equals(Side.NULL));
		Assert.assertTrue(Side.NULL.equals(Side.from(0)));
		Assert.assertTrue(Side.from(0).equals(Side.from(0)));
		Assert.assertTrue(Side.from(1).equals(Side.from(1)));
		Assert.assertTrue(!Side.from(-1).equals(Side.from(1)));
		Assert.assertTrue(!Side.NULL.equals(new Random()));
	}

}