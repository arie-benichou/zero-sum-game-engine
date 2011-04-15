
package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.cells;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.pieces.NullPiece;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.pieces.Piece;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions.NullBoardPosition;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions.BoardPosition;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

public class CellTest {

    private BoardCellInterface cell;

    @Before
    public void setUp() {
        this.cell = new BoardCell(new BoardPosition(1, 2));
    }

    @Test
    public void testNew() {
        Assert.assertEquals(new BoardPosition(1, 2), this.cell.getPosition());
        Assert.assertEquals(new NullPiece(), this.cell.getPiece());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNew() {
        new BoardCell(new NullBoardPosition());
    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(this.cell.hashCode(), this.cell.hashCode());
        Assert.assertNotSame(this.cell.hashCode(), new BoardCell(new BoardPosition(1, 3)).hashCode());
        Assert.assertNotSame(this.cell.hashCode(), new BoardCell(new BoardPosition(2, 2)).hashCode());

        Assert.assertEquals(this.cell.hashCode(), new BoardCell(new BoardPosition(1, 2)).hashCode());

        this.cell.setPiece(new Piece(OpponentsEnumeration.FIRST_PLAYER));
        Assert.assertEquals(this.cell.hashCode(), new BoardCell(new BoardPosition(1, 2)).hashCode());

        this.cell.setPiece(new Piece(OpponentsEnumeration.SECOND_PLAYER));
        Assert.assertEquals(this.cell.hashCode(), new BoardCell(new BoardPosition(1, 2)).hashCode());
    }

    @Test
    public void testEquals() {
        Assert.assertTrue(this.cell.equals(this.cell));
        Assert.assertSame(this.cell, this.cell);
        Assert.assertFalse(this.cell.equals(null));
        Assert.assertFalse(this.cell.equals(new Random()));

        Assert.assertFalse(this.cell.equals(new BoardCell(new BoardPosition(1, 3))));
        Assert.assertFalse(this.cell.equals(new BoardCell(new BoardPosition(2, 2))));

        Assert.assertTrue(this.cell.equals(new BoardCell(new BoardPosition(1, 2))));
        Assert.assertNotSame(this.cell, new BoardCell(new BoardPosition(1, 2)));

        this.cell.setPiece(new Piece(OpponentsEnumeration.FIRST_PLAYER));
        Assert.assertTrue(this.cell.equals(this.cell));
        Assert.assertFalse(this.cell.equals(new BoardCell(new BoardPosition(1, 2))));

        this.cell.setPiece(new Piece(OpponentsEnumeration.SECOND_PLAYER));
        Assert.assertTrue(this.cell.equals(this.cell));
        Assert.assertFalse(this.cell.equals(new BoardCell(new BoardPosition(1, 2))));

        final BoardCellInterface anotherCell = new BoardCell(new BoardPosition(1, 2));
        anotherCell.setPiece(new Piece(OpponentsEnumeration.FIRST_PLAYER));
        Assert.assertFalse(this.cell.equals(anotherCell));

        anotherCell.setPiece(new Piece(OpponentsEnumeration.SECOND_PLAYER));
        Assert.assertTrue(this.cell.equals(anotherCell));
        Assert.assertNotSame(this.cell, anotherCell);

        final BoardCellInterface yetAnotherCell = new BoardCell(new BoardPosition(3, 3));
        yetAnotherCell.setPiece(new Piece(OpponentsEnumeration.SECOND_PLAYER));
        Assert.assertFalse(this.cell.equals(yetAnotherCell));
    }

    @Test
    public void testCompareTo() {
        Assert.assertEquals(0, this.cell.compareTo(this.cell));
        Assert.assertEquals(1, this.cell.compareTo(new BoardCell(new BoardPosition(1, 1))));
        Assert.assertEquals(-1, this.cell.compareTo(new BoardCell(new BoardPosition(1, 3))));
        Assert.assertEquals(-1, this.cell.compareTo(new BoardCell(new BoardPosition(2, 2))));
        Assert.assertEquals(-1, this.cell.compareTo(new BoardCell(new BoardPosition(2, 3))));
    }

    @Test
    public void testOrder() {
        final List<BoardCellInterface> cells = new ArrayList<BoardCellInterface>(8);

        cells.add(new BoardCell(new BoardPosition(1, 4)));
        cells.add(new BoardCell(new BoardPosition(2, 2)));
        cells.add(new BoardCell(new BoardPosition(2, 1)));
        cells.add(new BoardCell(new BoardPosition(1, 1)));
        cells.add(new BoardCell(new BoardPosition(2, 3)));
        cells.add(new BoardCell(new BoardPosition(2, 4)));
        cells.add(new BoardCell(new BoardPosition(1, 3)));
        cells.add(new BoardCell(new BoardPosition(1, 2)));

        Assert.assertEquals(new BoardCell(new BoardPosition(1, 1)), Collections.min(cells));
        Assert.assertEquals(new BoardCell(new BoardPosition(2, 4)), Collections.max(cells));
    }

    @After
    public void tearDown() {
        this.cell = null;
    }

}