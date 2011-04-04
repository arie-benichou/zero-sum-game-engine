
package abstractions.mutation;

import abstractions.cell.CellInterface;
import abstractions.piece.PieceInterface;

public interface AtomicMutationInterface {

    CellInterface getConcernedCell();
    
    PieceInterface getPreviousState();    

    void setProtagonist(final PieceInterface concernedPiece);    
    
    PieceInterface getProtagonist();

    void process();

}
