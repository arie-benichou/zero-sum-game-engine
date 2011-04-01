
package cell;

import static side.API.*;
import static cell.API.*;
import static piece.API.*;
import static position.API.*;

import static piece.API.PieceFactory.*;
import static position.API.PositionFactory.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CellTest {

    private CellInterface cell;

    @Before
    public void setUp() {

        this.cell = new Cell(Position(1, 2));

    }

    @Test(expected = NullPointerException.class)
    public void testIllegalCell1() {
        
        new Cell(null);
        
    }

    @Test
    public void testCell() {

        assertFalse(this.cell.isNull());
        assertEquals(1, this.cell.getRow());
        assertEquals(2, this.cell.getColumn());
        assertEquals(NULL_PIECE, this.cell.getPiece());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNew() {

        new Cell(NULL_POSITION);

    }
    
    public void testSetPiece1() {
        
        this.cell.setPiece(Piece(FIRST_SIDE));
        assertTrue(this.cell.getPiece().equals(FIRST_SIDE));
        
    }
    
    public void testSetPiece2() {
        
        this.cell.setPiece(Piece(SECOND_SIDE));
        assertTrue(this.cell.getPiece().equals(SECOND_SIDE));
        
    }
    
    @Test(expected = NullPointerException.class)
    public void testSetPieceWithNullPointer() {
        
        this.cell.setPiece(null);
        
    }

    @Test
    public void testHashCode() {

        assertEquals(this.cell.hashCode(), this.cell.hashCode());
        assertNotSame(this.cell.hashCode(), new Cell(Position(1, 3)).hashCode());
        assertNotSame(this.cell.hashCode(), new Cell(Position(2, 2)).hashCode());

        assertEquals(this.cell.hashCode(), new Cell(Position(1, 2)).hashCode());

        this.cell.setPiece(Piece(FIRST_SIDE));
        assertEquals(this.cell.hashCode(), new Cell(Position(1, 2)).hashCode());

        this.cell.setPiece(Piece(SECOND_SIDE));
        assertEquals(this.cell.hashCode(), new Cell(Position(1, 2)).hashCode());

    }

    @Test
    public void testEquals() {

        assertTrue(this.cell.equals(this.cell));
        assertSame(this.cell, this.cell);
        assertFalse(this.cell.equals(null));
        assertFalse(this.cell.equals(new Random()));

        assertFalse(this.cell.equals(new Cell(Position(1, 3))));
        assertFalse(this.cell.equals(new Cell(Position(2, 2))));

        assertTrue(this.cell.equals(new Cell(Position(1, 2))));
        assertNotSame(this.cell, new Cell(Position(1, 2)));

        this.cell.setPiece(Piece(FIRST_SIDE));
        assertTrue(this.cell.equals(this.cell));
        assertFalse(this.cell.equals(new Cell(Position(1, 2))));

        this.cell.setPiece(Piece(SECOND_SIDE));
        assertTrue(this.cell.equals(this.cell));
        assertFalse(this.cell.equals(new Cell(Position(1, 2))));

        final CellInterface anotherCell = new Cell(Position(1, 2));
        anotherCell.setPiece(Piece(FIRST_SIDE));
        assertFalse(this.cell.equals(anotherCell));

        anotherCell.setPiece(Piece(SECOND_SIDE));
        assertTrue(this.cell.equals(anotherCell));
        assertNotSame(this.cell, anotherCell);

        final CellInterface yetAnotherCell = new Cell(Position(3, 3));
        yetAnotherCell.setPiece(Piece(SECOND_SIDE));
        assertFalse(this.cell.equals(yetAnotherCell));

    }
    
    @Test(expected = NullPointerException.class)
    public void testCompareToNull() {
    	
        this.cell.compareTo(null);
        
    }
    

    @Test
    public void testCompareTo() {

        assertEquals(0, this.cell.compareTo(this.cell));
        assertEquals(1, this.cell.compareTo(new Cell(Position(1, 1))));
        assertEquals(-1, this.cell.compareTo(new Cell(Position(1, 3))));
        assertEquals(-1, this.cell.compareTo(new Cell(Position(2, 2))));
        assertEquals(-1, this.cell.compareTo(new Cell(Position(2, 3))));

    }

    @Test
    public void testOrder() {

        final List<CellInterface> cells = new ArrayList<CellInterface>(8);

        cells.add(new Cell(Position(1, 4)));
        cells.add(new Cell(Position(2, 2)));
        cells.add(new Cell(Position(2, 1)));
        cells.add(new Cell(Position(1, 1)));
        cells.add(new Cell(Position(2, 3)));
        cells.add(new Cell(Position(2, 4)));
        cells.add(new Cell(Position(1, 3)));
        cells.add(new Cell(Position(1, 2)));

        assertEquals(new Cell(Position(1, 1)), Collections.min(cells));
        assertEquals(new Cell(Position(2, 4)), Collections.max(cells));

    }

    @After
    public void tearDown() {

        this.cell = null;

    }

}