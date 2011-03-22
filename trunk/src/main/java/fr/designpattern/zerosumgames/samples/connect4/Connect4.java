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

package fr.designpattern.zerosumgames.samples.connect4;

import java.util.ArrayList;
import java.util.List;

import fr.designpattern.zerosumgames.framework.game.builder.Builder;
import fr.designpattern.zerosumgames.framework.game.components.board.IGameBoard;
import fr.designpattern.zerosumgames.framework.game.components.board.cell.IGameBoardCell;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.GameBoardDimension;
import fr.designpattern.zerosumgames.framework.game.components.move.IGameMove;
import fr.designpattern.zerosumgames.framework.game.components.opponents.GamePlayersEnumeration;
import fr.designpattern.zerosumgames.framework.game.components.opponents.IGameOpponents;
import fr.designpattern.zerosumgames.framework.services.GameService;
import fr.designpattern.zerosumgames.samples.tictactoe.Tictactoe;
import fr.designpattern.zerosumgames.util.StaticContext;

public class Connect4 extends Tictactoe {
	// ------------------------------------------------------------
	public final static int CONNECTIONS = 4;
	public final static Class<Connect4PieceTypes> PIECE_TYPES = Connect4PieceTypes.class;
	public final static GameBoardDimension BOARD_DIMENSION = new GameBoardDimension(1, 6, 1, 7); 
	// ------------------------------------------------------------
	public Connect4(final IGameBoard board, final IGameOpponents opponents, final int connections) {
		super(board, opponents, connections);
	}	
	// ------------------------------------------------------------
	public Connect4(final IGameBoard board, final IGameOpponents opponents) {
		super(board, opponents, CONNECTIONS);
	}
	// -----------------------------------------------------------------
	public final List<IGameMove> getLegalMoves(final GamePlayersEnumeration side) {
		IGameBoardCell nextBottomCell;
		final List<IGameMove> legalMoves = new ArrayList<IGameMove>();
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
		new GameService(new Builder(StaticContext.thatClass()).build()).start();
	}
	// ------------------------------------------------------------
}