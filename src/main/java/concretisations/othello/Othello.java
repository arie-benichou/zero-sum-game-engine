
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
import abstractions.evaluation.EvaluationInterface;
import abstractions.evaluation.NegaMaxWithAlphaBetaEvaluator;
import abstractions.evaluation.NullEvaluator;
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
import abstractions.selection.FirstItemSelector;
import abstractions.side.Sides;
import abstractions.strategy.Strategy;
import abstractions.strategy.StrategyInterface;
import concretisations.othello.pieces.OthelloPieceSet;

public class Othello {

    private static CellManagerInterface cellManager() {
        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(8, 8)));
        final PieceManagerInterface pieceManager = new PieceManager(concretisations.othello.pieces.OthelloPieceSet.class);
        final CellManagerInterface cellManager = new CellManager(positionManager, pieceManager);
        cellManager.getCell(4, 4).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(4, 5).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(5, 4).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(5, 5).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        return cellManager;
    }

    private static AdversityInterface adversity() {
        final EvaluationInterface evaluator1 = new NullEvaluator();
        final EvaluationInterface evaluator2 = new NegaMaxWithAlphaBetaEvaluator(8);
        final StrategyInterface strategy1 = new Strategy(evaluator1, new FirstItemSelector());
        final StrategyInterface strategy2 = new Strategy(evaluator2, new FirstItemSelector());
        final PlayerInterface player1 = new Player("Player1", strategy1);
        final PlayerInterface player2 = new Player("Player2", strategy2);
        return new Adversity(player1, player2);
    }

    private static GameInterface game() {
        return new Game(Othello.cellManager(), new OthelloReferee());
    }

    private static GamePlayInterface gamePlay() {
        return new GamePlay(Othello.game(), Othello.adversity());
    }

    public static void main(final String[] args) {
        final ContextInterface context = new Context(Othello.gamePlay());
        // TODO créer ContextManagerInterface (ou GamePlayManagerInterface)
        final ContextManager contextManager = new ContextManager(context);
        contextManager.play();
        System.out.println(context.getHeuristicEvaluation(Sides.FIRST));
        System.out.println(context.getHeuristicEvaluation(Sides.SECOND));
    }

}