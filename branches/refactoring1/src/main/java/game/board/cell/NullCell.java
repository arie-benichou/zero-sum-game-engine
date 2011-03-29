
package game.board.cell;

import static game.board.position.API.*;

final class NullCell extends AbstractCell {

    public NullCell() {
        super(NULL_POSITION);
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