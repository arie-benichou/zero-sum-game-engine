
package concretisations.tictactoe.pieces;

import abstractions.adversity.Adversity;
import abstractions.adversity.AdversityInterface;
import abstractions.cell.CellManager;
import abstractions.cell.CellManagerInterface;
import abstractions.context.Context;
import abstractions.context.ContextInterface;
import abstractions.context.ContextManager;
import abstractions.dimension.DimensionManager;
import abstractions.dimension.DimensionManagerInterface;
import abstractions.direction.DirectionManager;
import abstractions.direction.DirectionManagerInterface;
import abstractions.evaluation.EvaluationInterface;
import abstractions.evaluation.NullEvaluator;
import abstractions.piece.PieceManager;
import abstractions.piece.PieceManagerInterface;
import abstractions.player.Player;
import abstractions.player.PlayerInterface;
import abstractions.position.PositionManager;
import abstractions.position.PositionManagerInterface;
import abstractions.selection.FirstItemSelector;
import abstractions.selection.SelectionInterface;
import abstractions.strategy.Strategy;
import abstractions.strategy.StrategyInterface;

public class TicTacToe {

    public TicTacToe() {

        final DimensionManagerInterface dimensionManager = new DimensionManager(3, 3);
        final DirectionManagerInterface directionManager = new DirectionManager(dimensionManager);
        final PositionManagerInterface positionManager = new PositionManager(directionManager);
        final PieceManagerInterface pieceManager = new PieceManager(concretisations.tictactoe.pieces.TicTacToePieceSet.class);
        final CellManagerInterface cellManager = new CellManager(positionManager, pieceManager);

        // TODO utiliser manager/factory
        final SelectionInterface selector = new FirstItemSelector();

        // TODO utiliser manager/factory
        final EvaluationInterface evaluator = new NullEvaluator();

        // TODO utiliser manager/factory
        final StrategyInterface strategy = new Strategy(evaluator, selector);

        // TODO utiliser manager/factory
        final PlayerInterface player1 = new Player("a player", strategy);

        // TODO utiliser manager/factory
        final PlayerInterface player2 = new Player("another player", strategy);

        // TODO utiliser manager/factory        
        final AdversityInterface adversity = new Adversity(player1, player2); // TODO fusionner side et adversity et faire AdversityManager
        // TODO injecter l'adversitymanager au piecemanager (jeu à une seule side nulle : free them all : sleepy)

        //System.out.println(adversity.getOpponent(Sides.FIRST));
        //System.out.println(adversity.getOpponent(Sides.SECOND));

        // TODO utiliser manager/factory
        final ContextInterface context = new Context(adversity, cellManager);

        //System.out.println(context);

        new ContextManager(context).start();

        //TODO ? TicTacToeContext, contextBuilder
        //TODO ? ContextLoop/ContextManager : service

    }

    public static void main(final String[] args) {

        new TicTacToe();

    }

}
