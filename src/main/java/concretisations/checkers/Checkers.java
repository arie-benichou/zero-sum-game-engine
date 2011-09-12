
package concretisations.checkers;

import abstractions.dimension.DimensionManager;
import abstractions.direction.DirectionManager;
import abstractions.immutable.context.adversity.Adversity;
import abstractions.immutable.context.adversity.player.Player;
import abstractions.immutable.context.adversity.player.PlayerInterface;
import abstractions.immutable.context.board.cell.piece.PieceManager;
import abstractions.immutable.context.board.cell.piece.PieceManagerInterface;
import abstractions.immutable.context.board.cell.piece.side.Side;
import abstractions.immutable.context.referee.RefereeInterface;
import abstractions.old.cell.CellManager;
import abstractions.old.cell.CellManagerInterface;
import abstractions.old.context.Context;
import abstractions.old.context.ContextManager;
import abstractions.old.evaluator.EvaluatorInterface;
import abstractions.old.evaluator.NullEvaluator;
import abstractions.old.position.PositionManager;
import abstractions.old.position.PositionManagerInterface;
import abstractions.old.selector.FirstItem;
import abstractions.old.selector.Random;
import abstractions.old.strategy.Strategy;
import abstractions.old.strategy.StrategyInterface;
import concretisations.checkers.pieces.CheckersPieceSet;

public class Checkers {

    public static void main(String[] args) {

        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(8, 8)));
        final PieceManagerInterface pieceManager = new PieceManager(concretisations.checkers.pieces.CheckersPieceSet.class);
        final CellManagerInterface cellManager = new CellManager(positionManager, pieceManager);

        final EvaluatorInterface evaluator = new NullEvaluator();

        final StrategyInterface strategy1 = new Strategy(evaluator, new Random());
        final StrategyInterface strategy2 = new Strategy(evaluator, new Random());

        final PlayerInterface player1 = new Player("Player1", strategy1);
        final PlayerInterface player2 = new Player("Player2", strategy2);
        final Adversity adversity = new Adversity(player1, player2);

        final RefereeInterface referee = new CheckersReferee();

        final Context context = new Context(adversity, cellManager, referee);

        final ContextManager contextManager = new ContextManager(context);

        onStart(cellManager);
        contextManager.startGamePlay();

    }

    private static void onStart(final CellManagerInterface cellManager) {
        for (int rowIndex = 1; rowIndex <= 3; ++rowIndex)
            for (int n = 1; n <= 4; ++n) {
                final int columnIndex = 2 * n + rowIndex % 2 - 1;
                cellManager.getCell(rowIndex, columnIndex).setPiece(Side.SECOND, CheckersPieceSet.MAN);
            }
        for (int rowIndex = 6; rowIndex <= 8; ++rowIndex)
            for (int n = 1; n <= 4; ++n) {
                final int columnIndex = 2 * n + rowIndex % 2 - 1;
                cellManager.getCell(rowIndex, columnIndex).setPiece(Side.FIRST, CheckersPieceSet.MAN);
            }
    }

}
