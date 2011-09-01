
package concretisations.tictactoe;

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
import abstractions.selector.Random;
import abstractions.strategy.Strategy;
import abstractions.strategy.StrategyInterface;

// TODO fusionner side et adversity et faire AdversityManager
// TODO injecter l'adversitymanager au piecemanager (jeu à une seule side nulle:
// free them all : sleepy)
// TODO ! builders
// TODO Loop du ContextManager -> service + thread
public class TicTacToe {

    public static void main(final String[] args) {

        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(3, 3)));
        final PieceManagerInterface pieceManager = new PieceManager(concretisations.tictactoe.pieces.TicTacToePieceSet.class);
        final CellManagerInterface cellManager = new CellManager(positionManager, pieceManager);

        //final EvaluationInterface evaluator1 = new MiniMaxEvaluator(9);
        final EvaluatorInterface evaluator2 = new NullEvaluator();

        final StrategyInterface strategy1 = new Strategy(evaluator2, new Random());
        final StrategyInterface strategy2 = new Strategy(evaluator2, new Random());

        final PlayerInterface player1 = new Player("Player1", strategy1);
        final PlayerInterface player2 = new Player("Player2", strategy2);
        final Adversity adversity = new Adversity(player1, player2);

        final RefereeInterface referee = new TicTacToeReferee();

        final Context context = new Context(adversity, cellManager, referee);

        //evaluator1.injectContext(context);

        final ContextManager contextManager = new ContextManager(context);

        TicTacToe.onStart(cellManager);

        contextManager.play();
        //System.out.println("\nAnd the winner is: " + winner);

    }

    private static void onStart(final CellManagerInterface cellManager) {}

}
