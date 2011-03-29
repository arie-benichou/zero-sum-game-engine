
package game.board.cells;

import static game.board.pieces.Pieces.*;
import static game.board.pieces.Pieces.Factory.*;

import game.board.pieces.Pieces;
import game.board.positions.Positions;

abstract class AbstractCell implements Cells.Interface {

    private final Positions.Interface position;
    private transient Pieces.Interface piece = NULL_PIECE;

    private volatile int hashCode;

    public AbstractCell(final Positions.Interface position) {
        this.position = position;
    }

    public final Positions.Interface getPosition() {
        return this.position;
    }

    public int getRow() {
        return this.position.getRow();
    }

    public int getColumn() {
        return this.position.getColumn();
    }

    public final void setPiece(final Pieces.Interface piece) {
        this.piece = piece;
    }

    public final Pieces.Interface getPiece() {
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
        else if (!(object instanceof Cells.Interface)) {
            isEqual = false;
        }
        else {
            final Cells.Interface cell = (Cells.Interface) object;
            if (cell.hashCode() != this.hashCode()) {
                isEqual = false;
            }
            else {
                isEqual = cell.getPiece().equals(this.getPiece());
            }
        }
        return isEqual;
    }

    public final int compareTo(final Cells.Interface cell) {
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