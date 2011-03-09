/*
 * @(#)GamePlayersEnumeration.java	0.99
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

package core;

import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import core.interfaces.IGame;
import core.interfaces.IGameBoard;
import core.interfaces.IGameBoardCell;
import core.interfaces.IGameBoardPosition;
import core.interfaces.IGameBoardMove;
import core.interfaces.IGamePiece;
import core.interfaces.IGamePieceFactory;
import core.interfaces.IGamePieceType;
import core.interfaces.IGamePlayer;
import core.interfaces.IGamePlayerStrategy;
import core.types.GamePlayersEnumeration;

/**
 * This class provides a skeletal implementation of the Game
 * interface, to minimize the effort required to implement this interface.
 * 
 * @author  Arie Benichou
 * @version 0.99, 01/03/2011
 */
public abstract class AbstractGame implements IGame {
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

	public final IGamePlayer getOpponent(final GamePlayersEnumeration playerTurn) {
		return this.opponents.get(1 - playerTurn.ordinal());
	}

	public final IGamePlayer getOpponentToPlayer(final IGamePlayer player) {
		return this.getOpponent(player.getOrder());
	}

	public final IGamePlayer getPlayer(final GamePlayersEnumeration playerTurn) {
		return this.opponents.get(playerTurn.ordinal());
	}
	// ---------------------------------------------------------------------
	protected void setupInitialGameState() { // TODO à revoir
	}
	// ---------------------------------------------------------------------
	public AbstractGame(final IGamePieceFactory pieceFactory, final IGameBoard board, final List<IGamePlayer> opponents) {
		this.setPieceFactory(pieceFactory);		
		this.setBoard(board);
		this.setOpponents(opponents);
	}
	// ---------------------------------------------------------------------
	// TODO créer IGameLegalMoveList
	protected void displayLegalMoveList(final GamePlayersEnumeration currentPlayer, final List<IGameBoardMove> legalMoveList) {
		int n = 0;		
		int numberOfDigits = (int) Math.log10(Math.abs(legalMoveList.size())) + 1;
		//System.out.println("\n" + currentPlayerOrdinal + " legal moves :");
		System.out.println("\nlegal moves :");
		for (IGameBoardMove legalMove : legalMoveList) {
			System.out.format("#%0" + numberOfDigits + "d: %s\n", ++n, legalMove);
		}
	}
	// ---------------------------------------------------------------------
	@Override
	public GamePlayersEnumeration applyGameStateTransition(final IGameBoard gameState, final IGameBoardMove moveToPlay) {
		// TODO à revoir
		return this.whoShallPlay(gameState, moveToPlay.getSide());
	}	
	// ---------------------------------------------------------------------
	// TODO utiliser un thread pour le client lourd
	// TODO pas de boucle pour la version client léger	
	@Override
	public void start() {

		GamePlayersEnumeration currentPlayer = GamePlayersEnumeration.FIRST_PLAYER;
		
		this.setupInitialGameState();
		
		System.out.println(this.getBoard());
		
		IGamePlayerStrategy playerStrategy;
		List<IGameBoardMove> legalMoves;
		IGameBoardMove legalMoveToPlay;
		
		do {
			
			legalMoves = this.getLegalMoves(this.getBoard(), currentPlayer);
			this.displayLegalMoveList(currentPlayer, legalMoves);
			
			playerStrategy = this.getPlayer(currentPlayer).getStrategy();			
			legalMoveToPlay = playerStrategy.chooseMoveAmong(legalMoves);
			
			//TODO ? utiliser GamePlayersEnumeration.NONE
			currentPlayer = this.applyGameStateTransition(this.getBoard(), legalMoveToPlay);
			
			System.out.println(this.getBoard());
			
		} while (currentPlayer != null);
		
	}
	// ---------------------------------------------------------------------
	@Override
	public GamePlayersEnumeration whoShallPlay(IGameBoard gameState,
			GamePlayersEnumeration currentPlayerOrdinal) {
		if (currentPlayerOrdinal.equals(GamePlayersEnumeration.FIRST_PLAYER)) {
			return GamePlayersEnumeration.SECOND_PLAYER;
		}
		return GamePlayersEnumeration.FIRST_PLAYER;
	}
	// ------------------------------------------------------------
	@Override
	public boolean hasNullMove() {
		return false;
	}
	// ---------------------------------------------------------------------
	@Override
	public boolean isGameOver(IGameBoard gameState, IGameBoardMove justPlayedMove) {
		// TODO ! à optimiser
		// TODO définir une méthode "hasLegalMove"
		// TODO ?! retourner false pour l'implémentation par défaut
		if (this.hasNullMove()) {
			return this.getLegalMoves(this.getBoard(),
					GamePlayersEnumeration.FIRST_PLAYER).size() == 1
					&& this.getLegalMoves(this.getBoard(),
							GamePlayersEnumeration.SECOND_PLAYER).size() == 1;
		}
		return this.getLegalMoves(this.getBoard(),
				GamePlayersEnumeration.FIRST_PLAYER).isEmpty()
				&& this.getLegalMoves(this.getBoard(),
						GamePlayersEnumeration.SECOND_PLAYER).isEmpty();
	}
	// ---------------------------------------------------------------------
	// façades
	// ---------------------------------------------------------------------	
	@Override
	public IGamePiece piece(GamePlayersEnumeration player, IGamePieceType pieceType) {
		return this.getPieceFactory().getPiece(player, pieceType);
	}
	@Override
	public IGameBoardCell getCell(IGameBoardPosition position) {
		return this.getBoard().getCell(position);
	}
	// ---------------------------------------------------------------------
	@Override
	public IGameBoardCell getCell(int clientRowIndex, int clientColumnIndex) {
		return this.getBoard().getCell(clientRowIndex, clientColumnIndex);
	}
	// ---------------------------------------------------------------------
	@Override
	public void pause() {
		throw new NotImplementedException();
	}
	// ---------------------------------------------------------------------	
	@Override
	public void resume() {
		throw new NotImplementedException();
	}
	// ---------------------------------------------------------------------
	@Override
	public void stop() {
		throw new NotImplementedException();
	}
	// ---------------------------------------------------------------------
	@Override
	public void reset() {
		throw new NotImplementedException();
	}
	// ---------------------------------------------------------------------
}