
package abstractions.player;

import abstractions.strategy.AbstractStrategy;
import abstractions.strategy.StrategyInterface;

//TODO à virer
public class NullPlayer implements PlayerInterface {

    private final String name;
    private final StrategyInterface strategy;

    protected NullPlayer(final String name, final StrategyInterface strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public NullPlayer() {
        this("", new AbstractStrategy());
    }

    public final String getName() {
        return this.name;
    }

    public final StrategyInterface getStrategy() {
        return this.strategy;
    }

}
