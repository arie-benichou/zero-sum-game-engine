
package opponent;

import side.API.SideInterface;

public interface OpponentInterface {

    String getName();

    SideInterface getSide();

    StrategyInterface getStrategy();

    GameInterface context;

}
