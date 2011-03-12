/*
 * @(#)IGamePieceFactory.java	0.99
 * 
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

package fr.designpattern.zerosumgames.core.interfaces;

import fr.designpattern.zerosumgames.core.types.GamePlayersEnumeration;

/**
 * This is the interface for the game piece factory.
 * 
 * @author Arie Benichou
 * @version 0.99, 01/03/2011
 */
public interface IGamePieceFactory {

	/**
	 * Returns a piece for a given player and a given type of piece.
	 * 
	 * @param player a given player
	 * 
	 * @param pieceType a given type of piece
	 * 
	 * @return a piece for a given player and a given type of piece
	 */
	IGamePiece getPiece(GamePlayersEnumeration player, IGamePieceType pieceType);
	
	//IGamePiece createPiece(IGamePieceType type, GamePlayersEnumeration side);	

}