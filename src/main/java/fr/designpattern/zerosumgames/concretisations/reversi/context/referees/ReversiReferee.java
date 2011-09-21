
package fr.designpattern.zerosumgames.concretisations.reversi.context.referees;

import java.util.List;

import com.google.common.collect.Lists;

import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.Piece;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.side.Side;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.piece.type.PieceType;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.position.Position;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.position.PositionInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.referee.RefereeInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveType;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;
import fr.designpattern.zerosumgames.concretisations.reversi.context.moves.ReversiMove;
import fr.designpattern.zerosumgames.concretisations.reversi.context.moves.ReversiNullMove;
import fr.designpattern.zerosumgames.concretisations.reversi.context.pieces.ReversiNullPiece;
import fr.designpattern.zerosumgames.concretisations.reversi.context.pieces.ReversiPawn;
import fr.designpattern.zerosumgames.concretisations.reversi.context.pieces.ReversiPieceTypeInterface;

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

    /*-------------------------------------8<-------------------------------------*/
    // TODO ?? responsabilité du referee
    /*-------------------------------------8<-------------------------------------*/

    @Override
    public final Double getHeuristicEvaluation(final ContextInterface context) {
        final int c1 = context.game().board().count(Piece.from(context.side(), PieceType.from(ReversiPawn.class)));
        final int c2 = context.game().board().count(Piece.from(context.side().opposite(), PieceType.from(ReversiPawn.class)));
        final int c3 = context.game().board().count(Piece.from(Side.NULL, PieceType.from(ReversiNullPiece.class)));
        return (0.0 + c1 - c2) / (c1 + c2 + c3);
    }

    @Override
    public final Double getTerminalEvaluation(final ContextInterface context) {
        final int c1 = context.game().board().count(Piece.from(context.side(), PieceType.from(ReversiPawn.class)));
        final int c2 = context.game().board().count(Piece.from(context.side().opposite(), PieceType.from(ReversiPawn.class)));
        final int c3 = context.game().board().count(Piece.from(Side.NULL, PieceType.from(ReversiNullPiece.class)));
        final Double evaluation = (0.0 + c1 - c2) / (c1 + c2 + c3);
        if (evaluation == 0.0) return c3 / (-10.0 * (c1 + c2 + c3));
        return evaluation;
    }

    /*-------------------------------------8<-------------------------------------*/

}
