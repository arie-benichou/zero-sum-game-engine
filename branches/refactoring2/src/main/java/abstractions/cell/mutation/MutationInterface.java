
package abstractions.cell.mutation;

import abstractions.cell.API.CellInterface;

public interface MutationInterface extends Comparable<MutationInterface> {

    void process();

    int getPriority();
    
    CellInterface getCell();

}
