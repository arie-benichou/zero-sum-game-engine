/*
 * @(#)IGameLegalMove.java	0.99
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

package main.java.games.core.interfaces;

import main.java.games.core.types.GamePlayersEnumeration;

/**
 * This is the interface for a game move.
 * 
 * TODO ? faire une factory
 * TODO ! implémenter NullMove
 * 
 * @author Arie Benichou
 * @version 0.99, 01/03/2011
 */
// TODO ajouter des façades
// TODO implémenter le jeu de de la vie
public interface IGameBoardMove extends IGameNullableComponent {

	/**
	 * Returns the concerned piece.
	 * 
	 * @return the concerned piece
	 */
	//IGamePiece getConcernedPiece();

	/**
	 * Returns the position.
	 * 
	 * @return the position
	 */
	IGameBoardPosition getPosition();

	GamePlayersEnumeration getSide();

}