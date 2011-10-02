
package definitions.pieces;

import context.entity.game.board.BoardInterface;
import context.entity.game.board.cell.BoardCellInterface;
import context.entity.game.board.cell.piece.side.SideInterface;
import context.entity.game.board.cell.position.PositionInterface;
import context.entity.game.board.direction.Direction;
import context.entity.game.board.direction.DirectionInterface;

public final class Connect4NullPiece implements Connect4PieceTypeInterface {

    private final static Connect4NullPiece INSTANCE = new Connect4NullPiece();

    public static Connect4NullPiece from() {
        return INSTANCE;
    }

    private Connect4NullPiece() {}

    @Override
    public Connect4PieceTypeInterface apply() {
        return this;
    }

    @Override
    public boolean isConnected(final SideInterface side, final BoardInterface board, final PositionInterface position, final DirectionInterface direction) {
        return false;
    }

    @Override
    public boolean hasApplication(final SideInterface side, final BoardInterface board, final PositionInterface position) {
        final BoardCellInterface bottomCell = board.cell(position.apply(Direction.BOTTOM));
        return !bottomCell.isEmpty() || bottomCell.isNull();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean isNull() {
        return true;
    }

}