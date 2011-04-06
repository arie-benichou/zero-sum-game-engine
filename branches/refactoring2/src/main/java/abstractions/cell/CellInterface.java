package abstractions.cell;

import java.util.Set;

import abstractions.board.BoardInterface;
import abstractions.mutation.MutationInterface;
import abstractions.piece.PieceFactory;
import abstractions.piece.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.absolute.AbsolutePositionInterface;
import abstractions.position.relative.RelativePositionInterface;
import abstractions.side.SideInterface;


/**
 * This is the interface for a cell.
 */
public interface CellInterface extends Comparable<CellInterface> {
    
    public final static CellInterface NULL_CELL = new NullCell();
    
    /**
     * Returns the position of this cell.
     * 
     * @return the position of this cell
     */
    AbsolutePositionInterface getPosition();

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
     * Returns true if this cell is empty.
     * 
     * @return true if this cell is empty
     */
    boolean isEmpty();

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
    void setPiece(final PieceInterface piece);
    //TODO ! injecter la factory de pieces au board
    void setPiece(SideInterface side, PieceTypeInterface pieceType);

    /**
     * Returns true if this cell is the null object, false otherwise.
     * 
     * @return true if this cell is the null object, false otherwise
     */
    //boolean isNull();

    //TODO ! javadoc        
    void setBoard(BoardInterface board);
    
    PieceFactory getPieceFactory();

    //TODO ! javadoc
    //TODO ? utiliser un facteur (times)    
    CellInterface getRelative(RelativePositionInterface relativePosition);

    //TODO ! javadoc
    Set<? extends MutationInterface> fetchAvailableMutations(SideInterface side);
    
    //TODO ! javadoc
    void willGenerateMutations(boolean willItGenerateMutations);
    
    /**
     * Returns true if this cell has at least one legal mutation for a side to play.
     * 
     * @return true if this cell has at least one legal mutation for a side to play
     */
    boolean willGenerateMutations();

}