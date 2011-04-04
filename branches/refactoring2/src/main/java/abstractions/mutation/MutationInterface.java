
package abstractions.mutation;

import abstractions.cell.CellInterface;

public interface MutationInterface extends Comparable<MutationInterface> {

    void process();

    int getPriority();
    
    CellInterface getCell();

}
