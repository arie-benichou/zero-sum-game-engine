
package abstractions.immutable.context.referee;

import java.util.List;

import abstractions.immutable.context.board.BoardInterface;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.immutable.move.type.MoveTypeInterface;

public final class NullReferee implements RefereeInterface {

    private final static RefereeInterface INSTANCE = new NullReferee();

    public static RefereeInterface from() {
        return INSTANCE;
    }

    private NullReferee() {}

    @Override
    public RefereeInterface apply() {
        return this;
    }

    @Override
    public List<MoveTypeInterface> computeMoveTypes(final BoardInterface board, final SideInterface side) {
        // TODO Auto-generated method stub
        return null;
    }

}
