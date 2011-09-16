
package concretisations.reversi.referees;

import java.util.List;

import abstractions.immutable.context.gameplay.game.board.BoardInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.gameplay.game.board.cell.position.Position;
import abstractions.immutable.context.gameplay.game.board.cell.position.PositionInterface;
import abstractions.immutable.context.gameplay.game.referee.RefereeInterface;
import abstractions.immutable.move.type.MoveType;
import abstractions.immutable.move.type.MoveTypeInterface;

import com.google.common.collect.Lists;

import concretisations.reversi.moves.ReversiMove;
import concretisations.reversi.moves.ReversiNullMove;
import concretisations.reversi.pieces.ReversiPieceTypeInterface;

public final class ReversiReferee implements RefereeInterface {

    private final static RefereeInterface INSTANCE = new ReversiReferee();

    public static RefereeInterface from() {
        return INSTANCE;
    }

    private ReversiReferee() {}

    @Override
    public RefereeInterface apply() {
        return this;
    }

    @Override
    public List<MoveTypeInterface> computePlayableMoves(final BoardInterface board, final SideInterface side) {
        final List<MoveTypeInterface> moveTypes = Lists.newArrayList();
        for (int row = 1; row <= board.rows(); ++row)
            for (int column = 1; column <= board.columns(); ++column) {
                final PositionInterface position = Position.from(row, column);
                if (((ReversiPieceTypeInterface) board.cell(position).value().type().value()).hasApplication(side, board, position))
                    moveTypes.add(MoveType.from(ReversiMove.from(position)));
            }
        moveTypes.add(MoveType.from(ReversiNullMove.class)); // TODO utiliser ReversiMove.NULL
        return moveTypes;
    }

    @Override
    public boolean isPlayable(final BoardInterface board, final SideInterface side) {
        boolean moveFound = false;
        for (int row = 1; row <= board.rows() && !moveFound; ++row)
            for (int column = 1; column <= board.columns() && !moveFound; ++column) {
                final PositionInterface position = Position.from(row, column);
                if (((ReversiPieceTypeInterface) board.cell(position).value().type().value()).hasApplication(side, board, position))
                    moveFound = true;
            }
        return moveFound;
    }

    @Override
    public boolean isGamePlayOver(final BoardInterface board, final SideInterface side) {
        return !this.isPlayable(board, side) && !this.isPlayable(board, side.opposite());
    }
}
