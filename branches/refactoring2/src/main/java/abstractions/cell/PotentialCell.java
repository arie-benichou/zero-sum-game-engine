
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

abstract class PotentialCell implements CellInterface {

    protected boolean willGenerateMutations = false;

    private final PositionInterface position;
    protected transient PieceInterface piece = NULL_PIECE;

    private volatile int hashCode;

    public PotentialCell(final PositionInterface position) {
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
    
    public Set<MutationInterface> fetchAvailableMutations(SideInterface side) { // TODO ? injecter un contexte
        // TODO utiliser l'objet null d'une piece
        // TODO ? renommer en getAvailableChainedMutations
        // TODO ? avoir une cellule universelle
        PieceInterface piece = this.getPiece();
        Set<MutationInterface> availableMutations = piece.computeAvailableMutations(this, side);
        //System.out.println("Available mutations for cell [" + this.getRow() + "][" + this.getColumn() + "]");
        //System.out.println(availableMutations);
        
        //availableMutations = this.filterAvailableMutations(availableMutations);
        
        //this.isMutable = !availableMutations.isEmpty();
        
        return availableMutations;
    }

    //public abstract Set<MutationInterface> filterAvailableMutations(Set<MutationInterface> availableMutations);


    public abstract void willGenerateMutations(boolean willItGenerateMutations);

    public abstract void setBoard(BoardInterface board);

    public abstract boolean isNull();

    public abstract boolean isEmpty();

    public abstract boolean willGenerateMutations();

    //TODO à virer
    public abstract CellInterface getNext(int rowDelta, int columnDelta);
    
    public abstract CellInterface getRelative(RelativePosition relativePosition);
    
    public abstract void setPiece(PieceInterface piece);
    
    @Override
    public abstract String toString();    

}