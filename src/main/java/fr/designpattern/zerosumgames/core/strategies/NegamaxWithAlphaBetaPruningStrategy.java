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

package fr.designpattern.zerosumgames.core.strategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import fr.designpattern.zerosumgames.core.GameBuilder;
import fr.designpattern.zerosumgames.core.GamePlayer;
import fr.designpattern.zerosumgames.core.GameService;
import fr.designpattern.zerosumgames.core.interfaces.IGame;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardMove;
import fr.designpattern.zerosumgames.core.interfaces.IGameBuilder;
import fr.designpattern.zerosumgames.core.interfaces.IGamePlayerStrategy;
import fr.designpattern.zerosumgames.core.types.GamePlayerNature;
import fr.designpattern.zerosumgames.core.types.GamePlayersEnumeration;
import fr.designpattern.zerosumgames.implementations.connect4.Connect4;
import fr.designpattern.zerosumgames.implementations.tictactoe.Tictactoe;

// TODO ! timer
public class NegamaxWithAlphaBetaPruningStrategy implements IGamePlayerStrategy {
	
	private int maxDepth;
	
	private int evaluatedMoves = 0;
	
	private int alphabetaCutoffs = 0;
	
	private GamePlayersEnumeration side;

	public NegamaxWithAlphaBetaPruningStrategy(int maxDepth) {
		this.maxDepth = maxDepth;
	}
	
	// TODO affiner la fonction d'évaluation statique du puissance 4 (cf version php)
	// TODO hasNoChoice()
	// TODO isGoingToWin()
	// TODO isAboutToLose()
	
	private boolean hasNoChoice(final List<IGameBoardMove> legalMoves) {
		return legalMoves.size() == 1;
	}
	//--------------------------------------------------------------------------------------
	public IGameBoardMove chooseMoveAmong(final IGame game, final List<IGameBoardMove> legalMoves) {

		this.evaluatedMoves = 0;
		
		if(this.hasNoChoice(legalMoves)) {
			System.out.println("Il n y a qu'un seul coup possible, je n'ai pas le choix...");
			return legalMoves.get(0);
		}
		
		this.side = legalMoves.get(0).getSide();

		System.out.println("\npreordering legal moves: ");
		
		List<IGameBoardMove> orderedLegalMoves = new ArrayList<IGameBoardMove>();
		
		this.chooseBestMoveAmong(game, legalMoves, 2, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, this.side);
		
		Collections.sort(legalMoves);
		Collections.reverse(legalMoves);
		
		for (IGameBoardMove legalMove: legalMoves) {
			System.out.println(legalMove);
			if(!legalMove.getEvaluation().equals(Double.NEGATIVE_INFINITY)) {
				orderedLegalMoves.add(legalMove);
			}
		}
		
		System.out.println("\nordered legal moves: ");		
		for (IGameBoardMove legalMove: orderedLegalMoves) {
			System.out.println(legalMove);
		}
		
		if(this.hasNoChoice(orderedLegalMoves)) {
			System.out.println("Il n y a qu'un seul coup valable, je n'ai pas vraiment le choix...");
			return orderedLegalMoves.get(0);
		}
		else if(orderedLegalMoves.size() == 0) {
			return legalMoves.get(new Random().nextInt(legalMoves.size()));
		}
		
		//this.maxDepth = maxDepth;
		this.chooseBestMoveAmong(game, orderedLegalMoves, this.maxDepth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, this.side);
		
		System.out.println("\nnumber of evaluated moves: " + this.evaluatedMoves);
		System.out.println("\nnumber of alpha/beta cut-offs: " + this.alphabetaCutoffs);
		
		Collections.sort(orderedLegalMoves);
		Collections.reverse(orderedLegalMoves);
		
		System.out.println("\nevaluation of legal moves: ");
		
		for (IGameBoardMove legalMove: orderedLegalMoves) {
			System.out.println(legalMove);
		}
		
		return orderedLegalMoves.get(0);
	}
		
	
	// TODO bestMove et worstMove
	private Double chooseBestMoveAmong(final IGame game, final List<IGameBoardMove> legalMoves, int depth, Double alpha, Double beta, GamePlayersEnumeration side) {
		//--------------------------------------------------------------------------------------
		Double bestEvaluation = alpha;
		//--------------------------------------------------------------------------------------
		GamePlayersEnumeration currentPlayerOrdinal;
		//--------------------------------------------------------------------------------------
		for(IGameBoardMove move : legalMoves) {
			//--------------------------------------------------------------------------------------
			currentPlayerOrdinal = game.applyGameStateTransition(move);
			//--------------------------------------------------------------------------------------			
			if( !(currentPlayerOrdinal == GamePlayersEnumeration.FIRST_PLAYER || currentPlayerOrdinal == GamePlayersEnumeration.SECOND_PLAYER) ) {
				move.setDepth(depth);
				if(currentPlayerOrdinal == GamePlayersEnumeration.NONE) {
					move.setEvaluation(0.0);
				}
				else {
					if(side == GamePlayersEnumeration.FIRST_PLAYER) {
						if(currentPlayerOrdinal == GamePlayersEnumeration.NOT_FIRST_PLAYER) {
							move.setEvaluation(Double.NEGATIVE_INFINITY);	
						}
						else {
							move.setEvaluation(Double.POSITIVE_INFINITY);
						}
					}
					else {
						if(currentPlayerOrdinal == GamePlayersEnumeration.NOT_SECOND_PLAYER) {
							move.setEvaluation(Double.NEGATIVE_INFINITY);	
						}
						else {
							move.setEvaluation(Double.POSITIVE_INFINITY);
						}
					}
				}
				//--------------------------------------------------------------------------------------				
				game.undo(move);
				//--------------------------------------------------------------------------------------				
				return move.getEvaluation();
			}
			//--------------------------------------------------------------------------------------
			if(depth == 0) {
				game.undo(move);
				return (double) game.evaluate(move);
			}
			//--------------------------------------------------------------------------------------
			move.setEvaluation(-this.chooseBestMoveAmong(game, game.getLegalMoves(move.getSide().getOpponent()), depth - 1, -beta, -alpha, side.getOpponent()));
			//--------------------------------------------------------------------------------------
			game.undo(move);
			//--------------------------------------------------------------------------------------
			if(move.getEvaluation() > bestEvaluation) {
				bestEvaluation = move.getEvaluation();
			}
			//--------------------------------------------------------------------------------------
			if(bestEvaluation >= beta) {
				++this.alphabetaCutoffs;
				break;
			}
			//--------------------------------------------------------------------------------------
			++this.evaluatedMoves;
			//--------------------------------------------------------------------------------------				
		}
		//--------------------------------------------------------------------------------------				
		return bestEvaluation;
		//--------------------------------------------------------------------------------------				
	}	
	
	// TODO simplification side + tests
	// TODO !améliorer l'api des opponents, ce n'est pas au player de connaitre son ordre!	
	// TODO revoir la fontion d'évaluation du tictactoe (tests)
	// TODO adapter Othello
	// TODO committer
	public static void main(final String[] args) {
		IGameBuilder gameBuilder = new GameBuilder(Connect4.class);
		gameBuilder.player1(new GamePlayer("p1", GamePlayersEnumeration.FIRST_PLAYER, GamePlayerNature.COMPUTER, new NegamaxWithAlphaBetaPruningStrategy(4)));
		//gameBuilder.player2(new GamePlayer("p2", GamePlayersEnumeration.FIRST_PLAYER, GamePlayerNature.HUMAN, new GamePlayerHumanStrategy()));
		gameBuilder.player2(new GamePlayer("p2", GamePlayersEnumeration.FIRST_PLAYER, GamePlayerNature.HUMAN, new NegamaxWithAlphaBetaPruningStrategy(6)));
		//gameBuilder.player2(new GamePlayer("p1", GamePlayersEnumeration.SECOND_PLAYER, GamePlayerNature.COMPUTER, new GamePlayerMinimaxStrategy(9)));
		new GameService(gameBuilder.build()).start();
	}

}