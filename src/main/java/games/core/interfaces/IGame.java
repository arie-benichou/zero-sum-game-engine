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

package main.java.games.core.interfaces;

import java.util.List;

import main.java.games.core.types.GamePlayersEnumeration;

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
	 * Apply game state transition.
	 *  
	 * @param gameState
	 * @param legalMoveChoosen the move to play
	 * 
	 * @return
	 */
	GamePlayersEnumeration applyGameStateTransition(IGameBoard gameState, IGameBoardMove legalMoveChoosen);

	/**
     * Returns <tt>true</tt> if this game is over.
     *  
	 * @param gameState the game board
	 * 
	 * @return <tt>true</tt> if this game is over
	 */
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
	void start(); // TODO à mettre dans GameService

	/**
	 * Pauses this game play. 
	 */
	//void pause();
	 // TODO à mettre dans GameService
	
	/**
	 * Resumes from pause.
	 */
	//void resume();	
	 // TODO à mettre dans GameService

	/**
	 * Stops this game play.
	 */
	//void stop();
	 // TODO à mettre dans GameService

	/**
	 * Stops this game play and starts a new one.
	 */
	//void reset();
	 // TODO à mettre dans GameService

	GamePlayersEnumeration whoShallPlay(GamePlayersEnumeration side, boolean isMoveCompleted, boolean isGameOver);

	boolean playMove(IGameBoard gameState, IGameBoardMove moveToPlay);

}