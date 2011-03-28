
package game.board.cells;

import game.board.positions.Positions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NullCellTest {

    private Cells.Interface cell;

    @Before
    public void setUp() {
        this.cell = new NullCell();
    }

    @Test
    public void testNew() {
        Assert.assertEquals(0, this.cell.getRow());
        Assert.assertEquals(0, this.cell.getColumn());
        //Assert.assertEquals(new NullPiece(), this.cell.getPiece()); TODO
        Assert.assertTrue(this.cell.isNull());
    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(this.cell.hashCode(), this.cell.hashCode());
        Assert.assertEquals(this.cell.hashCode(), new NullCell().hashCode());
        Assert.assertNotSame(this.cell.hashCode(), new NullCell().hashCode());
    }

    @Test
    public void testEquals() {
        Assert.assertEquals(this.cell, this.cell);
        Assert.assertSame(this.cell, this.cell);

        Assert.assertFalse(this.cell.equals(null));
        Assert.assertFalse(this.cell.equals(new Random()));

        Assert.assertEquals(this.cell, new NullCell());
        Assert.assertNotSame(this.cell, new NullCell());
    }

    @Test
    public void testCompareTo() {
        Assert.assertEquals(0, this.cell.compareTo(this.cell));
        Assert.assertEquals(-1, this.cell.compareTo(new Cell(Positions.Factory.Position(1, 1))));
    }

    @Test
    public void testOrder() {

        final List<Cells.Interface> cells = new ArrayList<Cells.Interface>(9);

        cells.add(new Cell(Positions.Factory.Position(1, 4)));

        cells.add(new Cell(Positions.Factory.Position(2, 2)));

        cells.add(new Cell(Positions.Factory.Position(2, 1)));

        cells.add(new Cell(Positions.Factory.Position(1, 1)));

        cells.add(new Cell(Positions.Factory.Position(2, 3)));

        cells.add(new Cell(Positions.Factory.Position(2, 4)));

        cells.add(new Cell(Positions.Factory.Position(1, 3)));

        cells.add(new Cell(Positions.Factory.Position(1, 2)));

        cells.add(new NullCell());

        Assert.assertEquals(new NullCell(), Collections.min(cells));
        Assert.assertEquals(new Cell(Positions.Factory.Position(2, 4)), Collections.max(cells));

    }

    @After
    public void tearDown() {
        this.cell = null;
    }

}