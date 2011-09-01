
package concretisations.checkers;

import abstractions.adversity.Adversity;
import abstractions.cell.CellManager;
import abstractions.cell.CellManagerInterface;
import abstractions.context.Context;
import abstractions.context.ContextManager;
import abstractions.dimension.DimensionManager;
import abstractions.direction.DirectionManager;
import abstractions.evaluator.EvaluatorInterface;
import abstractions.evaluator.NullEvaluator;
import abstractions.piece.PieceManager;
import abstractions.piece.PieceManagerInterface;
import abstractions.player.Player;
import abstractions.player.PlayerInterface;
import abstractions.position.PositionManager;
import abstractions.position.PositionManagerInterface;
import abstractions.referee.RefereeInterface;
import abstractions.selector.FirstItem;
import abstractions.selector.Random;
import abstractions.side.Sides;
import abstractions.strategy.Strategy;
import abstractions.strategy.StrategyInterface;
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
        contextManager.play();

    }

    private static void onStart(final CellManagerInterface cellManager) {
        for (int rowIndex = 1; rowIndex <= 3; ++rowIndex)
            for (int n = 1; n <= 4; ++n) {
                final int columnIndex = 2 * n + rowIndex % 2 - 1;
                cellManager.getCell(rowIndex, columnIndex).setPiece(Sides.SECOND, CheckersPieceSet.MAN);
            }
        for (int rowIndex = 6; rowIndex <= 8; ++rowIndex)
            for (int n = 1; n <= 4; ++n) {
                final int columnIndex = 2 * n + rowIndex % 2 - 1;
                cellManager.getCell(rowIndex, columnIndex).setPiece(Sides.FIRST, CheckersPieceSet.MAN);
            }
    }

}
