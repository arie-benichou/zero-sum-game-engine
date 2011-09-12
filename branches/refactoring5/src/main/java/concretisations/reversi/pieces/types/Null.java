
package concretisations.reversi.pieces.types;

import abstractions.immutable.context.board.BoardInterface;
import abstractions.immutable.context.board.cell.BoardCell;
import abstractions.immutable.context.board.cell.BoardCellInterface;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.board.cell.position.PositionInterface;
import abstractions.immutable.context.board.direction.Direction;
import abstractions.immutable.context.board.direction.DirectionInterface;

public final class Null implements ReversiPieceTypeInterface {

    private final static Null INSTANCE = new Null();

    public static Null from() {
        return INSTANCE;
    }

    private Null() {}

    @Override
    public ReversiPieceTypeInterface apply() {
        return this;
    }

    @Override
    public boolean isConnected(final SideInterface side, final BoardInterface board, final PositionInterface position, final DirectionInterface direction) {
        return false;
    }

    @Override
    public boolean hasApplication(final SideInterface side, final BoardInterface board, final PositionInterface position) {
        for (final DirectionInterface direction : Direction.ALL_AROUND) {
            final BoardCellInterface nextCell = board.cell(position.apply(direction));
            if (!nextCell.equals(BoardCell.NULL) && side.opposite().equals(nextCell.value().side())
                    && ((ReversiPieceTypeInterface) board.cell(nextCell.position().apply(direction)).value().type().value())
                            .isConnected(side, board, nextCell.position().apply(direction), direction))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}