package abstractions.mutation;

import abstractions.cell.old.CellInterface;


public class Birth extends AbstractAtomicMutation {

    public Birth(CellInterface concernedCell) {
        super(concernedCell);
    }
        
}
