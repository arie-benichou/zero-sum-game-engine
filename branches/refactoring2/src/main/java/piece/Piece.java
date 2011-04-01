
package piece;

import static side.API.*;

public class Piece extends AbstractPiece {

    public Piece(final SideInterface side) {
        super(side);
        if (!side.isOneSide()) {
            throw new IllegalArgumentException("Argument 'side' is not intended to be a null side object");
        }
    }

    @Override
    public final boolean isNull() {
        return false;
    }

    @Override
    public String toString() {
        String consoleView;
        if(this.getSide().isFirstSide()) {
            consoleView = "x";
        }
        else {
            consoleView = "o";
        }
        return consoleView;
    }

}