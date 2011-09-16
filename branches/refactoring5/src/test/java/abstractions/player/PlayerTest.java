
package abstractions.player;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.immutable.context.gameplay.adversity.player.Player;
import abstractions.immutable.context.gameplay.adversity.player.PlayerInterface;
import abstractions.old.evaluator.NullEvaluator;
import abstractions.old.selector.Random;
import abstractions.old.strategy.Strategy;
import abstractions.old.strategy.StrategyInterface;

public class PlayerTest {

    private PlayerInterface player;

    @Before
    public void setUp() throws Exception {
        this.player = new Player("player1", new Strategy(new NullEvaluator(), new Random()));
    }

    @Test
    public void testGetName() {
        Assert.assertTrue(this.player.name().equals("player1"));
    }

    @Test
    public void testGetStrategy() {
        Assert.assertTrue(this.player.getStrategy() instanceof StrategyInterface);
    }

    @After
    public void tearDown() throws Exception {
        this.player = null;
    }
}
