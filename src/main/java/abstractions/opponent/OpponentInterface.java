
package abstractions.opponent;

import abstractions.game.GameInterface;
import abstractions.side.SideInterface;
import abstractions.strategy.StrategyInterface;

public interface OpponentInterface {

    String getName();

    SideInterface getSide();

    StrategyInterface getStrategy();

    GameInterface context;

}
