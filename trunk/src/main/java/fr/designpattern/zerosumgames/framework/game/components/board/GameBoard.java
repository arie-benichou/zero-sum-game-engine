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

package fr.designpattern.zerosumgames.framework.game.components.board;

import java.util.Arrays;
import java.util.Iterator;

import fr.designpattern.zerosumgames.framework.game.components.board.cell.IGameBoardCell;
import fr.designpattern.zerosumgames.framework.game.components.board.cell.IGameBoardCellFactory;
import fr.designpattern.zerosumgames.framework.game.components.board.cell.piece.IGamePiece;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.IGameBoardDimension;
import fr.designpattern.zerosumgames.framework.game.components.board.position.IGameBoardPosition;

//TODO ? utiliser BoardCell et Equals
public class GameBoard implements IGameBoard {
	// ---------------------------------------------------------------------
	private IGameBoardCellFactory boardCellFactory;
	private final IGameBoardCellFactory getBoardCellFactory() {
		return this.boardCellFactory;
	}
	private final void setBoardCellFactory(final IGameBoardCellFactory boardCellFactory) {
		this.boardCellFactory = boardCellFactory;
	}
	// ---------------------------------------------------------------------
	private IGameBoardCell[][] board;
	private final IGameBoardCell[][] getBoard() {
		return this.board;
	}
	private final void setBoard(final IGameBoardCell[][] board) {
		this.board = board;
	}
	// ---------------------------------------------------------------------
	// TODO ? utiliser boardDimension
	private IGameBoardCell[][] createBoard() {
		
		final IGameBoardDimension dimension = this.getBoardCellFactory().getBoardPositionFactory().getBoardDimension();
		/*
		int[] byRow = new int[bd.getRowIndexRangeSize()];
		
		for (IGameBoardPosition[] line : this.getBoardCellFactory().getBoardPositionFactory().getBoardPositions())
			for (IGameBoardPosition position : line)
				if (!position.isNull()) 
					++byRow[position.getInternalRowIndex()];
		
		int xMax = 0;
		for(int x : byRow)
			if(x > xMax) xMax = x;

		return new IGameBoardCell[byRow.length][xMax];
		*/
		
		//TODO à optimiser plus tard
		return new IGameBoardCell[dimension.getRowIndexRangeSize()][dimension.getColumnIndexRangeSize()];
	}

	// ---------------------------------------------------------------------
	private IGameBoardCell[][] initializeBoard(IGameBoardCell[][] board) {
		for (IGameBoardPosition position : this.getBoardCellFactory().getGameBoardCells().keySet()) {
			board[position.getInternalRowIndex()][position.getInternalColumnIndex()] = this.getBoardCellFactory().cell(position);
		}
		return board;
	}
	// ---------------------------------------------------------------------
	public GameBoard(final IGameBoardCellFactory cellFactory) {
		///System.out.println("\nInitialisation du board...");
		this.setBoardCellFactory(cellFactory);
		this.setBoard(this.initializeBoard(this.createBoard()));
	}

	// ---------------------------------------------------------------------
	public IGameBoardCell getCell(final IGameBoardPosition position) {
		IGameBoardCell cell;
		if(position == null) {
			cell = this.getBoardCellFactory().getNullCell();
		}
		// TODO ? faire pareil que si position == null
		else if(position.isNull()) {
			cell = this.getBoardCellFactory().cell(position);
		}
		else {
			cell = this.getBoard()[position.getInternalRowIndex()][position.getInternalColumnIndex()];	
		}
		return cell;
	}
	// ---------------------------------------------------------------------	
	public IGameBoardCell getCell(final int clientRowIndex, final int clientColumnIndex) {
		return this.getCell(this.getBoardCellFactory().getBoardPositionFactory().position(clientRowIndex, clientColumnIndex));
	}	
	// ---------------------------------------------------------------------
	public Iterator<IGameBoardCell[]> iterator() {
		return Arrays.asList(this.getBoard()).iterator();
	}
	// ---------------------------------------------------------------------
	@Override
	@SuppressWarnings("unused")
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		IGamePiece piece;
		for (IGameBoardCell[] line : this.getBoard()) {
			sb.append("\n");
			sb.append("-");
			for (IGameBoardCell cell : line) {
				sb.append("----");
			}
			sb.append("\n");
			sb.append("|");
			for (IGameBoardCell cell : line) {
				piece = cell.getPiece();
				sb.append(" " + (piece == null ? " " : piece) + " ");
				sb.append("|");
			}
		}
		sb.append("\n");
		sb.append("-");
		for (IGameBoardCell cell : this.getBoard()[0]) {
			sb.append("----");
		}
		sb.append("\n");
		return sb.toString();
	}
	// ---------------------------------------------------------------------
}