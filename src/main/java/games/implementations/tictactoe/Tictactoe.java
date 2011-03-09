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

package main.java.implementations.tictactoe;

import java.util.ArrayList;
import java.util.List;

import main.java.core.AbstractGame;
import main.java.core.GameBoardDimension;
import main.java.core.GameBoardMove;
import main.java.core.GameBuilder;
import main.java.core.GamePieceFactory;
import main.java.core.interfaces.IGameBoard;
import main.java.core.interfaces.IGameBoardCell;
import main.java.core.interfaces.IGameBoardMove;
import main.java.core.interfaces.IGamePlayer;
import main.java.core.types.GameBoardCardinalPosition;
import main.java.core.types.GameBoardPlane;
import main.java.core.types.GamePlayersEnumeration;
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
		int n;
		final IGameBoardCell cell = this.getCell(justPlayedMove.getPosition());		
		IGameBoardCell neighbour = cell.getNeighbour(direction);
		for (n = 1; n < this.connections; n++) {
			if (neighbour.isNull() || neighbour.isEmpty()) {
				break;
			}
			if (neighbour.getPiece().getSide() != justPlayedMove.getSide()) {
				break;
			}
			neighbour = neighbour.getNeighbour(direction);
		}
		return n - 1;
	}	
	// ------------------------------------------------------------
	protected boolean isWinningMove(final IGameBoardMove justPlayedMove) {
		int connections;
		for (GameBoardPlane plane : GameBoardPlane.values()) {
			connections = 1;
			connections += this.countConnections(justPlayedMove, plane.getOneWay());
			connections += this.countConnections(justPlayedMove, plane.getOppositeWay());
			if (connections >= this.connections) {
				return true;
			}
		}
		return false;
	}	
	// ------------------------------------------------------------
	@Override
	public boolean isGameOver(final IGameBoard gameState, final IGameBoardMove justPlayedMove) {
		if (this.isWinningMove(justPlayedMove)) {
			return true;
		}
		return super.isGameOver(gameState, justPlayedMove);
	}
	// -----------------------------------------------------------------	
	public GamePlayersEnumeration applyGameStateTransition(final IGameBoard gameState, final IGameBoardMove justPlayedMove) {
		if (justPlayedMove.isNull()) {
			// TODO ! à améliorer			
			return super.applyGameStateTransition(gameState, justPlayedMove);
		}
		// TODO ! méthode playMove
		final IGameBoardCell concernedCell = this.getCell(justPlayedMove.getPosition());
		// TODO façade
		concernedCell.setPiece(this.piece(justPlayedMove.getSide(), TictactoePieceTypes.PAWN));
		// TODO ! à améliorer		
		if(this.isGameOver(gameState, justPlayedMove)) {
			return null;
		}
		// TODO ! à améliorer
		return super.applyGameStateTransition(gameState, justPlayedMove);
	}
	// ------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public static void main(final String[] args){
		new GameBuilder(StaticContext.thatClass()).build().start();
	}
	// ------------------------------------------------------------
}