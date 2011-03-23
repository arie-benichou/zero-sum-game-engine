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

package fr.designpattern.zerosumgames.samples.tictactoe;

import java.util.ArrayList;
import java.util.List;

import fr.designpattern.zerosumgames.framework.game.Game;
import fr.designpattern.zerosumgames.framework.game.builder.Builder;
import fr.designpattern.zerosumgames.framework.game.components.board.BoardInterface;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.BoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.BoardPlane;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.Dimension;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.cells.pieces.PieceInterface;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.cells.pieces.Pieces;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.cells.positions.PositionInterface;
import fr.designpattern.zerosumgames.framework.game.components.moves.Move;
import fr.designpattern.zerosumgames.framework.game.components.moves.MoveInterface;
import fr.designpattern.zerosumgames.framework.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.framework.opponents.OpponentsInterface;
import fr.designpattern.zerosumgames.services.GameService;
import fr.designpattern.zerosumgames.util.StaticContext;

public class Tictactoe extends Game {
	// ------------------------------------------------------------
	public final static int CONNECTIONS = 3;
	public final static Class<TictactoePieceTypes> PIECE_TYPES = TictactoePieceTypes.class;
	public final static Dimension BOARD_DIMENSION = new Dimension(1, 3, 1, 3);
	// ------------------------------------------------------------
	protected transient int connections;
	// ------------------------------------------------------------
	public Tictactoe(final BoardInterface board, final OpponentsInterface opponents) {
		this(board, opponents, Tictactoe.CONNECTIONS);
	}
	// ------------------------------------------------------------
	public Tictactoe(final BoardInterface board, final OpponentsInterface opponents, final int connections) {
		super(new Pieces(PIECE_TYPES), board, opponents);
		this.connections = connections;
	}
	// ------------------------------------------------------------
	@Override
	public boolean hasNullMove() {
		return false;
	}
	// ------------------------------------------------------------
	// TODO ? implémentation par défaut dans la classe abstraite
	protected MoveInterface makeMove(final OpponentsEnumeration side,
			final PositionInterface position) {
		// TODO utiliser un cache
		return new Move(side, position);
	}
	// ------------------------------------------------------------
	public List<MoveInterface> getLegalMoves(final OpponentsEnumeration side) {
		final List<MoveInterface> legalMoves = new ArrayList<MoveInterface>();
		for (CellInterface[] line : this.getBoard()) {
			for (CellInterface cell : line) {
				if (cell.isEmpty()) { // TODO ? isPlayable() ou canPlayHere()
					legalMoves.add(this.makeMove(side, cell.getPosition()));
				}
			}
		}
		return legalMoves;
	}	
	// ------------------------------------------------------------
	public boolean isGameOverFromVictory(final MoveInterface justPlayedMove) {
		boolean isGameOverFromVictory = false;
		for (BoardPlane plane : BoardPlane.values()) {
			final int connections = this.computeRealConnection(justPlayedMove, plane.getOneWay())+ 1 + this.computeRealConnection(justPlayedMove,plane.getOppositeWay());
			if (connections >= this.connections) {
				isGameOverFromVictory = true;
				break;
			}
		}
		return isGameOverFromVictory;
	}
	// ------------------------------------------------------------
	public boolean isGameOverFromDraw(final MoveInterface justPlayedMove) {
		return this.getLegalMoves(OpponentsEnumeration.opponent(justPlayedMove.getSide())).isEmpty();
	}
	// ------------------------------------------------------------
	public boolean doMove(final MoveInterface moveToPlay) {
		final CellInterface concernedCell = this.cell(moveToPlay.getPosition());
		concernedCell.setPiece(this.piece(moveToPlay.getSide()));
		return true;
	}
	// ------------------------------------------------------------
	public boolean undoMove(final MoveInterface move) {
		this.cell(move.getPosition()).setPiece(null); // TODO ? utiliser la pièce nulle
		return true; // is undo move complete ?
	}
	// ------------------------------------------------------------
	public double evaluate(final MoveInterface justPlayedMove) {
		
		double evaluation;
		
		if(this.isGameOverFromVictory(justPlayedMove)) {
			evaluation = Double.POSITIVE_INFINITY;
		}
		else if(this.isGameOverFromDraw(justPlayedMove)) {
			evaluation = 0.111;
		}
		else {
			//return this.computeRealConnections(justPlayedMove);
			evaluation = 0;
			final int potentialConnections = this.computePotentialConnections(justPlayedMove);
			final int realConnections = this.computeRealConnections(justPlayedMove);
			if(potentialConnections > 0) {
				final int n = (int)Math.log10(potentialConnections) + 1;
				evaluation = potentialConnections / Math.pow(10, n);
			}
			evaluation += realConnections;
		}
		
		return evaluation;		
	}
	// ------------------------------------------------------------
	protected int computeRealConnections(final MoveInterface justPlayedMove) {
		int connections = 0;
		for (BoardPlane plane : BoardPlane.values()) {
			connections = this.computeRealConnection(justPlayedMove,plane.getOneWay()) + this.computeRealConnection(justPlayedMove,plane.getOppositeWay());
		}
		return connections;
	}
	// ------------------------------------------------------------	
	protected int computePotentialConnections(final MoveInterface justPlayedMove) {
		int connections = 0;
		int connections1 = 0;
		int connections2 = 0;
		// ------------------------------------------------------------		
		for (BoardPlane plane : BoardPlane.values()) {
			// ------------------------------------------------------------
			connections1 = this.computePotentialConnection(justPlayedMove,plane.getOneWay());
			connections2 = this.computePotentialConnection(justPlayedMove,plane.getOppositeWay());
			// ------------------------------------------------------------
			connections += connections1 + connections2;
			// ------------------------------------------------------------			
		}
		return connections;
	}
	// ------------------------------------------------------------
	protected int computeRealConnection(final MoveInterface justPlayedMove, final BoardCardinalPosition direction) {
		int connected;
		CellInterface cell = this.cell(justPlayedMove.getPosition());
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
	protected int computePotentialConnection(final MoveInterface justPlayedMove, final BoardCardinalPosition direction) {
		int connected;
		CellInterface cell = this.cell(justPlayedMove.getPosition());
		for (connected = 1; connected < this.connections; ++connected) {
			cell = cell.getNeighbour(direction);
			if (cell.isNull()) {
				break;
			}
			if(!cell.isEmpty() && cell.getPiece().getSide() == OpponentsEnumeration.opponent(justPlayedMove.getSide())) {
				break;
			}
		}
		return --connected;
	}	
	// ------------------------------------------------------------
	// TODO faire également une méthode move(IGameBoardMove move) et étendre une
	// classe GameBoardMove abstraite pour chaque jeux
	private PieceInterface piece(final OpponentsEnumeration player) {
		return super.piece(player, TictactoePieceTypes.PAWN);
	}
	// ------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		new GameService(new Builder(StaticContext.thatClass()).build()).start();
	}
	// ------------------------------------------------------------
}