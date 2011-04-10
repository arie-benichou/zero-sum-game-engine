
package abstractions.cell;

import abstractions.piece.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionInterface;
import abstractions.position.PositionManager.DirectionInterface;
import abstractions.side.SideInterface;

public interface CellManagerInterface extends Iterable<ManagedCellInterface> {

    /**
     * Returns the null cell.
     * 
     * @return the null cell
     */
    ManagedCellInterface getNullCell();

    ManagedCellInterface getCell(final int rowIndex, final int columnIndex);

    ManagedCellInterface getCell(final PositionInterface position);

    // Façade du PieceManager    
    PieceInterface piece(SideInterface side, PieceTypeInterface pieceType);

    // Façade du PositionManager    
    PositionInterface position(final int rowIndex, final int columnIndex);

    // Façade du PositionManager    
    PositionInterface position(final PositionInterface position, final DirectionInterface direction);

}
