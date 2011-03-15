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

package fr.designpattern.zerosumgames.implementations.tictactoe;

import java.util.ArrayList;
import java.util.List;

import fr.designpattern.zerosumgames.core.Game;
import fr.designpattern.zerosumgames.core.GameBoardDimension;
import fr.designpattern.zerosumgames.core.GameBoardMove;
import fr.designpattern.zerosumgames.core.GameBuilder;
import fr.designpattern.zerosumgames.core.GamePieceFactory;
import fr.designpattern.zerosumgames.core.GameService;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoard;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardCell;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardMove;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardPosition;
import fr.designpattern.zerosumgames.core.interfaces.IGamePiece;
import fr.designpattern.zerosumgames.core.interfaces.IGamePlayer;
import fr.designpattern.zerosumgames.core.types.GameBoardCardinalPosition;
import fr.designpattern.zerosumgames.core.types.GameBoardPlane;
import fr.designpattern.zerosumgames.core.types.GamePlayersEnumeration;
import fr.designpattern.zerosumgames.implementations.connect4.Connect4PieceTypes;
import fr.designpattern.zerosumgames.util.StaticContext;

public class Tictactoe extends Game {
	// ------------------------------------------------------------	
	public final static int CONNECTIONS = 3;
	public final static Class<TictactoePieceTypes> PIECE_TYPES = TictactoePieceTypes.class;
	public final static GameBoardDimension BOARD_DIMENSION = new GameBoardDimension(1, 3, 1, 3); 
	// ------------------------------------------------------------
	protected transient int connections;
	// ------------------------------------------------------------
	@Override
	protected IGameBoard setupBoard(final IGameBoard board) {
		
		final IGamePiece croix = this.piece(GamePlayersEnumeration.FIRST_PLAYER);
		final IGamePiece rond = this.piece(GamePlayersEnumeration.SECOND_PLAYER);
		
		//TODO tests unitaires
		
			/*
			-------------
			| x |   |   |
			-------------
			|   | x |   |
			-------------
			|   |   |   |
			-------------
			*///Victoire imminente pour les croix
			/*
			board.getCell(1, 1).setPiece(croix);
			board.getCell(2, 2).setPiece(croix);
			*/
			
			/*
			-------------
			| o |   |   |
			-------------
			|   | o |   |
			-------------
			|   |   |   |
			-------------
			*///Défaite imminente pour les croix (victoire imminente pour les ronds)
			/*
			board.getCell(1, 1).setPiece(rond);
			board.getCell(2, 2).setPiece(rond);
			*/
		
			/*
			-------------
			| o | x | x |
			-------------
			| x | o | o |
			-------------
			| o |   |   |
			-------------
			*///
			/*
			board.getCell(1, 1).setPiece(rond);
			board.getCell(1, 2).setPiece(croix);
			board.getCell(1, 3).setPiece(croix);
			
			board.getCell(2, 1).setPiece(croix);
			board.getCell(2, 2).setPiece(rond);
			board.getCell(2, 3).setPiece(rond);
			
			board.getCell(3, 1).setPiece(rond);
			*/
		
		return board;
	}
	// ------------------------------------------------------------
	public Tictactoe(final IGameBoard board, final List<IGamePlayer> opponents) {
		this(board, opponents, Tictactoe.CONNECTIONS);
	}
	public Tictactoe(final IGameBoard board, final List<IGamePlayer> opponents, final int connections) {
		super(new GamePieceFactory(PIECE_TYPES), board, opponents);
		this.connections = connections;
	}	
	// ------------------------------------------------------------
	@Override
	public boolean hasNullMove() {
		return false;
	}	
	// ------------------------------------------------------------
	// TODO ? implémentation par défaut dans la classe abstraite
	protected IGameBoardMove makeMove(final GamePlayersEnumeration side, final IGameBoardPosition position) {
		// TODO utiliser un cache
		return new GameBoardMove(side, position);
	}
	// ------------------------------------------------------------			
	public List<IGameBoardMove> getLegalMoves(final GamePlayersEnumeration side) {
		final List<IGameBoardMove> legalMoves = new ArrayList<IGameBoardMove>();
		for (IGameBoardCell[] line : this.getBoard()) {
			for(IGameBoardCell cell : line) {
				if(cell.isEmpty()) { // TODO ? isPlayable() ou canPlayHere()
					legalMoves.add(this.makeMove(side, cell.getPosition()));
				}
			}
		}
		return legalMoves;
	}
	// ------------------------------------------------------------
	public boolean isGameOverFromVictory(final IGameBoardMove justPlayedMove) {
		boolean isWinningMove = false;
		
		//System.out.println(this.getCell(justPlayedMove.getPosition()));
		
		for (GameBoardPlane plane : GameBoardPlane.values()) {
			int n = this.computeConnections(justPlayedMove, plane.getOneWay()) + 1 + this.computeConnections(justPlayedMove, plane.getOppositeWay());
			//System.out.println(plane + ": " + n);
			if (n >= this.connections) {
				isWinningMove = true;
				break;
			}
		}
		return isWinningMove;
	}
	// ------------------------------------------------------------
	public boolean isGameOverFromDraw(final IGameBoardMove justPlayedMove) {
		return this.getLegalMoves(GamePlayersEnumeration.opponent(justPlayedMove.getSide())).isEmpty();
	}
	// ------------------------------------------------------------		
	public boolean playMove(final IGameBoardMove moveToPlay) {
		final IGameBoardCell concernedCell = this.getCell(moveToPlay.getPosition());
		concernedCell.setPiece(this.piece(moveToPlay.getSide()));
		return true;
	}
	// ------------------------------------------------------------
	public boolean undo(final IGameBoardMove move) {
		this.getCell(move.getPosition()).setPiece(null); //TODO ? utiliser la pièce nulle
		return true; // is undo move complete ?
	}
	// ------------------------------------------------------------
	public double evaluate(final IGameBoardMove justPlayedMove) {
		return this.computeAllConnections(justPlayedMove);
	}	
	// ------------------------------------------------------------
	protected int computeAllConnections(final IGameBoardMove justPlayedMove) {
		int connections = 1;
		for (GameBoardPlane plane : GameBoardPlane.values()) {
			connections += this.computeConnections(justPlayedMove, plane.getOneWay()) + this.computeConnections(justPlayedMove, plane.getOppositeWay());
		}
		return connections;
	}	
	// ------------------------------------------------------------		
	protected int computeConnections(final IGameBoardMove justPlayedMove, final GameBoardCardinalPosition direction) {
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
	// TODO faire également une méthode move(IGameBoardMove move) et étendre une classe GameBoardMove abstraite pour chaque jeux
	private IGamePiece piece(final GamePlayersEnumeration player) {
		return super.piece(player, TictactoePieceTypes.PAWN);
	}
	// ------------------------------------------------------------		
	@SuppressWarnings("unchecked")
	public static void main(final String[] args){
		new GameService(new GameBuilder(StaticContext.thatClass()).build()).start();
	}
	// ------------------------------------------------------------	
}