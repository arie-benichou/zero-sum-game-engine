
package abstractions.board;

import static abstractions.board.API.*;
import static abstractions.board.API.BoardFactory.*;
import static abstractions.cell.API.*;
import static abstractions.cell.API.CellFactory.*;
import static abstractions.position.API.PositionFactory.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import concretisations.checkers.pieces.Pieces;


import abstractions.board.Board;
import abstractions.cell.API.CellInterface;
import abstractions.piece.PieceSetFactory;
import abstractions.position.relative.RelativePosition;

public class BoardTest {

    private BoardInterface board;

    @Before
    public void setUp() {
        this.board = Board(2, 3);
        this.board.injectPieceFactory(new PieceSetFactory(Pieces.class));
    }

    @Test
    public final void testBoard() {

        assertTrue(this.board.getLowerBound().equals(cell(Position(1, 1))));
        assertTrue(this.board.getLowerBound().equals(this.board.getCell(1, 1)));

        assertTrue(this.board.getUpperBound().equals(cell(Position(2, 3))));
        assertTrue(this.board.getUpperBound().equals(this.board.getCell(2, 3)));

    }

    @Test(expected = NullPointerException.class)
    public final void testNewBoardWithNullPointer() {

        new Board(null);

    }

    @Test(expected = NullPointerException.class)
    public final void testNewBoardWithListContainingAtLeastOneNullPointer() {

        Set<CellInterface> cells = new HashSet<CellInterface>(2 * 3);

        cells.add(cell(Position(1, 1)));
        cells.add(null);

        new Board(cells);

    }

    // TODO ? créer NullCellException
    @Test(expected = IllegalArgumentException.class)
    public final void testNewBoardWithListContainingAtLeastOneNullCellObject() {

        Set<CellInterface> cells = new HashSet<CellInterface>(2 * 3);

        cells.add(cell(Position(1, 1)));
        cells.add(NULL_CELL);

        new Board(cells);

    }

    @Test
    public final void testCell() {

        assertTrue(this.board.getCell(1, 2).equals(cell(Position(1, 2))));
        assertFalse(this.board.getCell(1, 2) == (cell(Position(1, 2))));

    }

    @Test
    public final void testTopOf() {

        assertTrue(this.board.getCell(1, 1).getRelative(RelativePosition.TOP).equals(NULL_CELL));
        assertTrue(this.board.getCell(2, 1).getRelative(RelativePosition.TOP).equals(this.board.getCell(1, 1)));

    }

    @Test
    public final void testRightOf() {

        assertTrue(this.board.getCell(1, 3).getRelative(RelativePosition.RIGHT).equals(NULL_CELL));
        assertTrue(this.board.getCell(1, 2).getRelative(RelativePosition.RIGHT).equals(this.board.getCell(1, 3)));
        

    }

    @Test
    public final void testBottomOf() {

        assertTrue(this.board.getCell(2, 1).getRelative(RelativePosition.BOTTOM).equals(NULL_CELL));
        assertTrue(this.board.getCell(1, 1).getRelative(RelativePosition.BOTTOM).equals(this.board.getCell(2, 1)));

    }

    @Test
    public final void testLeftOf() {
        
        assertTrue(this.board.getCell(1, 1).getRelative(RelativePosition.LEFT).equals(NULL_CELL));
        assertTrue(this.board.getCell(1, 2).getRelative(RelativePosition.LEFT).equals(this.board.getCell(1, 1)));

    }

    @Test
    public final void testTopRightOf() {
        
        assertTrue(this.board.getCell(1, 3).getRelative(RelativePosition.TOP_RIGHT).equals(NULL_CELL));
        assertTrue(this.board.getCell(2, 2).getRelative(RelativePosition.TOP_RIGHT).equals(this.board.getCell(1, 3)));        
        
    }

    @Test
    public final void testTopLeftOf() {

        assertTrue(this.board.getCell(1, 1).getRelative(RelativePosition.TOP_LEFT).equals(NULL_CELL));
        assertTrue(this.board.getCell(2, 2).getRelative(RelativePosition.TOP_LEFT).equals(this.board.getCell(1, 1)));                
        

    }

    @Test
    public final void testBottomRightOf() {

        assertTrue(this.board.getCell(2, 3).getRelative(RelativePosition.BOTTOM_RIGHT).equals(NULL_CELL));
        assertTrue(this.board.getCell(1, 2).getRelative(RelativePosition.BOTTOM_RIGHT).equals(this.board.getCell(2, 3)));
        

    }

    @Test
    public final void testBottomLeftOf() {

        assertTrue(this.board.getCell(2, 1).getRelative(RelativePosition.BOTTOM_LEFT).equals(NULL_CELL));
        assertTrue(this.board.getCell(1, 2).getRelative(RelativePosition.BOTTOM_LEFT).equals(this.board.getCell(2, 1)));                        

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
        cells.add(cell(Position(1, 1)));
        cells.add(cell(Position(1, 2)));
        cells.add(cell(Position(1, 3)));

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

        expectedCells.add(cell(Position(1, 1)));
        expectedCells.add(cell(Position(1, 2)));
        expectedCells.add(cell(Position(1, 3)));

        expectedCells.add(cell(Position(2, 1)));
        expectedCells.add(cell(Position(2, 2)));
        expectedCells.add(cell(Position(2, 3)));

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
