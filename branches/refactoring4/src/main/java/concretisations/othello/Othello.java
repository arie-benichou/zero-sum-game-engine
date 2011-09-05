/*
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package concretisations.othello;

import abstractions.adversity.Adversity;
import abstractions.adversity.AdversityInterface;
import abstractions.cell.CellManager;
import abstractions.cell.CellManagerInterface;
import abstractions.context.ContextManager;
import abstractions.dimension.DimensionManager;
import abstractions.direction.DirectionManager;
import abstractions.evaluator.EvaluatorInterface;
import abstractions.evaluator.IterativeDeepening;
import abstractions.evaluator.MiniMax;
import abstractions.evaluator.MiniMaxAlphaBeta;
import abstractions.evaluator.NegaMax;
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
import abstractions.selector.FirstItem;
import abstractions.side.Sides;
import abstractions.strategy.Strategy;
import abstractions.strategy.StrategyInterface;
import concretisations.othello.pieces.OthelloPieceSet;

// TODO povoir dupliquer le contexte
// TODO povoir reseter un jeu
public class Othello {

    private final static int DEPTH = 6;
    private final static boolean RANDOM_ON_SAME_EVALUATION = true;
    private final static boolean AVOID_NULL_MOVE = true;

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

    private static AdversityInterface adversity1() {

        final EvaluatorInterface evaluator1 = new NullEvaluator();
        final StrategyInterface strategy1 = new Strategy(evaluator1, new FirstItem(Othello.RANDOM_ON_SAME_EVALUATION, Othello.AVOID_NULL_MOVE));

        final EvaluatorInterface evaluator2 = new MiniMax(Othello.DEPTH);
        final StrategyInterface strategy2 = new Strategy(evaluator2, new FirstItem(Othello.RANDOM_ON_SAME_EVALUATION, Othello.AVOID_NULL_MOVE));

        final PlayerInterface player1 = new Player("IA1", strategy1);
        final PlayerInterface player2 = new Player("IA2", strategy2);

        return new Adversity(player1, player2);
    }

    private static AdversityInterface adversity2() {
        final EvaluatorInterface evaluator1 = new NullEvaluator();
        final StrategyInterface strategy1 = new Strategy(evaluator1, new FirstItem(Othello.RANDOM_ON_SAME_EVALUATION, Othello.AVOID_NULL_MOVE));
        final EvaluatorInterface evaluator2 = new NegaMax(Othello.DEPTH);
        final StrategyInterface strategy2 = new Strategy(evaluator2, new FirstItem(Othello.RANDOM_ON_SAME_EVALUATION, Othello.AVOID_NULL_MOVE));
        final PlayerInterface player1 = new Player("IA1", strategy1);
        final PlayerInterface player2 = new Player("IA2", strategy2);
        return new Adversity(player1, player2);
    }

    private static AdversityInterface adversity3() {
        final EvaluatorInterface evaluator1 = new NullEvaluator();
        final StrategyInterface strategy1 = new Strategy(evaluator1, new FirstItem(Othello.RANDOM_ON_SAME_EVALUATION, Othello.AVOID_NULL_MOVE));
        final EvaluatorInterface evaluator2 = new MiniMaxAlphaBeta(Othello.DEPTH);
        final StrategyInterface strategy2 = new Strategy(evaluator2, new FirstItem(Othello.RANDOM_ON_SAME_EVALUATION, Othello.AVOID_NULL_MOVE));
        final PlayerInterface player1 = new Player("IA1", strategy1);
        final PlayerInterface player2 = new Player("IA2", strategy2);
        return new Adversity(player1, player2);
    }

    private static AdversityInterface adversity4() {
        final EvaluatorInterface evaluator1 = new NullEvaluator();
        final StrategyInterface strategy1 = new Strategy(evaluator1, new FirstItem(Othello.RANDOM_ON_SAME_EVALUATION, Othello.AVOID_NULL_MOVE));
        final EvaluatorInterface evaluator2 = new NegaMaxAlphaBeta(Othello.DEPTH);
        final StrategyInterface strategy2 = new Strategy(evaluator2, new FirstItem(Othello.RANDOM_ON_SAME_EVALUATION, Othello.AVOID_NULL_MOVE));
        final PlayerInterface player1 = new Player("IA1", strategy1);
        final PlayerInterface player2 = new Player("IA2", strategy2);
        return new Adversity(player1, player2);
    }

    private static AdversityInterface adversity5() {
        final EvaluatorInterface evaluator1 = new NullEvaluator();
        final StrategyInterface strategy1 = new Strategy(evaluator1, new FirstItem(Othello.RANDOM_ON_SAME_EVALUATION, Othello.AVOID_NULL_MOVE));
        final EvaluatorInterface evaluator2 = new IterativeDeepening(new NegaMaxAlphaBeta(Othello.DEPTH));
        final StrategyInterface strategy2 = new Strategy(evaluator2, new FirstItem(Othello.RANDOM_ON_SAME_EVALUATION, Othello.AVOID_NULL_MOVE));
        final PlayerInterface player1 = new Player("IA1", strategy1);
        final PlayerInterface player2 = new Player("IA2", strategy2);
        return new Adversity(player1, player2);
    }

    public static void main(final String[] args) {

        final GameInterface game4 = new Game(Othello.cellManager(), new OthelloReferee());
        final GamePlayInterface gamePlay4 = new GamePlay(game4, Othello.adversity4());
        new ContextManager(new OthelloContext(gamePlay4)).startGamePlay();

        //System.out.println(((NegaMaxAlphaBeta) gamePlay4.getAdversity().getPlayer(Sides.SECOND).getStrategy().getEvaluator()).cutOffs);

        final GameInterface game5 = new Game(Othello.cellManager(), new OthelloReferee());
        final GamePlayInterface gamePlay5 = new GamePlay(game5, Othello.adversity5());
        new ContextManager(new OthelloContext(gamePlay5)).startGamePlay();

        /*
        final GameInterface game3 = new Game(Othello.cellManager(), new OthelloReferee());
        final GamePlayInterface gamePlay3 = new GamePlay(game3, Othello.adversity3());
        new ContextManager(new OthelloContext(gamePlay3)).startGamePlay();
        */

        //System.out.println(((MiniMaxAlphaBeta) gamePlay3.getAdversity().getPlayer(Sides.SECOND).getStrategy().getEvaluator()).cutOffs);

        /*
        final GameInterface game2 = new Game(cellManager(), new OthelloReferee());
        final GamePlayInterface gamePlay2 = new GamePlay(game2, adversity2());
        new ContextManager(new OthelloContext(gamePlay2)).startGamePlay();

        final GameInterface game = new Game(cellManager(), new OthelloReferee());
        final GamePlayInterface gamePlay1 = new GamePlay(game, adversity1());
        new ContextManager(new OthelloContext(gamePlay1)).startGamePlay();
        */

    }
}