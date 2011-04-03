
package abstractions.cell.mutation;

import abstractions.cell.API.CellInterface;
import abstractions.piece.API.PieceInterface;

public interface AtomicMutationInterface {

    CellInterface getConcernedCell();
    
    PieceInterface getPreviousState();    

    void setProtagonist(final PieceInterface concernedPiece);    
    
    PieceInterface getProtagonist();

    void process();

}
