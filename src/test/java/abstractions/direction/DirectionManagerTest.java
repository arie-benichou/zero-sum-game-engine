
package abstractions.direction;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.dimension.API.DimensionFactory;
import abstractions.dimension.API.DimensionInterface;

public class DirectionManagerTest {

    private DirectionManagerInterface directionManager;
    private DimensionInterface dimension;

    @Before
    public void setUp() throws Exception {

        /*
        final Random random = new Random();
        this.dimension = DimensionFactory.Dimension(random.nextInt(99), random.nextInt(99));
        */

        this.dimension = DimensionFactory.Dimension(1, 6);
        //System.out.println(this.dimension);
        this.directionManager = new DirectionManager(this.dimension);
    }

    @Test(expected = IllegalDirectionException.class)
    public void testGetIllegalDirection1() {
        final DirectionManagerInterface manager = new DirectionManager(DimensionFactory.Dimension(1, 2));
        manager.getDirection(-1, 0);
    }

    @Test(expected = IllegalDirectionException.class)
    public void testGetIlegalDirection2() {
        final DirectionManagerInterface manager = new DirectionManager(DimensionFactory.Dimension(1, 2));
        manager.getDirection(1, 0);
    }

    @Test(expected = IllegalDirectionException.class)
    public void testGetIlegalDirection3() {
        final DirectionManagerInterface manager = new DirectionManager(DimensionFactory.Dimension(2, 1));
        System.out.println(manager.getDirection(0, 1));
    }

    //@Test(expected = IllegalDirectionException.class)
    public void testGetIlegalDirection4() {
        final DirectionManagerInterface manager = new DirectionManager(DimensionFactory.Dimension(2, 1));
        manager.getDirection(0, -1);
    }

    //@Test
    public void testGetDirection() {
        DirectionInterface direction;
        final int maxRowDelta = this.dimension.upperBoundForRows() - 1;
        final int maxColumnDelta = this.dimension.upperBoundForColumns() - 1;
        for (int rowDelta = -maxRowDelta; rowDelta <= maxRowDelta; ++rowDelta) {
            for (int columnDelta = -maxColumnDelta; columnDelta <= maxColumnDelta; ++columnDelta) {
                System.out.println("\n" + "y = " + rowDelta + " | x = " + columnDelta);
                direction = this.directionManager.getDirection(rowDelta, columnDelta);
                Assert.assertTrue(direction.getRowDelta() == rowDelta);
                Assert.assertTrue(direction.getColumnDelta() == columnDelta);
            }
        }
    }

    //@Test
    public void testGetNamedDirection() {

        System.out.println(this.directionManager.getNamedDirectionFromList(NamedDirection.TOP));

        /*
        Assert.assertTrue(this.directionManager.getNamedDirectionFromList(NamedDirection.TOP) == this.directionManager.getDirection(-1, 0));
        Assert.assertTrue(this.directionManager.getNamedDirectionFromList(NamedDirection.TOP_RIGHT) == this.directionManager.getDirection(-1, 1));
        Assert.assertTrue(this.directionManager.getNamedDirectionFromList(NamedDirection.RIGHT) == this.directionManager.getDirection(0, 1));
        Assert.assertTrue(this.directionManager.getNamedDirectionFromList(NamedDirection.BOTTOM_RIGHT) == this.directionManager.getDirection(1, 1));
        Assert.assertTrue(this.directionManager.getNamedDirectionFromList(NamedDirection.BOTTOM) == this.directionManager.getDirection(1, 0));
        Assert.assertTrue(this.directionManager.getNamedDirectionFromList(NamedDirection.BOTTOM_LEFT) == this.directionManager.getDirection(1, -1));
        Assert.assertTrue(this.directionManager.getNamedDirectionFromList(NamedDirection.LEFT) == this.directionManager.getDirection(0, -1));
        Assert.assertTrue(this.directionManager.getNamedDirectionFromList(NamedDirection.TOP_LEFT) == this.directionManager.getDirection(-1, -1));
        */
    }

    //@Test
    public void testGetDirectionsMap() {
        Assert.fail("Not yet implemented");
    }

    //@Test
    public void testReduce() {
        Assert.assertTrue(this.directionManager.reduce(this.directionManager.getDirectionsMap().values()) == this.directionManager.getDirection(0, 0));
    }

    @After
    public void tearDown() throws Exception {
        this.directionManager = null;
    }

}
