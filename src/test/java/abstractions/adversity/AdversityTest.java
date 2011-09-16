
package abstractions.adversity;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abstractions.immutable.context.gameplay.adversity.Adversity;
import abstractions.immutable.context.gameplay.adversity.AdversityInterface;
import abstractions.immutable.context.gameplay.adversity.player.Player;
import abstractions.immutable.context.gameplay.adversity.player.PlayerInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.side.Side;
import abstractions.old.evaluator.NullEvaluator;
import abstractions.old.selector.Random;
import abstractions.old.strategy.Strategy;

public class AdversityTest {

    private AdversityInterface adversity;
    private PlayerInterface player1;
    private PlayerInterface player2;

    @Before
    public void setUp() throws Exception {
        this.player1 = new Player("player1", new Strategy(new NullEvaluator(), new Random()));
        this.player2 = new Player("player2", new Strategy(new NullEvaluator(), new Random()));
        this.adversity = new Adversity(this.player1, this.player2);
    }

    @Test
    public void testGetPlayer() {
        Assert.assertTrue(this.adversity.getPlayer(Side.FIRST) == this.player1);
        Assert.assertTrue(this.adversity.getPlayer(Side.SECOND) == this.player2);
    }

    @After
    public void tearDown() throws Exception {
        this.adversity = null;
    }

}
