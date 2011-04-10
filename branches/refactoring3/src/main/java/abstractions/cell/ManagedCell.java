
package abstractions.cell;

import abstractions.piece.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionInterface;
import abstractions.position.PositionManager.Direction;
import abstractions.side.SideInterface;

public class ManagedCell implements ManagedCellInterface {

    private final PositionInterface position;
    private final CellManagerInterface cellManager;
    private PieceInterface piece;

    public ManagedCell(final CellManagerInterface cellManager, final PositionInterface position) {
        this.cellManager = cellManager;
        this.position = position;
    }

    @Override
    public PositionInterface getPosition() {
        return this.position;
    }

    @Override
    public int getRow() {
        return this.position.getRow();
    }

    @Override
    public int getColumn() {
        return this.position.getColumn();
    }

    @Override
    public PieceInterface getPiece() {
        return this.piece;
    }

    @Override
    public ManagedCellInterface setPiece(final PieceInterface piece) {
        this.piece = piece;
        return this;
    }

    @Override
    public ManagedCellInterface setPiece(final SideInterface side, final PieceTypeInterface pieceType) {
        return this.setPiece(this.cellManager.piece(side, pieceType));
    }

    @Override
    public boolean isNull() {
        return this.position.isNull();
    }

    @Override
    public boolean isEmpty() {
        return !this.isNull() && this.piece.getSide().isNull();
    }

    @Override
    public ManagedCellInterface getRelative(final Direction direction) {
        return this.cellManager.getCell(this.cellManager.position(this.position, direction));
    }

    @Override
    public int compareTo(final ManagedCellInterface that) {
        return this.position.compareTo(that.getPosition());
    }

    @Override
    public String toString() {
        return " " + this.getPiece() + " |";
    }

    @Override
    public int hashCode() {
        return this.position.hashCode();
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
        else if (!(object instanceof ManagedCellInterface)) {
            isEqual = false;
        }
        else {
            final ManagedCellInterface that = (ManagedCellInterface) object;
            isEqual = that.isNull() == this.isNull() && that.getPiece().equals(this.getPiece());
        }
        return isEqual;
    }
}
