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

package fr.designpattern.zerosumgames.framework.service.gameplay.game;

import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.pieces.PieceInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.pieces.PieceTypeInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.positions.PositionInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

/**
 * This is the interface for a game.
 */
public interface GameInterface {

	/**
	 * Returns true if this game allows null move, false otherwise.
	 * 
	 * @return true if this game allows null move, false otherwise
	 */
	boolean hasNullMove();

	/**
	 * Returns the list of legal moves for a given side.
	 * 
	 * @param side a given side
	 * 
	 * @return the list of legal moves for a given side
	 */
	List<LegalMoveInterface> getLegalMoves(final OpponentsEnumeration side);

	/**
	 * Plays a given move and returns the next side to play.
	 * 
	 * @param move a given move
	 * 
	 * @return the next side to play
	 */
	OpponentsEnumeration play(final LegalMoveInterface move);

	/**
	 * Applies a given move and returns true if the move is completed, false otherwise.
	 * 
	 * @param move a given move
	 * 
	 * @return true if the move is completed, false otherwise
	 */
	boolean doMove(final LegalMoveInterface move);

	/**
	 * Undo a given move and returns true if the move is completely undone, false otherwise.
	 * 
	 * @param move the played move
	 * 
	 * @return true if the move is completely undone, false otherwise
	 */
	boolean undoMove(final LegalMoveInterface move);

	/**
	 * Returns true if the game is over from a victory, false otherwise.
	 * 
	 * @param move the just played move
	 * 
	 * @return true if the game is over from a victory, false otherwise
	 */
	boolean isGameOverFromVictory(final LegalMoveInterface move);

	/**
	 * Returns true if the game is over from a draw, false otherwise.
	 * 
	 * @param move the just played move
	 * 
	 * @return true if the game is over from a victory of the current player, false otherwise
	 */
	boolean isGameOverFromDraw(final LegalMoveInterface move);

	/**
	 * Returns
	 * FIRST_PLAYER, if it's first player's turn,
	 * SECOND_PLAYER, if it's second player's turn,
	 * NOT_FIRST_PLAYER if the second player is a winner,
	 * NOT_SECOND_PLAYER, if the first player is a winner,
	 * NO_ONE, if the game is a draw.
	 * 
	 * @param move the just played move
	 * 
	 * @param isMoveDone the boolean for the move completion
	 * 
	 * @return
	 * FIRST_PLAYER, if it's first player's turn,
	 * SECOND_PLAYER, if it's second player's turn,
	 * NOT_FIRST_PLAYER if the second player is a winner,
	 * NOT_SECOND_PLAYER, if the first player is a winner,
	 * NO_ONE, if the game is a draw
	 */
	OpponentsEnumeration computeNextSideToPlay(final LegalMoveInterface move, final boolean isMoveDone);

	/**
	 * Returns the computation of the static evaluation for a given move.
	 * 
	 * @param move a given move
	 * 
	 * @return the computation of the static evaluation for a given move
	 */
	double computeStaticEvaluation(final LegalMoveInterface move);

	// ---------------------------------------------------------------------
	// Façades
	// ---------------------------------------------------------------------

	/**
	 * Returns a piece of this game for a given player and a given type of piece.
	 * 
	 * @param player a given player
	 * 
	 * @param pieceType a given type of piece.
	 * 
	 * @return a piece of this game for a given player and a given type of piece
	 */
	PieceInterface piece(final OpponentsEnumeration player, final PieceTypeInterface pieceType);

	/**
	 * Returns the board cell related to a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the board cell related to a given position
	 */
	CellInterface cell(final PositionInterface position);

	/**
	 * Returns the board cell related to a given row and a given column.
	 * 
	 * @param clientRowIndex a given row
	 * 
	 * @param clientColumnIndex a given column
	 * 
	 * @return the board cell related to a given row and a given column
	 */
	CellInterface cell(final int clientRowIndex, final int clientColumnIndex);

}