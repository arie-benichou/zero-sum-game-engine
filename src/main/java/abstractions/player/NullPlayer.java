
package abstractions.player;

import abstractions.strategy.NullStrategy;
import abstractions.strategy.StrategyInterface;

public class NullPlayer implements PlayerInterface {

    private final String name;
    private final StrategyInterface strategy;

    protected NullPlayer(final String name, final StrategyInterface strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public NullPlayer() {
        this("", new NullStrategy());
    }

    public final String getName() {
        return this.name;
    }

    public final StrategyInterface getStrategy() {
        return this.strategy;
    }

}
