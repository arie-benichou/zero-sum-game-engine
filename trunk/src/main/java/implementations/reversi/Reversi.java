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

package main.java.implementations.reversi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import main.java.core.AbstractGame;
import main.java.core.GameBoardDimension;
import main.java.core.GameBoardMove;
import main.java.core.GameBuilder;
import main.java.core.GamePieceFactory;
import main.java.core.interfaces.IGameBoard;
import main.java.core.interfaces.IGameBoardCell;
import main.java.core.interfaces.IGameBoardMove;
import main.java.core.interfaces.IGamePiece;
import main.java.core.interfaces.IGamePlayer;
import main.java.core.types.GameBoardCardinalPosition;
import main.java.core.types.GamePlayersEnumeration;
import main.java.util.StaticContext;

public class Reversi extends AbstractGame {	
	// ------------------------------------------------------------
	public final static Class<ReversiPieceTypes> PIECE_TYPES = ReversiPieceTypes.class;
	public final static GameBoardDimension BOARD_DIMENSION = new GameBoardDimension(1, 8, 1, 8);
	// ------------------------------------------------------------	
	@Override
	protected void setupInitialGameState() {
		final IGamePiece blackPawn = this.piece(GamePlayersEnumeration.FIRST_PLAYER, ReversiPieceTypes.PAWN);
		final IGamePiece whitePawn = this.piece(GamePlayersEnumeration.SECOND_PLAYER, ReversiPieceTypes.PAWN);
		this.getCell(4, 5).setPiece(blackPawn);
		this.getCell(5, 4).setPiece(blackPawn);
		this.getCell(4, 4).setPiece(whitePawn);
		this.getCell(5, 5).setPiece(whitePawn);
	}
	// ------------------------------------------------------------
	public Reversi(final IGameBoard board, final List<IGamePlayer> opponents) {
		// TODO !! à revoir
		super(new GamePieceFactory(PIECE_TYPES), board, opponents);
	}
	// ------------------------------------------------------------
	@Override
	public boolean hasNullMove() {
		return true;
	}
	// ------------------------------------------------------------
	private boolean isNeighbourCellHavingOpponentPiece(final IGameBoardCell neighbourCell, final GamePlayersEnumeration side) {
		// Si !(la cellule n'existe pas ou si la cellule est vide ou si la cellule contient une pièce du même joueur)
		return !(neighbourCell.isNull() || neighbourCell.isEmpty() || neighbourCell.getPiece().getSide() == side);
	}
	// ------------------------------------------------------------
	private boolean hasBoundInThisDirection(final Entry<GameBoardCardinalPosition, IGameBoardCell> neighbourEntry) {
		IGameBoardCell neighbourCell = neighbourEntry.getValue();
		// tant qu'une cellule voisine existe
		while (!(neighbourCell = neighbourCell.getNeighbour(neighbourEntry.getKey())).isNull()) {	
			// si la cellule voisine est vide
			if (neighbourCell.isEmpty()) {
				return false;
			}
			// si la cellule voisine contient une pièce de l'adversaire
			if (neighbourCell.getPiece().getSide() != neighbourEntry.getValue().getPiece().getSide()) {
				// TODO redéfinir les méthodes equals() et hashcode() d'une pièce
				return true;
			}
		}
		return false;
	}
	// ------------------------------------------------------------
	// TODO ? rajouter à l'interface
	public boolean canPlayHere(final IGameBoardCell cell, final GamePlayersEnumeration side) {
		// si la cellule n'est pas vide
		if (cell.isEmpty()) {
			//si la cellule n'est pas vide, les cellules voisines sont inspectées 
			for ( Entry<GameBoardCardinalPosition, IGameBoardCell> cellNeighbourEntry : cell.getNeighbourhood().entrySet()) {
				// si une des cellules voisines contient au moins une pièce de l'adversaire
				// et qu'une pièce du joueur se trouve à l'extrémité d'une série continue de pièces de l'adversaire			
				if (this.isNeighbourCellHavingOpponentPiece(cellNeighbourEntry.getValue(), side) && this.hasBoundInThisDirection(cellNeighbourEntry)) {
					return true;
				}
			}
		}
		return false;
	}
	// ------------------------------------------------------------
	@Override
	public final List<IGameBoardMove> getLegalMoves(final IGameBoard board, final GamePlayersEnumeration side) {
		final List<IGameBoardMove> legalMoves = new ArrayList<IGameBoardMove>();
		for (IGameBoardCell[] line : this.getBoard()) {
			for (IGameBoardCell cell : line) {
				if (this.canPlayHere(cell, side)) {
					// TODO utiliser une méthode de création
					legalMoves.add(new GameBoardMove(side, cell.getPosition()));
				}
			}
		}
		// TODO ? cache du nullMove pour chaque side
		legalMoves.add(new GameBoardMove(side, this.getCell(null).getPosition()));
		return legalMoves;
	}
	// ------------------------------------------------------------
	private List<IGameBoardCell> getCellsToRevert(final IGameBoardMove move) {
		final GamePlayersEnumeration side = move.getSide();
		final IGameBoardCell cell = this.getBoard().getCell(move.getPosition());
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
	// TODO renommer justPlayedMove en legalMoveChoosenByCurrentPlayer
	@Override
	public GamePlayersEnumeration applyGameStateTransition(final IGameBoard gameState, final IGameBoardMove justPlayedMove) {
		if (justPlayedMove.isNull()) {
			return super.applyGameStateTransition(gameState, justPlayedMove);
		}
		// TODO ! méthode playMove
		final IGamePiece playerPiece = this.piece(justPlayedMove.getSide(), ReversiPieceTypes.PAWN);
		this.getCell(justPlayedMove.getPosition()).setPiece(playerPiece);
		for (IGameBoardCell cellToRevert : this.getCellsToRevert(justPlayedMove)) {
			cellToRevert.setPiece(playerPiece);
		}
		// TODO ! à améliorer		
		if(this.isGameOver(gameState, justPlayedMove)) {
			return null;
		}
		// TODO ! à améliorer
		return super.applyGameStateTransition(gameState, justPlayedMove);
	}
	// ------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		new GameBuilder(StaticContext.thatClass()).build().start();
	}
	// ------------------------------------------------------------				
}