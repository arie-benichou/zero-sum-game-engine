
package game.board.dimension;

import static game.board.dimension.API.DimensionFactory.*;
import static game.board.dimension.API.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DimensionTest {

    private DimensionInterface dimension;

    @Before
    public void setUp() throws Exception {
        
        this.dimension = Dimension(3, 3);
        
    }

    @Test
    public final void testLegalDimensions() {
        
        assertTrue(this.dimension instanceof  DimensionInterface);
        
    }
    
    @Test(expected=IllegalDimensionException.class)
    public final void testIllegalDimensions1() {
        
        Dimension(0, 0);
        
    }
    
    @Test(expected=IllegalDimensionException.class)
    public final void testIllegalDimensions2() {
        
        Dimension(0, 1);
        
    }
    
    @Test(expected=IllegalDimensionException.class)
    public final void testIllegalDimensions3() {
        
        Dimension(1, 0);
        
    }
    
    
    @Test(expected=IllegalDimensionException.class)
    public final void testIllegalDimensions4() {
        
        Dimension(-1, 1);
        
    }
    
    @Test(expected=IllegalDimensionException.class)
    public final void testIllegalDimensions5() {
        
        Dimension(1, -1);
        
    }                
    

    @Test
    public final void testLowerBoundForRows() {
        
        assertTrue(this.dimension.lowerBoundForRows() == 1);
        
    }

    @Test
    public final void testUpperBoundForRows() {
        
        assertTrue(this.dimension.upperBoundForRows() == 3);
        
    }

    @Test
    public final void testLowerBoundForColumns() {
        
        assertTrue(this.dimension.lowerBoundForColumns() == 1);
        
    }

    @Test
    public final void testUpperBoundForColumns() {
        
        assertTrue(this.dimension.upperBoundForColumns() == 3);
        
    }

    @Test
    public final void testNumberOfRows() {
        
        assertTrue(this.dimension.numberOfRows() == 3);
        
    }

    @Test
    public final void testNumberOfColumns() {
        
        assertTrue(this.dimension.numberOfColumns() == 3);
        
    }

    @Test
    public final void testBoardCapacity() {
        
        assertTrue(this.dimension.boardCapacity() == 3 * 3);
        
    }

    @Test
    public final void testContains() {

        assertFalse(this.dimension.contains(0, 1));
        assertFalse(this.dimension.contains(1, 0));

        assertTrue(this.dimension.contains(1, 1));
        assertTrue(this.dimension.contains(1, 2));
        assertTrue(this.dimension.contains(1, 3));

        assertTrue(this.dimension.contains(2, 1));
        assertTrue(this.dimension.contains(2, 2));
        assertTrue(this.dimension.contains(2, 3));

        assertTrue(this.dimension.contains(3, 1));
        assertTrue(this.dimension.contains(3, 2));
        assertTrue(this.dimension.contains(3, 3));

        assertFalse(this.dimension.contains(3, 4));
        assertFalse(this.dimension.contains(4, 1));

    }
    
    @After
    public void tearDown() throws Exception {
        this.dimension = null;
    }    

}