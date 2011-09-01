
package concretisations.othello;

import abstractions.adversity.Adversity;
import abstractions.adversity.AdversityInterface;
import abstractions.cell.CellManager;
import abstractions.cell.CellManagerInterface;
import abstractions.context.Context;
import abstractions.context.ContextInterface;
import abstractions.context.ContextManager;
import abstractions.dimension.DimensionManager;
import abstractions.direction.DirectionManager;
import abstractions.evaluator.EvaluatorInterface;
import abstractions.evaluator.NegaMaxAlphaBeta;
import abstractions.evaluator.NullEvaluator;
import abstractions.game.Game;
import abstractions.game.GameInterface;
import abstractions.gameplay.GamePlay;
import abstractions.gameplay.GamePlayInterface;
import abstractions.piece.PieceManager;
import abstractions.piece.PieceManagerInterface;
import abstractions.player.Player;
import abstractions.player.PlayerInterface;
import abstractions.position.PositionManager;
import abstractions.position.PositionManagerInterface;
import abstractions.selector.LastItem;
import abstractions.selector.RandomAvoidingNullMove;
import abstractions.side.Sides;
import abstractions.strategy.Strategy;
import abstractions.strategy.StrategyInterface;
import concretisations.othello.pieces.OthelloPieceSet;

public class SmallOthello {

    private static CellManagerInterface cellManager() {
        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(6, 6)));
        final PieceManagerInterface pieceManager = new PieceManager(concretisations.othello.pieces.OthelloPieceSet.class);
        final CellManagerInterface cellManager = new CellManager(positionManager, pieceManager);
        cellManager.getCell(3, 3).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 4).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(4, 3).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(4, 4).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        return cellManager;
    }

    private static AdversityInterface adversity() {

        final EvaluatorInterface evaluator1 = new NullEvaluator();
        final StrategyInterface strategy1 = new Strategy(evaluator1, new RandomAvoidingNullMove());
        final PlayerInterface player1 = new Player("Player1", strategy1);

        final EvaluatorInterface evaluator2 = new NegaMaxAlphaBeta(9);
        final StrategyInterface strategy2 = new Strategy(evaluator2, new LastItem());
        final PlayerInterface player2 = new Player("Player2", strategy2);

        return new Adversity(player1, player2);

    }

    private static GameInterface game() {
        return new Game(SmallOthello.cellManager(), new OthelloReferee());
    }

    private static GamePlayInterface gamePlay() {
        return new GamePlay(SmallOthello.game(), SmallOthello.adversity());
    }

    public static void main(final String[] args) {
        final ContextInterface context = new Context(SmallOthello.gamePlay());
        // TODO créer ContextManagerInterface (ou GamePlayManagerInterface)
        final ContextManager contextManager = new ContextManager(context);
        contextManager.play();
        System.out.println(context.getHeuristicEvaluation(Sides.FIRST));
        System.out.println(context.getHeuristicEvaluation(Sides.SECOND));
    }

}