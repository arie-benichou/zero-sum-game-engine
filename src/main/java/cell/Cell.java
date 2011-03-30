
package cell;

import static position.API.*;

final class Cell extends AbstractCell {

    public Cell(final PositionInterface position) {
        super(position);
        if (position.isNull()) {
            throw new IllegalArgumentException("Argument 'position' must be a legal position");
        }
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String toString() {
        //return "[row: " + this.getRow() + "][column: " + this.getColumn() + "]";
        return " " + this.getPiece() + " |";

    }
}