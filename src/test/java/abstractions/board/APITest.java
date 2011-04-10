
package abstractions.board;

import static abstractions.board.API.*;
import static abstractions.board.API.BoardFactory.*;
import static abstractions.cell.API.CellFactory.*;
import static abstractions.position.API.PositionFactory.*;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;


import abstractions.board.Board;
import abstractions.board.API.BoardInterface;
import abstractions.cell.API.CellInterface;

public class APITest {


    @Test(expected = IllegalBoardException.class)
    public void testIllegalBoard() {
        
        Board(0, 0);
        
    }
    
    @Test
    public void testLegalBoard() {
        
        Set<ManagedCellInterface> cells = new HashSet<ManagedCellInterface>(1);
        
        cells.add(cell(Position(1,1)));
        
        assertFalse(Board(1, 2).equals(new Board(cells)));
        assertTrue(Board(1, 1).equals(new Board(cells)));
        assertNotSame(Board(1, 1), new Board(cells));
        
        cells.add(cell(Position(1,2)));
        
        assertTrue(Board(1, 2).equals(new Board(cells)));
        assertNotSame(Board(1, 2), new Board(cells));
        
    }
    
    @Test(expected = NullPointerException.class)
    public void testCloneWithNullPointer() {
    	
    	BoardFactory.Clone(null);
        
    }    
    

    @Test
    public void testClone() {
        
        BoardInterface board1 = Board(2,2);
        BoardInterface board2 = Clone(board1);
        
        assertNotSame(board1, board2);
        assertTrue(board1.equals(board2));
        
        for(ManagedCellInterface cell: board1) {
            assertNotSame(cell, board2.getCell(cell.getRow(), cell.getColumn()));
            assertEquals(cell, board2.getCell(cell.getRow(), cell.getColumn()));
        }
        
    }
}
