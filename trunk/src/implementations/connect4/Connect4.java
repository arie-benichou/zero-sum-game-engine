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

package implementations.connect4;

import java.util.ArrayList;
import java.util.List;

import core.GameBoardDimension;
import core.GameBoardMove;
import core.GameBuilder;
import core.interfaces.IGameBoard;
import core.interfaces.IGameBoardCell;
import core.interfaces.IGameBoardMove;
import core.interfaces.IGamePlayer;
import core.types.GamePlayersEnumeration;
import implementations.tictactoe.Tictactoe;
import util.StaticContext;

public class Connect4 extends Tictactoe {
	// ------------------------------------------------------------
	public final static Class<Connect4PieceTypes> PIECE_TYPES = Connect4PieceTypes.class; // TODO ! à revoir
	public final static GameBoardDimension BOARD_DIMENSION = new GameBoardDimension(1, 6, 1, 7); 
	// ------------------------------------------------------------
	public Connect4(IGameBoard board, List<IGamePlayer> opponents) {
		super(board, opponents, 4);
	}
	// -----------------------------------------------------------------	
	public Connect4(IGameBoard board, List<IGamePlayer> opponents, int numberOfPawnsToConnect) {
		super(board, opponents, numberOfPawnsToConnect);
	}	
	// -----------------------------------------------------------------
	@Override
	public final List<IGameBoardMove> getLegalMoves(IGameBoard board, GamePlayersEnumeration side) {
		IGameBoardCell nextBottomCell;
		List<IGameBoardMove> legalGameTransitions = new ArrayList<IGameBoardMove>();
		// TODO a améliorer
		for (IGameBoardCell[] line : this.getBoard()) {
			for(IGameBoardCell cell : line) {
				while(cell.isEmpty()) {
					nextBottomCell = cell.bottom();
					if(nextBottomCell.isNull() || !nextBottomCell.isEmpty()) {
						legalGameTransitions.add(new GameBoardMove(side, cell.getPosition()));
						break;
					}
					cell = nextBottomCell;
				}
			}
			break;			
		}
		return legalGameTransitions;
	}
	// ------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		new GameBuilder(StaticContext.thatClass()).build().start();
	}
	// ------------------------------------------------------------
}