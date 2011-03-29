
package game.board;

import static game.board.API.*;
import static game.board.API.BoardFactory.*;
import static game.cell.API.CellFactory.*;
import static game.position.API.PositionFactory.*;

import static org.junit.Assert.*;

import game.board.API.BoardInterface;
import game.cell.API.CellInterface;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class APITest {


    @Test(expected = IllegalBoardException.class)
    public void testIllegalBoard() {
        
        Board(0, 0);
        
    }

    @Test
    public void testLegalBoard() {
        
        List<CellInterface> cells = new ArrayList<CellInterface>(1);
        
        cells.add(Cell(Position(1,1)));
        
        assertFalse(Board(1, 2).equals(new Board(cells)));
        assertTrue(Board(1, 1).equals(new Board(cells)));
        assertNotSame(Board(1, 1), new Board(cells));
        
        cells.add(Cell(Position(1,2)));
        
        assertTrue(Board(1, 2).equals(new Board(cells)));
        assertNotSame(Board(1, 2), new Board(cells));
        
    }

    @Test
    public void testClone() {
        
        BoardInterface board1 = Board(2,2);
        BoardInterface board2 = Clone(board1);
        
        assertNotSame(board1, board2);
        assertTrue(board1.equals(board2));
        
        for(CellInterface cell: board1) {
            assertNotSame(cell, board2.getCell(cell.getRow(), cell.getColumn()));
            assertEquals(cell, board2.getCell(cell.getRow(), cell.getColumn()));
        }
        
    }
}
