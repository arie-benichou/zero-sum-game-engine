
package abstractions.position;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.dimension.API.DimensionFactory;

public class PositionManagerTest {

    private PositionManager manager;

    @Before
    public void setUp() throws Exception {

        this.manager = new PositionManager(DimensionFactory.Dimension(2, 2));

    }

    @Test
    public final void testGetPosition() {

        Assert.assertTrue(this.manager.getPosition(0, 0).equals(new Position(0, 0)));

        Assert.assertTrue(this.manager.getPosition(1, 1).equals(new Position(1, 1)));
        Assert.assertTrue(this.manager.getPosition(1, 2).equals(new Position(1, 2)));

        Assert.assertTrue(this.manager.getPosition(2, 1).equals(new Position(2, 1)));
        Assert.assertTrue(this.manager.getPosition(2, 2).equals(new Position(2, 2)));

        Assert.assertTrue(this.manager.getPosition(3, 1).equals(new Position(0, 0)));

    }

    @After
    public void tearDown() throws Exception {

        this.manager = null;

    }

}
