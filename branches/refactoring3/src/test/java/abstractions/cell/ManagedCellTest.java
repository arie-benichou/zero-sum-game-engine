
package abstractions.cell;

import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.dimension.API.DimensionFactory;
import abstractions.piece.IllegalPieceException;
import abstractions.piece.PieceInterface;
import abstractions.piece.PieceManager;
import abstractions.piece.PieceManagerInterface;
import abstractions.piece.mocks.PieceSet;
import abstractions.position.PositionManager;
import abstractions.position.PositionManager.Direction;
import abstractions.position.PositionManagerInterface;
import abstractions.side.Sides;

public class ManagedCellTest {

    private ManagedCellInterface cell;

    @Before
    public void setUp() throws Exception {

        final PositionManagerInterface positionManager = new PositionManager(DimensionFactory.Dimension(3, 3));
        final PieceManagerInterface pieceManager = new PieceManager(PieceSet.class);
        final CellManager cellManager = new CellManager(positionManager, pieceManager);
        this.cell = new ManagedCell(cellManager, positionManager.getPosition(1, 2));
        this.cell.setPiece(Sides.NULL, PieceSet.NULL);

    }

    @Test
    public void testGetPosition() {

        Assert.assertTrue(this.cell.getPosition().getRow() == 1);
        Assert.assertTrue(this.cell.getPosition().getColumn() == 2);

    }

    @Test
    public void testGetRow() {

        Assert.assertTrue(this.cell.getRow() == 1);

    }

    @Test
    public void testGetColumn() {

        Assert.assertTrue(this.cell.getColumn() == 2);

    }

    @Test
    public void testHashCode() {

        Assert.assertTrue(this.cell.hashCode() == this.cell.getPosition().hashCode());

    }

    @Test
    public void testIsNull() {

        Assert.assertFalse(this.cell.isNull());
        Assert.assertTrue(this.cell.getRelative(Direction.TOP).isNull());

    }

    @Test
    public void testGetPiece() {

        Assert.assertTrue(this.cell.getPiece().getSide().isNull());

    }

    @Test
    public void testSetPieceSideInterfacePieceTypeInterface() {

        Assert.assertTrue(this.cell.setPiece(Sides.FIRST, PieceSet.PAWN).getPiece().getSide().isFirstSide());

    }

    @Test(expected = NullPointerException.class)
    public void testSetPieceOnNullCell0() {

        final ManagedCellInterface nullCell = this.cell.getRelative(Direction.TOP);
        nullCell.setPiece(Sides.NULL, PieceSet.NULL);

    }

    @Test(expected = NullPointerException.class)
    public void testSetPieceOnNullCell1() {

        final ManagedCellInterface nullCell = this.cell.getRelative(Direction.TOP);
        nullCell.setPiece(Sides.FIRST, PieceSet.PAWN);

    }

    @Test(expected = NullPointerException.class)
    public void testSetPieceOnNullCell2() {

        final ManagedCellInterface nullCell = this.cell.getRelative(Direction.TOP);
        nullCell.setPiece(Sides.SECOND, PieceSet.PAWN);

    }

    @Test(expected = IllegalPieceException.class)
    public void testSetIllegalPieceSideInterfacePieceTypeInterface0() {
        Assert.assertTrue(this.cell.setPiece(Sides.NULL, PieceSet.PAWN).getPiece().getSide().isFirstSide());
    }

    @Test(expected = IllegalPieceException.class)
    public void testSetIllegalPieceSideInterfacePieceTypeInterface1() {
        Assert.assertTrue(this.cell.setPiece(Sides.FIRST, PieceSet.NULL).getPiece().getSide().isFirstSide());
    }

    @Test(expected = IllegalPieceException.class)
    public void testSetIllegalPieceSideInterfacePieceTypeInterface2() {
        Assert.assertTrue(this.cell.setPiece(Sides.SECOND, PieceSet.NULL).getPiece().getSide().isFirstSide());
    }

    @Test
    public void testSetPiecePieceInterface() {

        final PieceInterface piece = this.cell.setPiece(Sides.SECOND, PieceSet.PAWN).getPiece();
        this.cell.setPiece(Sides.FIRST, PieceSet.PAWN).getPiece();
        Assert.assertTrue(this.cell.setPiece(Sides.FIRST, PieceSet.PAWN).getPiece().getSide().isFirstSide());
        this.cell.setPiece(piece);
        Assert.assertTrue(this.cell.getPiece().getSide().isSecondSide());

    }

    @Test
    public void testGetRelative() {

        Assert.assertTrue(this.cell.getRelative(Direction.TOP).getPosition().getRow() == 0);
        Assert.assertTrue(this.cell.getRelative(Direction.TOP).getPosition().getColumn() == 0);

        Assert.assertTrue(this.cell.getRelative(Direction.BOTTOM).getPosition().getRow() == 2);
        Assert.assertTrue(this.cell.getRelative(Direction.BOTTOM).getPosition().getColumn() == 2);

        final ManagedCellInterface nullCell = this.cell.getRelative(Direction.TOP);

        Assert.assertTrue(nullCell.getRelative(Direction.BOTTOM_RIGHT) == nullCell);

    }

    @Test
    public void testIsEmpty() {

        Assert.assertTrue(this.cell.isEmpty());
        Assert.assertFalse(this.cell.getRelative(Direction.TOP).isEmpty());
        Assert.assertFalse(this.cell.setPiece(Sides.FIRST, PieceSet.PAWN).isEmpty());

    }

    @Test(expected = NullPointerException.class)
    public void testCompareToNull() {

        this.cell.compareTo(null);

    }

    @Test
    public void testCompareTo() {

        Assert.assertTrue(this.cell.compareTo(this.cell.getRelative(Direction.TOP)) == 1);

        Assert.assertTrue(this.cell.compareTo(this.cell.getRelative(Direction.BOTTOM)) == -1);
        Assert.assertTrue(this.cell.compareTo(this.cell) == 0);
        Assert.assertTrue(this.cell.compareTo(this.cell.getRelative(Direction.LEFT)) == 1);

        Assert.assertTrue(this.cell.compareTo(this.cell.getRelative(Direction.RIGHT).getRelative(Direction.BOTTOM_RIGHT)) == 1);

    }

    @Test
    public void testEqualsObject() {

        Assert.assertFalse(this.cell.equals(null));
        Assert.assertFalse(this.cell.equals(new Random()));
        Assert.assertFalse(this.cell.equals(this.cell.getRelative(Direction.TOP)));

        Assert.assertTrue(this.cell.equals(this.cell));
        Assert.assertTrue(this.cell.equals(this.cell.getRelative(Direction.RIGHT)));

        this.cell.getRelative(Direction.RIGHT).setPiece(Sides.FIRST, PieceSet.PAWN);
        Assert.assertFalse(this.cell.equals(this.cell.getRelative(Direction.RIGHT)));

        this.cell.getRelative(Direction.RIGHT).setPiece(Sides.SECOND, PieceSet.PAWN);
        Assert.assertFalse(this.cell.equals(this.cell.getRelative(Direction.RIGHT)));

        this.cell.getRelative(Direction.RIGHT).setPiece(Sides.NULL, PieceSet.NULL);
        Assert.assertTrue(this.cell.equals(this.cell.getRelative(Direction.RIGHT)));

    }

    @After
    public void tearDown() throws Exception {
        this.cell = null;
    }

}
