
package board;

import static board.API.*;
import static board.API.BoardFactory.*;
import static cell.API.*;
import static cell.API.CellFactory.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;
import static position.API.PositionFactory.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cell.API.CellInterface;

import board.Board;

public class BoardTest {

    private BoardInterface board;

    @Before
    public void setUp() {
        this.board = Board(2, 3);
    }

    @Test
    public final void testBoard() {

        assertTrue(this.board.getLowerBound().equals(Cell(Position(1, 1))));
        assertTrue(this.board.getLowerBound().equals(this.board.getCell(1, 1)));

        assertTrue(this.board.getUpperBound().equals(Cell(Position(2, 3))));
        assertTrue(this.board.getUpperBound().equals(this.board.getCell(2, 3)));

    }

    @Test(expected = NullPointerException.class)
    public final void testNewBoardWithNullPointer() {

        new Board(null);

    }

    @Test(expected = NullPointerException.class)
    public final void testNewBoardWithListContainingAtLeastOneNullPointer() {

        Set<CellInterface> cells = new HashSet<CellInterface>(2 * 3);

        cells.add(Cell(Position(1, 1)));
        cells.add(null);

        new Board(cells);

    }

    // TODO ? créer NullCellException
    @Test(expected = IllegalArgumentException.class)
    public final void testNewBoardWithListContainingAtLeastOneNullCellObject() {

        Set<CellInterface> cells = new HashSet<CellInterface>(2 * 3);

        cells.add(Cell(Position(1, 1)));
        cells.add(NULL_CELL);

        new Board(cells);

    }

    @Test
    public final void testCell() {

        assertTrue(this.board.getCell(1, 2).equals(Cell(Position(1, 2))));
        assertFalse(this.board.getCell(1, 2) == (Cell(Position(1, 2))));

    }

    @Test
    public final void testTopOf() {

        CellInterface cell11 = this.board.getCell(1, 1);
        assertTrue(this.board.topOf(cell11).equals(NULL_CELL));

        CellInterface cell21 = this.board.getCell(2, 1);
        assertTrue(this.board.topOf(cell21).equals(cell11));

    }

    @Test
    public final void testRightOf() {

        CellInterface cell13 = this.board.getCell(1, 3);
        assertTrue(this.board.rightOf(cell13).equals(NULL_CELL));

        CellInterface cell12 = this.board.getCell(1, 2);
        assertTrue(this.board.rightOf(cell12).equals(cell13));

    }

    @Test
    public final void testBottomOf() {

        CellInterface cell21 = this.board.getCell(2, 1);
        assertTrue(this.board.bottomOf(cell21).equals(NULL_CELL));

        CellInterface cell11 = this.board.getCell(1, 1);
        assertTrue(this.board.bottomOf(cell11).equals(cell21));

    }

    @Test
    public final void testLeftOf() {

        CellInterface cell11 = this.board.getCell(1, 1);
        assertTrue(this.board.leftOf(cell11).equals(NULL_CELL));

        CellInterface cell12 = this.board.getCell(1, 2);
        assertTrue(this.board.leftOf(cell12).equals(cell11));

    }

    @Test
    public final void testTopRightOf() {

        CellInterface cell13 = this.board.getCell(1, 3);
        assertTrue(this.board.topRightOf(cell13).equals(NULL_CELL));

        CellInterface cell22 = this.board.getCell(2, 2);
        assertTrue(this.board.topRightOf(cell22).equals(cell13));
    }

    @Test
    public final void testTopLeftOf() {

        CellInterface cell11 = this.board.getCell(1, 1);
        assertTrue(this.board.topLeftOf(cell11).equals(NULL_CELL));

        CellInterface cell22 = this.board.getCell(2, 2);
        assertTrue(this.board.topLeftOf(cell22).equals(cell11));

    }

    @Test
    public final void testBottomRightOf() {

        CellInterface cell23 = this.board.getCell(2, 3);
        assertTrue(this.board.bottomRightOf(cell23).equals(NULL_CELL));

        CellInterface cell12 = this.board.getCell(1, 2);
        assertTrue(this.board.bottomRightOf(cell12).equals(cell23));

    }

    @Test
    public final void testBottomLeftOf() {

        CellInterface cell21 = this.board.getCell(2, 1);
        assertTrue(this.board.bottomLeftOf(cell21).equals(NULL_CELL));

        CellInterface cell12 = this.board.getCell(1, 2);
        assertTrue(this.board.bottomLeftOf(cell12).equals(cell21));

    }

    @Test
    public void testHashCode() {

        assertEquals(this.board.hashCode(), this.board.hashCode());
        assertNotSame(this.board.hashCode(), Board(2, 3).hashCode());
        assertEquals(this.board.hashCode(), Board(2, 3).hashCode());

    }

    @Test
    public void testEquals() {

        assertTrue(this.board.equals(this.board));
        assertSame(this.board, this.board);

        assertFalse(this.board.equals(null));
        assertFalse(this.board.equals(new Random()));

        assertFalse(this.board.equals(Board(3, 2)));
        assertFalse(this.board.equals(Board(2, 2)));

        assertTrue(this.board.equals(Board(2, 3)));
        assertNotSame(this.board, Board(2, 3));

        Set<CellInterface> cells = new HashSet<CellInterface>(3);
        cells.add(Cell(Position(1, 1)));
        cells.add(Cell(Position(1, 2)));
        cells.add(Cell(Position(1, 3)));

        Board board1 = new Board(cells);
        Board board2 = new Board(cells);

        assertTrue(board1.equals(board2));
        assertNotSame(board1, board2);

        for (CellInterface cell : board1) {
            assertSame(cell, board2.getCell(cell.getRow(), cell.getColumn()));
            assertEquals(cell, board2.getCell(cell.getRow(), cell.getColumn()));
        }

    }

    @Test
    public final void testIterator() {

        List<CellInterface> expectedCells = new ArrayList<CellInterface>(2 * 3);

        expectedCells.add(Cell(Position(1, 1)));
        expectedCells.add(Cell(Position(1, 2)));
        expectedCells.add(Cell(Position(1, 3)));

        expectedCells.add(Cell(Position(2, 1)));
        expectedCells.add(Cell(Position(2, 2)));
        expectedCells.add(Cell(Position(2, 3)));

        List<CellInterface> testedCells = new ArrayList<CellInterface>(2 * 3);
        for (CellInterface cell : this.board) {
            testedCells.add(cell);
        }

        assertTrue(expectedCells.equals(testedCells));

    }

    @After
    public void tearDown() {
        this.board = null;
    }

}
