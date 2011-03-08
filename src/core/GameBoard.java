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

package core;

import java.util.Arrays;
import java.util.Iterator;

import core.interfaces.IGameBoard;
import core.interfaces.IGameBoardCell;
import core.interfaces.IGameBoardCellFactory;
import core.interfaces.IGameBoardDimension;
import core.interfaces.IGameBoardPosition;
import core.interfaces.IGamePiece;

//TODO ? utiliser BoardCell et Equals
public class GameBoard implements IGameBoard {
	// ---------------------------------------------------------------------
	protected IGameBoardCellFactory boardCellFactory;

	protected final IGameBoardCellFactory getBoardCellFactory() {
		return this.boardCellFactory;
	}

	protected final void setBoardCellFactory(
			IGameBoardCellFactory boardCellFactory) {
		this.boardCellFactory = boardCellFactory;
	}

	// ---------------------------------------------------------------------
	protected IGameBoardCell[][] board;

	protected final IGameBoardCell[][] getBoard() {
		return this.board;
	}

	protected final void setBoard(IGameBoardCell[][] board) {
		this.board = board;
	}

	// ---------------------------------------------------------------------
	// TODO ? utiliser boardDimension
	protected IGameBoardCell[][] createBoard() {
		
		IGameBoardDimension bd = this.getBoardCellFactory().getBoardPositionFactory().getBoardDimension();
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
		return new IGameBoardCell[bd.getRowIndexRangeSize()][bd.getColumnIndexRangeSize()];
	}

	// ---------------------------------------------------------------------
	protected IGameBoardCell[][] initializeBoard(IGameBoardCell[][] board) {
		for (IGameBoardPosition position : this.getBoardCellFactory().getGameBoardCells().keySet()) {
			board[position.getInternalRowIndex()][position.getInternalColumnIndex()] = this.getBoardCellFactory().cell(position);
		}
		return board;
	}
	// ---------------------------------------------------------------------
	public GameBoard(IGameBoardCellFactory cellFactory) {
		///System.out.println("\nInitialisation du board...");
		this.setBoardCellFactory(cellFactory);
		this.setBoard(this.initializeBoard(this.createBoard()));
	}

	// ---------------------------------------------------------------------
	@Override	
	public IGameBoardCell getCell(IGameBoardPosition position) {
		
		if(position == null) {
			return this.getBoardCellFactory().getNullCell();
		}
		
		// TODO ? faire pareil que si position == null
		if(position.isNull()) {
			this.getBoardCellFactory().cell(position);
		}

		return this.getBoard()[position.getInternalRowIndex()][position
				.getInternalColumnIndex()];
	}
	@Override
	public IGameBoardCell getCell(int clientRowIndex, int clientColumnIndex) {
		return this.getCell(this.getBoardCellFactory().getBoardPositionFactory().position(clientRowIndex, clientColumnIndex));
	}	
	// ---------------------------------------------------------------------
	@Override
	public Iterator<IGameBoardCell[]> iterator() {
		return Arrays.asList(this.getBoard()).iterator();
	}
	// ---------------------------------------------------------------------
	// TODO utiliser un StringBuilder
	@Override
	public String toString() {
		
		//return Arrays.deepToString(this.getBoard());
		
		IGamePiece piece;
		String badStr = "";
		for (IGameBoardCell[] line : this.getBoard()) {
			badStr += "\n";
			badStr += "-";
			for (IGameBoardCell cell : line) {
				badStr += "----";
				cell.toString(); // pour les warnings :o
			}
			badStr += "\n";
			badStr += "|";
			for (IGameBoardCell cell : line) {
				piece = cell.getPiece();
				badStr += " " + (piece == null ? " " : piece) + " ";
				badStr += "|";
			}
		}
		badStr += "\n";
		badStr += "-";
		for (IGameBoardCell cell : this.getBoard()[0]) {
			badStr += "----";
			cell.toString(); // pour les warnings :o
		}
		return badStr + "\n";
	}
	// ---------------------------------------------------------------------
}