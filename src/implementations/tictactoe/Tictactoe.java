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

package implementations.tictactoe;

import java.util.ArrayList;
import java.util.List;

import core.AbstractGame;
import core.GameBoardDimension;
import core.GameBoardMove;
import core.GameBuilder;
import core.GamePieceFactory;
import core.interfaces.IGameBoard;
import core.interfaces.IGameBoardCell;
import core.interfaces.IGameBoardMove;
import core.interfaces.IGamePlayer;
import core.types.GameBoardCardinalPosition;
import core.types.GameBoardPlane;
import core.types.GamePlayersEnumeration;
import util.StaticContext;

public class Tictactoe extends AbstractGame {
	// ------------------------------------------------------------	
	private int connections = 3; // TODO à revoir
	public final static Class<TictactoePieceTypes> PIECE_TYPES = TictactoePieceTypes.class;
	public final static GameBoardDimension BOARD_DIMENSION = new GameBoardDimension(1, 3, 1, 3); 
	// ------------------------------------------------------------
	public Tictactoe(IGameBoard board, List<IGamePlayer> opponents) {
		// TODO !! à revoir		
		super(new GamePieceFactory(PIECE_TYPES), board, opponents);
		
	}
	public Tictactoe(IGameBoard board, List<IGamePlayer> opponents, int numberOfPawnsToConnect) {
		this(board, opponents);
		this.connections = numberOfPawnsToConnect;
	}	
	// -----------------------------------------------------------------
	@Override
	public List<IGameBoardMove> getLegalMoves(IGameBoard board, GamePlayersEnumeration side) {
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
	protected int countConnections(IGameBoardMove justPlayedMove, GameBoardCardinalPosition direction) {
		IGameBoardCell neighbour, cell = this.getCell(justPlayedMove.getPosition());
		int n;
		neighbour = cell.getNeighbour(direction);
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
	protected boolean isWinningMove(IGameBoardMove justPlayedMove) {
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
	public boolean isGameOver(IGameBoard gameState, IGameBoardMove justPlayedMove) {
		if (this.isWinningMove(justPlayedMove)) {
			return true;
		}
		return super.isGameOver(gameState, justPlayedMove);
	}
	// -----------------------------------------------------------------	
	public GamePlayersEnumeration applyGameStateTransition(IGameBoard gameState, IGameBoardMove justPlayedMove) {
		if (justPlayedMove.isNull()) {
			// TODO ! à améliorer			
			return super.applyGameStateTransition(gameState, justPlayedMove);
		}
		// TODO ! méthode playMove
		IGameBoardCell concernedCell = this.getCell(justPlayedMove.getPosition());
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
	public static void main(String[] args){
		new GameBuilder(StaticContext.thatClass()).build().start();
	}
	// ------------------------------------------------------------
}