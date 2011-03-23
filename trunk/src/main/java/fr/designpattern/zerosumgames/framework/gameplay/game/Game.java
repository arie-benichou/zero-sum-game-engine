/*
 * @(#)Game.java	0.999
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

package fr.designpattern.zerosumgames.framework.gameplay.game;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors.MoveSelectorInterface;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.BoardInterface;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.cells.pieces.PieceInterface;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.cells.pieces.PieceTypeInterface;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.cells.pieces.PiecesInterface;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.cells.positions.PositionInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.OpponentsInterface;
import fr.designpattern.zerosumgames.framework.moves.MoveInterface;

/**
 * This class provides a skeletal implementation of the Game
 * interface, to minimize the effort required to implement this interface.
 * 
 * @author  Arie Benichou
 * @version 0.999, 21/03/2011
 */
public abstract class Game implements GameInterface {
	
	// ---------------------------------------------------------------------
	// Object Internals
	// ---------------------------------------------------------------------
	
	private PiecesInterface pieceFactory;
	private final void setPieceFactory(final PiecesInterface gamePieceFactory) {
		this.pieceFactory = gamePieceFactory;
	}	
	protected final PiecesInterface getPieceFactory() {
		return this.pieceFactory;
	}
	// ---------------------------------------------------------------------
	private BoardInterface board;
	private final void setBoard(final BoardInterface board) {
		this.board = board;
	}	
	protected final BoardInterface getBoard() {
		return this.board;
	}
	// ---------------------------------------------------------------------
	private OpponentsInterface opponents;
	private final void setOpponents(final OpponentsInterface opponents) {
		this.opponents = opponents;
	}	
	private final OpponentsInterface getOpponents() {
		return this.opponents;
	}	
	// ---------------------------------------------------------------------
	public Game(final PiecesInterface pieceFactory, final BoardInterface board, final OpponentsInterface opponents) {
		this.setPieceFactory(pieceFactory);		
		this.setBoard(board);
		this.setOpponents(opponents);
	}
	// ---------------------------------------------------------------------
	public String toString() {
		return this.getBoard().toString();
	}
	
	// ---------------------------------------------------------------------
	// Façades fournies
	// ---------------------------------------------------------------------
	public MoveSelectorInterface getPlayerStrategy(final OpponentsEnumeration playerOrdinal) {
		return this.getOpponents().getPlayerStrategy(playerOrdinal);
	}
	public final PieceInterface piece(final OpponentsEnumeration player, final PieceTypeInterface pieceType) {
		return this.getPieceFactory().getPiece(player, pieceType);
	}
	public final CellInterface cell(final PositionInterface position) {
		return this.getBoard().getCell(position);
	}
	public final CellInterface cell(final int clientRowIndex, final int clientColumnIndex) {
		return this.getBoard().getCell(clientRowIndex, clientColumnIndex);
	}
	
	// ---------------------------------------------------------------------	
	// Implémentations finales 
	// ---------------------------------------------------------------------
	
	public final OpponentsEnumeration whoShallPlay(final MoveInterface playedMove, final boolean isMoveDone) {
		final OpponentsEnumeration nexSideToPlay;
		if(!isMoveDone) {
			nexSideToPlay = playedMove.getSide();
		}
		else if(this.isGameOverFromVictory(playedMove)){
			nexSideToPlay = OpponentsEnumeration.not(OpponentsEnumeration.opponent(playedMove.getSide()));
		}
		else if(this.isGameOverFromDraw(playedMove)){
			nexSideToPlay = OpponentsEnumeration.NO_ONE;
		}
		else {
			nexSideToPlay = OpponentsEnumeration.opponent(playedMove.getSide());
		}
		return nexSideToPlay;
	}
	
	// ---------------------------------------------------------------------	
	// Méthodes à implémenter
	// ---------------------------------------------------------------------
	
	public abstract boolean hasNullMove();	
	public abstract List<MoveInterface> getLegalMoves(OpponentsEnumeration side);
	public abstract boolean doMove(MoveInterface moveToPlay);
	public abstract boolean undoMove(MoveInterface playedMove);
	public abstract boolean isGameOverFromVictory(MoveInterface playedMove);
	public abstract boolean isGameOverFromDraw(MoveInterface playedMove);
	public abstract double evaluate(MoveInterface playedMove);
	
}