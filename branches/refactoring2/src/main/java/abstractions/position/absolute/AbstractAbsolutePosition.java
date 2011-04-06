
package abstractions.position.absolute;

import static com.google.common.base.Preconditions.checkNotNull;

abstract class AbstractAbsolutePosition implements AbsolutePositionInterface {

    private final int row;
    private final int column;

    private volatile int hashCode;

    public AbstractAbsolutePosition(final int row, final int column) {
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
        else if (!(object instanceof AbsolutePositionInterface)) {
            isEqual = false;
        }
        else {
            final AbsolutePositionInterface that = (AbsolutePositionInterface) object;
            if (that.hashCode() != this.hashCode()) {
                isEqual = false;
            }
            else {
                isEqual = that.isNull() == this.isNull() && that.getRow() == this.getRow() && that.getColumn() == this.getColumn();
            }
        }
        return isEqual;
    }

    public final int compareTo(final AbsolutePositionInterface position) {

        //TODO ajouter aux tests unitaires
        checkNotNull(position, "Argument 'position' is not intended to be null.");

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

    @Override
    public String toString() {
        return "[row = " + this.getRow() + "][column = " + this.getColumn() + "]";
    }

}