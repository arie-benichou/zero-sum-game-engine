
package cell;


import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.assertTrue;
import static piece.API.*;
import static position.API.PositionFactory.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cell.Cell;
import cell.NullCell;
import cell.API.*;


public class NullCellTest {

    private  CellInterface cell;

    @Before
    public void setUp() {
        
        this.cell = new NullCell();
        
    }

    @Test
    public void testNew() {
        
        assertTrue(this.cell.isNull());
        assertEquals(0, this.cell.getRow());
        assertEquals(0, this.cell.getColumn());
        assertEquals(NULL_PIECE, this.cell.getPiece());
        
    }

    @Test
    public void testHashCode() {
        
        assertEquals(this.cell.hashCode(), this.cell.hashCode());
        assertEquals(this.cell.hashCode(), new NullCell().hashCode());
        assertNotSame(this.cell.hashCode(), new NullCell().hashCode());
        
    }

    @Test
    public void testEquals() {
        
        assertEquals(this.cell, this.cell);
        assertSame(this.cell, this.cell);

        assertFalse(this.cell.equals(null));
        assertFalse(this.cell.equals(new Random()));

        assertEquals(this.cell, new NullCell());
        assertNotSame(this.cell, new NullCell());
        
    }

    @Test
    public void testCompareTo() {
        
        assertEquals(0, this.cell.compareTo(this.cell));
        assertEquals(-1, this.cell.compareTo(new Cell(Position(1, 1))));
        
    }

    @Test
    public void testOrder() {

        final List<CellInterface> cells = new ArrayList<CellInterface>(9);

        cells.add(new Cell(Position(1, 4)));

        cells.add(new Cell(Position(2, 2)));

        cells.add(new Cell(Position(2, 1)));

        cells.add(new Cell(Position(1, 1)));

        cells.add(new Cell(Position(2, 3)));

        cells.add(new Cell(Position(2, 4)));

        cells.add(new Cell(Position(1, 3)));

        cells.add(new Cell(Position(1, 2)));

        cells.add(new NullCell());

        assertEquals(new NullCell(), Collections.min(cells));
        assertEquals(new Cell(Position(2, 4)), Collections.max(cells));

    }

    @After
    public void tearDown() {
        
        this.cell = null;
        
    }

}