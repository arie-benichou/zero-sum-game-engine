
package abstractions.mutation;

import abstractions.cell.CellInterface;

public class Death extends AbstractAtomicMutation {

    public Death(CellInterface concernedCell) {
        super(concernedCell);
    }

    @Override
    public void process() {
        // TODO utiliser la factory de pieces
        
        this.getConcernedCell().die();
        
        //this.getConcernedCell().setPiece();
    }

    

}
