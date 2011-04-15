
package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.cells;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.pieces.NullPiece;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions.NullBoardPosition;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions.BoardPosition;

public class NullCellTest {

    private BoardCellInterface cell;

    @Before
    public void setUp() {
        this.cell = new NullBoardCell();
    }

    @Test
    public void testNew() {
        Assert.assertEquals(new NullBoardPosition(), this.cell.getPosition());
        Assert.assertEquals(new NullPiece(), this.cell.getPiece());
        Assert.assertTrue(this.cell.isNull());
    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(this.cell.hashCode(), this.cell.hashCode());
        Assert.assertEquals(this.cell.hashCode(), new NullBoardCell().hashCode());
        Assert.assertNotSame(this.cell.hashCode(), new NullBoardCell().hashCode());
    }

    @Test
    public void testEquals() {
        Assert.assertEquals(this.cell, this.cell);
        Assert.assertSame(this.cell, this.cell);

        Assert.assertFalse(this.cell.equals(null));
        Assert.assertFalse(this.cell.equals(new Random()));

        Assert.assertEquals(this.cell, new NullBoardCell());
        Assert.assertNotSame(this.cell, new NullBoardCell());
    }

    @Test
    public void testCompareTo() {
        Assert.assertEquals(0, this.cell.compareTo(this.cell));
        Assert.assertEquals(-1, this.cell.compareTo(new BoardCell(new BoardPosition(1, 1))));
    }

    @Test
    public void testOrder() {

        final List<BoardCellInterface> cells = new ArrayList<BoardCellInterface>(9);

        cells.add(new BoardCell(new BoardPosition(1, 4)));

        cells.add(new BoardCell(new BoardPosition(2, 2)));

        cells.add(new BoardCell(new BoardPosition(2, 1)));

        cells.add(new BoardCell(new BoardPosition(1, 1)));

        cells.add(new BoardCell(new BoardPosition(2, 3)));

        cells.add(new BoardCell(new BoardPosition(2, 4)));

        cells.add(new BoardCell(new BoardPosition(1, 3)));

        cells.add(new BoardCell(new BoardPosition(1, 2)));

        cells.add(new NullBoardCell());

        Assert.assertEquals(new NullBoardCell(), Collections.min(cells));
        Assert.assertEquals(new BoardCell(new BoardPosition(2, 4)), Collections.max(cells));

    }

    @After
    public void tearDown() {
        this.cell = null;
    }

}