
package abstractions.piece;

import abstractions.side.SideInterface;

// TODO ? transformer cette classe en classe Pawn, la pièce de base est le pion
public abstract class AbstractPiece extends PotentialPiece {

    public AbstractPiece(final SideInterface side) {
        super(side);
        /*
        if (!side.isOneSide()) {
            throw new IllegalArgumentException("Argument 'side' is not intended to be a null side object");
        }
        */
    }

    @Override
    public final boolean isNull() {
        //return false;
        return this.getSide().isNull();
    }

    @Override
    public String toString() {
        String consoleView;
        if (this.getSide().isFirstSide()) {
            consoleView = "x";
        }
        else if (this.getSide().isSecondSide()) {
            consoleView = "o";
        }
        else {
            consoleView = " ";
        }
        return consoleView;
    }

}