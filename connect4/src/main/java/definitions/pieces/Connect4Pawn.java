
package definitions.pieces;

import context.entity.game.board.BoardInterface;
import context.entity.game.board.cell.BoardCellInterface;
import context.entity.game.board.cell.piece.side.SideInterface;
import context.entity.game.board.cell.position.PositionInterface;
import context.entity.game.board.direction.DirectionInterface;

public final class Connect4Pawn implements Connect4PieceTypeInterface {

    private final static Connect4Pawn INSTANCE = new Connect4Pawn();

    public static Connect4Pawn from() {
        return INSTANCE;
    }

    private Connect4Pawn() {}

    @Override
    public Connect4PieceTypeInterface apply() {
        return this;
    }

    @Override
    public boolean isConnected(final SideInterface side, final BoardInterface board, final PositionInterface position, final DirectionInterface direction) {
        if (board.cell(position).value().side().equals(side)) return true;
        final BoardCellInterface nextCell = board.cell(position.apply(direction));
        if (nextCell.isNull()) return false; // TODO utiliser PotentialReversiPawn et NullReversiPawn
        return ((Connect4PieceTypeInterface) nextCell.value().type().value()).isConnected(side, board, nextCell.position(), direction);
    }

    @Override
    public boolean hasApplication(final SideInterface side, final BoardInterface board, final PositionInterface position) {
        return false;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean isNull() {
        return false;
    }

}