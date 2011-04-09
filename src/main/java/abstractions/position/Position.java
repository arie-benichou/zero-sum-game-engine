
package abstractions.position;

import com.google.common.base.Preconditions;

final class Position implements PositionInterface {

    private final int row;
    private final int column;

    private volatile int hashCode;

    public Position(final int row, final int column) {

        this.row = row;
        this.column = column;

    }

    @Override
    public int getRow() {

        return this.row;

    }

    @Override
    public int getColumn() {

        return this.column;

    }

    @Override
    public boolean isNull() {

        return this.getRow() == 0 || this.getColumn() == 0; 

    }

    @Override
    public int hashCode() {

        int result = this.hashCode;

        if (result == 0) {
            result = new StringBuilder('r').append(this.getRow()).append('c').append(this.getColumn()).toString().hashCode();
            this.hashCode = result;
        }

        return result;
    }

    @Override
    public boolean equals(final Object object) {

        final boolean isEqual;

        if (object == this) {
            isEqual = true;
        }
        else if (object == null) {
            isEqual = false;
        }
        else if (!(object instanceof PositionInterface)) {
            isEqual = false;
        }
        else {
            final PositionInterface that = (PositionInterface) object;
            if (that.hashCode() != this.hashCode()) {
                isEqual = false;
            }
            else {
                isEqual = that.isNull() == this.isNull() && that.getRow() == this.getRow() && that.getColumn() == this.getColumn();
            }
        }

        return isEqual;

    }

    @Override
    public int compareTo(final PositionInterface that) {

        Preconditions.checkNotNull(that, "Argument 'position' is not intended to be null.");

        int value;

        if (this.getRow() < that.getRow()) {
            value = -1;
        }
        else if (this.getRow() > that.getRow()) {
            value = 1;
        }
        else if (this.getColumn() < that.getColumn()) {
            value = -1;
        }
        else if (this.getColumn() > that.getColumn()) {
            value = 1;
        }
        else {
            value = 0;
        }

        return value;

    }

    @Override
    public String toString() {

        return "[row = " + this.getRow() + "][column = " + this.getColumn() + "]";

    }

}