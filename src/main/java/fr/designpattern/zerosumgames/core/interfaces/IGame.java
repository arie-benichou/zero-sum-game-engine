/*
 * @(#)IGame.java	0.99
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

import java.util.List;

import fr.designpattern.zerosumgames.core.types.GamePlayersEnumeration;

/**
 * This is the interface for a board game.
 * 
 * @author  Arie Benichou
 * @version 0.99, 01/03/2011
 */
public interface IGame {

	/**
     * Returns <tt>true</tt> if this game allows null move.
     * 
	 * @return <tt>true</tt> if this game allows null move
	 */
	boolean hasNullMove();

	/**
	 * Returns the list of legal moves for a player, given a board.
	 * 
	 * @param gameState the game board 
	 * @param side the side to move
	 * 
	 * @return the list of legal moves for a given a board and a given player, .
	 */
	List<IGameBoardMove> getLegalMoves(IGameBoard gameState, GamePlayersEnumeration side);

	/**
	 * Plays a move (or a part of a real move)
	 * 
	 * @param gameState the game board
	 * @param moveToPlay the move to play
	 * 
	 * @return if the move is completed
	 */
	boolean playMove(IGameBoard gameState, IGameBoardMove moveToPlay);
	
	/**
     * Returns <tt>true</tt> if this game is over.
     *  
	 * @param gameState the game board
	 * 
	 * @return <tt>true</tt> if this game is over
	 */
	GamePlayersEnumeration isGameOver(IGameBoard gameState, IGameBoardMove justPlayedMove);	
	
	/**
	 * Returns FIRST_PLAYER, if it's the first player turn,
	 * SECOND_PLAYER, if it's the second player turn,
	 * null if the game is over.
	 *  
	 * @param side the side to play
	 * @param isMoveCompleted is the move completed ?
	 * @param isGameOver is the game over ?
	 * 
	 * @return FIRST_PLAYER, if it's the first player turn, SECOND_PLAYER, if it's the second player turn, null if the game is over.
	 */
	//GamePlayersEnumeration whoShallPlay(GamePlayersEnumeration side, boolean isMoveCompleted, boolean isGameOver);	
	
	/**
	 * Apply game state transition.
	 *  
	 * @param gameState the game board
	 * @param moveToPlay the move to play
	 * 
	 * @return the ordinal corresponding to the next player who has to play
	 */
	GamePlayersEnumeration applyGameStateTransition(IGameBoard gameState, IGameBoardMove moveToPlay);
	
	/**
	 * Returns a piece of this game for a given player
	 * and a given type of piece.
	 * 
	 * (GamePieceFactory facade)
	 * 
	 * @param player the player
	 * 
	 * @param pieceType the type of piece.
	 * 
	 * @return a piece of this game for a given player and a given type of piece
	 */
	IGamePiece piece(GamePlayersEnumeration player, IGamePieceType pieceType);
	

	/**
	 * Returns the board cell for a given position.
	 * 
	 * @param position the position
	 * 
	 * @return the board cell for a given position
	 */
	IGameBoardCell getCell(IGameBoardPosition position);

	/**
	 * Returns the board cell at (row, column)
	 *  
	 * @param clientRowIndex the row index
	 * 
	 * @param clientColumnIndex the column index
	 * 
	 * @return the board cell at (row, column)
	 */
	IGameBoardCell getCell(int clientRowIndex, int clientColumnIndex);
	
	void reset();

	List<IGameBoardMove> getLegalMoves(GamePlayersEnumeration currentPlayer);

	GamePlayersEnumeration applyGameStateTransition(IGameBoardMove legalMoveToPlay);

	IGamePlayer getPlayer(GamePlayersEnumeration currentPlayer);
	
	boolean undo(IGameBoardMove move);
	
	double evaluate(IGameBoardMove move);

	GamePlayersEnumeration whoShallPlay(GamePlayersEnumeration side, boolean isMoveCompleted, GamePlayersEnumeration isGameOver);	

}