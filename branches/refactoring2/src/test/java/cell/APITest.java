package cell;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.*;

import org.junit.Test;

import abstractions.cell.Cell;
import abstractions.cell.API.CellInterface;


import static abstractions.cell.API.*;
import static abstractions.cell.API.CellFactory.*;
import static abstractions.position.API.*;
import static abstractions.position.API.PositionFactory.*;

public class APITest {
	
    @Test(expected=NullPointerException.class)
    public void testCellWithNull() {
        
        CellFactory.Cell(null);
        
    }
        
    @Test(expected=NullPointerException.class)
    public void testCloneWithNull() {
        
        CellFactory.Clone(null);
        
    }	    

    @Test
    public void testCell() {

    	assertTrue(new Cell(Position(1, 1)).equals(CellFactory.Cell(Position(1,1))));
        
    }        
	
    @Test
    public void testCells() {
    	
        HashSet<CellInterface> expectedCells = new HashSet<CellInterface>(3);
        expectedCells.add(new Cell(Position(1, 1)));
        expectedCells.add(new Cell(Position(1, 2)));
        expectedCells.add(new Cell(Position(2, 1)));    	
        
        HashSet<PositionInterface> positions = new HashSet<PositionInterface>(4);
        
        positions.add(Position(1, 1));
        positions.add(Position(1, 2));
        positions.add(Position(2, 1));

        positions.add(Position(2, 1));
        positions.add(Position(2, 1));
        
        Set<CellInterface> cells = CellFactory.Cells(positions);
        
        assertTrue(expectedCells.equals(cells));
        
    }
	
    @Test
    public void testClone() {
    	
    	Cell cell = new Cell(Position(2, 4));
    	assertTrue(new Cell(Position(2, 4)).equals(CellFactory.Clone(cell)));
    	assertNotSame(new Cell(Position(2, 4)), CellFactory.Clone(cell));
        
    }

}
