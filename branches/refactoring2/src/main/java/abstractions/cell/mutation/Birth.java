package abstractions.cell.mutation;

import abstractions.cell.API.CellInterface;


public class Birth extends AbstractAtomicMutation {

    public Birth(CellInterface concernedCell) {
        super(concernedCell);
    }

    @Override
    public void process() {
        this.getConcernedCell().setPiece(this.getProtagonist());
    }
        
}
