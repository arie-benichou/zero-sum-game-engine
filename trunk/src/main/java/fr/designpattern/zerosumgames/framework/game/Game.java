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

package fr.designpattern.zerosumgames.framework.game;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.components.board.IGameBoard;
import fr.designpattern.zerosumgames.framework.game.components.board.cell.IGameBoardCell;
import fr.designpattern.zerosumgames.framework.game.components.board.cell.piece.IGamePiece;
import fr.designpattern.zerosumgames.framework.game.components.board.cell.piece.IGamePieceFactory;
import fr.designpattern.zerosumgames.framework.game.components.board.cell.piece.IGamePieceType;
import fr.designpattern.zerosumgames.framework.game.components.board.position.IGameBoardPosition;
import fr.designpattern.zerosumgames.framework.game.components.move.IGameMove;
import fr.designpattern.zerosumgames.framework.game.components.opponents.IGameOpponents;
import fr.designpattern.zerosumgames.framework.game.components.opponents.player.GamePlayersEnumeration;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategy.IGameStrategy;

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
	
	private IGamePieceFactory pieceFactory;
	private final void setPieceFactory(final IGamePieceFactory gamePieceFactory) {
		this.pieceFactory = gamePieceFactory;
	}	
	protected final IGamePieceFactory getPieceFactory() {
		return this.pieceFactory;
	}
	// ---------------------------------------------------------------------
	private IGameBoard board;
	private final void setBoard(final IGameBoard board) {
		this.board = board;
	}	
	protected final IGameBoard getBoard() {
		return this.board;
	}
	// ---------------------------------------------------------------------
	private IGameOpponents opponents;
	private final void setOpponents(final IGameOpponents opponents) {
		this.opponents = opponents;
	}	
	private final IGameOpponents getOpponents() {
		return this.opponents;
	}	
	// ---------------------------------------------------------------------
	public Game(final IGamePieceFactory pieceFactory, final IGameBoard board, final IGameOpponents opponents) {
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
	public IGameStrategy getPlayerStrategy(final GamePlayersEnumeration playerOrdinal) {
		return this.getOpponents().getPlayerStrategy(playerOrdinal);
	}
	public final IGamePiece piece(final GamePlayersEnumeration player, final IGamePieceType pieceType) {
		return this.getPieceFactory().getPiece(player, pieceType);
	}
	public final IGameBoardCell cell(final IGameBoardPosition position) {
		return this.getBoard().getCell(position);
	}
	public final IGameBoardCell cell(final int clientRowIndex, final int clientColumnIndex) {
		return this.getBoard().getCell(clientRowIndex, clientColumnIndex);
	}
	
	// ---------------------------------------------------------------------	
	// Implémentations finales 
	// ---------------------------------------------------------------------
	
	public final GamePlayersEnumeration whoShallPlay(final IGameMove playedMove, final boolean isMoveDone) {
		final GamePlayersEnumeration nexSideToPlay;
		if(!isMoveDone) {
			nexSideToPlay = playedMove.getSide();
		}
		else if(this.isGameOverFromVictory(playedMove)){
			nexSideToPlay = GamePlayersEnumeration.not(GamePlayersEnumeration.opponent(playedMove.getSide()));
		}
		else if(this.isGameOverFromDraw(playedMove)){
			nexSideToPlay = GamePlayersEnumeration.NO_ONE;
		}
		else {
			nexSideToPlay = GamePlayersEnumeration.opponent(playedMove.getSide());
		}
		return nexSideToPlay;
	}
	
	// ---------------------------------------------------------------------	
	// Méthodes à implémenter
	// ---------------------------------------------------------------------
	
	public abstract boolean hasNullMove();	
	public abstract List<IGameMove> getLegalMoves(GamePlayersEnumeration side);
	public abstract boolean doMove(IGameMove moveToPlay);
	public abstract boolean undoMove(IGameMove playedMove);
	public abstract boolean isGameOverFromVictory(IGameMove playedMove);
	public abstract boolean isGameOverFromDraw(IGameMove playedMove);
	public abstract double evaluate(IGameMove playedMove);
	
}