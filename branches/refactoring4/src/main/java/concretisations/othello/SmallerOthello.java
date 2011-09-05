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
import abstractions.context.ContextInterface;
import abstractions.context.ContextManager;
import abstractions.dimension.DimensionManager;
import abstractions.direction.DirectionManager;
import abstractions.evaluator.EvaluatorInterface;
import abstractions.evaluator.MiniMax;
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
import abstractions.selector.Human;
import abstractions.side.Sides;
import abstractions.strategy.Strategy;
import abstractions.strategy.StrategyInterface;
import concretisations.othello.pieces.OthelloPieceSet;

public class SmallerOthello {

    private static CellManagerInterface cellManager() {

        final PositionManagerInterface positionManager = new PositionManager(new DirectionManager(new DimensionManager(4, 4)));
        final PieceManagerInterface pieceManager = new PieceManager(concretisations.othello.pieces.OthelloPieceSet.class);
        final CellManagerInterface cellManager = new CellManager(positionManager, pieceManager);

        cellManager.getCell(2, 2).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);
        cellManager.getCell(2, 3).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 2).setPiece(Sides.SECOND, OthelloPieceSet.PAWN);
        cellManager.getCell(3, 3).setPiece(Sides.FIRST, OthelloPieceSet.PAWN);

        return cellManager;
    }

    private static AdversityInterface adversity() {

        // si le 2ème joueur joue parfaitement, le premier joueur ne peut pas gagner...
        final EvaluatorInterface evaluator1 = new NullEvaluator();
        final StrategyInterface strategy1 = new Strategy(evaluator1, new Human());

        // ...faisons donc jouer parfaitement le 2ème joueur ! 
        final EvaluatorInterface evaluator2 = new MiniMax(8);
        //final EvaluatorInterface evaluator2 = new NegaMax(8);
        final StrategyInterface strategy2 = new Strategy(evaluator2, new FirstItem(false, true));

        final PlayerInterface player1 = new Player("Human", strategy1);
        final PlayerInterface player2 = new Player("IA", strategy2);

        return new Adversity(player2, player1);
    }

    public static void main(final String[] args) {

        final GameInterface game = new Game(SmallerOthello.cellManager(), new OthelloReferee());
        final GamePlayInterface gamePlay = new GamePlay(game, SmallerOthello.adversity());
        final ContextInterface context = new OthelloContext(gamePlay);

        new ContextManager(context).startGamePlay();
    }
}