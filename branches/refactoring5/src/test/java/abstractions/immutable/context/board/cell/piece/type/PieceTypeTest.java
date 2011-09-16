
package abstractions.immutable.context.board.cell.piece.type;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Test;

import abstractions.immutable.ImmutableInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.type.PieceType;
import abstractions.immutable.context.gameplay.game.board.cell.piece.type.PieceType.NullPieceType;

public class PieceTypeTest {

    @Test
    public final void testHashCode() {
        final Class<? extends ImmutableInterface<?>> value = null;
        Assert.assertTrue(PieceType.NULL.hashCode() == PieceType.from(value).hashCode());
        Assert.assertTrue(PieceType.NULL.hashCode() != PieceType.from(_Pawn.class).hashCode());
        Assert.assertTrue(PieceType.from(_Pawn.from()).hashCode() == PieceType.from(_Pawn.class).hashCode());
    }

    @Test
    public final void testValue() {
        Assert.assertTrue(PieceType.NULL.value() == NullPieceType.from());
        Assert.assertTrue(PieceType.from(_Pawn.from()).value() == PieceType.from(_Pawn.class).value());
    }

    @Test
    public final void testApply() {
        Assert.assertTrue(PieceType.NULL.apply() == PieceType.NULL);
        Assert.assertTrue(PieceType.from(_Pawn.from()).apply() != PieceType.from(_Pawn.class));
        Assert.assertTrue(PieceType.from(_Pawn.from()).apply() == PieceType.from(_Pawn.from()));
        Assert.assertTrue(PieceType.from(_Pawn.class).apply() == PieceType.from(_Pawn.class));
        Assert.assertTrue(PieceType.from(_Pawn.from()).apply().equals(PieceType.from(_Pawn.class)));
        Assert.assertTrue(!PieceType.from(_Pawn.from()).apply().equals(PieceType.NULL));
    }

    @Test
    public final void testFromImmutableInterfaceOfQ() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testFromClassOfQextendsImmutableInterfaceOfQ() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testApplyClassOfQextendsImmutableInterfaceOfQ() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testApplyImmutableInterfaceOfQ() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testEqualsObject() {
        fail("Not yet implemented"); // TODO
    }

}
