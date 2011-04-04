
package abstractions.cell;

import static abstractions.piece.API.NULL_PIECE;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import abstractions.board.API.BoardInterface;
import abstractions.cell.API.CellInterface;
import abstractions.cell.mutation.MutationInterface;
import abstractions.piece.API.PieceInterface;
import abstractions.position.API.PositionInterface;
import abstractions.position.RelativePosition;
import abstractions.side.API.SideInterface;

abstract class AbstractCell implements CellInterface {

    protected boolean willGenerateMutations = false;

    private final PositionInterface position;
    protected transient PieceInterface piece = NULL_PIECE;

    private volatile int hashCode;

    public AbstractCell(final PositionInterface position) {
        checkNotNull(position, "Argument 'postion' is not intended to be null.");
        this.position = position;
    }

    public final PositionInterface getPosition() {
        return this.position;
    }

    public int getRow() {
        return this.position.getRow();
    }

    public int getColumn() {
        return this.position.getColumn();
    }

    public final PieceInterface getPiece() {
        return this.piece;
    }
    
    // TODO ? injecter un contexte
    public final Set<MutationInterface> fetchAvailableMutations(final SideInterface side) {
        return this.getPiece().computeAvailableMutations(this, side);
    }    

    @Override
    public int hashCode() {
        int result = this.hashCode;
        if (result == 0) {
            result = 17;
            result *= 31;
            result += this.isNull() ? 0 : 1;
            result *= 31;
            result += this.position.hashCode();
            this.hashCode = result;
        }
        return result;
    }

    @Override
    public final boolean equals(final Object object) {
        final boolean isEqual;
        if (object == this) {
            isEqual = true;
        }
        else if (object == null) {
            isEqual = false;
        }
        else if (!(object instanceof CellInterface)) {
            isEqual = false;
        }
        else {
            final CellInterface that = (CellInterface) object;
            if (that.hashCode() != this.hashCode()) {
                isEqual = false;
            }
            else {
                isEqual = that.getPiece().equals(this.getPiece());
            }
        }
        return isEqual;
    }

    public final int compareTo(final CellInterface cell) {
        checkNotNull(cell, "Argument 'cell' is not intended to be null.");
        if (this.getRow() < cell.getRow()) {
            return -1;
        }
        if (this.getRow() > cell.getRow()) {
            return 1;
        }
        if (this.getColumn() < cell.getColumn()) {
            return -1;
        }
        if (this.getColumn() > cell.getColumn()) {
            return 1;
        }
        return 0;
    }

    public abstract void willGenerateMutations(boolean willItGenerateMutations);

    public abstract void setBoard(BoardInterface board);

    public abstract boolean isNull();

    public abstract boolean isEmpty();

    public abstract boolean willGenerateMutations();
    
    public abstract CellInterface getRelative(RelativePosition relativePosition);
    
    public abstract void setPiece(PieceInterface piece);
    
    public abstract String toString();

}