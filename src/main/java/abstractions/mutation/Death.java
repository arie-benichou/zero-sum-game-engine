
package abstractions.mutation;

import abstractions.cell.CellInterface;

public class Death extends AbstractAtomicMutation {

    public Death(CellInterface concernedCell) {
        super(concernedCell);
    }
    
}
