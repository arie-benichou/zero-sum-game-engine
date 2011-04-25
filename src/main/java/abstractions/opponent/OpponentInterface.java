
package abstractions.opponent;

import abstractions.strategy.StrategyInterface;

// TODO ? utiliser le pattern visitor pour visiter les cellules
public interface OpponentInterface {

    String getName();

    //SideInterface getSide();

    StrategyInterface getStrategy();

    //GameInterface context;

}
