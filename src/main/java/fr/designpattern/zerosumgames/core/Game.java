/*
 * @(#)Game.java	0.99
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

package fr.designpattern.zerosumgames.core;

import java.util.List;

import fr.designpattern.zerosumgames.core.interfaces.IGame;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoard;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardCell;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardMove;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardPosition;
import fr.designpattern.zerosumgames.core.interfaces.IGamePiece;
import fr.designpattern.zerosumgames.core.interfaces.IGamePieceFactory;
import fr.designpattern.zerosumgames.core.interfaces.IGamePieceType;
import fr.designpattern.zerosumgames.core.interfaces.IGamePlayer;
import fr.designpattern.zerosumgames.core.types.GamePlayersEnumeration;

/**
 * This class provides a skeletal implementation of the Game
 * interface, to minimize the effort required to implement this interface.
 * 
 * @author  Arie Benichou
 * @version 0.99, 01/03/2011
 */
public abstract class Game implements IGame {
	// ---------------------------------------------------------------------
	private IGamePieceFactory pieceFactory;
	protected final IGamePieceFactory getPieceFactory() {
		return this.pieceFactory;
	}
	private final void setPieceFactory(final IGamePieceFactory gamePieceFactory) {
		this.pieceFactory = gamePieceFactory;
	}
	// ---------------------------------------------------------------------
	private IGameBoard board;
	protected IGameBoard getBoard() {
		return this.board;
	}
	private final void setBoard(final IGameBoard board) {
		this.board = board;
	}
	// ---------------------------------------------------------------------
	private List<IGamePlayer> opponents;
	public List<IGamePlayer> getOpponents() {
		return opponents;
	}
	private final void setOpponents(final List<IGamePlayer> opponents) {
		this.opponents = opponents;
	}
	// ---------------------------------------------------------------------
	public final IGamePlayer getPlayer(final GamePlayersEnumeration playerTurn) {
		return this.opponents.get(playerTurn.ordinal());
	}
	// ---------------------------------------------------------------------
	// TODO à revoir
	protected IGameBoard setupBoard(final IGameBoard board) {
		return board;
	}
	// ---------------------------------------------------------------------
	public Game(final IGamePieceFactory pieceFactory, final IGameBoard board, final List<IGamePlayer> opponents) {
		this.setPieceFactory(pieceFactory);		
		this.setBoard(board);
		this.setOpponents(opponents);
	}
	// ---------------------------------------------------------------------
	// TODO ! améliorer isMoveCompleted doit venir après is GameOver
	public GamePlayersEnumeration whoShallPlay(final GamePlayersEnumeration side, final boolean isMoveCompleted, final GamePlayersEnumeration isGameOver) {
		
		//GamePlayersEnumeration sideToPlay;
		
		// TODO rajouter dans GamePlayersEnumeration
		if(isGameOver == GamePlayersEnumeration.NONE) {
			return isGameOver;
		}
		
		if(isGameOver == GamePlayersEnumeration.NOT_FIRST_PLAYER) {
			return isGameOver;
		}
		
		if(isGameOver == GamePlayersEnumeration.NOT_SECOND_PLAYER) {
			return isGameOver;
		}				
		
		if(!isMoveCompleted) {
			return side;
		}
		
		return side.getOpponent();
	}
	// -----------------------------------------------------------------
	public abstract boolean playMove(IGameBoard gameState, IGameBoardMove moveToPlay);	
	// -----------------------------------------------------------------	
	public GamePlayersEnumeration applyGameStateTransition(final IGameBoard gameState, final IGameBoardMove moveToPlay) {
		//System.out.println(moveToPlay);
		return this.whoShallPlay(moveToPlay.getSide(), this.playMove(gameState, moveToPlay), this.isGameOver(gameState, moveToPlay));
	}
	// ------------------------------------------------------------		
	public void reset() {
		this.setupBoard(this.getBoard());
	}
	// ---------------------------------------------------------------------
	public abstract boolean hasNullMove();
	// ---------------------------------------------------------------------	
	// façades
	// ---------------------------------------------------------------------
	public List<IGameBoardMove> getLegalMoves(final GamePlayersEnumeration currentPlayer) {
		return this.getLegalMoves(this.getBoard(), currentPlayer);
	}
	// ---------------------------------------------------------------------
	public GamePlayersEnumeration applyGameStateTransition(final IGameBoardMove moveToPlay) {
		return this.applyGameStateTransition(this.getBoard(), moveToPlay);
	}
	// ---------------------------------------------------------------------
	public IGamePiece piece(final GamePlayersEnumeration player, final IGamePieceType pieceType) {
		return this.getPieceFactory().getPiece(player, pieceType);
	}
	public IGameBoardCell getCell(final IGameBoardPosition position) {
		return this.getBoard().getCell(position);
	}
	// ---------------------------------------------------------------------
	public IGameBoardCell getCell(final int clientRowIndex, final int clientColumnIndex) {
		return this.getBoard().getCell(clientRowIndex, clientColumnIndex);
	}
	// ---------------------------------------------------------------------
	public String toString() {
		return this.getBoard().toString();
	}
	// ---------------------------------------------------------------------
	/*
	@Override
	public void pause() {
		System.out.println("Not Implemented");
	}
	// ---------------------------------------------------------------------	
	@Override
	public void resume() {
		System.out.println("Not Implemented");
	}
	// ---------------------------------------------------------------------
	@Override
	public void stop() {
		System.out.println("Not Implemented");
	}
	// ---------------------------------------------------------------------
	@Override
	public void reset() {
		System.out.println("Not Implemented");
	}
	*/
	// ---------------------------------------------------------------------
}