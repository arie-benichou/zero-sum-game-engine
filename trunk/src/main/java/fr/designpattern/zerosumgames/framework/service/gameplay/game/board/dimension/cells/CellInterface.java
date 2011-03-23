/*
 * @(#)IGameBoardCell.java	0.99
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

package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells;

import java.util.Map;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.BoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.pieces.PieceInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.positions.PositionInterface;
import fr.designpattern.zerosumgames.util.NullObjectAvailableInterface;

/**
 * This is the interface for the game board cells.
 * 
 * @author Arie Benichou
 * @version 0.99, 01/03/2011
 */
public interface CellInterface extends NullObjectAvailableInterface, Cloneable {
	
	/**
	 * Returns <tt>true</tt> if this cell is empty.
	 * 
	 * @return <tt>true</tt> if this cell is empty
	 */
	boolean isEmpty();

	/**
	 * Returns the piece contained by this cell, null if the cell is empty.
	 * 
	 * TODO retourner un null object IGamePiece
	 * 
	 * @return the piece contained by this cell, null if the cell is empty
	 */
	PieceInterface getPiece();

	/**
	 * Assigns a piece to this cell.
	 * 
	 * @param piece the piece to be contained by this cell
	 */
	void setPiece(PieceInterface piece);

	/**
	 * Returns the position of this cell.
	 * 
	 * @return the position of this cell
	 */
	PositionInterface getPosition();

	/**
	 * Returns the neighbours cells of this cell.
	 * 
	 * @return the neighbours cells of this cell
	 */
	Map<BoardCardinalPosition, CellInterface> getNeighbourhood();

	/**
	 * Returns a neighbour cell of this cell for a given direction.
	 * 
	 * @param direction the direction
	 * 
	 * @return a neighbour cell of this cell for a given direction
	 */
	CellInterface getNeighbour(BoardCardinalPosition direction);

	/**
	 * Returns the top-neighbour cell of this cell.
	 * 
	 * @return the top-neighbour cell of this cell
	 */
	 // -------------
	 // |   | x |   |
	 // -------------
	 // |   | . |   |
	 // -------------
	 // |   |   |   |
	 // -------------
	CellInterface top();

	/**
	 * Returns the right-neighbour cell of this cell.
	 * 
	 * @return the right-neighbour cell of this cell
	 */
	 // -------------
	 // |   |   |   |
	 // -------------
	 // |   | . | x |
	 // -------------
	 // |   |   |   |
	 // -------------	
	CellInterface right();

	/**
	 * Returns the bottom-neighbour cell of this cell.
	 * 
	 * @return the bottom-neighbour cell of this cell
	 */
	 // -------------
	 // |   |   |   |
	 // -------------
	 // |   | . |   |
	 // -------------
	 // |   | x |   |
	 // -------------
	CellInterface bottom();

	/**
	 * Returns the left-neighbour cell of this cell.
	 * 
	 * @return the left-neighbour cell of this cell
	 */	
	 // -------------
	 // |   |   |   |
	 // -------------
	 // | x | . |   |
	 // -------------
	 // |   |   |   |
	 // -------------
	CellInterface left();

	/**
	 * Returns the top-right-neighbour cell of this cell.
	 * 
	 * @return the top-right-neighbour cell of this cell
	 */
	 // -------------
	 // |   |   | x |
	 // -------------
	 // |   | . |   |
	 // -------------
	 // |   |   |   |
	 // -------------
	CellInterface topRight();

	/**
	 * Returns the top-left-neighbour cell of this cell.
	 * 
	 * @return the top-left-neighbour cell of this cell
	 */	
	 // -------------
	 // | x |   |   |
	 // -------------
	 // |   | . |   |
	 // -------------
	 // |   |   |   |
	 // -------------
	CellInterface topLeft();
	
	/**
	 * Returns the bottom-right-neighbour cell of this cell.
	 * 
	 * @return the bottom-right-neighbour cell of this cell
	 */	
	 // -------------
	 // |   |   |   |
	 // -------------
	 // |   | . |   |
	 // -------------
	 // |   |   | x |
	 // -------------
	CellInterface bottomRight();

	/**
	 * Returns the bottom-left-neighbour cell of this cell.
	 * 
	 * @return the bottom-left-neighbour cell of this cell
	 */
	 // -------------
	 // |   |   |   |
	 // -------------
	 // |   | . |   |
	 // -------------
	 // | x |   |   |
	 // -------------
	CellInterface bottomLeft();

}