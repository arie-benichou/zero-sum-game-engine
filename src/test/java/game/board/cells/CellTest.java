
package game.board.cells;

import static org.junit.Assert.*;

import static game.board.positions.Positions.*;
import static game.board.positions.Positions.Factory.*;
import static junit.framework.Assert.fail;

import game.board.pieces.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import opponents.Side;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CellTest {

    private Cells.Interface cell;

    @Before
    public void setUp() {
        
        this.cell = new Cell(Position(1, 2));
        
    }

    @Test
    public void testNew() {
        
        assertFalse(this.cell.isNull());
        assertEquals(1, this.cell.getRow());
        assertEquals(2, this.cell.getColumn());
        
        /*
        assertEquals(NULL_PIECE, this.cell.getPiece()); //TODO ! Factory
        */
        
        fail("TODO Abstract Factory for Piece");
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNew() {
        
        new Cell(NULL_POSITION);
        
    }

    @Test
    public void testHashCode() {
        
        assertEquals(this.cell.hashCode(), this.cell.hashCode());
        assertNotSame(this.cell.hashCode(), new Cell(Position(1, 3)).hashCode());
        assertNotSame(this.cell.hashCode(), new Cell(Position(2, 2)).hashCode());

        assertEquals(this.cell.hashCode(), new Cell(Position(1, 2)).hashCode());

        this.cell.setPiece(new Piece(Side.FIRST_PLAYER));
        assertEquals(this.cell.hashCode(), new Cell(Position(1, 2)).hashCode());

        this.cell.setPiece(new Piece(Side.SECOND_PLAYER));
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

        this.cell.setPiece(new Piece(Side.FIRST_PLAYER));
        assertTrue(this.cell.equals(this.cell));
        assertFalse(this.cell.equals(new Cell(Position(1, 2))));

        this.cell.setPiece(new Piece(Side.SECOND_PLAYER));
        assertTrue(this.cell.equals(this.cell));
        assertFalse(this.cell.equals(new Cell(Position(1, 2))));

        final Cells.Interface anotherCell = new Cell(Position(1, 2));
        anotherCell.setPiece(new Piece(Side.FIRST_PLAYER));
        assertFalse(this.cell.equals(anotherCell));

        anotherCell.setPiece(new Piece(Side.SECOND_PLAYER));
        assertTrue(this.cell.equals(anotherCell));
        assertNotSame(this.cell, anotherCell);

        final Cells.Interface yetAnotherCell = new Cell(Position(3, 3));
        yetAnotherCell.setPiece(new Piece(Side.SECOND_PLAYER));
        assertFalse(this.cell.equals(yetAnotherCell));
        
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
        
        final List<Cells.Interface> cells = new ArrayList<Cells.Interface>(8);

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