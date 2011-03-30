
package game.position;

import static game.dimension.API.DimensionFactory.*;
import static game.position.API.*;
import static game.position.API.PositionFactory.*;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import game.position.NullPosition;
import game.position.Position;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class APITest {

    @Test
    public void testNullPosition() {
        
        assertTrue(new NullPosition().equals(NULL_POSITION));
        assertTrue(new NullPosition().equals(NullPosition()));
        
        assertFalse(new NullPosition() == NULL_POSITION);
        assertFalse(new NullPosition() == NullPosition());
        
        assertTrue(NULL_POSITION == NullPosition());
        
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
        
        final List<PositionInterface> cells = new ArrayList<PositionInterface>(1);
        
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
