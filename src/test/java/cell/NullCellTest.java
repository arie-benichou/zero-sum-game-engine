
package cell;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.assertTrue;

import static piece.API.*;
import static piece.API.PieceFactory.*;
import static position.API.PositionFactory.*;
import static side.API.*;

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

    private CellInterface nullCell;

    @Before
    public void setUp() {

        this.nullCell = new NullCell();

    }
    
    @Test(expected = NullPointerException.class)
    public void testCompareToNull() {
    	
        this.nullCell.compareTo(null);
        
    }    
    

    @Test
    public void testNullCell() {

        assertTrue(this.nullCell.isNull());
        assertEquals(0, this.nullCell.getRow());
        assertEquals(0, this.nullCell.getColumn());
        assertEquals(NULL_PIECE, this.nullCell.getPiece());

    }

    @Test(expected = NullPointerException.class)
    public void testSetPieceWithNullPointer() {
        
        this.nullCell.setPiece(null);
        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testForbiddenSetPiece1() {
        
        this.nullCell.setPiece(Piece(FIRST_SIDE));
        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testForbiddenSetPiece2() {
        
        this.nullCell.setPiece(Piece(SECOND_SIDE));
        
    }
    @Test
    public void testAllowedSetPiece() {
        this.nullCell.setPiece(NULL_PIECE);
    }        

    @Test
    public void testHashCode() {

        assertEquals(this.nullCell.hashCode(), this.nullCell.hashCode());
        assertEquals(this.nullCell.hashCode(), new NullCell().hashCode());
        assertNotSame(this.nullCell.hashCode(), new NullCell().hashCode());

    }

    @Test
    public void testEquals() {

        assertEquals(this.nullCell, this.nullCell);
        assertSame(this.nullCell, this.nullCell);

        assertFalse(this.nullCell.equals(null));
        assertFalse(this.nullCell.equals(new Random()));

        assertEquals(this.nullCell, new NullCell());
        assertNotSame(this.nullCell, new NullCell());

    }

    @Test
    public void testCompareTo() {

        assertEquals(0, this.nullCell.compareTo(this.nullCell));
        assertEquals(-1, this.nullCell.compareTo(new Cell(Position(1, 1))));

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

        this.nullCell = null;

    }

}