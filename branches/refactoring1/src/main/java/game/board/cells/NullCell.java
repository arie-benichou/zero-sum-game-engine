
package game.board.cells;

import game.board.positions.Positions;

final class NullCell extends AbstractCell {

    public NullCell() {
        super(Positions.NULL_POSITION);
    }

    @Override
    public final boolean isNull() {
        return true;
    }

    @Override
    public String toString() {
        return "";
    }

}