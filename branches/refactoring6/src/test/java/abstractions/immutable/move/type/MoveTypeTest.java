
package abstractions.immutable.move.type;

import org.junit.Assert;
import org.junit.Test;

import abstractions.immutable.move.type.mocks.SomeConcreteMoveType;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.position.Position;
import fr.designpattern.zerosumgames.abstractions.immutable.move.ConcreteMoveTypeInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveType;

public class MoveTypeTest {

	@Test
	public final void testHashCode() {}

	@Test
	public final void testValue() {}

	@Test
	public final void testFromConcreteMoveTypeInterface() {

		final ConcreteMoveTypeInterface concreteMoveTypeInstance = null;
		Assert.assertTrue(MoveType.from(concreteMoveTypeInstance).equals(MoveType.NULL));

		final Class<? extends ConcreteMoveTypeInterface> concreteMoveTypeClass = null;
		Assert.assertTrue(MoveType.from(concreteMoveTypeClass).equals(MoveType.NULL));

		Assert.assertTrue(MoveType.from(SomeConcreteMoveType.from()).equals(MoveType.from(SomeConcreteMoveType.from())));
		Assert.assertTrue(MoveType.from(SomeConcreteMoveType.from()).equals(MoveType.from(SomeConcreteMoveType.from(null))));
		Assert.assertTrue(MoveType.from(SomeConcreteMoveType.from(null)).equals(MoveType.from(SomeConcreteMoveType.class)));

		Assert.assertTrue(!MoveType.from(SomeConcreteMoveType.from(Position.from(1, 1))).equals(MoveType.from(SomeConcreteMoveType.class)));
		Assert.assertTrue(MoveType.from(SomeConcreteMoveType.from(Position.from(1, 1))).equals(MoveType.from(SomeConcreteMoveType.from(Position.from(1, 1)))));
		Assert.assertTrue(!MoveType.from(SomeConcreteMoveType.from(Position.from(1, 1))).equals(MoveType.from(SomeConcreteMoveType.from(Position.from(1, 2)))));
	}

	@Test
	public final void testFromClassOfQextendsConcreteMoveTypeInterface() {}

	@Test
	public final void testApply() {}

	@Test
	public final void testApplyClassOfQextendsConcreteMoveTypeInterface() {}

	@Test
	public final void testApplyConcreteMoveTypeInterface() {}

	@Test
	public final void testEqualsObject() {}

}
