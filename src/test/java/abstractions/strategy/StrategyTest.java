
package abstractions.strategy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abstractions.old.evaluator.NullEvaluator;
import abstractions.old.selector.Random;
import abstractions.old.strategy.Strategy;
import abstractions.old.strategy.StrategyInterface;

public class StrategyTest {

    private StrategyInterface strategy;

    @Before
    public void setUp() throws Exception {
        this.strategy = new Strategy(new NullEvaluator(), new Random());
    }

    @Test
    public void testGetEvaluator() {
        Assert.assertTrue(this.strategy.getEvaluator() instanceof NullEvaluator);
    }

    @Test
    public void testGetSelector() {
        Assert.assertTrue(this.strategy.getSelector() instanceof Random);
    }

    @After
    public void tearDown() throws Exception {
        this.strategy = null;
    }

}
