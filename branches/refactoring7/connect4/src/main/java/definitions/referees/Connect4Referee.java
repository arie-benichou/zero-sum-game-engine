
package definitions.referees;

import java.util.List;

import com.google.common.collect.Lists;

import context.ContextInterface;
import context.entity.game.board.cell.piece.Piece;
import context.entity.game.board.cell.piece.PieceInterface;
import context.entity.game.board.cell.piece.side.Side;
import context.entity.game.board.cell.piece.type.PieceType;
import context.entity.game.board.cell.position.Position;
import context.entity.game.board.cell.position.PositionInterface;
import context.entity.game.board.direction.Direction;
import context.entity.game.board.direction.DirectionInterface;
import context.entity.game.referee.RefereeInterface;
import context.event.Move;
import context.event.MoveInterface;
import definitions.evaluations.MyStaticEvaluationFunction;
import definitions.moves.Connect4Move;
import definitions.pieces.Connect4NullPiece;

public final class Connect4Referee implements RefereeInterface {

    private final static PieceInterface NULL_PIECE = Piece.from(Side.NULL, PieceType.from(Connect4NullPiece.class));

    /*-------------------------------------8<-------------------------------------*/

    private final static RefereeInterface INSTANCE = new Connect4Referee();

    private final static DirectionInterface[] DIRECTIONS = { Direction.TOP, Direction.LEFT, Direction.TOP_LEFT, Direction.TOP_RIGHT };

    public static RefereeInterface from() {
        return INSTANCE;
    }

    private Connect4Referee() {}

    @Override
    public RefereeInterface apply() {
        return this;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public List<MoveInterface> allowedOptions(final ContextInterface context) {
        final List<MoveInterface> allowedOptions = Lists.newArrayList();
        for (int column = 1; column <= context.game().board().columns(); ++column) {
            if (context.game().board().cell(Position.from(1, column)).isEmpty()) {
                boolean moveFound = false;
                for (int row = 1; row <= context.game().board().rows() && !moveFound; ++row) {
                    final PositionInterface position = Position.from(row, column);
                    if (context.game().board().cell(position).value().type().value().hasApplication(context.side(), context.game().board(), position)) {
                        allowedOptions.add(Move.from(Connect4Move.from(context, position))); // TODO ConcreteMoveInterface
                        moveFound = true;
                    }
                }
            }
        }
        return allowedOptions;
    }

    /*-------------------------------------8<-------------------------------------*/

    private int computeRealConnection(final MoveInterface justPlayedMove, final DirectionInterface direction) {
        int connected;
        PositionInterface position = justPlayedMove.position();
        for (connected = 0; connected < 3; ++connected) {
            position = position.apply(direction);
            if (!justPlayedMove.context().game().board().cell(position).value().side().equals(justPlayedMove.context().side())) break;
        }
        return connected;
    }

    /*
    private boolean isPlayable(final BoardInterface board, final SideInterface side) {
        boolean moveFound = false;
        for (int column = 1; column <= board.columns() && !moveFound; ++column)
            if (board.cell(Position.from(1, column)).isEmpty())
                for (int row = 1; row <= board.rows() && !moveFound; ++row) {
                    final PositionInterface position = Position.from(row, column);
                    if (board.cell(position).value().type().value().hasApplication(side, board, position))
                        moveFound = true;
                }
        return moveFound;
    }
    */

    private boolean isVictory(final ContextInterface context) {
        if (!context.history().isEmpty())
            for (final DirectionInterface direction : DIRECTIONS)
                if (this.computeRealConnection(context.history().head(), direction)
                        + this.computeRealConnection(context.history().head(), direction.opposite()) >= 3) return true;
        return false;
    }

    //TODO board.contains(PieceInterface)

    private boolean isDraw(final ContextInterface context) {
        return context.game().board().contains(NULL_PIECE) ? false : !this.isVictory(context);
    }

    @Override
    public boolean isOver(final ContextInterface context) {
        if (!context.game().board().contains(NULL_PIECE)) return true;
        return this.isVictory(context);
        //if (this.isVictory(context)) return true;
        //return !this.isPlayable(context.game().board(), context.side());
    }

    /*-------------------------------------8<-------------------------------------*/
    // TODO Ces méthodes ne devraient pas faire partie des responsabilité du referee mais de la stratégie du joueur
    // TODO StaticEvaluation et DynamicEvaluation (Evaluation actuelle)
    /*-------------------------------------8<-------------------------------------*/

    // ------------------------------------------------------------
    public int computeRealConnections(final ContextInterface context) {
        int connections = 0;
        for (final DirectionInterface direction : DIRECTIONS)
            connections += this.computeRealConnection(context.history().head(), direction)
                    + this.computeRealConnection(context.history().head(), direction.opposite());
        return connections;
    }

    @Override
    public final Double estimate(final ContextInterface context) {
        /*
        final int numberOfPawnsForThisSide = context.game().board().count(Piece.from(context.side(), PieceType.from(Connect4Pawn.class)));
        final int numberOfPawnsForOppositeSide = context.game().board().count(Piece.from(context.side().opposite(), PieceType.from(Connect4Pawn.class)));
        final int numberOfEmptyCells = context.game().board().count(Piece.from(Side.NULL, PieceType.from(Connect4NullPiece.class)));
        return (0.0 + numberOfPawnsForThisSide - numberOfPawnsForOppositeSide) / (numberOfPawnsForThisSide + numberOfPawnsForOppositeSide + numberOfEmptyCells);
        */

        /*
        Double heuristicEvaluation = 0.0;
        for (final BoardCellInterface cell : context.game().board().neighbourhoodOf(context.history().head().position()).values())
            if (!cell.isNull()) {
                if (cell.isEmpty()) heuristicEvaluation += 0.5 / 8;
                else if (cell.value().side().equals(context.side())) heuristicEvaluation += 1.0 / 8;
                else heuristicEvaluation += -0.5 / 8;
            }
        return heuristicEvaluation;
        */

        //return (0.0 + this.computeRealConnections(context)) / (3 * 7);

        //return 0.0;

        final int maximalPotential = 698; //TODO lazy init
        final Double e1 = MyStaticEvaluationFunction.from(context);
        final Double e2 = MyStaticEvaluationFunction.from(context.apply(context.side().opposite()));
        return (e1 - e2) / maximalPotential;

    }

    @Override
    public final Double evaluate(final ContextInterface context) {// TODO retourner -1, 0 ou 1
        /*
        final Double evaluation = this.estimate(context);
        return evaluation == 0.0 ? context.game().board().count(Piece.from(Side.NULL, PieceType.from(Connect4NullPiece.class)))
                / (-10.0 * context.game().board().numberOfCells()) : evaluation;
        
        */

        /*
        if (this.isVictory(context)) return 1.0;
        return 0.0;
        */

        //return this.isDraw(context) ? 0.0 + this.estimate(context) : 1.0 + this.estimate(context);
        return (this.isDraw(context) ? 0.0 : 1.0) + this.estimate(context) / 10000;

        //return this.isDraw(context) ? this.estimate(context) / 100 : this.estimate(context) * 1000; // TODO à améliorer

        //return this.isDraw(context) ? 0.0 : context.side().equals(context.history().head().context().side()) ? 1.0 : -1.0;

        //return context.side().equals(context.history().head().context().side()) ? 1.0 : -1.0;

    }
    /*-------------------------------------8<-------------------------------------*/

}
