
package abstractions.immutable.cells;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.immutable.pieces.PieceInterface;
import abstractions.immutable.pieces.OldPieceTypeInterface;
import abstractions.immutable.side.SideInterface;

public class CellManagerTest {

    private final static PieceInterface VALUE1 = new PieceInterface() {

        @Override
        public OldPieceTypeInterface type() {
            return null;
        }

        @Override
        public SideInterface side() {
            return null;
        }

        @Override
        public String toString() {
            return "1";
        };

    };

    private final static PieceInterface VALUE2 = new PieceInterface() {

        @Override
        public OldPieceTypeInterface type() {
            return null;
        }

        @Override
        public SideInterface side() {
            return null;
        }

        @Override
        public String toString() {
            return "2";
        };

    };

    private MyCellManager cellManager;

    @Before
    public void setUp() throws Exception {

        this.cellManager = new MyCellManager(2, 3);

    }

    @After
    public void tearDown() throws Exception {

        this.cellManager = null;

    }

    @Test
    public void testRows() {

        Assert.assertTrue(this.cellManager.rows() == 2);

    }

    @Test
    public void testColumns() {

        Assert.assertTrue(this.cellManager.columns() == 3);

    }

    @Test
    public void testImmutableCellManagerIntIntImmutableCell() {

        final Cell<PieceInterface> myDefaultValueForAllCells = new Cell<PieceInterface>(VALUE1);
        this.cellManager = new MyCellManager(2, 3, myDefaultValueForAllCells);

        Assert.assertTrue(this.cellManager.cell(0, 0).value() == VALUE1);
        Assert.assertTrue(this.cellManager.cell(0, 1).value() == VALUE1);
        Assert.assertTrue(this.cellManager.cell(0, 2).value() == VALUE1);
        Assert.assertTrue(this.cellManager.cell(1, 0).value() == VALUE1);
        Assert.assertTrue(this.cellManager.cell(1, 1).value() == VALUE1);
        Assert.assertTrue(this.cellManager.cell(1, 2).value() == VALUE1);

    }

    @Test
    public void testImmutableCellManagerIntInt() {

        this.cellManager = new MyCellManager(0, 0);
        //this.cellManager = new ImmutableCellManager(-1, 1);

    }

    @Test
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void testImmutableCellManagerImmutableCellArrayArray() {

        final Cell<PieceInterface> value = new Cell<PieceInterface>(VALUE1);
        final Cell[][] cells = {
                { value, value, value },
                { value, value, value },
                { value, value, value }
        };

        this.cellManager = new MyCellManager(cells);

        Assert.assertTrue(this.cellManager.cell(0, 0).value() == VALUE1);
        Assert.assertTrue(this.cellManager.cell(0, 1).value() == VALUE1);
        Assert.assertTrue(this.cellManager.cell(0, 2).value() == VALUE1);
        Assert.assertTrue(this.cellManager.cell(1, 0).value() == VALUE1);
        Assert.assertTrue(this.cellManager.cell(1, 1).value() == VALUE1);
        Assert.assertTrue(this.cellManager.cell(1, 2).value() == VALUE1);

        cells[0][0] = cells[0][0].apply(VALUE2);

        Assert.assertTrue(this.cellManager.cell(0, 0).value() == VALUE1);

    }

    @Test
    public void testCell() {

        Assert.assertTrue(this.cellManager.cell(0, 0) == MyCellManager.EMPTY_CELL);
        Assert.assertTrue(this.cellManager.cell(0, 1) == MyCellManager.EMPTY_CELL);
        Assert.assertTrue(this.cellManager.cell(0, 2) == MyCellManager.EMPTY_CELL);

        Assert.assertTrue(this.cellManager.cell(1, 0) == MyCellManager.EMPTY_CELL);
        Assert.assertTrue(this.cellManager.cell(1, 1) == MyCellManager.EMPTY_CELL);
        Assert.assertTrue(this.cellManager.cell(1, 2) == MyCellManager.EMPTY_CELL);

        //Assert.assertTrue(this.cellManager.cell(1, 3) == myImmutableCellManager.EMPTY_CELL);

    }

    @Test
    public void testToString() {

        final String expected1 = "\n -------------\n |   |   |   | \n -------------\n |   |   |   | \n -------------\n";
        Assert.assertTrue(this.cellManager.toString().equals(expected1));

        final String expected2 = "\n -------------\n | 1 | 1 | 1 | \n -------------\n | 1 | 1 | 1 | \n -------------\n";
        this.cellManager = new MyCellManager(2, 3, new Cell<PieceInterface>(VALUE1));
        Assert.assertTrue(this.cellManager.toString().equals(expected2));
    }
}
