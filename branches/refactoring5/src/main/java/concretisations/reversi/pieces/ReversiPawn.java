
package concretisations.reversi.pieces;

import abstractions.immutable.context.gameplay.game.board.BoardInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.gameplay.game.board.cell.position.PositionInterface;
import abstractions.immutable.context.gameplay.game.board.direction.DirectionInterface;

public final class ReversiPawn implements ReversiPieceTypeInterface {

    private final static ReversiPawn INSTANCE = new ReversiPawn();

    public static ReversiPawn from() {
        return INSTANCE;
    }

    private ReversiPawn() {}

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