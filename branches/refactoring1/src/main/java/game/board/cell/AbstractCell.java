
package game.board.cell;

import static game.board.cell.API.*;
import static game.board.piece.API.*;
import static game.board.position.API.*;

abstract class AbstractCell implements CellInterface {

    private final PositionInterface position;
    private transient PieceInterface piece = NULL_PIECE;

    private volatile int hashCode;

    public AbstractCell(final PositionInterface position) {
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

    public final void setPiece(final PieceInterface piece) {
        this.piece = piece;
    }

    public final PieceInterface getPiece() {
        return this.piece;
    }

    public final boolean isEmpty() {
        return this.getPiece().isNull();
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
            final CellInterface cell = (CellInterface) object;
            if (cell.hashCode() != this.hashCode()) {
                isEqual = false;
            }
            else {
                isEqual = cell.getPiece().equals(this.getPiece());
            }
        }
        return isEqual;
    }

    public final int compareTo(final CellInterface cell) {
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

    public abstract boolean isNull();

    @Override
    public abstract String toString();

}