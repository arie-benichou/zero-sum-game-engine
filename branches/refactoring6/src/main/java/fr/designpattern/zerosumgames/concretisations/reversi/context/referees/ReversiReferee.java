
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

    //private final static MoveInterface NULL_MOVE = Move.from(MoveType.from(ReversiNullMove.class), BoardMutation.NULL); // TODO à revoir

    public static RefereeInterface from() {
        return INSTANCE;
    }

    private ReversiReferee() {}

    @Override
    public RefereeInterface apply() {
        return this;
    }

    /*
    @Override
    public List<MoveTypeInterface> playableMoves(final BoardInterface board, final SideInterface side) {
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
    */

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
    public boolean isOver(final ContextInterface context) {
        if (context.history().size() > 1 && context.history().head().isNull() && context.history().tail().head().isNull()) return true;
        return !this.isPlayable(context.game().board(), context.side()) && !this.isPlayable(context.game().board(), context.side().opposite());
    }

    /*-------------------------------------8<-------------------------------------*/
    // TODO ?? responsabilité du referee
    /*-------------------------------------8<-------------------------------------*/

    @Override
    public final Double heuristicEvaluation(final ContextInterface context) {
        final int numberOfPawnsForThisSide = context.game().board().count(Piece.from(context.side(), PieceType.from(ReversiPawn.class)));
        final int numberOfPawnsForOppositeSide = context.game().board().count(Piece.from(context.side().opposite(), PieceType.from(ReversiPawn.class)));
        final int numberOfEmptyCells = context.game().board().count(Piece.from(Side.NULL, PieceType.from(ReversiNullPiece.class)));
        return (0.0 + numberOfPawnsForThisSide - numberOfPawnsForOppositeSide) / (numberOfPawnsForThisSide + numberOfPawnsForOppositeSide + numberOfEmptyCells);
    }

    @Override
    public final Double terminalEvaluation(final ContextInterface context) {
        final Double evaluation = this.heuristicEvaluation(context);
        return evaluation == 0.0 ? context.game().board().count(Piece.from(Side.NULL, PieceType.from(ReversiNullPiece.class)))
                / (-10.0 * context.game().board().numberOfCells()) : evaluation;
    }

    @Override
    public List<MoveTypeInterface> playableMoves(final ContextInterface context) {
        final List<MoveTypeInterface> moveTypes = Lists.newArrayList();
        for (int row = 1; row <= context.game().board().rows(); ++row)
            for (int column = 1; column <= context.game().board().columns(); ++column) {
                final PositionInterface position = Position.from(row, column);
                if (((ReversiPieceTypeInterface) context.game().board().cell(position).value().type().value()).hasApplication(context.side(), context.game()
                        .board(), position))
                    moveTypes.add(MoveType.from(ReversiMove.from(context, position)));
            }
        // TODO utiliser ReversiMove.NULL
        // ?? TODO injecter le contexte également
        moveTypes.add(MoveType.from(ReversiNullMove.from(context)));
        return moveTypes;
    }

    /*-------------------------------------8<-------------------------------------*/

}
