
package board;

import static board.API.*;
import static board.API.BoardFactory.*;
import static cell.API.CellFactory.*;

import static org.junit.Assert.*;
import static position.API.PositionFactory.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import cell.API.CellInterface;

import board.Board;
import board.API.BoardInterface;

public class APITest {


    @Test(expected = IllegalBoardException.class)
    public void testIllegalBoard() {
        
        Board(0, 0);
        
    }

    @Test
    public void testLegalBoard() {
        
        Set<CellInterface> cells = new HashSet<CellInterface>(1);
        
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
