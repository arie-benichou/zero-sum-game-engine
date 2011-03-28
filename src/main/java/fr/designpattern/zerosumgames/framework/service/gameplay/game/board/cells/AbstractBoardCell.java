
package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.cells;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.pieces.PieceFactoryInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.pieces.PieceInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions.BoardPositionInterface;

public abstract class AbstractBoardCell implements BoardCellInterface {

    private final BoardPositionInterface position;
    private transient PieceInterface piece = PieceFactoryInterface.NULL_PIECE;

    private volatile int hashCode;

    public AbstractBoardCell(final BoardPositionInterface position) {
        this.position = position;
        //        this.setPiece(new Piece(OpponentsEnumeration.FIRST_PLAYER));
        //        this.setPiece(new Piece(OpponentsEnumeration.SECOND_PLAYER));
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
        else if (!(object instanceof BoardCellInterface)) {
            isEqual = false;
        }
        else {
            final BoardCellInterface cell = (BoardCellInterface) object;
            if (cell.hashCode() != this.hashCode()) {
                isEqual = false;
            }
            else {
                isEqual = cell.getPiece().equals(this.getPiece());
            }
        }
        return isEqual;
    }

    public final int compareTo(final BoardCellInterface cell) {
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