/*
 * Copyright (C) 2011 Arié Bénichou
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

package fr.designpattern.zerosumgames.implementations.othello;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

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
import fr.designpattern.zerosumgames.core.types.GamePlayersEnumeration;
import fr.designpattern.zerosumgames.util.StaticContext;

public class Othello extends Game {	
	// ------------------------------------------------------------
	public final static Class<OthelloPieceTypes> PIECE_TYPES = OthelloPieceTypes.class;
	public final static GameBoardDimension BOARD_DIMENSION = new GameBoardDimension(1, 8, 1, 8);
	// ------------------------------------------------------------
	public Othello(final IGameBoard board, final List<IGamePlayer> opponents) {
		super(new GamePieceFactory(PIECE_TYPES), board, opponents);// TODO !! à revoir
	}
	// ------------------------------------------------------------	
	@Override
	public boolean hasNullMove() {
		return true;
	}
	// ------------------------------------------------------------
	public final List<IGameBoardMove> getLegalMoves(final IGameBoard board, final GamePlayersEnumeration side) {
		final List<IGameBoardMove> legalMoves = new ArrayList<IGameBoardMove>();
		for (IGameBoardCell[] line : board) {
			for (IGameBoardCell cell : line) {
				if (this.canPlayHere(cell, side)) {
					legalMoves.add(this.makeMove(side, cell.getPosition()));
				}
			}
		}
		// TODO ? cache du nullMove pour chaque side
		legalMoves.add(this.makeMove(side, this.getCell(null).getPosition()));
		return legalMoves;
	}
	// ------------------------------------------------------------
	@Override
	// TODO renommer : doMove/undoMove
	// TODO laisser la méthode abstraite dans la classe Game
	public boolean playMove(final IGameBoard board, final IGameBoardMove moveToPlay) {
		final OthelloMove othelloMove = (OthelloMove)moveToPlay;
		if (!othelloMove.isNull()) {
			final IGamePiece playerPiece = this.piece(othelloMove.getSide(), OthelloPieceTypes.PAWN);
			this.getCell(othelloMove.getPosition()).setPiece(playerPiece);
			othelloMove.setCellsToRevert(this.computeCellsToRevert(board, othelloMove));
			this.revertCells(othelloMove.getCellsToRevert());
		}
		return true;  // is move done ?
	}
	// ------------------------------------------------------------
	// TODO renommer : undoMove/doMove
	public boolean undo(final IGameBoardMove playedMove) {
		if(!playedMove.isNull()) {
			this.getCell(playedMove.getPosition()).setPiece(null); //TODO ? utiliser la pièce nulle
			final OthelloMove othelloMove = (OthelloMove)playedMove;
			this.revertCells(othelloMove.getCellsToRevert());
		}
		return true; // is move undone ?
	}
	// ------------------------------------------------------------
	/*
	public GamePlayersEnumeration isGameOver(final IGameBoard board, final IGameBoardMove justPlayedMove) {
		return
			this.getLegalMoves(board, GamePlayersEnumeration.FIRST_PLAYER).size() == 1
			&&
			this.getLegalMoves(board, GamePlayersEnumeration.SECOND_PLAYER).size() == 1
		;
		return null; // TODO
	}
	*/
	
	public GamePlayersEnumeration isGameOver(final IGameBoard gameState, final IGameBoardMove justPlayedMove) {
		
		GamePlayersEnumeration nextPlayer;
		// Suite à ce coup, si l'adversaire ne peut plus jouer
		if(this.getLegalMoves(this.getBoard(), justPlayedMove.getSide().getOpponent()).isEmpty()) {
			nextPlayer = GamePlayersEnumeration.NONE;
		}
		else {
			nextPlayer = justPlayedMove.getSide().getOpponent();
		}
		return nextPlayer;
	}
	// ------------------------------------------------------------		
	public double evaluate(IGameBoardMove move) {
		// TODO Auto-generated method stub
		return 0;
	}
	// ------------------------------------------------------------	
	@Override
	protected IGameBoard setupBoard(final IGameBoard board) {
		board.getCell(4, 5).setPiece(this.piece(GamePlayersEnumeration.FIRST_PLAYER));
		board.getCell(5, 4).setPiece(this.piece(GamePlayersEnumeration.FIRST_PLAYER));
		board.getCell(4, 4).setPiece(this.piece(GamePlayersEnumeration.SECOND_PLAYER));
		board.getCell(5, 5).setPiece(this.piece(GamePlayersEnumeration.SECOND_PLAYER));
		return board;
	}
	// ------------------------------------------------------------
	private IGamePiece piece(GamePlayersEnumeration player) {
		return super.piece(player, OthelloPieceTypes.PAWN);
	}
	// ------------------------------------------------------------	
	private boolean isNeighbourCellHavingOpponentPiece(final IGameBoardCell neighbourCell, final GamePlayersEnumeration side) {
		// Est-ce que la cellule : (existe, n'est pas vide, contient une pièce de l'adversaire)
		return !neighbourCell.isNull() && !neighbourCell.isEmpty() && neighbourCell.getPiece().getSide() == side.getOpponent();
		 // TODO utiliser la pièce nulle et la celulle nulle
	}
	// ------------------------------------------------------------
	private boolean isBoundableInThisDirection(final Entry<GameBoardCardinalPosition, IGameBoardCell> neighbourEntry) {
		boolean isBoundable = false;
		final GameBoardCardinalPosition direction = neighbourEntry.getKey();
		final GamePlayersEnumeration opponent = neighbourEntry.getValue().getPiece().getSide().getOpponent();
		IGameBoardCell neighbourCell = neighbourEntry.getValue().getNeighbour(direction);
		// tant qu'une cellule voisine existe et qu'elle n'est pas vide
		while (!(neighbourCell.isNull() && neighbourCell.isEmpty())) {	
			// si la cellule voisine contient une pièce de l'adversaire
			if (neighbourCell.getPiece().getSide() == opponent) {
				isBoundable = true;
				break;
			}
			neighbourCell = neighbourCell.getNeighbour(direction);
		}
		return isBoundable;
	}
	// ------------------------------------------------------------
	// TODO ? rajouter à l'interface
	public boolean canPlayHere(final IGameBoardCell cell, final GamePlayersEnumeration side) {
		boolean canPlayHere = false;
		// si la cellule est vide
		if (cell.isEmpty()) {
			//si la cellule est vide, les cellules voisines sont inspectées 
			for ( Entry<GameBoardCardinalPosition, IGameBoardCell> cellNeighbourEntry : cell.getNeighbourhood().entrySet()) {
				// si une des cellules voisines contient au moins une pièce de l'adversaire
				// et qu'une pièce du joueur se trouve à l'extrémité d'une série continue de pièces de l'adversaire			
				if (this.isNeighbourCellHavingOpponentPiece(cellNeighbourEntry.getValue(), side) && this.isBoundableInThisDirection(cellNeighbourEntry)) {
					canPlayHere = true;
					break;
				}
			}
		}
		return canPlayHere;
	}
	// ------------------------------------------------------------
	// TODO ? implémentation par défaut dans la classe abstraite
	private IGameBoardMove makeMove(final GamePlayersEnumeration side, final IGameBoardPosition position) {
		// TODO utiliser un cache
		return new GameBoardMove(side, position);
	}
	// ------------------------------------------------------------		
	private List<IGameBoardCell> computeCellsToRevert(final IGameBoard board, final IGameBoardMove move) {
		final GamePlayersEnumeration side = move.getSide();
		final IGameBoardCell cell = board.getCell(move.getPosition());
		final List<IGameBoardCell> cellsToRevert = new ArrayList<IGameBoardCell>();
		final List<IGameBoardCell> opponentCells = new ArrayList<IGameBoardCell>();
		IGameBoardCell neighbourCell;
		for ( Entry<GameBoardCardinalPosition, IGameBoardCell> cellNeighbourEntry : cell.getNeighbourhood().entrySet()) {
			//si la cellule voisine ne contient pas une pièce de l'adversaire
			if(!this.isNeighbourCellHavingOpponentPiece(cellNeighbourEntry.getValue(), side)) {
				continue;
			}
			opponentCells.clear();
			opponentCells.add(cellNeighbourEntry.getValue());
			neighbourCell = cellNeighbourEntry.getValue().getNeighbour(cellNeighbourEntry.getKey());
			// tant qu'une cellule voisine existe
			while (!neighbourCell.isNull()) {
				// si la cellule voisine est vide
				if (neighbourCell.isEmpty()) {
					break;
				}
				// si la cellule voisine contient une pièce du joueur
				if (neighbourCell.getPiece().getSide() == side) {
					cellsToRevert.addAll(opponentCells);
					break;
				} else {
					opponentCells.add(neighbourCell);
				}
				neighbourCell = neighbourCell.getNeighbour(cellNeighbourEntry.getKey());
			}
		}
		return cellsToRevert;		
	}	
	// ------------------------------------------------------------
	private void revertCells(List<IGameBoardCell> cellsToRevert) {
		final IGamePiece piece = this.piece(cellsToRevert.get(0).getPiece().getSide().getOpponent());
		for (IGameBoardCell cell : cellsToRevert) {
			cell.setPiece(piece);
		}
	}
	// ------------------------------------------------------------	
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		new GameService(new GameBuilder(StaticContext.thatClass()).build()).start();
	}
	// ------------------------------------------------------------	
}