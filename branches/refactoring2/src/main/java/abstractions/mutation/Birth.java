package abstractions.mutation;

import abstractions.cell.CellInterface;


public class Birth extends AbstractAtomicMutation {

    public Birth(CellInterface concernedCell) {
        super(concernedCell);
    }
        
}
