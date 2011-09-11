
package abstractions.immutable.context;

import abstractions.immutable.board.cell.piece.side.SideInterface;

public abstract class AbstractSidedContext<T> implements SidedContextInterface<T> {

    private final SideInterface currentSide;

    public AbstractSidedContext(final SideInterface currentSide) {
        this.currentSide = currentSide;
    }

    @Override
    public final SideInterface getSide() {
        return this.currentSide;
    }

    @Override
    public SidedContextInterface<T> apply(final T option) {
        return null;
    }

}
