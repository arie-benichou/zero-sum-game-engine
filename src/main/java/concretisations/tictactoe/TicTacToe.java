
package concretisations.tictactoe;

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
import abstractions.side.SideInterface;
import abstractions.strategy.Strategy;
import abstractions.strategy.StrategyInterface;

public class TicTacToe {

    public static void main(String[] args) {

        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(3, 3)));
        final PieceManagerInterface pieceManager = new PieceManager(concretisations.tictactoe.pieces.TicTacToePieceSet.class);
        final CellManagerInterface cellManager = new CellManager(positionManager, pieceManager);

        //final EvaluationInterface evaluator1 = new MiniMaxEvaluator(9);
        final EvaluationInterface evaluator2 = new NullEvaluator();

        final StrategyInterface strategy1 = new Strategy(evaluator2, new RandomItemSelector());
        final StrategyInterface strategy2 = new Strategy(evaluator2, new RandomItemSelector());

        final PlayerInterface player1 = new Player("Player1", strategy1);
        final PlayerInterface player2 = new Player("Player2", strategy2);
        final Adversity adversity = new Adversity(player1, player2);

        final RefereeInterface referee = new TicTacToeReferee();

        final Context context = new Context(adversity, cellManager, referee);

        //evaluator1.injectContext(context);

        final ContextManager contextManager = new ContextManager(context);

        onStart(cellManager);

        contextManager.play();
        //System.out.println("\nAnd the winner is: " + winner);

    }

    private static void onStart(final CellManagerInterface cellManager) {}

}
