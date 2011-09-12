
package concretisations.reversi;

import java.util.List;

import abstractions.immutable.context.board.BoardInterface;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.board.cell.position.Position;
import abstractions.immutable.context.board.cell.position.PositionInterface;
import abstractions.immutable.context.referee.RefereeInterface;
import abstractions.immutable.move.type.MoveType;
import abstractions.immutable.move.type.MoveTypeInterface;

import com.google.common.collect.Lists;

import concretisations.reversi.moves.types.NewPawn;
import concretisations.reversi.moves.types.ReversiNullMove;
import concretisations.reversi.pieces.types.ReversiPieceTypeInterface;

public final class Referee implements RefereeInterface {

    private final static RefereeInterface INSTANCE = new Referee();

    public static RefereeInterface from() {
        return INSTANCE;
    }

    private Referee() {}

    @Override
    public RefereeInterface apply() {
        return this;
    }

    @Override
    public List<MoveTypeInterface> computeLegalMoves(final BoardInterface board, final SideInterface side) {
        final List<MoveTypeInterface> moveTypes = Lists.newArrayList();
        for (int row = 1; row <= board.rows(); ++row)
            for (int column = 1; column <= board.columns(); ++column) {
                final PositionInterface position = Position.from(row, column);
                if (((ReversiPieceTypeInterface) board.cell(position).value().type().value()).hasApplication(side, board, position))
                    moveTypes.add(MoveType.from(NewPawn.from(position)));
            }
        moveTypes.add(MoveType.from(ReversiNullMove.class));
        return moveTypes;
    }

}
