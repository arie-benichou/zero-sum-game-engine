
package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.positions;

public abstract class AbstractBoardPosition implements BoardPositionInterface {

    private final int row;
    private final int column;

    private volatile int hashCode;

    public AbstractBoardPosition(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

    public final int getRow() {
        return this.row;
    }

    public final int getColumn() {
        return this.column;
    }

    public abstract boolean isNull();

    @Override
    public String toString() {
        return "[row = " + this.getRow() + "][column = " + this.getColumn() + "]";
    }

    @Override
    public int hashCode() {
        int result = this.hashCode;
        if (result == 0) {
            result = 17;

            result *= 31;
            result += this.isNull() ? 0 : 1;

            result *= 31;
            result += this.getRow();

            result *= 31;
            result += this.getColumn();

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
        else if (!(object instanceof BoardPositionInterface)) {
            isEqual = false;
        }
        else {
            final BoardPositionInterface position = (BoardPositionInterface) object;
            if (position.hashCode() != this.hashCode()) {
                isEqual = false;
            }
            else {
                isEqual = position.isNull() == this.isNull() && position.getRow() == this.getRow() && position.getColumn() == this.getColumn();
            }
        }
        return isEqual;
    }

    public final int compareTo(final BoardPositionInterface position) {
        if (this.getRow() < position.getRow()) {
            return -1;
        }
        if (this.getRow() > position.getRow()) {
            return 1;
        }
        if (this.getColumn() < position.getColumn()) {
            return -1;
        }
        if (this.getColumn() > position.getColumn()) {
            return 1;
        }
        return 0;
    }

}