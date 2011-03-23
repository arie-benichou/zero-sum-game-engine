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
import fr.designpattern.zerosumgames.framework.game.components.board.BoardInterface;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.Dimension;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.moves.MoveInterface;
import fr.designpattern.zerosumgames.framework.opponents.OpponentsEnumeration;
import fr.designpattern.zerosumgames.framework.opponents.OpponentsInterface;
import fr.designpattern.zerosumgames.samples.tictactoe.Tictactoe;
import fr.designpattern.zerosumgames.services.GameService;
import fr.designpattern.zerosumgames.util.StaticContext;

public class Connect4 extends Tictactoe {
	// ------------------------------------------------------------
	public final static int CONNECTIONS = 4;
	public final static Class<Connect4PieceTypes> PIECE_TYPES = Connect4PieceTypes.class;
	public final static Dimension BOARD_DIMENSION = new Dimension(1, 6, 1, 7); 
	// ------------------------------------------------------------
	public Connect4(final BoardInterface board, final OpponentsInterface opponents, final int connections) {
		super(board, opponents, connections);
	}	
	// ------------------------------------------------------------
	public Connect4(final BoardInterface board, final OpponentsInterface opponents) {
		super(board, opponents, CONNECTIONS);
	}
	// -----------------------------------------------------------------
	public final List<MoveInterface> getLegalMoves(final OpponentsEnumeration side) {
		CellInterface nextBottomCell;
		final List<MoveInterface> legalMoves = new ArrayList<MoveInterface>();
		// TODO a améliorer
		for (CellInterface[] line : this.getBoard()) {
			for(CellInterface cell : line) {
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