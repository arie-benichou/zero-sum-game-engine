
package game.board.cells;

import game.board.pieces.Piece;
import game.board.pieces.PieceFactory;
import game.board.positions.Positions;
import game.board.positions.Positions.Factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import opponents.Side;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CellTest {

    private Cells.Interface cell;
    private PieceFactory pieceFactory;

    @Before
    public void setUp() {
        this.pieceFactory = new PieceFactory();
        //import static game.board.positions.Positions.Factory.* ne fonctionne pas.
        this.cell = new Cell(Factory.Position(1, 2));
    }

    @Test
    public void testNew() {
        Assert.assertEquals(1, this.cell.getRow());
        Assert.assertEquals(2, this.cell.getColumn());
        Assert.assertEquals(this.pieceFactory.nullPiece(), this.cell.getPiece());
        Assert.assertFalse(this.cell.isNull());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNew() {
        new Cell(Positions.NULL_POSITION);
    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(this.cell.hashCode(), this.cell.hashCode());
        Assert.assertNotSame(this.cell.hashCode(), new Cell(Positions.Factory.Position(1, 3)).hashCode());
        Assert.assertNotSame(this.cell.hashCode(), new Cell(Positions.Factory.Position(2, 2)).hashCode());

        Assert.assertEquals(this.cell.hashCode(), new Cell(Positions.Factory.Position(1, 2)).hashCode());

        this.cell.setPiece(new Piece(Side.FIRST_PLAYER));
        Assert.assertEquals(this.cell.hashCode(), new Cell(Positions.Factory.Position(1, 2)).hashCode());

        this.cell.setPiece(new Piece(Side.SECOND_PLAYER));
        Assert.assertEquals(this.cell.hashCode(), new Cell(Positions.Factory.Position(1, 2)).hashCode());
    }

    @Test
    public void testEquals() {
        Assert.assertTrue(this.cell.equals(this.cell));
        Assert.assertSame(this.cell, this.cell);
        Assert.assertFalse(this.cell.equals(null));
        Assert.assertFalse(this.cell.equals(new Random()));

        Assert.assertFalse(this.cell.equals(new Cell(Positions.Factory.Position(1, 3))));
        Assert.assertFalse(this.cell.equals(new Cell(Positions.Factory.Position(2, 2))));

        Assert.assertTrue(this.cell.equals(new Cell(Positions.Factory.Position(1, 2))));
        Assert.assertNotSame(this.cell, new Cell(Positions.Factory.Position(1, 2)));

        this.cell.setPiece(new Piece(Side.FIRST_PLAYER));
        Assert.assertTrue(this.cell.equals(this.cell));
        Assert.assertFalse(this.cell.equals(new Cell(Positions.Factory.Position(1, 2))));

        this.cell.setPiece(new Piece(Side.SECOND_PLAYER));
        Assert.assertTrue(this.cell.equals(this.cell));
        Assert.assertFalse(this.cell.equals(new Cell(Positions.Factory.Position(1, 2))));

        final Cells.Interface anotherCell = new Cell(Positions.Factory.Position(1, 2));
        anotherCell.setPiece(new Piece(Side.FIRST_PLAYER));
        Assert.assertFalse(this.cell.equals(anotherCell));

        anotherCell.setPiece(new Piece(Side.SECOND_PLAYER));
        Assert.assertTrue(this.cell.equals(anotherCell));
        Assert.assertNotSame(this.cell, anotherCell);

        final Cells.Interface yetAnotherCell = new Cell(Positions.Factory.Position(3, 3));
        yetAnotherCell.setPiece(new Piece(Side.SECOND_PLAYER));
        Assert.assertFalse(this.cell.equals(yetAnotherCell));
    }

    @Test
    public void testCompareTo() {
        Assert.assertEquals(0, this.cell.compareTo(this.cell));
        Assert.assertEquals(1, this.cell.compareTo(new Cell(Positions.Factory.Position(1, 1))));
        Assert.assertEquals(-1, this.cell.compareTo(new Cell(Positions.Factory.Position(1, 3))));
        Assert.assertEquals(-1, this.cell.compareTo(new Cell(Positions.Factory.Position(2, 2))));
        Assert.assertEquals(-1, this.cell.compareTo(new Cell(Positions.Factory.Position(2, 3))));
    }

    @Test
    public void testOrder() {
        final List<Cells.Interface> cells = new ArrayList<Cells.Interface>(8);

        cells.add(new Cell(Positions.Factory.Position(1, 4)));
        cells.add(new Cell(Positions.Factory.Position(2, 2)));
        cells.add(new Cell(Positions.Factory.Position(2, 1)));
        cells.add(new Cell(Positions.Factory.Position(1, 1)));
        cells.add(new Cell(Positions.Factory.Position(2, 3)));
        cells.add(new Cell(Positions.Factory.Position(2, 4)));
        cells.add(new Cell(Positions.Factory.Position(1, 3)));
        cells.add(new Cell(Positions.Factory.Position(1, 2)));

        Assert.assertEquals(new Cell(Positions.Factory.Position(1, 1)), Collections.min(cells));
        Assert.assertEquals(new Cell(Positions.Factory.Position(2, 4)), Collections.max(cells));
    }

    @After
    public void tearDown() {
        this.cell = null;
    }

}