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

import fr.designpattern.zerosumgames.core.interfaces.IGame;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardMove;
import fr.designpattern.zerosumgames.core.strategies.moveSelectors.IAlgorithm;
import fr.designpattern.zerosumgames.core.strategies.moveSelectors.heuristics.OneSingleMoveExists;
import fr.designpattern.zerosumgames.core.strategies.moveSelectors.heuristics.WinningMoveExists;
import fr.designpattern.zerosumgames.core.types.GamePlayersEnumeration;

public class BestMoveStrategy implements IArtificialIntelligenceStrategy {
	
	private IAlgorithm engine = null;
	private final void setEngine(IAlgorithm engine) {
		this.engine = engine;
	}		
	public final IAlgorithm getEngine() {
		return engine;
	}	
	
	public BestMoveStrategy(IAlgorithm engine) {
		this.setEngine(engine);
	}

	public IGameBoardMove chooseMoveAmong(final IGame context, final List<IGameBoardMove> legalMoves) {
		
		//--------------------------------------------------------------------------------------		
		if(new OneSingleMoveExists().checkPredicate(legalMoves)) {
			return legalMoves.get(0);
		}
		//--------------------------------------------------------------------------------------
		
		// uniquement pour tictactoe et puissance4 : TODO à améliorer
		for(IGameBoardMove move: legalMoves) {
			move.setEvaluation(this.getEngine().evaluateDeeply(context, move, 1));
			move.setDepth(1);
		}
		//--------------------------------------------------------------------------------------
		if(new WinningMoveExists().checkPredicate(legalMoves)) {
			return Collections.max(legalMoves);
		}
		//--------------------------------------------------------------------------------------
		// uniquement pour tictactoe et puissance4 : TODO à améliorer
		List<IGameBoardMove> OpponentLegalMovesForSameContext = context.getLegalMoves(GamePlayersEnumeration.opponent(legalMoves.get(0).getSide()));
		for(IGameBoardMove move: OpponentLegalMovesForSameContext) {
			move.setEvaluation(this.getEngine().evaluateDeeply(context, move, 1));
			move.setDepth(1);
		}
		//--------------------------------------------------------------------------------------
		if(new WinningMoveExists().checkPredicate(OpponentLegalMovesForSameContext)) {
			int index = 0;
			while(OpponentLegalMovesForSameContext.get(index).getEvaluation() != Double.POSITIVE_INFINITY) ++index; // TODO QuickSearch si la liste est grande
			return legalMoves.get(index);
		}
		//--------------------------------------------------------------------------------------


		
		// tri de la liste en fonction des scores obtenus au niveau 2
		for(IGameBoardMove move: legalMoves) {
			move.setEvaluation(this.getEngine().evaluateDeeply(context, move, 2));
			move.setDepth(2);
		}

		
		Collections.sort(legalMoves);
		Collections.reverse(legalMoves);
		
		System.out.println("\nEvaluation en profondeur des coups légaux...\n");
		
		for(IGameBoardMove move : legalMoves) {
			move.setEvaluation(this.getEngine().evaluateDeeply(context, move));
		}

		
		Collections.sort(legalMoves);
		Collections.reverse(legalMoves);
		for (IGameBoardMove legalMove: legalMoves) {
			System.out.println(legalMove.debug());
		}
		
		
		return Collections.max(legalMoves);
		//--------------------------------------------------------------------------------------
		
	}

}