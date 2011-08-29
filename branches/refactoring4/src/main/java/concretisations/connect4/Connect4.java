
package concretisations.connect4;

import abstractions.adversity.Adversity;
import abstractions.cell.CellManager;
import abstractions.cell.CellManagerInterface;
import abstractions.context.Context;
import abstractions.context.ContextManager;
import abstractions.dimension.DimensionManager;
import abstractions.direction.DirectionManager;
import abstractions.evaluation.EvaluationInterface;
import abstractions.evaluation.NullEvaluator;
import abstractions.piece.PieceManager;
import abstractions.piece.PieceManagerInterface;
import abstractions.player.Player;
import abstractions.player.PlayerInterface;
import abstractions.position.PositionManager;
import abstractions.position.PositionManagerInterface;
import abstractions.referee.RefereeInterface;
import abstractions.selection.RandomItemSelector;
import abstractions.strategy.Strategy;
import abstractions.strategy.StrategyInterface;

public class Connect4 {

    public static void main(String[] args) {

        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(6, 7)));
        final PieceManagerInterface pieceManager = new PieceManager(concretisations.connect4.pieces.Connect4PieceSet.class);
        final CellManagerInterface cellManager = new CellManager(positionManager, pieceManager);

        final EvaluationInterface evaluator = new NullEvaluator();

        final StrategyInterface strategy1 = new Strategy(evaluator, new RandomItemSelector());
        final StrategyInterface strategy2 = new Strategy(evaluator, new RandomItemSelector());
        //final StrategyInterface strategy2 = new Strategy(evaluator, new HumanMoveSelector());

        final PlayerInterface player1 = new Player("Player1", strategy1);
        final PlayerInterface player2 = new Player("Player2", strategy2);
        
        final Adversity adversity = new Adversity(player1, player2);

        final RefereeInterface referee = new Connect4Referee();

        final Context context = new Context(adversity, cellManager, referee);

        final ContextManager contextManager = new ContextManager(context);

        contextManager.start();

    }

}
