/*
 * @(#)IGame.java	0.999
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

package fr.designpattern.zerosumgames.framework.game;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.components.board.cells.IGameBoardCell;
import fr.designpattern.zerosumgames.framework.game.components.board.cells.pieces.IGamePiece;
import fr.designpattern.zerosumgames.framework.game.components.board.cells.pieces.IGamePieceType;
import fr.designpattern.zerosumgames.framework.game.components.board.positions.IGameBoardPosition;
import fr.designpattern.zerosumgames.framework.game.components.moves.IGameMove;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.GamePlayersEnumeration;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategy.IGameStrategy;

/**
 * This is the interface for a board game.
 * 
 * @author  Arie Benichou
 * @version 0.999, 21/03/2011
 */
public interface GameInterface {

	/**
     * Returns true if this game allows null move.
     * 
	 * @return true if this game allows null move
	 */
	boolean hasNullMove();
		
	/**
	 * Returns the list of legal moves for a side.
	 *  
	 * @param side the side to play
	 * 
	 * @return the list of legal moves
	 */
	List<IGameMove> getLegalMoves(final GamePlayersEnumeration side);
	
	
	/**
	 * Plays a move and returns true if the move is completed, 
	 * false otherwise.
	 * 
	 * @param moveToPlay the move to play
	 * 
	 * @return true if the move is completed, false otherwise
	 */
	boolean doMove(final IGameMove moveToPlay);
	
	/**
	 * Undo the played move and returns true if the move is completely undone, false otherwise.
	 * 
	 * @param playedMove the played move
	 * 
	 * @return true if the move is completely undone, false otherwise
	 */	
	boolean undoMove(final IGameMove playedMove);
	
	/**
	 * Returns true if the game is over from a victory of the current player, false otherwise.
	 * 
	 * @param playedMove the played move
	 * 
	 * @return true if the game is over from a victory of the current player, false otherwise
	 */
	boolean isGameOverFromVictory(final IGameMove playedMove);

	/**
	 * Returns true if the game is over from a draw, false otherwise.
	 * 
	 * @param playedMove the played move
	 * 
	 * @return true if the game is over from a victory of the current player, false otherwise
	 */	
	boolean isGameOverFromDraw(final IGameMove playedMove);
	
	/**
	 * Returns FIRST_PLAYER, if it's the first player turn,
	 * SECOND_PLAYER, if it's the second player turn,
	 * NOT_FIRST_PLAYER if SECOND_PLAYER is winner,
	 * NOT_SECOND_PLAYER, if FIRST_PLAYER is winner,
	 * NO_ONE, if the game is a draw.
	 *  
	 * @param side the side to play
	 * @param isMoveCompleted is the move completed ?
	 * @param isGameOver is the game over ?
	 * 
	 * @return FIRST_PLAYER, if it's the first player turn,
	 * SECOND_PLAYER, if it's the second player turn,
	 * NOT_FIRST_PLAYER if SECOND_PLAYER is winner,
	 * NOT_SECOND_PLAYER, if FIRST_PLAYER is winner,
	 * NO_ONE, if the game is a draw
	 */
	GamePlayersEnumeration whoShallPlay(final IGameMove playedMove, final boolean isMoveDone);			
	
	/**
	 * Returns the computation of the move evaluation of the game.
	 *  
	 * @param playedMove the played move
	 * 
	 * @return the computation of the move evaluation of the game
	 */
	double evaluate(final IGameMove playedMove);
	
	// ---------------------------------------------------------------------
	// Façades
	// ---------------------------------------------------------------------
	
	IGameStrategy getPlayerStrategy(final GamePlayersEnumeration currentPlayer);
	
	/**
	 * Returns a piece of this game for a given player
	 * and a given type of piece.
	 * 
	 * @param player the player
	 * 
	 * @param pieceType the type of piece.
	 * 
	 * @return a piece of this game for a given player and a given type of piece
	 */
	IGamePiece piece(final GamePlayersEnumeration player, final IGamePieceType pieceType);

	/**
	 * Returns the board cell for a given position.
	 * 
	 * @param position the position
	 * 
	 * @return the board cell for a given position
	 */
	IGameBoardCell cell(final IGameBoardPosition position);

	/**
	 * Returns the board cell at (row, column)
	 *  
	 * @param clientRowIndex the row index
	 * 
	 * @param clientColumnIndex the column index
	 * 
	 * @return the board cell at (row, column)
	 */
	IGameBoardCell cell(final int clientRowIndex, final int clientColumnIndex);

	
}