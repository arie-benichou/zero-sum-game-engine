
package game.cell;

import static game.position.API.*;

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