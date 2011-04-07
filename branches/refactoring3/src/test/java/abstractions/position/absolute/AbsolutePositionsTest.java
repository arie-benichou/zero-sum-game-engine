
package abstractions.position.absolute;

import static abstractions.dimension.API.DimensionFactory.Dimension;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AbsolutePositionsTest {

    private AbsolutePositions factory;

    @Before
    public void setUp() {

        this.factory = AbsolutePositions.getInstance();

    }

    @Test
    public void testNullPosition() {

        assertTrue(new NullAbsolutePosition().equals(AbsolutePositions.NULL_POSITION));
        assertFalse(new NullAbsolutePosition() == AbsolutePositions.NULL_POSITION);

    }

    @Test(expected = IllegalAbsolutePositionException.class)
    public void testIllegalPosition() {

        this.factory.getPosition(0, 0);

    }

    @Test
    public void testLegalPosition() {

        assertTrue(this.factory.getPosition(1, 2).equals(new AbsolutePosition(1, 2)));
        assertFalse(this.factory.getPosition(1, 2) == new AbsolutePosition(1, 2));

    }

    @Test
    public void testGetAllPositions() {

        final Set<AbsolutePositionInterface> positions = new HashSet<AbsolutePositionInterface>(1);

        positions.add(this.factory.getPosition(1, 1));

        assertTrue(this.factory.getAllPositions(Dimension(1, 1)).equals(positions));

        positions.add(this.factory.getPosition(1, 2));

        assertTrue(this.factory.getAllPositions(Dimension(1, 2)).equals(positions));

        positions.add(this.factory.getPosition(2, 1));
        positions.add(this.factory.getPosition(2, 2));

        assertTrue(positions.equals(this.factory.getAllPositions(Dimension(2, 2))));

        positions.add(this.factory.getPosition(2, 4));
        positions.add(this.factory.getPosition(2, 3));

        assertFalse(positions.equals(this.factory.getAllPositions(Dimension(2, 4))));

    }

    @After
    public void tearDown() {

        this.factory = null;

    }

}
