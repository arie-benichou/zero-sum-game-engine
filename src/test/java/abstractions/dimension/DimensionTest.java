
package abstractions.dimension;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public final class DimensionTest { // NOPMD

    private transient DimensionManagerInterface dimension;

    @Before
    public void setUp() throws Exception {

        this.dimension = DimensionFactory.dimension(3, 3);

    }

    @Test(expected = IllegalDimensionException.class)
    public void testIllegalDimensions1() {

        DimensionFactory.dimension(0, 0);

    }

    @Test(expected = IllegalDimensionException.class)
    public void testIllegalDimensions2() {

        DimensionFactory.dimension(0, 1);

    }

    @Test(expected = IllegalDimensionException.class)
    public void testIllegalDimensions3() {

        DimensionFactory.dimension(1, 0);

    }

    @Test(expected = IllegalDimensionException.class)
    public void testIllegalDimensions4() {

        DimensionFactory.dimension(-1, 1);

    }

    @Test(expected = IllegalDimensionException.class)
    public void testIllegalDimensions5() {

        DimensionFactory.dimension(1, -1);

    }

    @Test
    public void testLowerBoundForRows() {

        Assert.assertTrue(this.dimension.lowerBoundForRows() == 1);

    }

    @Test
    public void testUpperBoundForRows() {

        Assert.assertTrue(this.dimension.upperBoundForRows() == 3);

    }

    @Test
    public void testLowerBoundForColumns() {

        Assert.assertTrue(this.dimension.lowerBoundForColumns() == 1);

    }

    @Test
    public void testUpperBoundForColumns() {

        Assert.assertTrue(this.dimension.upperBoundForColumns() == 3);

    }

    @Test
    public void testNumberOfRows() {

        Assert.assertTrue(this.dimension.numberOfRows() == 3);

    }

    @Test
    public void testNumberOfColumns() {

        Assert.assertTrue(this.dimension.numberOfColumns() == 3);

    }

    @Test
    public void testBoardCapacity() {

        Assert.assertTrue(this.dimension.capacity() == 3 * 3);

    }

    @Test
    public void testContains() {

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
        this.dimension = null; // NOPMD 
    }

}