
package abstractions.position;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.dimension.API.DimensionFactory;

public class PositionSetFactoryTest {

    private PositionSetFactory factory;

    @Before
    public void setUp() {

        this.factory = new PositionSetFactory();

    }

    @Test
    public void testNewPositionSet() {

        final Set<PositionInterface> expectedSet = new HashSet<PositionInterface>();
        expectedSet.add(new Position(0, 0));

        expectedSet.add(new Position(1, 1));
        Assert.assertTrue(this.factory.newPositionSet(DimensionFactory.Dimension(1, 1)).equals(expectedSet));

        expectedSet.add(new Position(1, 2));
        Assert.assertTrue(this.factory.newPositionSet(DimensionFactory.Dimension(1, 2)).equals(expectedSet));

        expectedSet.add(new Position(2, 1));
        expectedSet.add(new Position(2, 2));
        Assert.assertTrue(expectedSet.equals(this.factory.newPositionSet(DimensionFactory.Dimension(2, 2))));

        expectedSet.add(new Position(1, 3));
        expectedSet.add(new Position(1, 4));
        expectedSet.add(new Position(2, 3));
        expectedSet.add(new Position(2, 4));
        Assert.assertTrue(expectedSet.equals(this.factory.newPositionSet(DimensionFactory.Dimension(2, 4))));

        expectedSet.add(new Position(2, 5));
        Assert.assertFalse(expectedSet.equals(this.factory.newPositionSet(DimensionFactory.Dimension(2, 4))));
        expectedSet.remove(new Position(2, 5));

    }

    @Test
    public void testNewPositionSet1x1() {

        final Set<PositionInterface> expectedSet = new HashSet<PositionInterface>();

        expectedSet.add(new Position(0, 0));
        expectedSet.add(new Position(1, 1));

        final Set<PositionInterface> set = this.factory.newPositionSet(DimensionFactory.Dimension(1, 1));
        Assert.assertTrue(expectedSet.equals(set));

        expectedSet.remove(new Position(0, 0));
        Assert.assertFalse(expectedSet.equals(set));

    }

    @Test
    public void testNewPositionSet1x2() {

        final Set<PositionInterface> expectedSet = new HashSet<PositionInterface>();

        expectedSet.add(new Position(0, 0));

        expectedSet.add(new Position(1, 1));
        expectedSet.add(new Position(1, 2));

        final Set<PositionInterface> set = this.factory.newPositionSet(DimensionFactory.Dimension(1, 2));
        Assert.assertTrue(expectedSet.equals(set));

        expectedSet.remove(new Position(0, 0));
        Assert.assertFalse(expectedSet.equals(set));
    }

    @Test
    public void testNewPositionSet2x1() {

        final Set<PositionInterface> expectedSet = new HashSet<PositionInterface>();

        expectedSet.add(new Position(0, 0));

        expectedSet.add(new Position(1, 1));
        expectedSet.add(new Position(2, 1));

        final Set<PositionInterface> set = this.factory.newPositionSet(DimensionFactory.Dimension(2, 1));
        Assert.assertTrue(expectedSet.equals(set));

        expectedSet.remove(new Position(0, 0));
        Assert.assertFalse(expectedSet.equals(set));
    }

    @Test
    public void testNewPositionSet2x2() {

        final Set<PositionInterface> expectedSet = new HashSet<PositionInterface>();

        expectedSet.add(new Position(0, 0));

        expectedSet.add(new Position(1, 1));
        expectedSet.add(new Position(1, 2));

        expectedSet.add(new Position(2, 1));
        expectedSet.add(new Position(2, 2));

        final Set<PositionInterface> set = this.factory.newPositionSet(DimensionFactory.Dimension(2, 2));
        Assert.assertTrue(expectedSet.equals(set));

        expectedSet.remove(new Position(0, 0));
        Assert.assertFalse(expectedSet.equals(set));
    }

    @Test
    public void testNewPositionSet3x3() {

        final Set<PositionInterface> expectedSet = new HashSet<PositionInterface>();

        expectedSet.add(new Position(0, 0));

        expectedSet.add(new Position(1, 1));
        expectedSet.add(new Position(1, 2));
        expectedSet.add(new Position(1, 3));

        expectedSet.add(new Position(2, 1));
        expectedSet.add(new Position(2, 2));
        expectedSet.add(new Position(2, 3));

        expectedSet.add(new Position(3, 1));
        expectedSet.add(new Position(3, 2));
        expectedSet.add(new Position(3, 3));

        final Set<PositionInterface> set = this.factory.newPositionSet(DimensionFactory.Dimension(3, 3));
        Assert.assertTrue(expectedSet.equals(set));

        expectedSet.remove(new Position(0, 0));
        Assert.assertFalse(expectedSet.equals(set));
    }

    @After
    public void tearDown() {

        this.factory = null;

    }

}
