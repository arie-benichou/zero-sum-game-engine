
package abstractions.position;

import static abstractions.dimension.API.DimensionFactory.Dimension;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import abstractions.position.absolute.AbsolutePosition;
import abstractions.position.absolute.AbsolutePositionInterface;
import abstractions.position.absolute.IllegalAbsolutePositionException;
import abstractions.position.absolute.NullAbsolutePosition;

public class _APITest {

    @Test
    public void testNullPosition() {

        assertTrue(new NullAbsolutePosition().equals(NULL_POSITION));
        assertFalse(new NullAbsolutePosition() == NULL_POSITION);

    }

    @Test(expected = IllegalAbsolutePositionException.class)
    public void testIllegalPosition() {

        Position(0, 0);

    }

    @Test
    public void testLegalPosition() {

        assertTrue(Position(1, 2).equals(new AbsolutePosition(1, 2)));
        assertFalse(Position(1, 2) == new AbsolutePosition(1, 2));

    }

    @Test
    public void testPositions() {

        final Set<AbsolutePositionInterface> cells = new HashSet<AbsolutePositionInterface>(1);

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
