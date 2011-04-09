
package abstractions.cell;

import abstractions.piece.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionInterface;
import abstractions.side.SideInterface;

/**
 * This is the interface for a cell.
 */
public interface ManagedCellInterface extends Comparable<ManagedCellInterface> {

    /**
     * Returns the position of this cell.
     * 
     * @return the position of this cell
     */
    PositionInterface getPosition();

    /**
     * Returns the row for this cell.
     * 
     * @return the row for this cell
     */
    int getRow();

    /**
     * Returns the column for this cell.
     * 
     * @return the column for this cell
     */
    int getColumn();

    /**
     * Returns the piece contained by this cell.
     * 
     * @return the piece contained by this cell
     */
    PieceInterface getPiece();

    /**
     * Assigns a piece to this cell.
     * 
     * @param piece
     *            the piece to be contained by this cell
     */
    ManagedCellInterface setPiece(final PieceInterface piece);

    ManagedCellInterface setPiece(SideInterface side, PieceTypeInterface pieceType);

    /**
     * Returns true if this cell is the null object, false otherwise.
     * 
     * @return true if this cell is the null object, false otherwise
     */
    boolean isNull();

    boolean isEmpty();

}