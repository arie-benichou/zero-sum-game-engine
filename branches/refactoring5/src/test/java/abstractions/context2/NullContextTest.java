
package abstractions.context2;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.immutable.context.NullContext;

public class NullContextTest {

    private NullContext<Integer> context;

    @Before
    public void setUp() throws Exception {
        this.context = new NullContext<Integer>();
    }

    @Test
    public void testApply() {
        Assert.assertTrue(this.context.apply(null) == this.context);
        Assert.assertTrue(this.context.apply(-1) == this.context);
        Assert.assertTrue(this.context.apply(0) == this.context);
        Assert.assertTrue(this.context.apply(1) == this.context);
    }

    @After
    public void tearDown() throws Exception {
        this.context = null;
    }

}
