
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
import abstractions.selection.HumanMoveSelector;
import abstractions.selection.RandomItemSelector;
import abstractions.side.Sides;
import abstractions.strategy.Strategy;
import abstractions.strategy.StrategyInterface;
import concretisations.othello.pieces.OthelloPieceSet;

public class SmallOthello { // TODO implémenter une GameInterface (inclure l'interface du referee ?)

    public static void main(String[] args) {

        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(4, 4)));
        final PieceManagerInterface pieceManager = new PieceManager(concretisations.othello.pieces.OthelloPieceSet.class);
        final CellManagerInterface cellManager = new CellManager(positionManager, pieceManager);

        //final EvaluationInterface evaluator1 = new MiniMaxEvaluator(7);
        final EvaluationInterface evaluator2 = new NullEvaluator();

        final StrategyInterface strategy1 = new Strategy(evaluator2, new HumanMoveSelector());
        final StrategyInterface strategy2 = new Strategy(evaluator2, new HumanMoveSelector());

        final PlayerInterface player1 = new Player("Player1", strategy1);
        final PlayerInterface player2 = new Player("Player2", strategy2);
        final Adversity adversity = new Adversity(player1, player2);

        final RefereeInterface referee = new OthelloReferee();

        final Context context = new Context(adversity, cellManager, referee);

        //evaluator1.injectContext(context);

        final ContextManager contextManager = new ContextManager(context);

        onStart(cellManager);

        //SideInterface winner = contextManager.play(16);
        contextManager.play();
    }

    private static void onStart(final CellManagerInterface cellManager) {
        cellManager.getCell(2, 2).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(2, 3).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 2).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 3).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
    }

    /*
    private static void onStart(final CellManagerInterface cellManager) {
        cellManager.getCell(3, 3).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 4).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(4, 3).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(4, 4).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
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