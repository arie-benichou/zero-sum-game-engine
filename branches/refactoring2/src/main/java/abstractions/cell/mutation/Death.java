
package abstractions.cell.mutation;

import abstractions.cell.API.CellInterface;

public class Death extends AbstractAtomicMutation {

    public Death(CellInterface concernedCell) {
        super(concernedCell);
    }

    @Override
    public void process() {
        this.getConcernedCell().setPiece(abstractions.piece.API.NULL_PIECE);
    }

    

}
