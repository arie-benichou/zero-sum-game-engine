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

package core.interfaces;

import java.util.List;

import core.types.GamePlayersEnumeration;

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
	 * Returns the player ordinal.
	 * 
	 * @param gameState the game board (TODO revoir la légitimité du paramètre)
	 * @param currentPlayerOrdinal the player ordinal
	 * 
	 * @return the player ordinal
	 */
	GamePlayersEnumeration whoShallPlay(IGameBoard gameState, GamePlayersEnumeration currentPlayerOrdinal);

	/**
	 * Returns the list of legal moves for a player, given a board.
	 * 
	 * @param gameState the game board 
	 * @param side the side to move
	 * 
	 * @return the list of legal moves for a player, given a board.
	 */
	List<IGameBoardMove> getLegalMoves(IGameBoard gameState, GamePlayersEnumeration side);

	/**
	 * Plays a move.
	 * 
	 * @param legalMove 
	 */
	//void play(IGameBoardMove legalMove);
	
	/**
	 * Returns the last played move.
	 * 
	 * @return
	 */
	//IGameBoardMove getLastPlayedMove();

	/**
	 * Apply game state transition.
	 * 
	 * @param playedMove the played move
	 */
	//void applyGameStateTransition(IGameBoardMove playedMove);
	
	GamePlayersEnumeration applyGameStateTransition(IGameBoard gameState, IGameBoardMove legalMoveChoosenByCurrentPlayer);

	/**
     * Returns <tt>true</tt> if this game is over.
     *  
	 * @param gameState the game board
	 * 
	 * @return <tt>true</tt> if this game is over
	 */
	//boolean isGameOver(IGameBoard gameState);
	boolean isGameOver(IGameBoard gameState, IGameBoardMove justPlayedMove);
	
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


	
	IGameBoardCell getCell(IGameBoardPosition position);
	IGameBoardCell getCell(int clientRowIndex, int clientColumnIndex);
	
	

	/**
	 * Starts a new game play. 
	 */
	void start();

	/**
	 * Pauses this game play. 
	 */
	void pause();
	
	/**
	 * Resumes from pause.
	 */
	void resume();	

	/**
	 * Stops this game play.
	 */
	void stop();

	/**
	 * Stops this game play and starts a new one.
	 */
	void reset();
	

	//GamePlayersEnumeration getCurrentPlayerOrdinal();

}