/*
 * Copyright (C) 2011 Arié Bénichou
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.    
 */

package main.java.games.core.strategies;

import java.util.List;
import java.util.Random;

import main.java.games.core.BestEvaluatedMove;
import main.java.games.core.GameBoardDimension;
import main.java.games.core.GameBuilder;
import main.java.games.core.GamePlayer;
import main.java.games.core.GameService;
import main.java.games.core.interfaces.IGame;
import main.java.games.core.interfaces.IGameBoardMove;
import main.java.games.core.interfaces.IGameBuilder;
import main.java.games.core.interfaces.IGamePlayerStrategy;
import main.java.games.core.types.GamePlayerNature;
import main.java.games.core.types.GamePlayersEnumeration;
import main.java.games.implementations.tictactoe.Tictactoe;

public class GamePlayerMinimaxStrategy implements IGamePlayerStrategy {
	
	private int depth;

	public GamePlayerMinimaxStrategy(int depth) {
		this.depth = depth;
	}
	
	@Override
	public IGameBoardMove chooseMoveAmong(final IGame game, final List<IGameBoardMove> legalMoves) {
		// TODO utiliser un décorateur
		int depth = this.depth;
		BestEvaluatedMove bestMove = this.chooseBestMoveAmong(game, legalMoves, depth, 0, 0);
		System.out.println("Meilleur coup trouvé: " + bestMove.getMove() + " Evaluation: " + bestMove.getEvaluation());
		if(bestMove.getMove() == null) {
			System.out.println("## Holy Shit, I'm doomed! ##");
			return legalMoves.get(new Random().nextInt(legalMoves.size()));
		}
		return bestMove.getMove();
	}
	
	private BestEvaluatedMove chooseBestMoveAmong(final IGame game, final List<IGameBoardMove> legalMoves, int depth, double alpha, double beta) {
		
		//4System.out.println(depth);
		
		double evaluation;
		
		BestEvaluatedMove bestMove = new BestEvaluatedMove(); // TODO implémenter une interface
		bestMove.setEvaluation(Double.NEGATIVE_INFINITY);
		
		GamePlayersEnumeration currentPlayerOrdinal;
		
		for(IGameBoardMove move : legalMoves) {
			
			// Pour les dames: tant que nextPlayerOrdinal = currentPlayerOrdinal 
			currentPlayerOrdinal = game.applyGameStateTransition(move);
			// TODO utiliser NOT_FIRST_PLAYER, NOT_SECOND_PLAYER, NONE avec FIRST_PLAYER et SECOND_PLAYER pour optimiser l'IA
			
			
			if(currentPlayerOrdinal == GamePlayersEnumeration.NOT_SECOND_PLAYER) {
				evaluation = Double.POSITIVE_INFINITY; // TODO ? prendre en compte le sideToPlay
				if(move.getSide() == GamePlayersEnumeration.SECOND_PLAYER) {
					evaluation = -evaluation; 
				}
			}
			else if(currentPlayerOrdinal == GamePlayersEnumeration.NOT_FIRST_PLAYER) {
				evaluation = Double.POSITIVE_INFINITY; // TODO ? prendre en compte le sideToPlay
				if(move.getSide() == GamePlayersEnumeration.FIRST_PLAYER) {
					evaluation = -evaluation; 
				}				
			}
			else if(currentPlayerOrdinal == GamePlayersEnumeration.NONE) {
				//System.out.println("Match nul");
				evaluation = 0;
			}
			else {
				if(depth == 1) {
					//System.out.println("Limite de profondeur atteinte.");
					evaluation = game.evaluate(move);
				}
				else {
					evaluation = -this.chooseBestMoveAmong(game, game.getLegalMoves(move.getSide().getOpponent()), depth - 1, -beta, -alpha).getEvaluation();
				}				
			}
			
			//System.out.println(game);

			//System.out.println(evaluation);

			
			game.undo(move);
			
			if(evaluation > bestMove.getEvaluation()) {
				
				//System.out.println("Meilleur coup trouvé jusqu'à présent: " + bestMove.getEvaluation());
				
				bestMove.setEvaluation(evaluation);
				bestMove.setMove(move);
			}
			
			/*
			if(bestMove.getEvaluation() >= beta) {
				break;
			}
			
			if(bestMove.getEvaluation() > alpha) {
				alpha = bestMove.getEvaluation();
			}
			*/
			
		}
		
		return bestMove;
	}	
	
	public static void main(final String[] args) {
		
		
		/*
		System.out.println(Double.POSITIVE_INFINITY - 1);
		System.exit(0);
		*/
		
		//IGameBuilder gameBuilder = new GameBuilder(Connect4.class);
		IGameBuilder gameBuilder = new GameBuilder(Tictactoe.class);
		//gameBuilder.boardDimension(new GameBoardDimension(1, 4, 1, 4));
		// TODO utiliser un bag d'opponents dans le builder à la place de player1 et player2

		gameBuilder.player1(new GamePlayer("IA1", GamePlayersEnumeration.FIRST_PLAYER, GamePlayerNature.COMPUTER, new GamePlayerMinimaxStrategy(2)));
		gameBuilder.player2(new GamePlayer("IA2", GamePlayersEnumeration.SECOND_PLAYER, GamePlayerNature.COMPUTER, new GamePlayerMinimaxStrategy(2)));

		/*
		gameBuilder.player1(new GamePlayer("IA", GamePlayersEnumeration.FIRST_PLAYER, GamePlayerNature.COMPUTER, new GamePlayerRandomStrategy()));		
		gameBuilder.player2(new GamePlayer("Humain", GamePlayersEnumeration.SECOND_PLAYER, GamePlayerNature.HUMAN, new GamePlayerMinimaxStrategy()));
		*/

		/*
		gameBuilder.player1(new GamePlayer("Humain", GamePlayersEnumeration.FIRST_PLAYER, GamePlayerNature.HUMAN, new GamePlayerRandomStrategy()));
		gameBuilder.player2(new GamePlayer("IA", GamePlayersEnumeration.SECOND_PLAYER, GamePlayerNature.COMPUTER, new GamePlayerRandomStrategy()));		
		*/
		
		//TictactoeBuilder.player2(new GamePlayer("Humain", GamePlayersEnumeration.SECOND_PLAYER, GamePlayerNature.HUMAN, new GamePlayerHumanStrategy()));		
		new GameService(gameBuilder.build()).start();
	}

}