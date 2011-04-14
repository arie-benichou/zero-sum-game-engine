
package abstractions.dimension;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DimensionTest {

    private DimensionManagerInterface dimension;

    @Before
    public void setUp() throws Exception {

        this.dimension = DimensionFactory.Dimension(3, 3);

    }

    @Test
    public final void testLegalDimensions() {

        Assert.assertTrue(this.dimension instanceof DimensionManagerInterface);

    }

    @Test(expected = IllegalDimensionException.class)
    public final void testIllegalDimensions1() {

        DimensionFactory.Dimension(0, 0);

    }

    @Test(expected = IllegalDimensionException.class)
    public final void testIllegalDimensions2() {

        DimensionFactory.Dimension(0, 1);

    }

    @Test(expected = IllegalDimensionException.class)
    public final void testIllegalDimensions3() {

        DimensionFactory.Dimension(1, 0);

    }

    @Test(expected = IllegalDimensionException.class)
    public final void testIllegalDimensions4() {

        DimensionFactory.Dimension(-1, 1);

    }

    @Test(expected = IllegalDimensionException.class)
    public final void testIllegalDimensions5() {

        DimensionFactory.Dimension(1, -1);

    }

    @Test
    public final void testLowerBoundForRows() {

        Assert.assertTrue(this.dimension.lowerBoundForRows() == 1);

    }

    @Test
    public final void testUpperBoundForRows() {

        Assert.assertTrue(this.dimension.upperBoundForRows() == 3);

    }

    @Test
    public final void testLowerBoundForColumns() {

        Assert.assertTrue(this.dimension.lowerBoundForColumns() == 1);

    }

    @Test
    public final void testUpperBoundForColumns() {

        Assert.assertTrue(this.dimension.upperBoundForColumns() == 3);

    }

    @Test
    public final void testNumberOfRows() {

        Assert.assertTrue(this.dimension.numberOfRows() == 3);

    }

    @Test
    public final void testNumberOfColumns() {

        Assert.assertTrue(this.dimension.numberOfColumns() == 3);

    }

    @Test
    public final void testBoardCapacity() {

        Assert.assertTrue(this.dimension.capacity() == 3 * 3);

    }

    @Test
    public final void testContains() {

        Assert.assertFalse(this.dimension.contains(0, 1));
        Assert.assertFalse(this.dimension.contains(1, 0));

        Assert.assertTrue(this.dimension.contains(1, 1));
        Assert.assertTrue(this.dimension.contains(1, 2));
        Assert.assertTrue(this.dimension.contains(1, 3));

        Assert.assertTrue(this.dimension.contains(2, 1));
        Assert.assertTrue(this.dimension.contains(2, 2));
        Assert.assertTrue(this.dimension.contains(2, 3));

        Assert.assertTrue(this.dimension.contains(3, 1));
        Assert.assertTrue(this.dimension.contains(3, 2));
        Assert.assertTrue(this.dimension.contains(3, 3));

        Assert.assertFalse(this.dimension.contains(3, 4));
        Assert.assertFalse(this.dimension.contains(4, 1));

    }

    @After
    public void tearDown() throws Exception {
        this.dimension = null;
    }

}