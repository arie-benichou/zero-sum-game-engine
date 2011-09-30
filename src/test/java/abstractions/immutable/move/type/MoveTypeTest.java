
package abstractions.immutable.move.type;

import org.junit.Assert;
import org.junit.Test;

import abstractions.immutable.move.type.mocks.SomeConcreteMoveType;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.position.Position;
import fr.designpattern.zerosumgames.abstractions.immutable.move.MoveInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.Move;

public class MoveTypeTest {

	@Test
	public final void testHashCode() {}

	@Test
	public final void testValue() {}

	@Test
	public final void testFromMoveInterface() {

		final MoveInterface concreteMoveTypeInstance = null;
		Assert.assertTrue(Move.from(concreteMoveTypeInstance).equals(Move.NULL));

		final Class<? extends MoveInterface> concreteMoveTypeClass = null;
		Assert.assertTrue(Move.from(concreteMoveTypeClass).equals(Move.NULL));

		Assert.assertTrue(Move.from(SomeConcreteMoveType.from()).equals(Move.from(SomeConcreteMoveType.from())));
		Assert.assertTrue(Move.from(SomeConcreteMoveType.from()).equals(Move.from(SomeConcreteMoveType.from(null))));
		Assert.assertTrue(Move.from(SomeConcreteMoveType.from(null)).equals(Move.from(SomeConcreteMoveType.class)));

		Assert.assertTrue(!Move.from(SomeConcreteMoveType.from(Position.from(1, 1))).equals(Move.from(SomeConcreteMoveType.class)));
		Assert.assertTrue(Move.from(SomeConcreteMoveType.from(Position.from(1, 1))).equals(Move.from(SomeConcreteMoveType.from(Position.from(1, 1)))));
		Assert.assertTrue(!Move.from(SomeConcreteMoveType.from(Position.from(1, 1))).equals(Move.from(SomeConcreteMoveType.from(Position.from(1, 2)))));
	}

	@Test
	public final void testFromClassOfQextendsMoveInterface() {}

	@Test
	public final void testApply() {}

	@Test
	public final void testApplyClassOfQextendsMoveInterface() {}

	@Test
	public final void testApplyMoveInterface() {}

	@Test
	public final void testEqualsObject() {}

}
