
package abstractions.mutation;

import java.util.List;

import abstractions.cell.old.CellInterface;
import abstractions.side.SideInterface;

// TODO utiliser le pattern composite
// TODO renommer en CompositeMutationInterface
// TODO implémenter en partie l'interface via une classe abstraite
public interface MutationInterface extends Comparable<MutationInterface> {

    int getPriority();
    
    CellInterface getConcernedCell();
    
    SideInterface getSide();    
    
    void process();
    
    void cancel();
    
    List<AtomicMutationInterface> getSequence();    

}
