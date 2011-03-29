
package game.board;

import static game.board.API.*;
import static game.cell.API.*;

import static game.board.API.BoardFactory.*;
import static game.cell.API.CellFactory.*;
import static game.position.API.PositionFactory.*;

import java.util.ArrayList;
import java.util.List;

import game.cell.API.CellInterface;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

    private BoardInterface board;

    @Before
    public void setUp() {
        this.board = Board(2, 3);
    }

    @Test
    public final void testBoard() {
        
        assertTrue(this.board.getLowerBound().equals(Cell(Position(1,1))));
        assertTrue(this.board.getLowerBound().equals(this.board.getCell(1, 1)));
        
        assertTrue(this.board.getUpperBound().equals(Cell(Position(2,3))));
        assertTrue(this.board.getUpperBound().equals(this.board.getCell(2, 3)));
        
    }

    @Test
    public final void testCell() {
        
        assertTrue(this.board.getCell(1, 2).equals(Cell(Position(1,2))));
        assertFalse(this.board.getCell(1, 2) == (Cell(Position(1,2))));
        
    }

    @Test
    public final void testIterator() {
        
        List<CellInterface> expectedCells = new ArrayList<CellInterface>(2*3);
        
        expectedCells.add(Cell(Position(1, 1)));
        expectedCells.add(Cell(Position(1, 2)));
        expectedCells.add(Cell(Position(1, 3)));
        
        expectedCells.add(Cell(Position(2, 1)));
        expectedCells.add(Cell(Position(2, 2)));
        expectedCells.add(Cell(Position(2, 3)));
        
        List<CellInterface> testedCells = new ArrayList<CellInterface>(2*3);
        for(CellInterface cell : this.board) {
            testedCells.add(cell);
        }
        
        assertTrue(expectedCells.equals(testedCells));
        
    }

    @After
    public void tearDown() {
        this.board = null;
    }

}
