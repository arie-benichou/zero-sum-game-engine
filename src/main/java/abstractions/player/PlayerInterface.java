
package abstractions.player;

import java.util.List;

import abstractions.mutation.MutationInterface;

// TODO ? utiliser le pattern visitor pour visiter les cellules
public interface PlayerInterface {

    String getName();

    //façade    
    MutationInterface applyStrategy(List<MutationInterface> mutations);

    //StrategyInterface getStrategy();

}
