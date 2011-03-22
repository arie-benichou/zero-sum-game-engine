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
import fr.designpattern.zerosumgames.framework.game.components.board.GameBoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.game.components.board.GameBoardPlane;
import fr.designpattern.zerosumgames.framework.game.components.board.IGameBoard;
import fr.designpattern.zerosumgames.framework.game.components.board.cell.IGameBoardCell;
import fr.designpattern.zerosumgames.framework.game.components.board.cell.piece.GamePieceFactory;
import fr.designpattern.zerosumgames.framework.game.components.board.cell.piece.IGamePiece;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.GameBoardDimension;
import fr.designpattern.zerosumgames.framework.game.components.board.position.IGameBoardPosition;
import fr.designpattern.zerosumgames.framework.game.components.move.GameBoardMove;
import fr.designpattern.zerosumgames.framework.game.components.move.IGameMove;
import fr.designpattern.zerosumgames.framework.game.components.opponents.GamePlayersEnumeration;
import fr.designpattern.zerosumgames.framework.game.components.opponents.IGameOpponents;
import fr.designpattern.zerosumgames.framework.services.GameService;
import fr.designpattern.zerosumgames.util.StaticContext;

public class Tictactoe extends Game {
	// ------------------------------------------------------------
	public final static int CONNECTIONS = 3;
	public final static Class<TictactoePieceTypes> PIECE_TYPES = TictactoePieceTypes.class;
	public final static GameBoardDimension BOARD_DIMENSION = new GameBoardDimension(1, 3, 1, 3);
	// ------------------------------------------------------------
	protected transient int connections;
	// ------------------------------------------------------------
	public Tictactoe(final IGameBoard board, final IGameOpponents opponents) {
		this(board, opponents, Tictactoe.CONNECTIONS);
	}
	// ------------------------------------------------------------
	public Tictactoe(final IGameBoard board, final IGameOpponents opponents, final int connections) {
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
	protected IGameMove makeMove(final GamePlayersEnumeration side,
			final IGameBoardPosition position) {
		// TODO utiliser un cache
		return new GameBoardMove(side, position);
	}
	// ------------------------------------------------------------
	@Override
	public List<IGameMove> getLegalMoves(final GamePlayersEnumeration side, final IGameMove previousMove) {
		final List<IGameMove> legalMoves = new ArrayList<IGameMove>();
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
	public List<IGameMove> getLegalMoves(final GamePlayersEnumeration side) {
		final List<IGameMove> legalMoves = new ArrayList<IGameMove>();
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
	public boolean isGameOverFromVictory(final IGameMove justPlayedMove) {
		boolean isGameOverFromVictory = false;
		for (GameBoardPlane plane : GameBoardPlane.values()) {
			final int connections = this.computeRealConnection(justPlayedMove, plane.getOneWay())+ 1 + this.computeRealConnection(justPlayedMove,plane.getOppositeWay());
			if (connections >= this.connections) {
				isGameOverFromVictory = true;
				break;
			}
		}
		return isGameOverFromVictory;
	}
	// ------------------------------------------------------------
	public boolean isGameOverFromDraw(final IGameMove justPlayedMove) {
		return this.getLegalMoves(GamePlayersEnumeration.opponent(justPlayedMove.getSide()), justPlayedMove).isEmpty();
	}
	// ------------------------------------------------------------
	public boolean doMove(final IGameMove moveToPlay) {
		final IGameBoardCell concernedCell = this.cell(moveToPlay.getPosition());
		concernedCell.setPiece(this.piece(moveToPlay.getSide()));
		return true;
	}
	// ------------------------------------------------------------
	public boolean undoMove(final IGameMove move) {
		this.cell(move.getPosition()).setPiece(null); // TODO ? utiliser la pièce nulle
		return true; // is undo move complete ?
	}
	// ------------------------------------------------------------
	public double evaluate(final IGameMove justPlayedMove) {
		
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
	protected int computeRealConnections(final IGameMove justPlayedMove) {
		int connections = 0;
		for (GameBoardPlane plane : GameBoardPlane.values()) {
			connections = this.computeRealConnection(justPlayedMove,plane.getOneWay()) + this.computeRealConnection(justPlayedMove,plane.getOppositeWay());
		}
		return connections;
	}
	// ------------------------------------------------------------	
	protected int computePotentialConnections(final IGameMove justPlayedMove) {
		int connections = 0;
		int connections1 = 0;
		int connections2 = 0;
		// ------------------------------------------------------------		
		for (GameBoardPlane plane : GameBoardPlane.values()) {
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
	protected int computeRealConnection(final IGameMove justPlayedMove, final GameBoardCardinalPosition direction) {
		int connected;
		IGameBoardCell cell = this.cell(justPlayedMove.getPosition());
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
	protected int computePotentialConnection(final IGameMove justPlayedMove, final GameBoardCardinalPosition direction) {
		int connected;
		IGameBoardCell cell = this.cell(justPlayedMove.getPosition());
		for (connected = 1; connected < this.connections; ++connected) {
			cell = cell.getNeighbour(direction);
			if (cell.isNull()) {
				break;
			}
			if(!cell.isEmpty() && cell.getPiece().getSide() == GamePlayersEnumeration.opponent(justPlayedMove.getSide())) {
				break;
			}
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
		new GameService(new Builder(StaticContext.thatClass()).build()).start();
	}
	// ------------------------------------------------------------
}