
package abstractions.position;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.dimension.API.DimensionFactory;
import abstractions.position.PositionManager.Direction;

public class PositionManagerTest {

    private PositionManager manager;

    @Before
    public void setUp() throws Exception {

        this.manager = new PositionManager(DimensionFactory.Dimension(3, 3));

    }

    @Test
    public final void testGetPosition() {

        Assert.assertTrue(this.manager.getPosition(0, 0) == this.manager.getNullPosition());

        Assert.assertTrue(this.manager.getPosition(1, 1).equals(new Position(1, 1)));
        Assert.assertTrue(this.manager.getPosition(1, 2).equals(new Position(1, 2)));
        Assert.assertTrue(this.manager.getPosition(1, 3).equals(new Position(1, 3)));

        Assert.assertTrue(this.manager.getPosition(2, 1).equals(new Position(2, 1)));
        Assert.assertTrue(this.manager.getPosition(2, 2).equals(new Position(2, 2)));
        Assert.assertTrue(this.manager.getPosition(2, 3).equals(new Position(2, 3)));

        Assert.assertTrue(this.manager.getPosition(3, 1).equals(new Position(3, 1)));
        Assert.assertTrue(this.manager.getPosition(3, 2).equals(new Position(3, 2)));
        Assert.assertTrue(this.manager.getPosition(3, 3).equals(new Position(3, 3)));

        Assert.assertTrue(this.manager.getPosition(4, 1) == this.manager.getNullPosition());

    }

    @Test
    public final void testGetPositionWithOffset1() {

        final PositionInterface position = new Position(1, 1);

        Assert.assertTrue(this.manager.getPosition(position, Direction.TOP) == this.manager.getNullPosition());
        Assert.assertTrue(this.manager.getPosition(position, Direction.BOTTOM).equals(new Position(2, 1)));
        Assert.assertTrue(this.manager.getPosition(position, Direction.LEFT) == this.manager.getNullPosition());
        Assert.assertTrue(this.manager.getPosition(position, Direction.RIGHT).equals(new Position(1, 2)));

        Assert.assertTrue(this.manager.getPosition(position, Direction.TOP_LEFT) == this.manager.getNullPosition());
        Assert.assertTrue(this.manager.getPosition(position, Direction.TOP_RIGHT) == this.manager.getNullPosition());
        Assert.assertTrue(this.manager.getPosition(position, Direction.BOTTOM_LEFT) == this.manager.getNullPosition());
        Assert.assertTrue(this.manager.getPosition(position, Direction.BOTTOM_RIGHT).equals(new Position(2, 2)));

    }

    @Test
    public final void testGetPositionWithOffset2() {

        final PositionInterface position = new Position(2, 2);

        Assert.assertTrue(this.manager.getPosition(position, Direction.TOP).equals(new Position(1, 2)));
        Assert.assertTrue(this.manager.getPosition(position, Direction.BOTTOM).equals(new Position(3, 2)));
        Assert.assertTrue(this.manager.getPosition(position, Direction.LEFT).equals(new Position(2, 1)));
        Assert.assertTrue(this.manager.getPosition(position, Direction.RIGHT).equals(new Position(2, 3)));

        Assert.assertTrue(this.manager.getPosition(position, Direction.TOP_LEFT).equals(new Position(1, 1)));
        Assert.assertTrue(this.manager.getPosition(position, Direction.TOP_RIGHT).equals(new Position(1, 3)));
        Assert.assertTrue(this.manager.getPosition(position, Direction.BOTTOM_LEFT).equals(new Position(3, 1)));
        Assert.assertTrue(this.manager.getPosition(position, Direction.BOTTOM_RIGHT).equals(new Position(3, 3)));

    }

    @After
    public void tearDown() throws Exception {

        this.manager = null;

    }

}
