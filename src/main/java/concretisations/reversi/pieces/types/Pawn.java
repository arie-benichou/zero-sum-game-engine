
package concretisations.reversi.pieces.types;

import abstractions.immutable.context.board.BoardInterface;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.board.cell.position.PositionInterface;
import abstractions.immutable.context.board.direction.DirectionInterface;

public final class Pawn implements ReversiPieceTypeInterface {

    private final static Pawn INSTANCE = new Pawn();

    public static Pawn from() {
        return INSTANCE;
    }

    private Pawn() {}

    @Override
    public ReversiPieceTypeInterface apply() {
        return this;
    }

    @Override
    public boolean isConnected(final SideInterface side, final BoardInterface board, final PositionInterface position, final DirectionInterface direction) {
        if (board.cell(position).value().side().equals(side)) return true;
        return ((ReversiPieceTypeInterface) board.cell(position.apply(direction)).value().type().value())
                .isConnected(side, board, position.apply(direction), direction);
    }

    @Override
    public boolean hasApplication(final SideInterface side, final BoardInterface board, final PositionInterface position) {
        return false;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}