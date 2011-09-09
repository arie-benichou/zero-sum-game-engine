
package abstractions.immutable.context;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.immutable.cells.Cell;
import abstractions.immutable.cells.BoardGameCells;
import abstractions.immutable.side.Side;

import com.google.common.collect.Lists;

public class MyZeroSumGameContextTest {

    MyZeroSumGameContext context;

    @Before
    public void setUp() throws Exception {
        final List<Cell> cells = Lists.newArrayList(
                BoardGameCellFactory.get(0),
                BoardGameCellFactory.get(1),
                BoardGameCellFactory.get(2),
                BoardGameCellFactory.get(3)
                );
        this.context = new MyZeroSumGameContext(Side.FIRST, cells);
    }

    @After
    public void tearDown() throws Exception {
        this.context = null;
    }

    @Test
    public void testGetSide() {
        Assert.assertTrue(this.context.getSide().isFirst());
    }

    @Test
    public void testToString() {
        Assert.assertTrue(this.context.toString().equals(Side.FIRST.toString() + " : 0 1 2 3"));
        Assert.assertTrue(this.context.apply(0).toString().equals(Side.SECOND.toString() + " : 1 1 2 3"));
        Assert.assertTrue(this.context.apply(1).toString().equals(Side.SECOND.toString() + " : 0 2 2 3"));
        Assert.assertTrue(this.context.apply(2).toString().equals(Side.SECOND.toString() + " : 0 1 3 3"));
        Assert.assertTrue(this.context.apply(3).toString().equals(Side.SECOND.toString() + " : 0 1 2 4"));
        Assert.assertTrue(this.context.toString().equals(Side.FIRST.toString() + " : 0 1 2 3"));
    }

    @Test
    public void testApply() {
        Assert.assertTrue(this.context.apply(0).getSide().isSecondSide());
    }

    /*
    @Test
    public void testIsOver() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetOptions() {
        fail("Not yet implemented");
    }
    */

}