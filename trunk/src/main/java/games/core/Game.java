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

package main.java.games.core;

import java.util.List;

import main.java.games.core.interfaces.IGame;
import main.java.games.core.interfaces.IGameBoard;
import main.java.games.core.interfaces.IGameBoardCell;
import main.java.games.core.interfaces.IGameBoardMove;
import main.java.games.core.interfaces.IGameBoardPosition;
import main.java.games.core.interfaces.IGamePiece;
import main.java.games.core.interfaces.IGamePieceFactory;
import main.java.games.core.interfaces.IGamePieceType;
import main.java.games.core.interfaces.IGamePlayer;
import main.java.games.core.types.GamePlayersEnumeration;

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
	@Override
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
	@Override
	public GamePlayersEnumeration whoShallPlay(final GamePlayersEnumeration side, final boolean isMoveCompleted, final boolean isGameOver) {
		GamePlayersEnumeration sideToPlay = null;
		if(!isGameOver) {
			if(isMoveCompleted) {
				sideToPlay = side.getOpponent();
			}
			else {
				//System.out.println("Tu dois continuer à jouer...");
				sideToPlay = side;	
			}
		}
		return sideToPlay;
	}
	// -----------------------------------------------------------------
	@Override
	public abstract boolean playMove(IGameBoard gameState, IGameBoardMove moveToPlay);	
	// -----------------------------------------------------------------	
	@Override
	public GamePlayersEnumeration applyGameStateTransition(final IGameBoard gameState, final IGameBoardMove moveToPlay) {
		return this.whoShallPlay(moveToPlay.getSide(), this.playMove(gameState, moveToPlay), this.isGameOver(gameState, moveToPlay));
	}
	// ------------------------------------------------------------		
	@Override
	public void reset() {
		this.setupBoard(this.getBoard());
	}
	// ---------------------------------------------------------------------
	@Override	
	public abstract boolean hasNullMove();
	// ---------------------------------------------------------------------	
	// façades
	// ---------------------------------------------------------------------
	@Override
	public List<IGameBoardMove> getLegalMoves(final GamePlayersEnumeration currentPlayer) {
		return this.getLegalMoves(this.getBoard(), currentPlayer);
	}
	// ---------------------------------------------------------------------
	@Override	
	public GamePlayersEnumeration applyGameStateTransition(final IGameBoardMove moveToPlay) {
		return this.applyGameStateTransition(this.getBoard(), moveToPlay);
	}
	// ---------------------------------------------------------------------
	@Override
	public IGamePiece piece(final GamePlayersEnumeration player, final IGamePieceType pieceType) {
		return this.getPieceFactory().getPiece(player, pieceType);
	}
	@Override
	public IGameBoardCell getCell(final IGameBoardPosition position) {
		return this.getBoard().getCell(position);
	}
	// ---------------------------------------------------------------------
	@Override
	public IGameBoardCell getCell(final int clientRowIndex, final int clientColumnIndex) {
		return this.getBoard().getCell(clientRowIndex, clientColumnIndex);
	}
	// ---------------------------------------------------------------------
	@Override
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