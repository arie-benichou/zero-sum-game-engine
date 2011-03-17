/*
 * @(#)Checkers.java	0.99
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

package fr.designpattern.zerosumgames.implementations.checkers;

import java.util.ArrayList;
import java.util.List;

import fr.designpattern.zerosumgames.core.Game;
import fr.designpattern.zerosumgames.core.GameBoardDimension;
import fr.designpattern.zerosumgames.core.GameBuilder;
import fr.designpattern.zerosumgames.core.GamePieceFactory;
import fr.designpattern.zerosumgames.core.GameService;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoard;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardCell;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardMove;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardPosition;
import fr.designpattern.zerosumgames.core.interfaces.IGamePlayer;
import fr.designpattern.zerosumgames.core.types.GameBoardCardinalPosition;
import fr.designpattern.zerosumgames.core.types.GamePlayersEnumeration;
import fr.designpattern.zerosumgames.implementations.checkers.pieces.CheckersPiece;
import fr.designpattern.zerosumgames.util.StaticContext;

public class Checkers extends Game {
	// ------------------------------------------------------------	
	public final static Class<CheckersPieceTypes> PIECE_TYPES = CheckersPieceTypes.class;
	public final static GameBoardDimension BOARD_DIMENSION = new GameBoardDimension(1, 8, 1, 8); 
	// ------------------------------------------------------------
	public Checkers(final IGameBoard board, final List<IGamePlayer> opponents) {
		// TODO !! à revoir
		super(new GamePieceFactory(PIECE_TYPES), board, opponents);		
	}
	// ------------------------------------------------------------
	@Override
	protected IGameBoard setupBoard(final IGameBoard board) {
		int n, clientColumnIndex;
		for(int clientRowIndex = 1; clientRowIndex<=3; ++clientRowIndex) {
			for(n = 1; n<=4; ++n) {
				clientColumnIndex = 2*n + clientRowIndex% 2 - 1;
				board.getCell(clientRowIndex, clientColumnIndex).setPiece(this.piece(GamePlayersEnumeration.SECOND_PLAYER, CheckersPieceTypes.MAN));
			}
		}
		// TODO permettre d'autres dimensions		
		for(int clientRowIndex = 6; clientRowIndex<=8; ++clientRowIndex) {
			for(n = 1; n<=4; ++n) {
				clientColumnIndex = 2*n + clientRowIndex% 2 - 1;
				board.getCell(clientRowIndex, clientColumnIndex).setPiece(this.piece(GamePlayersEnumeration.FIRST_PLAYER, CheckersPieceTypes.MAN));
			}
		}
		return board;
	}
	// -----------------------------------------------------------------	
	public List<IGameBoardCell> getRelevantCells(final IGameBoard board, final GamePlayersEnumeration side) {
		final List<IGameBoardCell> relevantCells = new ArrayList<IGameBoardCell>();
		for (IGameBoardCell[] line : board) {
			for(IGameBoardCell cell : line) {
				// TODO ? utiliser la pièce nulle
				if(!cell.isEmpty() && cell.getPiece().getSide() == side) {
					relevantCells.add(cell);
				}
			}
		}
		return relevantCells;
	}
	// ------------------------------------------------------------
	@Override
	public boolean hasNullMove() {
		return false;
	}	
	// -----------------------------------------------------------------	
	private IGameBoardMove makeMove(final GamePlayersEnumeration side, final IGameBoardPosition position, final GameBoardCardinalPosition direction) {
		// TODO utiliser un cache
		return new CheckersMove(side, position, direction);
	}
	// ------------------------------------------------------------
	public final List<IGameBoardMove> getLegalMoves(final IGameBoard board, final GamePlayersEnumeration side) {
		final List<IGameBoardMove> jumpingMoves = new ArrayList<IGameBoardMove>();
		final List<IGameBoardMove> walkingMoves = new ArrayList<IGameBoardMove>();
		CheckersPiece piece;
		List<GameBoardCardinalPosition> pieceOptions;
		boolean hasToJump = false;
		for(IGameBoardCell cell : this.getRelevantCells(board, side)) {
			piece = (CheckersPiece)cell.getPiece();
			pieceOptions = piece.getJumpOptions(cell);
			if(!pieceOptions.isEmpty()) {
				hasToJump = true;
				for(GameBoardCardinalPosition direction : pieceOptions) {
					jumpingMoves.add(this.makeMove(side, cell.getPosition(), direction));
				}
				continue;
			}
			if(!hasToJump) {
				pieceOptions = piece.getWalkOptions(cell);
				for(GameBoardCardinalPosition direction : pieceOptions) {
					walkingMoves.add(this.makeMove(side, cell.getPosition(), direction));
				}
			}
		}
		return jumpingMoves.isEmpty() ? walkingMoves : jumpingMoves; 
	}
	// ------------------------------------------------------------
	private boolean hasToKeepPlaying(final IGameBoard gameState, final CheckersMove move) {
		IGameBoardCell actualCell = gameState.getCell(move.getPosition());
		actualCell = actualCell.getNeighbour(move.getDirection());
		actualCell = actualCell.getNeighbour(move.getDirection());
		final CheckersPiece piece =  (CheckersPiece) actualCell.getPiece();
		//Est-ce que la pièce peut encore sauter ?
		return !piece.getJumpOptions(actualCell).isEmpty();
	}
	// -----------------------------------------------------------------
	@Override
	public boolean playMove(final IGameBoard gameState, final IGameBoardMove moveToPlay) {
		final CheckersMove checkersMove = (CheckersMove)moveToPlay;
		// récupération de la cellule corespondant à la position
		final IGameBoardCell cell = gameState.getCell(checkersMove.getPosition());
		// récupération de la pièce à déplacer
		final CheckersPiece pieceToMove = (CheckersPiece)cell.getPiece();
		// suppression de la pièce à sa position actuelle
		cell.setPiece(null);
		boolean hasBeenEating = false;
		// récupération de la cellulle correspondant à la direction choisie
		IGameBoardCell destinationCell = cell.getNeighbour(checkersMove.getDirection());		
		// si la cellule n'est pas vide
		if(!destinationCell.isEmpty()) {
			hasBeenEating = true;
			// la pièce de cette cellule est supprimée
			destinationCell.setPiece(null); // TODO ? utiliser la pièce nulle
			// et la cellule de destination devient la suivante
			destinationCell = destinationCell.getNeighbour(checkersMove.getDirection());
		}
		// la pièce concernée par le coup est "déplacée" à sa cellule de destination
		destinationCell.setPiece(pieceToMove);
		boolean hasBeenCrowned = false;
		// Si la pièce est un pion promotable
		if(pieceToMove.isPromotable(destinationCell)){
			hasBeenCrowned = true;
			// le pion est promu roi
			destinationCell.setPiece(this.piece(checkersMove.getSide(), CheckersPieceTypes.KING));
		}
		return !(hasBeenEating && !hasBeenCrowned && this.hasToKeepPlaying(gameState, checkersMove));
	}
	// -----------------------------------------------------------------
	public GamePlayersEnumeration isGameOver(final IGameBoard gameState, final IGameBoardMove justPlayedMove) {
		// Est-ce que, suite à ce coup, l'adversaire peut encore jouer ?
		//return this.getLegalMoves(gameState, justPlayedMove.getSide().getOpponent()).isEmpty();
		return null; // TODO !
	}
	// -----------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		new GameService(new GameBuilder(StaticContext.thatClass()).build()).start();
	}
	// ------------------------------------------------------------
	public boolean undo(final IGameBoardMove move) {
		// TODO Auto-generated method stub
		return false;
	}
	public double evaluate(final IGameBoardMove move) {
		// TODO Auto-generated method stub
		return 0;
	}
}