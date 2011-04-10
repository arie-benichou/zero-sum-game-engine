
package abstractions.mutation;

import abstractions.cell.old.CellInterface;
import abstractions.piece.PieceInterface;

//TODO utiliser le pattern composite
public interface AtomicMutationInterface {

    CellInterface getConcernedCell();

    AtomicMutationInterface setProtagonist(final PieceInterface concernedPiece);    
    
    PieceInterface getProtagonist();

    void process();
    
    void cancel();

}
