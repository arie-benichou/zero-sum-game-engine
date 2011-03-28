
package game.board.cells;

import game.board.positions.Positions;

final class Cell extends AbstractCell {

    public Cell(final Positions.Interface position) {
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