
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

    @Test
    public void testGetDirection() {
        //this.directionManager.getDirection(NamedDirection.TOP);
    }

    @Test
    public void testReduce() {
        Assert.fail("Not yet implemented");
    }

    @After
    public void tearDown() throws Exception {
        this.directionManager = null;
    }

}
