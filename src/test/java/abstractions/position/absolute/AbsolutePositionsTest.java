
package abstractions.position.absolute;

import static abstractions.dimension.API.DimensionFactory.Dimension;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.position.Position;
import abstractions.position.PositionInterface;
import abstractions.position.PositionSetFactory;

public class AbsolutePositionsTest {

    private PositionSetFactory factory;

    @Before
    public void setUp() {

        this.factory = PositionSetFactory.getInstance();

    }

    @Test
    public void testNullPosition() {

        assertTrue(new NullPosition().equals(PositionSetFactory.NULL_POSITION));
        assertFalse(new NullPosition() == PositionSetFactory.NULL_POSITION);

    }

    @Test(expected = IllegaPositionException.class)
    public void testIllegalPosition() {

        this.factory.newPosition(0, 0);

    }

    @Test
    public void testLegalPosition() {

        assertTrue(this.factory.newPosition(1, 2).equals(new Position(1, 2)));
        assertFalse(this.factory.newPosition(1, 2) == new Position(1, 2));

    }

    @Test
    public void testGetAllPositions() {

        final Set<PositionInterface> positions = new HashSet<PositionInterface>(1);

        positions.add(this.factory.newPosition(1, 1));

        assertTrue(this.factory.NewPositionSet(Dimension(1, 1)).equals(positions));

        positions.add(this.factory.newPosition(1, 2));

        assertTrue(this.factory.NewPositionSet(Dimension(1, 2)).equals(positions));

        positions.add(this.factory.newPosition(2, 1));
        positions.add(this.factory.newPosition(2, 2));

        assertTrue(positions.equals(this.factory.NewPositionSet(Dimension(2, 2))));

        positions.add(this.factory.newPosition(2, 4));
        positions.add(this.factory.newPosition(2, 3));

        assertFalse(positions.equals(this.factory.NewPositionSet(Dimension(2, 4))));

    }

    @After
    public void tearDown() {

        this.factory = null;

    }

}
