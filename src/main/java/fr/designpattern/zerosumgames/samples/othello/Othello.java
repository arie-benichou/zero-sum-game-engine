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

package fr.designpattern.zerosumgames.samples.othello;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import fr.designpattern.zerosumgames.framework.gameplay.game.GameBuilder;
import fr.designpattern.zerosumgames.framework.gameplay.game.Game;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.BoardInterface;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.BoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.Dimension;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.cells.pieces.PieceInterface;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.cells.pieces.Pieces;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.cells.positions.PositionInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.OpponentsInterface;
import fr.designpattern.zerosumgames.framework.moves.MoveInterface;
import fr.designpattern.zerosumgames.services.GameService;
import fr.designpattern.zerosumgames.util.StaticContext;

//TODO améliorer la fonction d'évaluation en prenant en compte le nombre de cellules voisines à celle jouée
public class Othello extends Game {	
	// ------------------------------------------------------------
	public final static Class<OthelloPieceTypes> PIECE_TYPES = OthelloPieceTypes.class;
	public final static Dimension BOARD_DIMENSION = new Dimension(1, 8, 1, 8);
	//private transient int nullMoveHasBeenPlayed = 0;
	// ------------------------------------------------------------
	private void setupBoard(final BoardInterface board) {
		board.getCell(4, 5).setPiece(this.piece(OpponentsEnumeration.FIRST_PLAYER));
		board.getCell(5, 4).setPiece(this.piece(OpponentsEnumeration.FIRST_PLAYER));
		board.getCell(4, 4).setPiece(this.piece(OpponentsEnumeration.SECOND_PLAYER));
		board.getCell(5, 5).setPiece(this.piece(OpponentsEnumeration.SECOND_PLAYER));
	}	
	// ------------------------------------------------------------
	public Othello(final BoardInterface board, final OpponentsInterface opponents) {
		super(new Pieces(PIECE_TYPES), board, opponents);// TODO !! à revoir
		this.setupBoard(board);
	}
	// ------------------------------------------------------------
	@Override
	public boolean hasNullMove() {
		return true;
	}
	// ------------------------------------------------------------
	// TODO à virer
	public final List<LegalMoveInterface> getLegalMoves(final OpponentsEnumeration side, final LegalMoveInterface previousMove) {
		final List<LegalMoveInterface> legalMoves = new ArrayList<LegalMoveInterface>();
		for (CellInterface[] line : this.getBoard()) {
			for (CellInterface cell : line) {
				if (this.canPlayHere(cell, side)) {
					legalMoves.add(this.makeMove(side, cell.getPosition()));
				}
			}
		}
		// TODO ? cache du nullMove pour chaque side
		legalMoves.add(this.makeMove(side, this.cell(null).getPosition()));
		return legalMoves;
	}
	// ------------------------------------------------------------	
	public final List<LegalMoveInterface> getLegalMoves(final OpponentsEnumeration side) {
		final List<LegalMoveInterface> legalMoves = new ArrayList<LegalMoveInterface>();
		for (CellInterface[] line : this.getBoard()) {
			for (CellInterface cell : line) {
				if (this.canPlayHere(cell, side)) {
					legalMoves.add(this.makeMove(side, cell.getPosition()));
				}
			}
		}
		// TODO ? cache du nullMove pour chaque side
		legalMoves.add(this.makeMove(side, this.cell(null).getPosition()));
		return legalMoves;
	}	
	// ------------------------------------------------------------
	public boolean undoMove(final LegalMoveInterface playedMove) {
		if(!playedMove.isNull()) {
			this.cell(playedMove.getPosition()).setPiece(null); //TODO ? utiliser la pièce nulle
			final OthelloMove othelloMove = (OthelloMove)playedMove;
			this.revertCells(othelloMove.getCellsToRevert());
		}
		else {
			//--this.nullMoveHasBeenPlayed;
		}
		return true; // is move undone ?
	}
	// ------------------------------------------------------------
	private boolean isGameOver(LegalMoveInterface previousMove) {
		boolean isGameOver = false;
		// Game Over si deux coups nuls consécutifs
		//if(this.nullMoveHasBeenPlayed > 1) {
			//isGameOver = true;
			//System.out.println("\ndeux coups nuls consécutifs");
		//}
		// Game Over s'il ne reste que le coup nul pour chacun des joueurs
		/*else*/ if(this.getLegalMoves(OpponentsEnumeration.FIRST_PLAYER, previousMove).size() == 1 && this.getLegalMoves(OpponentsEnumeration.SECOND_PLAYER, previousMove).size() == 1) {
			isGameOver = true;
			//System.out.println("\nplus de coups légaux");
		}
		return isGameOver;
	}
	// ------------------------------------------------------------
	private PieceInterface piece(final OpponentsEnumeration player) {
		return super.piece(player, OthelloPieceTypes.PAWN);
	}
	// ------------------------------------------------------------	
	private boolean isNeighbourCellHavingOpponentPiece(final CellInterface neighbourCell, final OpponentsEnumeration side) {
		// Est-ce que la cellule : (existe, n'est pas vide, contient une pièce de l'adversaire)
		return !neighbourCell.isNull() && !neighbourCell.isEmpty() && neighbourCell.getPiece().getSide() == OpponentsEnumeration.opponent(side);
		 // TODO utiliser la pièce nulle et la celulle nulle
	}
	// ------------------------------------------------------------
	private boolean isBoundableInThisDirection(final Entry<BoardCardinalPosition, CellInterface> neighbourEntry) {
		boolean isBoundable = false;
		final BoardCardinalPosition direction = neighbourEntry.getKey();
		final OpponentsEnumeration opponent = OpponentsEnumeration.opponent(neighbourEntry.getValue().getPiece().getSide());
		CellInterface neighbourCell = neighbourEntry.getValue().getNeighbour(direction);
		// tant qu'une cellule voisine existe et qu'elle n'est pas vide
		while (!neighbourCell.isNull() && !neighbourCell.isEmpty()) {	
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
	public boolean canPlayHere(final CellInterface cell, final OpponentsEnumeration side) {
		boolean canPlayHere = false;
		// si la cellule est vide
		if (cell.isEmpty()) {
			//si la cellule est vide, les cellules voisines sont inspectées 
			for ( Entry<BoardCardinalPosition, CellInterface> cellNeighbourEntry : cell.getNeighbourhood().entrySet()) {
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
	private LegalMoveInterface makeMove(final OpponentsEnumeration side, final PositionInterface position) {
		// TODO utiliser un cache
		return new OthelloMove(side, position);
	}
	// ------------------------------------------------------------		
	private List<CellInterface> computeCellsToRevert(final LegalMoveInterface move) {
		final OpponentsEnumeration side = move.getSide();
		final CellInterface cell = this.cell(move.getPosition());
		final List<CellInterface> cellsToRevert = new ArrayList<CellInterface>();
		final List<CellInterface> opponentCells = new ArrayList<CellInterface>();
		CellInterface neighbourCell;
		for ( Entry<BoardCardinalPosition, CellInterface> cellNeighbourEntry : cell.getNeighbourhood().entrySet()) {
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
	private void revertCells(final List<CellInterface> cellsToRevert) {
		final PieceInterface piece = this.piece(OpponentsEnumeration.opponent(cellsToRevert.get(0).getPiece().getSide()));
		for (CellInterface cell : cellsToRevert) {
			cell.setPiece(piece);
		}
	}
	// ------------------------------------------------------------	
	// TODO renommer : doMove/undoMove
	// TODO laisser la méthode abstraite dans la classe Game	
	public boolean doMove(final LegalMoveInterface moveToPlay) {
		final OthelloMove othelloMove = (OthelloMove)moveToPlay;
		
		if (!othelloMove.isNull()) {
			
			//this.nullMoveHasBeenPlayed = 0;
			
			final PieceInterface playerPiece = this.piece(othelloMove.getSide(), OthelloPieceTypes.PAWN);
			this.cell(othelloMove.getPosition()).setPiece(playerPiece);
			othelloMove.setCellsToRevert(this.computeCellsToRevert(othelloMove));
			this.revertCells(othelloMove.getCellsToRevert());
		}
		else {
			//++this.nullMoveHasBeenPlayed;
			///System.out.println("coup nul joué : " + this.nullMoveHasBeenPlayed);
		}
		
		////System.out.println("delta pour " + othelloMove.getSide() + this.computeDelta(othelloMove.getSide()) );
		return true;  // is move done ?
	}
	// ------------------------------------------------------------
	// TODO à optimiser
	public int computeDelta(final OpponentsEnumeration side) {
		int delta = 0;
		for (CellInterface[] line : this.getBoard()) {
			for (CellInterface cell : line) {
				if( cell.isNull() || cell.isEmpty() ){
					continue;
				}
				if(cell.getPiece().getSide().equals(side)) {
					++delta;
				}
				else {
					--delta;
				}
			}
		}
		return delta;
	}
	// ------------------------------------------------------------	
	public boolean isGameOverFromVictory(final LegalMoveInterface previousMove) {
		return this.isGameOver(previousMove) && this.computeDelta(previousMove.getSide()) > 0;
	}
	// ------------------------------------------------------------		
	public boolean isGameOverFromDraw(final LegalMoveInterface previousMove) {
		return this.isGameOver(previousMove) && this.computeDelta(previousMove.getSide()) == 0;
	}
	// ------------------------------------------------------------		
	public double evaluate(final LegalMoveInterface move) {
		
		if(move.isNull()) {
			return this.computeDelta(move.getSide());
		}
		
		int n = 1;
		for ( Entry<BoardCardinalPosition, CellInterface> cellNeighbourEntry : this.cell(move.getPosition()).getNeighbourhood().entrySet()) {
			if(cellNeighbourEntry.getValue().isNull()) {
				n+=1;
			}
		}
		
		return this.computeDelta(move.getSide()) + n;
	}
	// ------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		new GameService(new GameBuilder(StaticContext.thatClass()).build()).start();
	}
	// ------------------------------------------------------------	
}