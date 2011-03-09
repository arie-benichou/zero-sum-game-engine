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

package main.java.games.implementations.connect4;

import java.util.ArrayList;
import java.util.List;

import main.java.games.core.GameBoardDimension;
import main.java.games.core.GameBoardMove;
import main.java.games.core.GameBuilder;
import main.java.games.core.interfaces.IGameBoard;
import main.java.games.core.interfaces.IGameBoardCell;
import main.java.games.core.interfaces.IGameBoardMove;
import main.java.games.core.interfaces.IGamePlayer;
import main.java.games.core.types.GamePlayersEnumeration;
import main.java.games.implementations.tictactoe.Tictactoe;
import main.java.util.StaticContext;

public class Connect4 extends Tictactoe {
	// ------------------------------------------------------------
	public final static Class<Connect4PieceTypes> PIECE_TYPES = Connect4PieceTypes.class; // TODO ! à revoir
	public final static GameBoardDimension BOARD_DIMENSION = new GameBoardDimension(1, 6, 1, 7); 
	// ------------------------------------------------------------
	public Connect4(final IGameBoard board, final List<IGamePlayer> opponents) {
		super(board, opponents, 4);
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
						// TODO méthode de création
						legalMoves.add(new GameBoardMove(side, cell.getPosition()));
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
		new GameBuilder(StaticContext.thatClass()).build().start();
	}
	// ------------------------------------------------------------
}