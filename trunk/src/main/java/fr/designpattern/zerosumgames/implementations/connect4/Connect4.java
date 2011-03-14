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

package fr.designpattern.zerosumgames.implementations.connect4;

import java.util.ArrayList;
import java.util.List;

import fr.designpattern.zerosumgames.core.GameBoardDimension;
import fr.designpattern.zerosumgames.core.GameBuilder;
import fr.designpattern.zerosumgames.core.GameService;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoard;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardCell;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardMove;
import fr.designpattern.zerosumgames.core.interfaces.IGamePiece;
import fr.designpattern.zerosumgames.core.interfaces.IGamePlayer;
import fr.designpattern.zerosumgames.core.types.GamePlayersEnumeration;
import fr.designpattern.zerosumgames.implementations.tictactoe.Tictactoe;
import fr.designpattern.zerosumgames.util.StaticContext;

public class Connect4 extends Tictactoe {
	// ------------------------------------------------------------
	public final static int CONNECTIONS = 4;
	public final static Class<Connect4PieceTypes> PIECE_TYPES = Connect4PieceTypes.class;
	public final static GameBoardDimension BOARD_DIMENSION = new GameBoardDimension(1, 6, 1, 7); 
	// ------------------------------------------------------------
	@Override
	protected IGameBoard setupBoard(final IGameBoard board) {
		
		final IGamePiece blackPawn = this.piece(GamePlayersEnumeration.FIRST_PLAYER, Connect4PieceTypes.PAWN);
		final IGamePiece whitePawn = this.piece(GamePlayersEnumeration.SECOND_PLAYER, Connect4PieceTypes.PAWN);
		
		//TODO tests unitaires profondeur 1
			/*
			board.getCell(6, 3).setPiece(whitePawn);
			board.getCell(6, 4).setPiece(whitePawn);
			board.getCell(6, 5).setPiece(whitePawn);
			*/
	
			/*
			board.getCell(6, 3).setPiece(blackPawn);
			board.getCell(6, 4).setPiece(blackPawn);
			board.getCell(6, 5).setPiece(blackPawn);
			*/
		
		
		//TODO tests unitaires profondeur 2
			/*
			board.getCell(6, 3).setPiece(whitePawn);
			board.getCell(6, 4).setPiece(whitePawn);
			board.getCell(6, 5).setPiece(whitePawn);
			board.getCell(6, 6).setPiece(blackPawn);
			*/

		//TODO tests unitaires profondeur 4
		
			//public final static GameBoardDimension BOARD_DIMENSION = new GameBoardDimension(1, 2, 1, 8);
		
			/*
			board.getCell(2, 1).setPiece(blackPawn);
			board.getCell(2, 4).setPiece(whitePawn);
			board.getCell(2, 5).setPiece(whitePawn);
			board.getCell(2, 8).setPiece(blackPawn);
			*/			
		
			/*
			board.getCell(2, 1).setPiece(blackPawn);
			board.getCell(2, 4).setPiece(whitePawn);
			board.getCell(2, 5).setPiece(whitePawn);
			board.getCell(2, 6).setPiece(whitePawn);
			board.getCell(2, 8).setPiece(blackPawn);
			*/
			
			/*
			board.getCell(6, 4).setPiece(whitePawn);
			board.getCell(6, 5).setPiece(whitePawn);
			*/

			
		return board;
	}
	// ------------------------------------------------------------	
	public Connect4(final IGameBoard board, final List<IGamePlayer> opponents) {
		super(board, opponents, CONNECTIONS);
	}
	// -----------------------------------------------------------------	
	public Connect4(final IGameBoard board, final List<IGamePlayer> opponents, final int connections) {
		super(board, opponents, connections);
	}	
	// -----------------------------------------------------------------
	@Override
	public final List<IGameBoardMove> getLegalMoves(final IGameBoard board, final GamePlayersEnumeration side) {
		IGameBoardCell nextBottomCell;
		final List<IGameBoardMove> legalMoves = new ArrayList<IGameBoardMove>();
		// TODO a améliorer
		for (IGameBoardCell[] line : this.getBoard()) {
			for(IGameBoardCell cell : line) {
				while(cell.isEmpty()) {
					nextBottomCell = cell.bottom();
					if(nextBottomCell.isNull() || !nextBottomCell.isEmpty()) {
						legalMoves.add(this.makeMove(side, cell.getPosition()));
						break;
					}
					cell = nextBottomCell;
				}
			}
			break;			
		}
		return legalMoves;
	}
	// ------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		new GameService(new GameBuilder(StaticContext.thatClass()).build()).start();
	}
	// ------------------------------------------------------------
}