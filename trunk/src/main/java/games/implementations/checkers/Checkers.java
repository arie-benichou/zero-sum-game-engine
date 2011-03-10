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

package main.java.games.implementations.checkers;

import java.util.ArrayList;
import java.util.List;

import main.java.games.core.AbstractGame;
import main.java.games.core.GameBoardDimension;
import main.java.games.core.GameBuilder;
import main.java.games.core.GamePieceFactory;
import main.java.games.core.interfaces.IGameBoard;
import main.java.games.core.interfaces.IGameBoardCell;
import main.java.games.core.interfaces.IGameBoardMove;
import main.java.games.core.interfaces.IGamePiece;
import main.java.games.core.interfaces.IGamePlayer;
import main.java.games.core.types.GameBoardCardinalPosition;
import main.java.games.core.types.GamePlayersEnumeration;
import main.java.games.implementations.checkers.pieces.AbstractCheckersPiece;
import main.java.util.StaticContext;


public class Checkers extends AbstractGame {
	// ------------------------------------------------------------	
	public final static Class<CheckersPieceTypes> PIECE_TYPES = CheckersPieceTypes.class; // TODO ! à revoir
	public final static GameBoardDimension BOARD_DIMENSION = new GameBoardDimension(1, 8, 1, 8); 
	// ------------------------------------------------------------
	public Checkers(final IGameBoard board, final List<IGamePlayer> opponents) {
		// TODO !! à revoir
		super(new GamePieceFactory(PIECE_TYPES), board, opponents);		
	}
	// ------------------------------------------------------------
	public List<IGameBoardCell> getRelevantCells(final GamePlayersEnumeration side) { // TODO passer le board en paramètre
		final List<IGameBoardCell> pieces = new ArrayList<IGameBoardCell>();
		for (IGameBoardCell[] line : this.getBoard()) { // TODO utiliser le board passé en paramètre
			for(IGameBoardCell cell : line) {
				// TODO ? utiliser la pièce nulle
				if(cell.isEmpty()) {
					continue;
				}
				if(cell.getPiece().getSide() == side) {
					pieces.add(cell);
				}
			}
		}
		return pieces;
	}
	// ------------------------------------------------------------
	@Override
	public final List<IGameBoardMove> getLegalMoves(final IGameBoard board, final GamePlayersEnumeration side) {
		final List<IGameBoardMove> jumpingMoves = new ArrayList<IGameBoardMove>();
		final List<IGameBoardMove> walkingMoves = new ArrayList<IGameBoardMove>();
		AbstractCheckersPiece piece;
		List<GameBoardCardinalPosition> pieceOptions;
		boolean hasToJump = false;
		for(IGameBoardCell cell : this.getRelevantCells(side)) { // TODO passer le board en paramètre
			piece = (AbstractCheckersPiece)cell.getPiece();
			pieceOptions = piece.getJumpOptions(cell);
			if(!pieceOptions.isEmpty()) {
				hasToJump = true;
				for(GameBoardCardinalPosition direction : pieceOptions) {
					// TODO méthode de création
					jumpingMoves.add(new CheckersMove(side, cell.getPosition(), direction));
				}
			}
			else if(!hasToJump) {
				pieceOptions = piece.getWalkOptions(cell);
				for(GameBoardCardinalPosition direction : pieceOptions) {
					walkingMoves.add(new CheckersMove(side, cell.getPosition(), direction));
				}
			}
			
		}
		return jumpingMoves.isEmpty() ? walkingMoves : jumpingMoves; 
	}
	// ------------------------------------------------------------
	@Override
	protected void setupInitialGameState() {
		// TODO permettre d'autres dimensions
		// TODO passer le board en paramètre
		int n;		
		int clientColumnIndex;
		int clientRowIndex;
		for(clientRowIndex = 1; clientRowIndex<=3; ++clientRowIndex) {
			for(n = 1; n<=4; ++n) {
				clientColumnIndex = 2*n + clientRowIndex% 2 - 1;
				// TODO ? utiliser le board passé en paramètre
				this.getCell(clientRowIndex, clientColumnIndex).setPiece(this.piece(GamePlayersEnumeration.SECOND_PLAYER, CheckersPieceTypes.MAN)); // TODO ? façade man
			}
		}
		for(clientRowIndex = 6; clientRowIndex<=8; ++clientRowIndex) {
			for(n = 1; n<=4; ++n) {
				clientColumnIndex = 2*n + clientRowIndex% 2 - 1;
				// TODO ? utiliser le board passé en paramètre
				this.getCell(clientRowIndex, clientColumnIndex).setPiece(this.piece(GamePlayersEnumeration.FIRST_PLAYER, CheckersPieceTypes.MAN)); // TODO ? façade man
			}
		}
	}
	// -----------------------------------------------------------------
	// TODO ?! ajouter à l'interface
	private boolean playMove(final IGameBoard gameState, final CheckersMove move) {
		// récupération de la cellule corespondant à la position
		final IGameBoardCell cell = gameState.getCell(move.getPosition());
		// récupération de la pièce à déplacer
		final IGamePiece pieceToMove = cell.getPiece();
		// suppression de la pièce à sa position actuelle
		cell.setPiece(null);
		// récupération de la cellulle correspondant à la direction choisie
		IGameBoardCell destinationCell = cell.getNeighbour(move.getDirection());
		boolean hasBeenEating = false;
		// si la cellule n'est pas vide
		if(!destinationCell.isEmpty()) {
			hasBeenEating = true;
			// la pièce de cette cellule est supprimée
			destinationCell.setPiece(null);
			// et la cellule de destination devient la suivante
			destinationCell = destinationCell.getNeighbour(move.getDirection());
		}
		// la pièce concernée par le coup est "déplacée" à sa cellule de destination
		destinationCell.setPiece(pieceToMove);
		boolean hasBeenCrowned = false;
		// vérification de l'éventuelle promotion d'un pion
		if(
			pieceToMove.getType() == CheckersPieceTypes.MAN && (
				destinationCell.getNeighbour(GameBoardCardinalPosition.TOP).isNull() ||
				destinationCell.getNeighbour(GameBoardCardinalPosition.BOTTOM).isNull()
			)
		){
			hasBeenCrowned = true;
			// il est promu roi
			destinationCell.setPiece(this.piece(move.getSide(), CheckersPieceTypes.KING)); // TODO ? façade king
			//System.out.println("\nLongue vie au roi!");				
		}
		return hasBeenEating && !hasBeenCrowned; 
	}
	// -----------------------------------------------------------------
	// TODO ?! ajouter à une interface ICheckers, entre autres...
	private boolean hasToKeepPlaying(final IGameBoard gameState, final CheckersMove move) {
		IGameBoardCell actualCell = gameState.getCell(move.getPosition());
		actualCell = actualCell.getNeighbour(move.getDirection());
		actualCell = actualCell.getNeighbour(move.getDirection());
		final AbstractCheckersPiece piece =  (AbstractCheckersPiece) actualCell.getPiece();
		//Et que la pièce peut encore effectuer une capture
		return !piece.getJumpOptions(actualCell).isEmpty(); // TODO faire façade canJump pour une pièce
	}
	// -----------------------------------------------------------------	
	@Override
	public GamePlayersEnumeration applyGameStateTransition(final IGameBoard gameState, final IGameBoardMove moveToPlay) {
		GamePlayersEnumeration nextSideToPlay = null;
		final CheckersMove checkersMove = (CheckersMove)moveToPlay;
		boolean isJumpingMove = false;
		if (!checkersMove.isNull()) {
			isJumpingMove = this.playMove(gameState, checkersMove);
		}
		if(!this.isGameOver(gameState, checkersMove)) {
			// TODO appeler who shall play			
			nextSideToPlay = (isJumpingMove && this.hasToKeepPlaying(gameState, checkersMove)) ? checkersMove.getSide() : super.applyGameStateTransition(gameState, checkersMove);
		}			
		return nextSideToPlay;
	}
	// ------------------------------------------------------------
	@Override
	public boolean isGameOver(final IGameBoard gameState, final IGameBoardMove justPlayedMove) { // TODO faire optimisation pour les autres jeux utilisant l'implémentation par défaut (la modifier)
		boolean isGameOver = false;
		// Suite à ce coup, si l'adversaire... 
		final GamePlayersEnumeration oppositeSide = this.getOpponent(justPlayedMove.getSide()).getOrder(); // TODO améliorer l'API à ce niveau
		// n'a plus aucune pièce
		if(this.getRelevantCells(oppositeSide).isEmpty()) { // TODO passer le board en paramètre
			isGameOver = true;
		}
		// a encore des pièces mais qu'il ne peut plus jouer
		else if(this.getLegalMoves(this.getBoard(), oppositeSide).isEmpty()) {// TODO ? mettre en cache ou définir une deuxième méthode prenant en paramètre les cellules relevantes
			isGameOver = true;
		}
		return isGameOver;
	}
	// ------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		new GameBuilder(StaticContext.thatClass()).build().start();
	}
	// ------------------------------------------------------------
}