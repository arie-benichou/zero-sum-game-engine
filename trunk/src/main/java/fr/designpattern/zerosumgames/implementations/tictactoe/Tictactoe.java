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
	public final static GameBoardDimension BOARD_DIMENSION = new GameBoardDimension(
			1, 3, 1, 3);
	// ------------------------------------------------------------
	protected transient int connections;

	// ------------------------------------------------------------
	@Override
	protected IGameBoard setupBoard(final IGameBoard board) {

		final IGamePiece croix = this
				.piece(GamePlayersEnumeration.FIRST_PLAYER);
		final IGamePiece rond = this
				.piece(GamePlayersEnumeration.SECOND_PLAYER);

		// TODO tests

		/*
		 * ------------- | x | | | ------------- | | x | | ------------- | | | |
		 * -------------
		 */// Victoire imminente pour les croix
		 //board.getCell(2, 1).setPiece(croix);
		 //board.getCell(2, 2).setPiece(croix);

		/*
		 * ------------- | o | | | ------------- | | o | | ------------- | | | |
		 * -------------
		 */// Défaite imminente pour les croix (victoire imminente pour les
			// ronds)
		 //board.getCell(2, 1).setPiece(rond);
		 //board.getCell(2, 2).setPiece(rond);

		/*
		 * ------------- | o | x | x | ------------- | x | o | o | -------------
		 * | o | | | -------------
		 *///
		/*
		 * board.getCell(1, 1).setPiece(rond); board.getCell(1,
		 * 2).setPiece(croix); board.getCell(1, 3).setPiece(croix);
		 * 
		 * board.getCell(2, 1).setPiece(croix); board.getCell(2,
		 * 2).setPiece(rond); board.getCell(2, 3).setPiece(rond);
		 * 
		 * board.getCell(3, 1).setPiece(rond);
		 */

		return board;
	}

	// ------------------------------------------------------------
	public Tictactoe(final IGameBoard board, final List<IGamePlayer> opponents) {
		this(board, opponents, Tictactoe.CONNECTIONS);
	}

	public Tictactoe(final IGameBoard board, final List<IGamePlayer> opponents,
			final int connections) {
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
	protected IGameBoardMove makeMove(final GamePlayersEnumeration side,
			final IGameBoardPosition position) {
		// TODO utiliser un cache
		return new GameBoardMove(side, position);
	}

	// ------------------------------------------------------------
	public List<IGameBoardMove> getLegalMoves(final GamePlayersEnumeration side) {
		final List<IGameBoardMove> legalMoves = new ArrayList<IGameBoardMove>();
		for (IGameBoardCell[] line : this.getBoard()) {
			for (IGameBoardCell cell : line) {
				if (cell.isEmpty()) { // TODO ? isPlayable() ou canPlayHere()
					legalMoves.add(this.makeMove(side, cell.getPosition()));
				}
			}
		}
		return legalMoves;
	}

	// ------------------------------------------------------------
	public boolean isGameOverFromVictory(final IGameBoardMove justPlayedMove) {
		boolean isWinningMove = false;

		// System.out.println(this.getCell(justPlayedMove.getPosition()));

		for (GameBoardPlane plane : GameBoardPlane.values()) {
			int n = this.computeRealConnection(justPlayedMove, plane.getOneWay())
					+ 1
					+ this.computeRealConnection(justPlayedMove,
							plane.getOppositeWay());
			// System.out.println(plane + ": " + n);
			if (n >= this.connections) {
				isWinningMove = true;
				break;
			}
		}
		
		
		//double potentialConnections = this.evaluate(justPlayedMove);
		////System.out.println("\n" + potentialConnections);
		
		
		return isWinningMove;
	}

	// ------------------------------------------------------------
	public boolean isGameOverFromDraw(final IGameBoardMove justPlayedMove) {
		return this.getLegalMoves(
				GamePlayersEnumeration.opponent(justPlayedMove.getSide()))
				.isEmpty();
	}

	// ------------------------------------------------------------
	public boolean playMove(final IGameBoardMove moveToPlay) {
		final IGameBoardCell concernedCell = this.getCell(moveToPlay
				.getPosition());
		concernedCell.setPiece(this.piece(moveToPlay.getSide()));
		return true;
	}

	// ------------------------------------------------------------
	public boolean undo(final IGameBoardMove move) {
		this.getCell(move.getPosition()).setPiece(null); // TODO ? utiliser la
															// pièce nulle
		return true; // is undo move complete ?
	}

	// ------------------------------------------------------------
	public double evaluate(final IGameBoardMove justPlayedMove) {
		
		double evaluation = 0;
		
		int potentialConnections = this.computePotentialConnections(justPlayedMove);
		///System.out.println("potentialConnections = " + potentialConnections);

		int realConnections = this.computeRealConnections(justPlayedMove);
		///System.out.println("realConnections = " + realConnections);
		
		if(potentialConnections > 0) {
			int n = (int)Math.log10(potentialConnections) + 1;
			evaluation = potentialConnections / Math.pow(10, n);
		}
		
		evaluation += realConnections;
		
		///System.out.println("evaluation = " + evaluation);
		
		return evaluation;
	}
	// ------------------------------------------------------------
	protected int computeRealConnections(final IGameBoardMove justPlayedMove) {
		int connections = 0;
		int connections1 = 0;
		int connections2 = 0;
		// ------------------------------------------------------------		
		///System.out.println(justPlayedMove);
		// ------------------------------------------------------------		
		for (GameBoardPlane plane : GameBoardPlane.values()) {
			// ------------------------------------------------------------
			///System.out.println("\n" + plane + "\n");
			
			connections1 = this.computeRealConnection(justPlayedMove,plane.getOneWay());
			///System.out.println(connections1);
			
			connections2 = this.computeRealConnection(justPlayedMove,plane.getOppositeWay());
			///System.out.println(connections2);
			// ------------------------------------------------------------
			connections += connections1 + connections2;
			// ------------------------------------------------------------			
		}
		return connections;
	}
	// ------------------------------------------------------------	
	protected int computePotentialConnections(final IGameBoardMove justPlayedMove) {
		int connections = 0;
		int connections1 = 0;
		int connections2 = 0;
		// ------------------------------------------------------------		
		///System.out.println(justPlayedMove);
		// ------------------------------------------------------------		
		for (GameBoardPlane plane : GameBoardPlane.values()) {
			// ------------------------------------------------------------
			///System.out.println("\n" + plane + "\n");
			
			connections1 = this.computePotentialConnection(justPlayedMove,plane.getOneWay());
			///System.out.println(connections1);
			
			connections2 = this.computePotentialConnection(justPlayedMove,plane.getOppositeWay());
			///System.out.println(connections2);
			// ------------------------------------------------------------
			connections += connections1 + connections2;
			// ------------------------------------------------------------			
		}
		return connections;
	}
	// ------------------------------------------------------------
	protected int computeRealConnection(final IGameBoardMove justPlayedMove, final GameBoardCardinalPosition direction) {
		int connected;
		IGameBoardCell cell = this.getCell(justPlayedMove.getPosition());
		for (connected = 1; connected < this.connections; ++connected) {
			cell = cell.getNeighbour(direction);
			if (cell.isNull() || cell.isEmpty()
					|| cell.getPiece().getSide() != justPlayedMove.getSide()) {
				break;
			}
		}
		return --connected;
	}
	// ------------------------------------------------------------	
	protected int computePotentialConnection(final IGameBoardMove justPlayedMove, final GameBoardCardinalPosition direction) {
		int connected;
		IGameBoardCell cell = this.getCell(justPlayedMove.getPosition());
		///System.out.println(direction);
		for (connected = 1; connected < this.connections; ++connected) {
			cell = cell.getNeighbour(direction);
			if (cell.isNull()) {
				break;
			}
			if(!cell.isEmpty() && cell.getPiece().getSide() == GamePlayersEnumeration.opponent(justPlayedMove.getSide())) {
				break;
			}
			// ------------------------------------------------------------			
			//System.out.println("blanc / pion du joueur");
			// ------------------------------------------------------------			
		}
		return --connected;
	}	
	// ------------------------------------------------------------
	// TODO faire également une méthode move(IGameBoardMove move) et étendre une
	// classe GameBoardMove abstraite pour chaque jeux
	private IGamePiece piece(final GamePlayersEnumeration player) {
		return super.piece(player, TictactoePieceTypes.PAWN);
	}

	// ------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		new GameService(new GameBuilder(StaticContext.thatClass()).build())
				.start();
	}
	// ------------------------------------------------------------
}