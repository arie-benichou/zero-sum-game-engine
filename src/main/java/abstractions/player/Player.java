
package abstractions.player;

import abstractions.strategy.StrategyInterface;

public class Player implements PlayerInterface {

    private final String name;
    private final StrategyInterface strategy;

    public Player(final String name, final StrategyInterface strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public final String getName() {
        return this.name;
    }

    public final StrategyInterface getStrategy() {
        return this.strategy;
    }

    @Override
    public String toString() {
        return "Player(" + "\"" + this.name + "\"" + ", " + this.strategy + ")";
    }
}
