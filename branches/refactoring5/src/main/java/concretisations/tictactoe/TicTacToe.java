
package concretisations.tictactoe;

import abstractions.dimension.DimensionManager;
import abstractions.direction.DirectionManager;
import abstractions.immutable.context.adversity.Adversity;
import abstractions.immutable.context.adversity.player.Player;
import abstractions.immutable.context.adversity.player.PlayerInterface;
import abstractions.immutable.context.board.cell.piece.PieceManager;
import abstractions.immutable.context.board.cell.piece.PieceManagerInterface;
import abstractions.immutable.context.referee.RefereeInterface;
import abstractions.old.cell.CellManager;
import abstractions.old.cell.CellManagerInterface;
import abstractions.old.context.Context;
import abstractions.old.context.ContextManager;
import abstractions.old.evaluator.EvaluatorInterface;
import abstractions.old.evaluator.NullEvaluator;
import abstractions.old.position.PositionManager;
import abstractions.old.position.PositionManagerInterface;
import abstractions.old.selector.Random;
import abstractions.old.strategy.Strategy;
import abstractions.old.strategy.StrategyInterface;

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

        contextManager.startGamePlay();
        //System.out.println("\nAnd the winner is: " + winner);

    }

    private static void onStart(final CellManagerInterface cellManager) {}

}
