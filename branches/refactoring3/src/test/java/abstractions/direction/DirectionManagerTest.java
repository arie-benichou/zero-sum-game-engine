
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
    public void testDirectionManager() {
        Assert.fail("Not yet implemented");
    }

    @Test
    public void testGetNamedDirection() {
        Assert.fail("Not yet implemented");
    }

    @Test
    public void testReduce() {
        Assert.fail("Not yet implemented");
    }

    @Test
    public void testGetDirectionsMap() {
        Assert.fail("Not yet implemented");
    }

    @Test
    public void testMain() {
        Assert.fail("Not yet implemented");
    }

}
