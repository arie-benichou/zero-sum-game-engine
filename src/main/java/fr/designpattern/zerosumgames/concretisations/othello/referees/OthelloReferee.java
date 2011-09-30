
package fr.designpattern.zerosumgames.concretisations.othello.referees;

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
import fr.designpattern.zerosumgames.abstractions.immutable.move.Move;
import fr.designpattern.zerosumgames.abstractions.immutable.move.MoveInterface;
import fr.designpattern.zerosumgames.concretisations.othello.moves.OthelloMove;
import fr.designpattern.zerosumgames.concretisations.othello.moves.OthelloNullMove;
import fr.designpattern.zerosumgames.concretisations.othello.pieces.OthelloNullPiece;
import fr.designpattern.zerosumgames.concretisations.othello.pieces.OthelloPawn;
import fr.designpattern.zerosumgames.concretisations.othello.pieces.OthelloPieceTypeInterface;

public final class OthelloReferee implements RefereeInterface {

    /*-------------------------------------8<-------------------------------------*/

    private final static RefereeInterface INSTANCE = new OthelloReferee();

    public static RefereeInterface from() {
        return INSTANCE;
    }

    private OthelloReferee() {}

    @Override
    public RefereeInterface apply() {
        return this;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public List<MoveInterface> allowedOptions(final ContextInterface context) {
        final List<MoveInterface> moveTypes = Lists.newArrayList();
        for (int row = 1; row <= context.game().board().rows(); ++row)
            for (int column = 1; column <= context.game().board().columns(); ++column) {
                final PositionInterface position = Position.from(row, column);
                if (((OthelloPieceTypeInterface) context.game().board().cell(position).value().type().value()).hasApplication(context.side(), context.game()
                        .board(), position))
                    moveTypes.add(Move.from(OthelloMove.from(context, position)));
            }
        // TODO utiliser ReversiMove.NULL
        // ?? TODO injecter le contexte également
        moveTypes.add(Move.from(OthelloNullMove.from(context)));
        return moveTypes;
    }

    /*-------------------------------------8<-------------------------------------*/

    private boolean isPlayable(final BoardInterface board, final SideInterface side) {
        boolean moveFound = false;
        for (int row = 1; row <= board.rows() && !moveFound; ++row)
            for (int column = 1; column <= board.columns() && !moveFound; ++column) {
                final PositionInterface position = Position.from(row, column);
                if (((OthelloPieceTypeInterface) board.cell(position).value().type().value()).hasApplication(side, board, position))
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
    public final Double estimate(final ContextInterface context) {
        final int numberOfPawnsForThisSide = context.game().board().count(Piece.from(context.side(), PieceType.from(OthelloPawn.class)));
        final int numberOfPawnsForOppositeSide = context.game().board().count(Piece.from(context.side().opposite(), PieceType.from(OthelloPawn.class)));
        final int numberOfEmptyCells = context.game().board().count(Piece.from(Side.NULL, PieceType.from(OthelloNullPiece.class)));
        return (0.0 + numberOfPawnsForThisSide - numberOfPawnsForOppositeSide) / (numberOfPawnsForThisSide + numberOfPawnsForOppositeSide + numberOfEmptyCells);
    }

    @Override
    public final Double evaluate(final ContextInterface context) {
        final Double evaluation = this.estimate(context);
        return evaluation == 0.0 ? context.game().board().count(Piece.from(Side.NULL, PieceType.from(OthelloNullPiece.class)))
                / (-10.0 * context.game().board().numberOfCells()) : evaluation;
    }

    /*-------------------------------------8<-------------------------------------*/

}
