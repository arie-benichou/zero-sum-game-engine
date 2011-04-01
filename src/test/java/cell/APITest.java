package cell;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import position.API.PositionFactory;
import position.API.PositionInterface;
import cell.API.CellFactory;
import cell.API.CellInterface;


public class APITest {
    
    
    @Test public void testCells() {
        
        HashSet<PositionInterface> positions = new HashSet<PositionInterface>(4);
        
        positions.add(PositionFactory.Position(1, 1));
        positions.add(PositionFactory.Position(1, 2));
        positions.add(PositionFactory.Position(2, 1));
        
        positions.add(PositionFactory.Position(2, 1));
        positions.add(PositionFactory.Position(2, 1));
        
        Set<CellInterface> cells = CellFactory.Cells(positions);
        
        Assert.assertTrue(cells.size() == 3);
        
    }

}
