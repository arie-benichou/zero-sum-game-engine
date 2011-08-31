
package concretisations.othello;

import abstractions.adversity.Adversity;
import abstractions.cell.CellManager;
import abstractions.cell.CellManagerInterface;
import abstractions.context.Context;
import abstractions.context.ContextManager;
import abstractions.dimension.DimensionManager;
import abstractions.direction.DirectionManager;
import abstractions.evaluation.EvaluationInterface;
import abstractions.evaluation.MiniMaxEvaluator;
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

public class SmallOthello { // TODO implémenter une GameInterface (inclure l'interface du referee ?)

    public static void main(final String[] args) {

        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(6, 6)));
        final PieceManagerInterface pieceManager = new PieceManager(concretisations.othello.pieces.OthelloPieceSet.class);
        final CellManagerInterface cellManager = new CellManager(positionManager, pieceManager);

        final EvaluationInterface evaluator1 = new NullEvaluator();
        //final EvaluationInterface evaluator2 = new MiniMaxEvaluator(11);
        final EvaluationInterface evaluator2 = new MiniMaxEvaluator(5);

        final StrategyInterface strategy1 = new Strategy(evaluator1, new FirstItemSelector());
        //final StrategyInterface strategy2 = new Strategy(evaluator2, new RandomItemSelector());
        final StrategyInterface strategy2 = new Strategy(evaluator2, new FirstItemSelector());

        final PlayerInterface player1 = new Player("Player1", strategy1);
        final PlayerInterface player2 = new Player("Player2", strategy2);
        final Adversity adversity = new Adversity(player1, player2);

        final RefereeInterface referee = new OthelloReferee();

        final Context context = new Context(adversity, cellManager, referee);

        evaluator2.injectContext(context);

        final ContextManager contextManager = new ContextManager(context);

        SmallOthello.onStart(cellManager);

        //SideInterface winner = contextManager.play(16);
        contextManager.play();

        System.out.println(context.getHeuristicEvaluation(Sides.FIRST));
        System.out.println(context.getHeuristicEvaluation(Sides.SECOND));

    }

    /*
    private static void onStart(final CellManagerInterface cellManager) {
        cellManager.getCell(2, 2).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(2, 3).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 2).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 3).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
    }
    */

    private static void onStart(final CellManagerInterface cellManager) {
        cellManager.getCell(3, 3).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 4).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(4, 3).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(4, 4).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
    }

    /*
    -------------------------
    |   |   |   |   |   | o |
    -------------------------
    |   |   |   |   | o |   |
    -------------------------
    | x | x | x | x | o |   |
    -------------------------
    |   |   | x | o | o |   |
    -------------------------
    |   |   | o |   | o |   |
    -------------------------
    |   | o |   |   |   |   |
    -------------------------
    */
    /*
    private static void onStart(final CellManagerInterface cellManager) {

        cellManager.getCell(1, 6).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);

        cellManager.getCell(2, 5).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);

        cellManager.getCell(3, 1).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 2).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 3).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 4).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 5).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);

        cellManager.getCell(4, 3).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(4, 4).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(4, 5).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);

        cellManager.getCell(5, 3).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(5, 5).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);

        cellManager.getCell(6, 2).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
    }
    */

    /*
    -------------------------
    |   |   | o | o | o | o |
    -------------------------
    |   |   |   | o | o | o |
    -------------------------
    |   | o | x | x | o | o |
    -------------------------
    |   |   | x | x | o | o |
    -------------------------
    |   | x | x |   | o | o |
    -------------------------
    | x | o | o | o | o | x |
    -------------------------    
    */

    /*
    private static void onStart(final CellManagerInterface cellManager) {

        cellManager.getCell(1, 3).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(1, 4).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(1, 5).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(1, 6).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);

        cellManager.getCell(2, 4).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(2, 5).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(2, 6).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);

        //cellManager.getCell(3, 1).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        //cellManager.getCell(3, 2).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 2).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 3).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 4).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 5).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 6).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);

        cellManager.getCell(4, 3).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(4, 4).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(4, 5).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(4, 6).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);

        cellManager.getCell(5, 2).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(5, 3).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(5, 5).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(5, 6).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);

        cellManager.getCell(6, 1).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(6, 2).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(6, 3).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(6, 4).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(6, 5).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(6, 6).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);

    }
    */

    /*
    -----------------
    |   | o | o | o |
    -----------------
    |   | o | o | o |
    -----------------
    | x | o | x | o |
    -----------------
    | o | o | o | x |
    -----------------
    */

    /*
    private static void _onStart(final CellManagerInterface cellManager) {
        cellManager.getCell(1, 2).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(1, 3).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(1, 4).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);

        cellManager.getCell(2, 2).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(2, 3).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(2, 4).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);

        cellManager.getCell(3, 1).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 2).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 3).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 4).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);

        cellManager.getCell(4, 1).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(4, 2).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(4, 3).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(4, 4).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
    }
    */

}