
package concretisations.connect4;

import abstractions.dimension.DimensionManager;
import abstractions.direction.DirectionManager;
import abstractions.immutable.context.gameplay.adversity.Adversity;
import abstractions.immutable.context.gameplay.adversity.player.Player;
import abstractions.immutable.context.gameplay.adversity.player.PlayerInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.PieceManager;
import abstractions.immutable.context.gameplay.game.board.cell.piece.PieceManagerInterface;
import abstractions.immutable.context.gameplay.game.referee.RefereeInterface;
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

public class Connect4 {

    public static void main(String[] args) {

        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(6, 7)));
        final PieceManagerInterface pieceManager = new PieceManager(concretisations.connect4.pieces.Connect4PieceSet.class);
        final CellManagerInterface cellManager = new CellManager(positionManager, pieceManager);

        final EvaluatorInterface evaluator = new NullEvaluator();

        final StrategyInterface strategy1 = new Strategy(evaluator, new Random());
        final StrategyInterface strategy2 = new Strategy(evaluator, new Random());
        //final StrategyInterface strategy2 = new Strategy(evaluator, new HumanMoveSelector());

        final PlayerInterface player1 = new Player("Player1", strategy1);
        final PlayerInterface player2 = new Player("Player2", strategy2);
        
        final Adversity adversity = new Adversity(player1, player2);

        final RefereeInterface referee = new Connect4Referee();

        final Context context = new Context(adversity, cellManager, referee);

        final ContextManager contextManager = new ContextManager(context);

        contextManager.startGamePlay();

    }

}
