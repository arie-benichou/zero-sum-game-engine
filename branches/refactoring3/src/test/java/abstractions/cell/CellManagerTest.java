
package abstractions.cell;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.dimension.API.DimensionFactory;
import abstractions.piece.PieceInterface;
import abstractions.piece.PieceManager;
import abstractions.piece.PieceManagerInterface;
import abstractions.piece.mocks.PieceSet;
import abstractions.position.PositionInterface;
import abstractions.position.PositionManager;
import abstractions.position.PositionManager.Direction;
import abstractions.position.PositionManagerInterface;
import abstractions.side.Sides;

public class CellManagerTest {

    private CellManager cellManager;

    @Before
    public void setUp() throws Exception {
        final PositionManagerInterface positionManager = new PositionManager(DimensionFactory.Dimension(3, 3));
        final PieceManagerInterface pieceManager = new PieceManager(PieceSet.class);
        this.cellManager = new CellManager(positionManager, pieceManager);
    }

    @Test
    public void testIsNull() {

        Assert.assertTrue(this.cellManager.getNullCell().isNull());
        Assert.assertTrue(this.cellManager.getCell(0, 0).isNull());
        Assert.assertFalse(this.cellManager.getCell(1, 1).isNull());

    }

    @Test
    public void testGetNullCell() {

        final ManagedCellInterface nullCell = this.cellManager.getNullCell();
        Assert.assertTrue(nullCell.isNull());

    }

    @Test
    public void testGetCellFromPrimitives() {

        final int rowIndex = 1, columnIndex = 1;
        final ManagedCellInterface cell = this.cellManager.getCell(rowIndex, columnIndex);

        Assert.assertTrue(cell.getPosition() == this.cellManager.position(rowIndex, columnIndex));
        Assert.assertTrue(cell.getPiece() == this.cellManager.piece(Sides.NULL, PieceSet.NULL));

    }

    @Test
    public void testGetCellFromPositionObject() {

        final PositionInterface position = this.cellManager.position(1, 1);
        final ManagedCellInterface cell = this.cellManager.getCell(position);

        Assert.assertTrue(cell.getPosition() == this.cellManager.position(1, 1));
        Assert.assertTrue(cell.getPiece() == this.cellManager.piece(Sides.NULL, PieceSet.NULL));

    }

    @Test
    public void testPieceManagerFacade() {

        PieceInterface piece;

        piece = this.cellManager.piece(Sides.NULL, PieceSet.NULL);
        Assert.assertTrue(piece.getSide().equals(Sides.NULL));
        Assert.assertTrue(piece.getType().equals(PieceSet.NULL));

        piece = this.cellManager.piece(Sides.FIRST, PieceSet.PAWN);
        Assert.assertTrue(piece.getSide().equals(Sides.FIRST));
        Assert.assertTrue(piece.getType().equals(PieceSet.PAWN));

        piece = this.cellManager.piece(Sides.SECOND, PieceSet.PAWN);
        Assert.assertTrue(piece.getSide().equals(Sides.SECOND));
        Assert.assertTrue(piece.getType().equals(PieceSet.PAWN));

    }

    @Test
    public void testPositionManagerFacade1() {

        PositionInterface position;

        position = this.cellManager.position(0, 0);
        Assert.assertTrue(position.getRow() == 0);
        Assert.assertTrue(position.getColumn() == 0);

        position = this.cellManager.position(2, 2);
        Assert.assertTrue(position.getRow() == 2);
        Assert.assertTrue(position.getColumn() == 2);

        position = this.cellManager.position(4, 4);
        Assert.assertTrue(position.getRow() == 0);
        Assert.assertTrue(position.getColumn() == 0);

    }

    @Test
    public void testPositionManagerFacade2() {

        PositionInterface initialPosition, position, expectedPosition;

        initialPosition = this.cellManager.position(1, 1);

        position = this.cellManager.position(initialPosition, Direction.TOP);
        expectedPosition = this.cellManager.position(0, 0);
        Assert.assertTrue(position.equals(expectedPosition));

        position = this.cellManager.position(initialPosition, Direction.RIGHT);
        expectedPosition = this.cellManager.position(1, 2);
        Assert.assertTrue(position.equals(expectedPosition));

        initialPosition = this.cellManager.position(2, 2);

        position = this.cellManager.position(initialPosition, Direction.TOP);
        expectedPosition = this.cellManager.position(1, 2);
        Assert.assertTrue(position.equals(expectedPosition));

        position = this.cellManager.position(initialPosition, Direction.RIGHT);
        expectedPosition = this.cellManager.position(2, 3);
        Assert.assertTrue(position.equals(expectedPosition));

    }

    @Test
    public void testIterableInterface() {

        final List<ManagedCellInterface> expectedCells = new ArrayList<ManagedCellInterface>(1 + 3 * 3);

        expectedCells.add(this.cellManager.getNullCell());

        expectedCells.add(this.cellManager.getCell(1, 1));
        expectedCells.add(this.cellManager.getCell(1, 2));
        expectedCells.add(this.cellManager.getCell(1, 3));

        expectedCells.add(this.cellManager.getCell(2, 1));
        expectedCells.add(this.cellManager.getCell(2, 2));
        expectedCells.add(this.cellManager.getCell(2, 3));

        expectedCells.add(this.cellManager.getCell(3, 1));
        expectedCells.add(this.cellManager.getCell(3, 2));
        expectedCells.add(this.cellManager.getCell(3, 3));

        final List<ManagedCellInterface> cells = new ArrayList<ManagedCellInterface>();
        for (final ManagedCellInterface cell : this.cellManager) {
            System.out.println(cell.getPosition());
            cells.add(cell);
        }

        Assert.assertTrue(expectedCells.equals(cells));

    }

    @After
    public void tearDown() throws Exception {
        this.cellManager = null;
    }

}
