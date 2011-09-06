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
import abstractions.cell.CellManager;
import abstractions.cell.CellManagerInterface;
import abstractions.context.ContextManager;
import abstractions.dimension.DimensionManager;
import abstractions.direction.DirectionManager;
import abstractions.evaluator.CachingEvaluator;
import abstractions.evaluator.EvaluatorInterface;
import abstractions.evaluator.IterativeDeepening;
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
import abstractions.selector.SelectorInterface;
import abstractions.side.Sides;
import abstractions.strategy.Strategy;
import concretisations.othello.pieces.OthelloPieceSet;

// TODO pouvoir dupliquer le contexte
// TODO pouvoir reseter un jeu
class Othello {

    public static void main(final String[] args) {

        final PositionManagerInterface positionManager;
        final PieceManagerInterface pieceManager;
        final CellManagerInterface cellManager;
        final EvaluatorInterface evaluator1;
        final EvaluatorInterface evaluator2;
        final SelectorInterface selector1;
        final SelectorInterface selector2;
        final PlayerInterface player1;
        final PlayerInterface player2;
        GameInterface game;
        GamePlayInterface gamePlay;

        positionManager = new PositionManager(new DirectionManager(new DimensionManager(8, 8)));
        pieceManager = new PieceManager(concretisations.othello.pieces.OthelloPieceSet.class);

        cellManager = new CellManager(positionManager, pieceManager);
        cellManager.getCell(4, 4).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(4, 5).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(5, 4).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(5, 5).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);

        evaluator1 = new NullEvaluator();
        evaluator2 = new IterativeDeepening(new CachingEvaluator(new NegaMaxAlphaBeta(6)));

        selector1 = new FirstItem(SelectorInterface.RANDOM_ON_SAME_EVALUATION, SelectorInterface.AVOID_NULL_MOVE);
        selector2 = new FirstItem();

        player1 = new Player("IA1", new Strategy(evaluator1, selector1));
        player2 = new Player("IA2", new Strategy(evaluator2, selector2));

        //one time
        game = new Game(cellManager, new OthelloReferee());
        gamePlay = new GamePlay(game, new Adversity(player1, player2));
        new ContextManager(new OthelloContext(gamePlay)).startGamePlay();

        //two times
        game = new Game(cellManager, new OthelloReferee());
        gamePlay = new GamePlay(game, new Adversity(player1, player2));
        new ContextManager(new OthelloContext(gamePlay)).startGamePlay();

    }

}