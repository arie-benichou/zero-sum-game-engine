/*
 * Copyright 2011 Arie Benichou
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

package fr.designpattern.zerosumgames.framework.service.gameplay;

import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.OpponentInterface;

/**
 * This is the interface for a gameplay. 
 */
public interface GamePlayInterface {

	/**
	 * Returns the legal moves for a given side.
	 * 
	 * @param side the side to play
	 * 
	 * @return the legal moves for a given side
	 */
	List<LegalMoveInterface> getLegalMoves(final OpponentsEnumeration side);
	
	/**
	 * Returns the opponent correspondig to the given order.
	 * 
	 * @param side the given side
	 * 
	 * @return the opponent correspondig to the given order
	 */
	OpponentInterface getOpponentByOrder(final OpponentsEnumeration side);
	
	/**
	 * Returns the side to play.
	 * 
	 * @return the side to play
	 */
	OpponentsEnumeration getSideToPlay();
		
	/**
	 * Returns true if the gameplay is over, false otherwise.
	 * 
	 * @return true if the gameplay is over, false otherwise
	 */
	boolean isGamePlayOver();
	
	/**
	 * Plays a move.
	 * 
	 * @param the move to play
	 */
	void play(LegalMoveInterface move);
}