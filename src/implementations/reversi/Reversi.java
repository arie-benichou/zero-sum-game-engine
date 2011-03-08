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

package implementations.reversi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import core.Game;
import core.GameBoard;
import core.GameBoardCellFactory;
import core.GameBoardDimension;
import core.GameBoardMove;
import core.GameBoardPositionFactory;
import core.GamePieceFactory;
import core.GamePlayer;
import core.GamePlayerRandomStrategy;
import core.interfaces.IGameBoard;
import core.interfaces.IGameBoardCell;
import core.interfaces.IGameBoardCellFactory;
import core.interfaces.IGameBoardDimension;
import core.interfaces.IGameBoardMove;
import core.interfaces.IGameBoardPositionFactory;
import core.interfaces.IGamePiece;
import core.interfaces.IGamePieceFactory;
import core.interfaces.IGamePlayer;
import core.interfaces.IGamePlayerStrategy;
import core.types.GameBoardCardinalPosition;
import core.types.GamePlayerNature;
import core.types.GamePlayersEnumeration;

public class Reversi extends Game {	
	// ------------------------------------------------------------	
	@Override
	protected void setupInitialGameState() {
		IGamePiece blackPawn = this.piece(GamePlayersEnumeration.FIRST_PLAYER, ReversiPieceTypes.PAWN);
		IGamePiece whitePawn = this.piece(GamePlayersEnumeration.SECOND_PLAYER, ReversiPieceTypes.PAWN);
		this.getCell(4, 5).setPiece(blackPawn);
		this.getCell(5, 4).setPiece(blackPawn);
		this.getCell(4, 4).setPiece(whitePawn);
		this.getCell(5, 5).setPiece(whitePawn);
	}
	// ------------------------------------------------------------
	public Reversi(IGameBoard board, IGameBoardPositionFactory positionFactory, IGamePieceFactory pieceFactory, List<IGamePlayer> opponents) {
		super(pieceFactory, board, opponents);
	}
	// ------------------------------------------------------------
	@Override
	public boolean hasNullMove() {
		return true;
	}
	// ------------------------------------------------------------
	private boolean isNeighbourCellHavingOpponentPiece(IGameBoardCell neighbourCell, GamePlayersEnumeration side) {
		// Si la cellule n'existe pas ou si la cellule est vide		
		if (neighbourCell.isNull() || neighbourCell.isEmpty()) {
			return false;
		}
		// Si la cellule contient une pièce du même joueur		
		if (neighbourCell.getPiece().getSide() == side) {
			return false;
		}
		// La cellule contient une pièce de l'adversaire
		return true;
	}
	// ------------------------------------------------------------
	private boolean hasBoundInThisDirection(Entry<GameBoardCardinalPosition, IGameBoardCell> cellNeighbourEntry) {
		IGameBoardCell neighbourCell = cellNeighbourEntry.getValue();
		// tant qu'une cellule voisine existe
		while (!(neighbourCell = neighbourCell.getNeighbour(cellNeighbourEntry.getKey())).isNull()) {	
			// si la cellule voisine est vide
			if (neighbourCell.isEmpty()) {
				return false;
			}
			// si la cellule voisine contient une pièce de l'adversaire
			if (neighbourCell.getPiece().getSide() != cellNeighbourEntry.getValue().getPiece().getSide()) {
				// TODO redéfinir les méthodes equals() et hashcode() d'une pièce
				return true;
			}
		}
		return false;
	}
	// ------------------------------------------------------------
	// TODO ? rajouter à l'interface
	public boolean canPlayHere(IGameBoardCell cell, GamePlayersEnumeration side) {
		// si la cellule n'est pas vide
		if (!cell.isEmpty()) {
			return false;
		}
		//si la cellule n'est pas vide, les cellules voisines sont inspectées 
		for ( Entry<GameBoardCardinalPosition, IGameBoardCell> cellNeighbourEntry : cell.getNeighbourhood().entrySet()) {
			// si une des cellules voisines contient au moins une pièce de l'adversaire
			if (this.isNeighbourCellHavingOpponentPiece(cellNeighbourEntry.getValue(), side)) {
				// et qu'une pièce du joueur se trouve à l'extrémité d'une série continue de pièces de l'adversaire
				if (this.hasBoundInThisDirection(cellNeighbourEntry)) {
					return true;
				}
			}
		}
		return false;
	}
	// ------------------------------------------------------------
	@Override
	public final List<IGameBoardMove> getLegalMoves(IGameBoard board, GamePlayersEnumeration side) {
		List<IGameBoardMove> legalGameTransitions = new ArrayList<IGameBoardMove>();
		for (IGameBoardCell[] line : this.getBoard()) {
			for (IGameBoardCell cell : line) {
				if (this.canPlayHere(cell, side)) {
					// TODO utiliser une factory de move avec un cache
					legalGameTransitions.add(new GameBoardMove(side, cell.getPosition()));
				}
			}
		}
		
		// TODO ? cache du nullMove pour chaque side
		legalGameTransitions.add(new GameBoardMove(side, this.getCell(null).getPosition()));
		
		return legalGameTransitions;
	}
	// ------------------------------------------------------------
	private List<IGameBoardCell> getCellsToRevert(IGameBoardMove move) {
		GamePlayersEnumeration side = move.getSide();
		IGameBoardCell cell = this.getBoard().getCell(move.getPosition());
		List<IGameBoardCell> cellsToRevert = new ArrayList<IGameBoardCell>();
		List<IGameBoardCell> opponentCells = new ArrayList<IGameBoardCell>();
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
	public GamePlayersEnumeration applyGameStateTransition(IGameBoard gameState, IGameBoardMove justPlayedMove) {
		
		if (justPlayedMove.isNull()) {
			return super.applyGameStateTransition(gameState, justPlayedMove);
		}
				
		// TODO ! méthode playMove
		IGamePiece playerPiece = this.piece(justPlayedMove.getSide(), ReversiPieceTypes.PAWN);
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
	// TODO ! GameBuilder
	public static void main(String[] args) throws Exception {
		// ------------------------------------------------------------
		GamePieceFactory gpf = new GamePieceFactory(ReversiPieceTypes.class);
		// ------------------------------------------------------------		
		IGameBoardDimension gbd = new GameBoardDimension(1, 8, 1, 8);
		IGameBoardPositionFactory gbpf = new GameBoardPositionFactory(gbd);
		IGameBoardCellFactory gbcf = new GameBoardCellFactory(gbpf);
		IGameBoard board = new GameBoard(gbcf);
		// ------------------------------------------------------------
		IGamePlayerStrategy player1Strategy = new GamePlayerRandomStrategy();
		IGamePlayer player1 = new GamePlayer("Player 1", GamePlayersEnumeration.FIRST_PLAYER, GamePlayerNature.COMPUTER, player1Strategy);
		IGamePlayerStrategy player2Strategy = new GamePlayerRandomStrategy();
		IGamePlayer player2 = new GamePlayer("Player 2", GamePlayersEnumeration.SECOND_PLAYER, GamePlayerNature.COMPUTER, player2Strategy);
		List<IGamePlayer> opponents = new ArrayList<IGamePlayer>();
		opponents.add(player1);
		opponents.add(player2);
		// ------------------------------------------------------------		
		new Reversi(board, gbpf, gpf, opponents).start();
		// ------------------------------------------------------------				
	}
	// ------------------------------------------------------------
}