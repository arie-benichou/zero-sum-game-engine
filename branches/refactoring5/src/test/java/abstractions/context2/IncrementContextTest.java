
package abstractions.context2;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.immutable.context.ContextInterface;
import abstractions.immutable.context.IncrementContext;

public class IncrementContextTest {

    private IncrementContext context;

    @Before
    public void setUp() throws Exception {
        this.context = new IncrementContext();
    }

    @After
    public void tearDown() throws Exception {
        this.context = null;
    }

    @Test
    public void testIncrementContext() {
        Assert.assertTrue(this.context.toString().equals("1"));
    }

    @Test
    public void testApply() {
        Assert.assertTrue(this.context.apply(1) != this.context);
        Assert.assertTrue(this.context.apply(1).toString().equals("2"));
        ContextInterface<Integer> context = this.context;
        context = context.apply(1);
        context = context.apply(1);
        context = context.apply(1);
        context = context.apply(1);
        Assert.assertTrue(context.toString().equals("5"));
        context = context.apply(1);
        Assert.assertTrue(context.toString().equals("6"));
    }

    /*
    @Test
    public void testGetOptions() {
        Assert.assertTrue(this.context.getOptions().size() == 2);
    }

    @Test
    public void testIsOver() {
        ContextInterface<Integer> context = this.context;
        Assert.assertTrue(context.isOver() == false);
        context = context.apply(1);
        context = context.apply(1);
        context = context.apply(1);
        Assert.assertTrue(context.isOver() == false);
        context = context.apply(1);
        Assert.assertTrue(context.isOver() == true);
    }
    */

}
