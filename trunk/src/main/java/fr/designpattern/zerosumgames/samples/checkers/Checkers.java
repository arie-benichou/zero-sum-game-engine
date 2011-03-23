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

package fr.designpattern.zerosumgames.samples.checkers;

import java.util.ArrayList;
import java.util.List;

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
import fr.designpattern.zerosumgames.samples.checkers.pieces.CheckersPiece;
import fr.designpattern.zerosumgames.services.GameService;
import fr.designpattern.zerosumgames.util.StaticContext;

public class Checkers extends Game {
	// ------------------------------------------------------------	
	public final static Class<CheckersPieceTypes> PIECE_TYPES = CheckersPieceTypes.class;
	public final static Dimension BOARD_DIMENSION = new Dimension(1, 8, 1, 8); 
	// ------------------------------------------------------------
	public Checkers(final BoardInterface board, final OpponentsInterface opponents) {
		// TODO !! à revoir
		super(new Pieces(PIECE_TYPES), board, opponents);
		this.setupTestBoard(board);
	}
	// ------------------------------------------------------------
	private void setupTestBoard(final BoardInterface board) {
		this.setupBoard(board);
		
		/*
		board.getCell(8, 4).setPiece(this.piece(GamePlayersEnumeration.FIRST_PLAYER, CheckersPieceTypes.MAN));
		//board.getCell(7, 5).setPiece(this.piece(GamePlayersEnumeration.FIRST_PLAYER, CheckersPieceTypes.MAN));
		board.getCell(7, 5).setPiece(this.piece(GamePlayersEnumeration.SECOND_PLAYER, CheckersPieceTypes.MAN));
		board.getCell(5, 5).setPiece(this.piece(GamePlayersEnumeration.SECOND_PLAYER, CheckersPieceTypes.MAN));
		board.getCell(5, 7).setPiece(this.piece(GamePlayersEnumeration.SECOND_PLAYER, CheckersPieceTypes.MAN));
		board.getCell(3, 3).setPiece(this.piece(GamePlayersEnumeration.SECOND_PLAYER, CheckersPieceTypes.MAN));
		board.getCell(3, 5).setPiece(this.piece(GamePlayersEnumeration.SECOND_PLAYER, CheckersPieceTypes.MAN));
		board.getCell(1, 1).setPiece(this.piece(GamePlayersEnumeration.SECOND_PLAYER, CheckersPieceTypes.MAN));
		
		//System.out.println(this);
		//System.exit(0);
		*/
		
		/*
		board.getCell(4, 7).setPiece(this.piece(GamePlayersEnumeration.SECOND_PLAYER, CheckersPieceTypes.MAN));
		board.getCell(5, 6).setPiece(this.piece(GamePlayersEnumeration.FIRST_PLAYER, CheckersPieceTypes.MAN));
		board.getCell(7, 4).setPiece(this.piece(GamePlayersEnumeration.FIRST_PLAYER, CheckersPieceTypes.MAN));		
		board.getCell(7, 6).setPiece(this.piece(GamePlayersEnumeration.FIRST_PLAYER, CheckersPieceTypes.MAN));
		*/
		
		/*
		System.out.println(this);
		
		IGameBoardMove moveToPlay = this.makeMove(GamePlayersEnumeration.FIRST_PLAYER , this.cell(6,1).getPosition(), GameBoardCardinalPosition.TOP_RIGHT);
		
		this.doMove(moveToPlay);
		System.out.println(this);
		
		this.undoMove(moveToPlay);		
		System.out.println(this);
		
		
		
		System.out.println(this);
		
		moveToPlay = this.makeMove(GamePlayersEnumeration.FIRST_PLAYER , this.cell(4,7).getPosition(), GameBoardCardinalPosition.BOTTOM_LEFT);
		
		this.doMove(moveToPlay);
		System.out.println(this);
		
		this.undoMove(moveToPlay);		
		System.out.println(this);		
		
		
		System.exit(0);
		*/
		
		
		
		
		
		/*
		---------------------------------
		|   | X |   | X |   | X |   |   |
		---------------------------------
		| x |   |   |   |   |   |   |   |
		---------------------------------
		|   |   |   |   |   |   |   |   |
		---------------------------------
		|   |   |   |   |   |   |   |   |
		---------------------------------
		|   | X |   |   |   | o |   |   |
		---------------------------------
		|   |   |   |   |   |   |   |   |
		---------------------------------
		|   |   |   |   |   |   |   |   |
		---------------------------------
		| O |   |   |   |   |   |   |   |
		---------------------------------
		*/
		
		/*
		board.getCell(1, 1).setPiece(this.piece(GamePlayersEnumeration.FIRST_PLAYER, CheckersPieceTypes.KING));
		board.getCell(1, 3).setPiece(this.piece(GamePlayersEnumeration.FIRST_PLAYER, CheckersPieceTypes.KING));
		//board.getCell(2, 1).setPiece(this.piece(GamePlayersEnumeration.FIRST_PLAYER, CheckersPieceTypes.MAN));
		board.getCell(4, 2).setPiece(this.piece(GamePlayersEnumeration.SECOND_PLAYER, CheckersPieceTypes.KING));
		board.getCell(4, 4).setPiece(this.piece(GamePlayersEnumeration.FIRST_PLAYER, CheckersPieceTypes.KING));
		//board.getCell(8, 1).setPiece(this.piece(GamePlayersEnumeration.SECOND_PLAYER, CheckersPieceTypes.KING));
		*/
		
		//System.out.println(this);
		
		//System.exit(0);
		
	}
	// ------------------------------------------------------------	
	private void setupBoard(final BoardInterface board) {
		int n, clientColumnIndex;
		for(int clientRowIndex = 1; clientRowIndex<=3; ++clientRowIndex) {
			for(n = 1; n<=4; ++n) {
				clientColumnIndex = 2*n + clientRowIndex% 2 - 1;
				board.getCell(clientRowIndex, clientColumnIndex).setPiece(this.piece(OpponentsEnumeration.SECOND_PLAYER, CheckersPieceTypes.MAN));
			}
		}
		// TODO permettre d'autres dimensions		
		for(int clientRowIndex = 6; clientRowIndex<=8; ++clientRowIndex) {
			for(n = 1; n<=4; ++n) {
				clientColumnIndex = 2*n + clientRowIndex% 2 - 1;
				board.getCell(clientRowIndex, clientColumnIndex).setPiece(this.piece(OpponentsEnumeration.FIRST_PLAYER, CheckersPieceTypes.MAN));
			}
		}
	}
	// -----------------------------------------------------------------	
	private List<CellInterface> getRelevantCells(final OpponentsEnumeration side) {
		final List<CellInterface> relevantCells = new ArrayList<CellInterface>();
		for (CellInterface[] line : this.getBoard()) {
			for(CellInterface cell : line) {
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
	private LegalMoveInterface makeMove(final OpponentsEnumeration side, final PositionInterface position, final BoardCardinalPosition direction) {
		// TODO utiliser un cache
		return new CheckersMove(side, position, direction);
	}
	// ------------------------------------------------------------
	@Override
	public List<LegalMoveInterface> getLegalMoves(OpponentsEnumeration side) {
		final List<LegalMoveInterface> jumpingMoves = new ArrayList<LegalMoveInterface>();
		CheckersPiece piece;
		List<BoardCardinalPosition> pieceOptions;
		
		// TODO chaque jeu doit définir son nullMove
		if(!previousMove.isNull()) {
			final CheckersMove previousCheckersMove = (CheckersMove)previousMove;
			if(!previousCheckersMove.isDone()) {
				//System.out.println("Tu n'as pas fini ton coup, celà réduit les coups légaux possibles...");
				//System.out.println(previousCheckersMove.getPosition());
				//System.out.println(previousCheckersMove.getDirection());
				CellInterface cell = this.cell(previousCheckersMove.getPosition()).getNeighbour(previousCheckersMove.getDirection()).getNeighbour(previousCheckersMove.getDirection());
				piece = (CheckersPiece)cell.getPiece();
				pieceOptions = piece.getJumpOptions(cell);
				for(BoardCardinalPosition direction : pieceOptions) {
					jumpingMoves.add(this.makeMove(side, cell.getPosition(), direction));
				}
				return jumpingMoves;
			}
		}
		
		final List<LegalMoveInterface> walkingMoves = new ArrayList<LegalMoveInterface>();		
		boolean hasToJump = false;
		
		for(CellInterface cell : this.getRelevantCells(side)) {
			piece = (CheckersPiece)cell.getPiece();
			pieceOptions = piece.getJumpOptions(cell);
			if(!pieceOptions.isEmpty()) {
				hasToJump = true;
				for(BoardCardinalPosition direction : pieceOptions) {
					jumpingMoves.add(this.makeMove(side, cell.getPosition(), direction));
				}
				continue;
			}
			if(!hasToJump) {
				pieceOptions = piece.getWalkOptions(cell);
				for(BoardCardinalPosition direction : pieceOptions) {
					walkingMoves.add(this.makeMove(side, cell.getPosition(), direction));
				}
			}
		}
		
		return jumpingMoves.isEmpty() ? walkingMoves : jumpingMoves; 
	}
	// ------------------------------------------------------------
	private boolean hasToKeepPlaying(final CheckersMove move) {
		CellInterface actualCell = this.cell(move.getPosition());
		actualCell = actualCell.getNeighbour(move.getDirection());
		actualCell = actualCell.getNeighbour(move.getDirection());
		final CheckersPiece piece =  (CheckersPiece) actualCell.getPiece();
		//Est-ce que la pièce peut encore sauter ?
		return !piece.getJumpOptions(actualCell).isEmpty();
	}
	// -----------------------------------------------------------------
	@Override
	public boolean doMove(final LegalMoveInterface moveToPlay) {
		final CheckersMove checkersMove = (CheckersMove)moveToPlay;
		// récupération de la cellule corespondant à la position
		final CellInterface cell = this.cell(checkersMove.getPosition());
		// récupération de la pièce à déplacer
		final CheckersPiece pieceToMove = (CheckersPiece)cell.getPiece();
		// suppression de la pièce à sa position actuelle
		cell.setPiece(null);
		// récupération de la cellulle correspondant à la direction choisie
		CellInterface destinationCell = cell.getNeighbour(checkersMove.getDirection());		
		// si la cellule n'est pas vide
		if(!destinationCell.isEmpty()) {
			// la pièce de cette cellule est supprimée
			checkersMove.setCapturedPiece(destinationCell.getPiece());
			destinationCell.setPiece(null); // TODO ? utiliser la pièce nulle
			// et la cellule de destination devient la suivante
			destinationCell = destinationCell.getNeighbour(checkersMove.getDirection());
		}
		// la pièce concernée par le coup est "déplacée" à sa cellule de destination
		destinationCell.setPiece(pieceToMove);
		checkersMove.hasBeenCrowned(false);
		// Si la pièce est un pion promotable
		if(pieceToMove.isPromotable(destinationCell)){
			checkersMove.hasBeenCrowned(true);
			// le pion est promu roi
			destinationCell.setPiece(this.piece(checkersMove.getSide(), CheckersPieceTypes.KING));
		}
		
		checkersMove.isDone(checkersMove.getCapturedPiece() == null || checkersMove.hasBeenCrowned() || !this.hasToKeepPlaying(checkersMove));
		
		return checkersMove.isDone();// TODO virer le return boolean de doMove et undoMove
	}
	// -----------------------------------------------------------------
	// TODO un move est un composiste de transistions de jeu
	// Pour Checkers, les transistions possibles sont :
		//une pièce se déplace d'une case
		//une pièce capture une autre pièce
		//une pièce se fait promouvoir en King
		//game over {victoire, match null}
	
	// -----------------------------------------------------------------
	public boolean undoMove(final LegalMoveInterface move) {
		
		final CheckersMove checkersMove = (CheckersMove)move;
		
		CellInterface cell = this.cell(move.getPosition()).getNeighbour(checkersMove.getDirection());
		
		if(checkersMove.getCapturedPiece() != null) {// TODO utiliser la pièce nulle
			cell.setPiece(checkersMove.getCapturedPiece());
			cell = cell.getNeighbour(checkersMove.getDirection());
		}
		
		PieceInterface piece = checkersMove.hasBeenCrowned() ? this.piece(move.getSide(), CheckersPieceTypes.MAN) : cell.getPiece();
		this.cell(move.getPosition()).setPiece(piece);
		
		cell.setPiece(null); // TODO utiliser la pièce nulle
		
		return true; // TODO à virer
	}	
	// -----------------------------------------------------------------
	// TODO à améliorer
	private boolean isGameOver(LegalMoveInterface previousMove) {
		if(this.getRelevantCells(OpponentsEnumeration.opponent(previousMove.getSide())).isEmpty()) {
			return true;
		}
		else if(this.getLegalMoves(OpponentsEnumeration.opponent(previousMove.getSide()), previousMove).isEmpty()) {
			return true;
		}
		return false;
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
					//System.out.println(cell.getPiece().getType());
					if(cell.getPiece().getType() == CheckersPieceTypes.KING) {
						delta+=2;
						//System.out.println("King 1 count twice!");
					}
				}
				else {
					--delta;
					//System.out.println(cell.getPiece().getType());
					if(cell.getPiece().getType() == CheckersPieceTypes.KING) {
						delta-=2;
						//System.out.println("King 2 count twice!");
					}					
				}
			}
		}
		return delta;
	}
	// ------------------------------------------------------------	
	public boolean isGameOverFromVictory(final LegalMoveInterface previousMove) {
		return this.isGameOver(previousMove) && this.computeDelta(previousMove.getSide()) != 0;
	}
	// ------------------------------------------------------------		
	public boolean isGameOverFromDraw(final LegalMoveInterface previousMove) {
		return this.isGameOver(previousMove) && this.computeDelta(previousMove.getSide()) == 0;
	}
	// ------------------------------------------------------------			
	public double evaluate(final LegalMoveInterface move) {
		return this.computeDelta(move.getSide());
	}	
	// -----------------------------------------------------------------	
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		new GameService(new GameBuilder(StaticContext.thatClass()).build()).start();
	}
	// ------------------------------------------------------------
}