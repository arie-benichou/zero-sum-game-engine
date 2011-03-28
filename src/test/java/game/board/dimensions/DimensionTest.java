/**
 * 
 */

package game.board.dimensions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author artefact
 * 
 */
public class DimensionTest {

    private Dimensions.Interface dimension;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.dimension = Dimensions.Factory.Dimension(3, 3);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.dimension = null;
    }

    /**
     * Test method for
     * {@link game.board.dimensions.Dimensions#Dimensions(game.board.dimensions.RowsRange, game.board.dimensions.ColumnsRange)}
     * .
     */
    @Test
    public final void testDimensions() {
        Assert.assertTrue(this.dimension instanceof Dimensions.Interface);
    }

    /**
     * Test method for
     * {@link game.board.dimensions.Dimensions#lowerBoundForRows()}.
     */
    @Test
    public final void testGetRowsLowerBound() {
        Assert.assertTrue(this.dimension.lowerBoundForRows() == 1);
    }

    /**
     * Test method for
     * {@link game.board.dimensions.Dimensions#upperBoundForRows()}.
     */
    @Test
    public final void testGetRowsUpperBound() {
        Assert.assertTrue(this.dimension.upperBoundForRows() == 3);
    }

    /**
     * Test method for
     * {@link game.board.dimensions.Dimensions#getMinColumnIndex()}.
     */
    @Test
    public final void testGetMinColumnIndex() {
        Assert.assertTrue(this.dimension.lowerBoundForColumns() == 1);
    }

    /**
     * Test method for
     * {@link game.board.dimensions.Dimensions#getMaxColumnIndex()}.
     */
    @Test
    public final void testGetMaxColumnIndex() {
        Assert.assertTrue(this.dimension.upperBoundForColumns() == 3);
    }

    /**
     * Test method for
     * {@link game.board.dimensions.Dimensions#getRowIndexRangeSize()}.
     */
    @Test
    public final void testGetRowIndexRangeSize() {
        Assert.assertTrue(this.dimension.numberOfRows() == 3);
    }

    /**
     * Test method for
     * {@link game.board.dimensions.Dimensions#getColumnIndexRangeSize()}.
     */
    @Test
    public final void testGetColumnIndexRangeSize() {
        Assert.assertTrue(this.dimension.numberOfColumns() == 3);
    }

    /**
     * Test method for {@link game.board.dimensions.Dimensions#boardCapacity()}.
     */
    @Test
    public final void testGetCapacity() {
        Assert.assertTrue(this.dimension.boardCapacity() == 3 * 3);
    }

    /**
     * Test method for
     * {@link game.board.dimensions.Dimensions#contains(int, int)}.
     */
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

}
