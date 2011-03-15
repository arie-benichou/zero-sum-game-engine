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

import java.util.Collections;
import java.util.List;

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

// TODO ! timer
public class NegamaxWithAlphaBetaPruningStrategy implements IGamePlayerStrategy {
	//--------------------------------------------------------------------------------------
	private int maxDepth;
	//--------------------------------------------------------------------------------------
	private int alphabetaCutoffs;
	//--------------------------------------------------------------------------------------	
	public NegamaxWithAlphaBetaPruningStrategy(int maxDepth) {
		this.maxDepth = maxDepth;
	}
	//--------------------------------------------------------------------------------------		
	private Double chooseBestMoveAmong(final IGame game, final List<IGameBoardMove> legalMoves, int depth, Double alpha, Double beta, int side) {
		
		Double bestMoveEvaluation = alpha;
		
		////System.out.println("==================================================================");		
		////System.out.println(" side  = " + side + "  |  alpha = " + alpha + " |  beta  = " + beta);
		////System.out.println("==================================================================");
		
		GamePlayersEnumeration currentPlayerOrdinal;

		for(IGameBoardMove move : legalMoves) {
			
			////System.out.println("------------------------------------------------------------------");
			
			currentPlayerOrdinal = game.applyGameStateTransition(move);
			
			if(currentPlayerOrdinal == GamePlayersEnumeration.NONE || depth == 1) {
				move.setEvaluation(game.evaluate(move));
				///System.out.println(game);
				game.undo(move);
				return move.getEvaluation();
			}
			else {
				move.setEvaluation(-this.chooseBestMoveAmong(game, game.getLegalMoves(move.getSide().getOpponent()), depth - 1, -beta, -alpha, -side));
				if(move.getEvaluation().isInfinite() || move.getEvaluation().equals(0.0)) {
					////System.out.println("\nGame Over remonté.");
					//////System.out.println("Evaluation locale du Game Over : " + move.getEvaluation());
				}
				///System.out.println(game);				
				game.undo(move);
			}
			
			////System.out.println("\névaluation: " + move.getEvaluation() + "\n");
			
			if(move.getEvaluation() > bestMoveEvaluation) {
				////System.out.println("Ce coup apporte une meilleure évaluation !");
				bestMoveEvaluation = move.getEvaluation();
				////System.out.println("alpha = " + alpha + " |  beta  = " + beta);
			}
			else {
				////System.out.println("Ce coup n'apporte pas de meilleure évaluation.");
			}
			
			////System.out.println("------------------------------------------------------------------");		
			////System.out.println(" side  = " + side + "  |  alpha = " + alpha + " |  beta  = " + beta);
			////System.out.println("------------------------------------------------------------------");			
			
			if(bestMoveEvaluation >= beta) {
				////System.out.println("------------------------------------------------------------------");				
				////System.out.println("Il ne faut pas prendre son adversaire pour un bêta...");
				////System.out.println("------------------------------------------------------------------");
				++this.alphabetaCutoffs;
				break;
			}
			
		}
		return bestMoveEvaluation;
		//--------------------------------------------------------------------------------------				
	}
	
	// TODO affiner la fonction d'évaluation statique du puissance 4 (cf version php)
	private boolean hasNoChoice(final List<IGameBoardMove> legalMoves) {
		return legalMoves.size() == 1;
	}
	//--------------------------------------------------------------------------------------
	public IGameBoardMove chooseMoveAmong(final IGame game, final List<IGameBoardMove> legalMoves) {
		
		if(this.hasNoChoice(legalMoves)) {
			System.out.println("Il n y a qu'un seul coup possible, je n'ai pas le choix...");
			return legalMoves.get(0);
		}
		
		Double alpha;
		
		//test si victoire imminente pour le joueur actuel (profondeur1)
		alpha = this.chooseBestMoveAmong(game, legalMoves, 1, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1);
		if(alpha.equals(Double.POSITIVE_INFINITY)) {
			System.out.println("Victoire imminente!");
		}
		else {
			System.out.println("Pas de victoire imminente...");
			
			Collections.sort(legalMoves);
			
			for (IGameBoardMove legalMove: legalMoves) {
				System.out.println(legalMove);
			}
			
			// sinon, test si victoire imminente (défaite dans un futur très proche du joueur actuel) pour l'adversaire (profondeur2)			
			alpha = this.chooseBestMoveAmong(game, legalMoves, 2, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1);
			
			for (IGameBoardMove legalMove: legalMoves) {
				System.out.println(legalMove);
			}

		}

		System.exit(0);
		
		
		
		
		
		
		this.alphabetaCutoffs = 0;			
		alpha = this.chooseBestMoveAmong(game, legalMoves, 2, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1);
		System.out.println("alpha/beta cut-offs : " + this.alphabetaCutoffs);			
		
		Collections.sort(legalMoves);
		Collections.reverse(legalMoves);
		
		//TODO ? et enfin, sinon, tester jusqu'à la maxDepth
		
		
		this.alphabetaCutoffs = 0;
		alpha = this.chooseBestMoveAmong(game, legalMoves, this.maxDepth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1);
		System.out.println("alpha: " + alpha);
		System.out.println("alpha/beta cut-offs : " + this.alphabetaCutoffs);
		
		// TODO ? mémoriser la profondeur du Game Over et trier par ordre de profondeur
		if(alpha.equals(Double.NEGATIVE_INFINITY)) {
			// TODO réutiliser les résultats de la recherche précédente
			System.out.println("Holy Shit! I'm doomed.");
			this.alphabetaCutoffs = 0;			
			alpha = this.chooseBestMoveAmong(game, legalMoves, 2, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1);
			System.out.println("alpha/beta cut-offs : " + this.alphabetaCutoffs);			
		}
		
		System.out.println("\n");
		
		for (IGameBoardMove legalMove: legalMoves) {
			System.out.println(legalMove);
		}
		return Collections.max(legalMoves);
	}	
	//--------------------------------------------------------------------------------------		
	// TODO ! améliorer l'api des opponents, ce n'est pas au player de connaitre son ordre.	
	// TODO revoir la fontion d'évaluation du tictactoe (tests)
	// TODO adapter Othello (revertedCells en dans Move)
	//--------------------------------------------------------------------------------------
	public static void main(final String[] args) {
		IGameBuilder gameBuilder = new GameBuilder(Connect4.class);
		//IGameBuilder gameBuilder = new GameBuilder(Tictactoe.class);
		gameBuilder.player1(new GamePlayer("p1", GamePlayersEnumeration.FIRST_PLAYER, GamePlayerNature.COMPUTER, new NegamaxWithAlphaBetaPruningStrategy(3)));
		//gameBuilder.player1(new GamePlayer("p1", GamePlayersEnumeration.FIRST_PLAYER, GamePlayerNature.COMPUTER, new HumanStrategy()));
		gameBuilder.player2(new GamePlayer("p2", GamePlayersEnumeration.SECOND_PLAYER, GamePlayerNature.COMPUTER, new NegamaxWithAlphaBetaPruningStrategy(7)));
		//gameBuilder.player2(new GamePlayer("p2", GamePlayersEnumeration.SECOND_PLAYER, GamePlayerNature.COMPUTER, new HumanStrategy()));
		new GameService(gameBuilder.build()).start();
	}

}