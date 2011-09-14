
package abstractions.immutable.move.type;

import abstractions.immutable.context.board.BoardInterface;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.board.cell.position.PositionInterface;
import abstractions.immutable.move.ConcreteMoveTypeInterface;
import abstractions.immutable.move.mutation.BoardMutationInterface;

public final class _NewPawn implements ConcreteMoveTypeInterface {

    /*-------------------------------------8<-------------------------------------*/

    private final static _NewPawn INSTANCE = new _NewPawn();

    /*-------------------------------------8<-------------------------------------*/

    public static _NewPawn from() {
        return INSTANCE;
    }

    private _NewPawn() {}

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public _NewPawn apply() {
        return this;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public PositionInterface position() {
        return null;
    }

    @Override
    public ConcreteMoveTypeInterface apply(final PositionInterface position) {
        return this;
    }

    @Override
    public BoardMutationInterface computeBoardMutation(final SideInterface side, final BoardInterface board) {
        return null;
    }

    /*-------------------------------------8<-------------------------------------*/

}