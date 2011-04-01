
package abstractions.opponent;

import abstractions.side.API.SideInterface;

class Opponent implements OpponentInterface {

    private final String name;
    private final SideInterface side;
    private final StrategyInterface strategy;
    private final GameInterface context;

    public Opponent(final String name, final SideInterface side /*, final GameInterface context, final StrategyInterface strategy*/) {
        this.name = name;
        this.side = side;
        this.strategy = strategy;
        this.context = context;
    }

    public final String getName() {
        return this.name;
    }

    public final SideInterface getSide() {
        return this.side;
    }

    public final StrategyInterface getStrategy() {
        return this.strategy;
    }

    public final GameInterface getContext() {
        return this.context;
    }

}
