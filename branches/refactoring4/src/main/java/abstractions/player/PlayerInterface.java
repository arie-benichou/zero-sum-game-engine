
package abstractions.player;

import abstractions.strategy.StrategyInterface;

// TODO ? utiliser le pattern visitor pour visiter les cellules
public interface PlayerInterface {

    String getName();

    StrategyInterface getStrategy();

}
