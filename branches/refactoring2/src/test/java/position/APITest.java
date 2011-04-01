
package position;

import static abstractions.dimension.API.DimensionFactory.*;
import static abstractions.position.API.*;
import static abstractions.position.API.PositionFactory.*;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import abstractions.position.NullPosition;
import abstractions.position.Position;


public class APITest {

    @Test
    public void testNullPosition() {
        
        assertTrue(new NullPosition().equals(NULL_POSITION));
        assertFalse(new NullPosition() == NULL_POSITION);
        
    }

    @Test(expected = IllegalPositionException.class)
    public void testIllegalPosition() {
        
        Position(0, 0);
        
    }

    @Test
    public void testLegalPosition() {
        
        assertTrue(Position(1, 2).equals(new Position(1, 2)));
        assertFalse(Position(1, 2) == new Position(1, 2));
        
    }

    @Test
    public void testPositions() {
        
        final Set<PositionInterface> cells = new HashSet<PositionInterface>(1);
        
        cells.add(Position(1, 1));
        
        assertTrue(Positions(Dimension(1, 1)).equals(cells));
        
        cells.add(Position(1, 2));
        
        assertTrue(Positions(Dimension(1, 2)).equals(cells));
        
        cells.add(Position(2, 1));
        cells.add(Position(2, 2));

        assertTrue(cells.equals(Positions(Dimension(2, 2))));
        
        cells.add(Position(2, 4));
        cells.add(Position(2, 3));
        
        // Le type de la collection de positions est une liste, les positions sont donc ordonnées. 
        assertFalse(cells.equals(Positions(Dimension(2, 4))));
        
    }
}
