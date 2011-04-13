
package abstractions.direction;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.dimension.API.DimensionFactory;

public class DirectionManagerTest {

    private DirectionManagerInterface directionManager;

    @Before
    public void setUp() throws Exception {
        this.directionManager = new DirectionManager(DimensionFactory.Dimension(3, 3));
    }

    @After
    public void tearDown() throws Exception {
        this.directionManager = null;
    }

    @Test
    public void testGetDirection() {
        Assert.fail("Not yet implemented");
    }

    @Test
    public void testGetNamedDirection() {
        Assert.assertTrue(this.directionManager.getNamedDirectionFromList(NamedDirection.TOP) == this.directionManager.getDirection(-1, 0));
        Assert.assertTrue(this.directionManager.getNamedDirectionFromList(NamedDirection.TOP_RIGHT) == this.directionManager.getDirection(-1, 1));
        Assert.assertTrue(this.directionManager.getNamedDirectionFromList(NamedDirection.RIGHT) == this.directionManager.getDirection(0, 1));
        Assert.assertTrue(this.directionManager.getNamedDirectionFromList(NamedDirection.BOTTOM_RIGHT) == this.directionManager.getDirection(1, 1));
        Assert.assertTrue(this.directionManager.getNamedDirectionFromList(NamedDirection.BOTTOM) == this.directionManager.getDirection(1, 0));
        Assert.assertTrue(this.directionManager.getNamedDirectionFromList(NamedDirection.BOTTOM_LEFT) == this.directionManager.getDirection(1, -1));
        Assert.assertTrue(this.directionManager.getNamedDirectionFromList(NamedDirection.LEFT) == this.directionManager.getDirection(0, -1));
        Assert.assertTrue(this.directionManager.getNamedDirectionFromList(NamedDirection.TOP_LEFT) == this.directionManager.getDirection(-1, -1));
    }

    @Test
    public void testGetDirectionsMap() {
        Assert.fail("Not yet implemented");
    }

    @Test
    public void testReduce() {
        Assert.assertTrue(this.directionManager.reduce(this.directionManager.getDirectionsMap().values()) == this.directionManager.getDirection(0, 0));
    }

}
