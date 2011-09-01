
package concretisations.othello;

import abstractions.adversity.Adversity;
import abstractions.cell.CellManager;
import abstractions.cell.CellManagerInterface;
import abstractions.context.Context;
import abstractions.context.ContextManager;
import abstractions.dimension.DimensionManager;
import abstractions.direction.DirectionManager;
import abstractions.evaluation.EvaluationInterface;
import abstractions.evaluation.MiniMaxWithAlphaBetaEvaluator;
import abstractions.evaluation.NullEvaluator;
import abstractions.piece.PieceManager;
import abstractions.piece.PieceManagerInterface;
import abstractions.player.Player;
import abstractions.player.PlayerInterface;
import abstractions.position.PositionManager;
import abstractions.position.PositionManagerInterface;
import abstractions.referee.RefereeInterface;
import abstractions.selection.FirstItemSelector;
import abstractions.side.Sides;
import abstractions.strategy.Strategy;
import abstractions.strategy.StrategyInterface;
import concretisations.othello.pieces.OthelloPieceSet;

public class Othello { // TODO implémenter une GameInterface (inclure l'interface du referee ?)

    public static void main(final String[] args) {

        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(8, 8)));
        final PieceManagerInterface pieceManager = new PieceManager(concretisations.othello.pieces.OthelloPieceSet.class);
        final CellManagerInterface cellManager = new CellManager(positionManager, pieceManager);

        final EvaluationInterface evaluator1 = new MiniMaxWithAlphaBetaEvaluator(10);
        //final EvaluationInterface evaluator1 = new NegaMaxWithAlphaBetaEvaluator(8);
        final EvaluationInterface evaluator2 = new NullEvaluator();

        final StrategyInterface strategy1 = new Strategy(evaluator1, new FirstItemSelector());
        final StrategyInterface strategy2 = new Strategy(evaluator2, new FirstItemSelector());

        final PlayerInterface player1 = new Player("Player1", strategy1);
        final PlayerInterface player2 = new Player("Player2", strategy2);
        final Adversity adversity = new Adversity(player1, player2);

        final RefereeInterface referee = new OthelloReferee();

        final Context context = new Context(adversity, cellManager, referee);

        evaluator1.injectContext(context);
        evaluator2.injectContext(context);

        final ContextManager contextManager = new ContextManager(context);

        Othello.onStart(cellManager);

        contextManager.play();

        System.out.println(context.getHeuristicEvaluation(Sides.FIRST));
        System.out.println(context.getHeuristicEvaluation(Sides.SECOND));
    }

    private static void onStart(final CellManagerInterface cellManager) {
        cellManager.getCell(4, 4).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(4, 5).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(5, 4).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(5, 5).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
    }

}
