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

import main.java.games.core.Game;
import main.java.games.core.GameBoardDimension;
import main.java.games.core.GameBoardMove;
import main.java.games.core.GameBuilder;
import main.java.games.core.GamePieceFactory;
import main.java.games.core.GameService;
import main.java.games.core.interfaces.IGameBoard;
import main.java.games.core.interfaces.IGameBoardCell;
import main.java.games.core.interfaces.IGameBoardMove;
import main.java.games.core.interfaces.IGameBoardPosition;
import main.java.games.core.interfaces.IGamePlayer;
import main.java.games.core.types.GameBoardCardinalPosition;
import main.java.games.core.types.GameBoardPlane;
import main.java.games.core.types.GamePlayersEnumeration;
import main.java.util.StaticContext;

public class Tictactoe extends Game {
	// ------------------------------------------------------------	
	public final static int CONNECTIONS = 4;
	public final static Class<TictactoePieceTypes> PIECE_TYPES = TictactoePieceTypes.class;
	public final static GameBoardDimension BOARD_DIMENSION = new GameBoardDimension(1, 3, 1, 3); 
	// ------------------------------------------------------------
	private transient int connections;	
	// ------------------------------------------------------------	
	public Tictactoe(final IGameBoard board, final List<IGamePlayer> opponents) {
		this(board, opponents, Tictactoe.CONNECTIONS);
	}
	public Tictactoe(final IGameBoard board, final List<IGamePlayer> opponents, final int connections) {
		super(new GamePieceFactory(PIECE_TYPES), board, opponents);
		this.connections = connections;
	}	
	// -----------------------------------------------------------------
	@Override
	public boolean hasNullMove() {
		return false;
	}	
	// -----------------------------------------------------------------
	// TODO ? implémentation par défaut dans la classe abstraite
	protected IGameBoardMove makeMove(final GamePlayersEnumeration side, final IGameBoardPosition position) {
		// TODO utiliser un cache
		return new GameBoardMove(side, position);
	}
	// ------------------------------------------------------------			
	@Override
	public List<IGameBoardMove> getLegalMoves(final IGameBoard board, final GamePlayersEnumeration side) {
		final List<IGameBoardMove> legalMoves = new ArrayList<IGameBoardMove>();
		for (IGameBoardCell[] line : this.getBoard()) {
			for(IGameBoardCell cell : line) {
				if(cell.isEmpty()) { // TODO ? isPlayable()
					legalMoves.add(this.makeMove(side, cell.getPosition()));
				}
			}
		}
		return legalMoves;
	}
	// ------------------------------------------------------------
	protected int countConnections(final IGameBoardMove justPlayedMove) {
		int connections = 0;
		for (GameBoardPlane plane : GameBoardPlane.values()) {
			connections += this.countConnections(justPlayedMove, plane.getOneWay()) + this.countConnections(justPlayedMove, plane.getOppositeWay());
			/*
			if (connections >= this.connections) {
				isWinningMove = true;
				break;
			}
			*/
		}
		return connections;
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
	public GamePlayersEnumeration isGameOver(final IGameBoard gameState, final IGameBoardMove justPlayedMove) {
		
		//boolean isGameOver = false;
		GamePlayersEnumeration nextPlayer;
		
		// TODO ! rajouter à l'interface
		if(this.isWinningMove(justPlayedMove)) {
			//isGameOver = true;
			nextPlayer = justPlayedMove.getSide().getOpponent().not();
		}
		else {
			// Suite à ce coup, si l'adversaire ne peut plus jouer
			if(this.getLegalMoves(this.getBoard(), justPlayedMove.getSide().getOpponent()).isEmpty()) {
				nextPlayer = GamePlayersEnumeration.NONE;
			}
			else {
				nextPlayer = justPlayedMove.getSide().getOpponent();
			}
		}
		return nextPlayer;
	}
	// -----------------------------------------------------------------
	@Override
	public boolean playMove(final IGameBoard gameState, final IGameBoardMove moveToPlay) {
		//if (!moveToPlay.isNull()) {
			final IGameBoardCell concernedCell = this.getCell(moveToPlay.getPosition());
			concernedCell.setPiece(this.piece(moveToPlay.getSide(), TictactoePieceTypes.PAWN));
		//}
		return true;
	}
	// -----------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public static void main(final String[] args){
		new GameService(new GameBuilder(StaticContext.thatClass()).build()).start();
	}
	// ------------------------------------------------------------
	// TODO ajouter un attribut capture dans un coup aux dames
	@Override
	public boolean undo(final IGameBoardMove move) {
		//TODO ? utiliser la pièce nulle
		this.getCell(move.getPosition()).setPiece(null);
		return true; // is undo move complete ?
	}
	// ------------------------------------------------------------
	// TODO à améliorer
	/*
	@Override
	public double evaluate(IGameBoardMove move) {
		
		double evaluation;
		*/
		/*
		if(this.isWinningMove(move)) {
			evaluation = Double.POSITIVE_INFINITY;	
		}
		else {
		*/
		/*
			evaluation = this.countConnections(move);
		//}
		
		return evaluation;
	}
	*/
	// ------------------------------------------------------------
	@Override
	// TODO utiliser NOT_FIRST_PLAYER, NOT_SECOND_PLAYER, NONE avec FIRST_PLAYER et SECOND_PLAYER pour optimiser l'IA  
	public double evaluate(IGameBoardMove justPlayedMove) {
		/*
		double evaluation = 0.001;
		if(this.isWinningMove(justPlayedMove)) {
			evaluation = Double.POSITIVE_INFINITY;
		}
		else if(!this.getLegalMoves(this.getBoard(), justPlayedMove.getSide().getOpponent()).isEmpty()) {
			evaluation = this.countConnections(justPlayedMove);
		}
		return evaluation;
		*/
		return this.countConnections(justPlayedMove);
	}
	// ------------------------------------------------------------
}