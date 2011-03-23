/*
 * @(#)IGamePiece.java	0.99
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

package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.pieces;

//TODO !! définir les types de pièces dans le jeu et la texture peut changer au runtime
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

/**
 * This is the interface for a game piece.
 * 
 * TODO ? mapping : String getSymbol() / int getInternalValue() 
 * 
 * @author Arie Benichou
 * @version 0.99, 01/03/2011
 */
public interface PieceInterface {

	/**
	 * Returns the type of this piece.
	 *  
	 * @return the type of this piece
	 */
	PieceTypeInterface getType();

	/**
	 * Returns the player side of this piece.
	 *  
	 * @return the player side of this piece
	 */
	OpponentsEnumeration getSide();

}