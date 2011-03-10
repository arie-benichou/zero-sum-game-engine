/*
 * @(#)Tictactoe.java	0.99
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

package main.java.games.implementations.tictactoe;

import java.util.ArrayList;
import java.util.List;

import main.java.games.core.AbstractGame;
import main.java.games.core.GameBoardDimension;
import main.java.games.core.GameBoardMove;
import main.java.games.core.GameBuilder;
import main.java.games.core.GamePieceFactory;
import main.java.games.core.interfaces.IGameBoard;
import main.java.games.core.interfaces.IGameBoardCell;
import main.java.games.core.interfaces.IGameBoardMove;
import main.java.games.core.interfaces.IGamePlayer;
import main.java.games.core.types.GameBoardCardinalPosition;
import main.java.games.core.types.GameBoardPlane;
import main.java.games.core.types.GamePlayersEnumeration;
import main.java.util.StaticContext;

public class Tictactoe extends AbstractGame {
	// ------------------------------------------------------------	
	private int connections = 3; // TODO à revoir
	public final static Class<TictactoePieceTypes> PIECE_TYPES = TictactoePieceTypes.class;
	public final static GameBoardDimension BOARD_DIMENSION = new GameBoardDimension(1, 3, 1, 3); 
	// ------------------------------------------------------------
	public Tictactoe(final IGameBoard board, final List<IGamePlayer> opponents) {
		// TODO !! à revoir		
		super(new GamePieceFactory(PIECE_TYPES), board, opponents);
		
	}
	public Tictactoe(final IGameBoard board, final List<IGamePlayer> opponents, final int connections) {
		this(board, opponents);
		this.connections = connections;
	}	
	// -----------------------------------------------------------------
	@Override
	public List<IGameBoardMove> getLegalMoves(final IGameBoard board, final GamePlayersEnumeration side) {
		final List<IGameBoardMove> legalMoves = new ArrayList<IGameBoardMove>();
		for (IGameBoardCell[] line : this.getBoard()) {
			for(IGameBoardCell cell : line) {
				if(cell.isEmpty()) { // TODO ? isPlayable()
					// TODO méthode de création
					legalMoves.add(new GameBoardMove(side, cell.getPosition()));
				}
			}
		}
		return legalMoves;
	}
	// ------------------------------------------------------------	
	protected int countConnections(final IGameBoardMove justPlayedMove, final GameBoardCardinalPosition direction) {
		int connected;
		IGameBoardCell cell = this.getCell(justPlayedMove.getPosition());
		for (connected = 1; connected < this.connections; ++connected) {
			cell = cell.getNeighbour(direction);
			if (cell.isNull() || cell.isEmpty() || cell.getPiece().getSide() != justPlayedMove.getSide()) {
				break;
			}
		}
		return --connected;
	}	
	// ------------------------------------------------------------
	protected boolean isWinningMove(final IGameBoardMove justPlayedMove) {
		boolean isWinningMove = false;
		for (GameBoardPlane plane : GameBoardPlane.values()) {
			final int connections =
				this.countConnections(justPlayedMove, plane.getOneWay())
				+ 1
				+ this.countConnections(justPlayedMove, plane.getOppositeWay());
			if (connections >= this.connections) {
				isWinningMove = true;
				break;
			}
		}
		return isWinningMove;
	}	
	// ------------------------------------------------------------
	@Override
	public boolean isGameOver(final IGameBoard gameState, final IGameBoardMove justPlayedMove) {
		boolean isGameOver = false;
		if(this.isWinningMove(justPlayedMove)) {
			isGameOver = true;
		}
		else {
			// Suite à ce coup, si l'adversaire...			
			final GamePlayersEnumeration oppositeSide = this.getOpponent(justPlayedMove.getSide()).getOrder(); // TODO améliorer l'API à ce niveau
			// ne peut plus jouer
			if(this.getLegalMoves(this.getBoard(), oppositeSide).isEmpty()) {
				isGameOver = true;
			}
		}
		return isGameOver;
	}
	// -----------------------------------------------------------------	
	public GamePlayersEnumeration applyGameStateTransition(final IGameBoard gameState, final IGameBoardMove moveToPlay) {
		
		GamePlayersEnumeration nextSideToPlay = null;
		
		if (!moveToPlay.isNull()) {
			// TODO ! méthode playMove
			final IGameBoardCell concernedCell = this.getCell(moveToPlay.getPosition());
			concernedCell.setPiece(this.piece(moveToPlay.getSide(), TictactoePieceTypes.PAWN)); // TODO façade			
		}
		
		if(!this.isGameOver(gameState, moveToPlay)) {
			// TODO appeler who shall play
			nextSideToPlay = super.applyGameStateTransition(gameState, moveToPlay);
		}			
		
		return nextSideToPlay;
	}
	// ------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public static void main(final String[] args){
		
		new GameBuilder(StaticContext.thatClass()).build().start();
		
		/*
		final GameBuilder gb = new GameBuilder(StaticContext.thatClass());
		gb.boardDimension(new GameBoardDimension(1, 4, 1, 4));
		gb.build().start();
		*/		
		
	}
	// ------------------------------------------------------------
}