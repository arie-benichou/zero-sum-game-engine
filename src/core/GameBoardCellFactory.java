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

import java.util.HashMap;
import java.util.Map;

import core.interfaces.IGameBoardCell;
import core.interfaces.IGameBoardCellFactory;
import core.interfaces.IGameBoardPosition;
import core.interfaces.IGameBoardPositionFactory;
import core.interfaces.IGamePiece;
import core.types.GameBoardCardinalPosition;

public class GameBoardCellFactory implements IGameBoardCellFactory {
	// ---------------------------------------------------------------------
	protected IGameBoardPositionFactory boardPositionFactory;

	protected final void setBoardPositionFactory(
			IGameBoardPositionFactory boardPositionFactory) {
		this.boardPositionFactory = boardPositionFactory;
	}

	@Override
	public final IGameBoardPositionFactory getBoardPositionFactory() {
		return this.boardPositionFactory;
	}

	// ---------------------------------------------------------------------
	protected HashMap<IGameBoardPosition, IGameBoardCell> gameBoardCells;

	protected final void setGameBoardCells(
			HashMap<IGameBoardPosition, IGameBoardCell> gameBoardCells) {
		this.gameBoardCells = gameBoardCells;
	}

	@Override
	public final HashMap<IGameBoardPosition, IGameBoardCell> getGameBoardCells() {
		return gameBoardCells;
	}

	// ---------------------------------------------------------------------
	private final IGameBoardCell nullCell; 
	@Override
	public IGameBoardCell getNullCell() {
		return this.nullCell;
	}	
	// ---------------------------------------------------------------------

	protected final HashMap<IGameBoardPosition, IGameBoardCell> initializeBoardCells(
			HashMap<IGameBoardPosition, IGameBoardCell> boardCells) {

		///int n = 0;
		IGameBoardCell cell;
		
		///int numberOfPositions = this.getBoardPositionFactory().getNumberOfPositions();
		///int numberOfDigitsInNumberOfPositions = numberOfPositions == 0 ? 1: (int) Math.log10(Math.abs(numberOfPositions)) + 1;
		///System.out.println("\nInitialisation des " + numberOfPositions + " cellules...");
		
		for (IGameBoardPosition[] line : this.getBoardPositionFactory().getBoardPositions()) {
			for (IGameBoardPosition position : line) {
				
				
				
					///System.out.format("%0" + numberOfDigitsInNumberOfPositions+ "d : ", ++n);

					// TODO ? utiliser une méthode de création
					cell = new GameBoardCell(position);

					///System.out.print(cell + "\n");
					boardCells.put(position, cell);
				
				
			}
		}
		
		return boardCells;
	}

	// ---------------------------------------------------------------------
	public GameBoardCellFactory(IGameBoardPositionFactory boardPositionFactory) {
		this.setBoardPositionFactory(boardPositionFactory);
		this.setGameBoardCells(this.initializeBoardCells(new HashMap<IGameBoardPosition, IGameBoardCell>(boardPositionFactory.getNumberOfPositions())));
		
		this.nullCell = new GameBoardCell(this.getBoardPositionFactory().getNullPosition()); 
	}

	// ---------------------------------------------------------------------
	@Override
	public IGameBoardCell cell(IGameBoardPosition position) {
		return  position.isNull() ? this.nullCell : this.gameBoardCells.get(position);
	}

	// =====================================================================
	protected class GameBoardCell implements IGameBoardCell {
		// ---------------------------------------------------------------------		
		private IGameBoardPosition position;
		public final IGameBoardPosition getPosition() {
			return this.position;
		}
		private final void setPosition(IGameBoardPosition position) {
			this.position = position;
		}
		// ---------------------------------------------------------------------
		@Override
		public boolean isNull() {
			return this.getPosition().isNull();
		}
		// ---------------------------------------------------------------------				
		private IGamePiece piece = null;
		public final void setPiece(IGamePiece piece) {
			
			// TODO à améliorer
			if(this.isNull()) {
				throw new RuntimeException();
			}			
			
			this.piece = piece;
		}
		public final IGamePiece getPiece() {
			
			// TODO à améliorer
			if(this.isNull()) {
				throw new RuntimeException();
			}
			
			return this.piece;
		}
		// ---------------------------------------------------------------------
		public boolean isEmpty() {
			
			// TODO à améliorer
			if(this.isNull()) {
				throw new RuntimeException();
			}
			
			return this.getPiece() == null;
		}
		// ---------------------------------------------------------------------
		private HashMap<GameBoardCardinalPosition, IGameBoardCell> neighbourhood = null;
		@Override
		public Map<GameBoardCardinalPosition, IGameBoardCell> getNeighbourhood() {
			if (this.neighbourhood == null) {
				this.neighbourhood = new HashMap<GameBoardCardinalPosition, IGameBoardCell>(8);
				this.neighbourhood.put(GameBoardCardinalPosition.TOP, cell(getBoardPositionFactory().topOf(this.getPosition())));
				this.neighbourhood.put(GameBoardCardinalPosition.RIGHT, cell(getBoardPositionFactory().rightOf(this.getPosition())));
				this.neighbourhood.put(GameBoardCardinalPosition.BOTTOM, cell(getBoardPositionFactory().bottomOf(this.getPosition())));
				this.neighbourhood.put(GameBoardCardinalPosition.LEFT, cell(getBoardPositionFactory().leftOf(this.getPosition())));
				this.neighbourhood.put(GameBoardCardinalPosition.TOP_RIGHT, cell(getBoardPositionFactory().topRightOf(this.getPosition())));
				this.neighbourhood.put(GameBoardCardinalPosition.TOP_LEFT, cell(getBoardPositionFactory().topLeftOf(this.getPosition())));
				this.neighbourhood.put(GameBoardCardinalPosition.BOTTOM_RIGHT, cell(getBoardPositionFactory().bottomRightOf(this.getPosition())));
				this.neighbourhood.put(GameBoardCardinalPosition.BOTTOM_LEFT, cell(getBoardPositionFactory().bottomLeftOf(this.getPosition())));
			}
			return this.neighbourhood;
		}
		// ---------------------------------------------------------------------
		public GameBoardCell(IGameBoardPosition position) {
			this.setPosition(position);
		}
		// ---------------------------------------------------------------------
		@Override
		public String toString() {
			return "[position=" + position + ", piece=" + piece + "]";
		}
		// ---------------------------------------------------------------------
		@Override
		public IGameBoardCell getNeighbour(GameBoardCardinalPosition key) {
			return this.getNeighbourhood().get(key);
		}
		// ---------------------------------------------------------------------
		@Override
		public IGameBoardCell top() {
			return this.getNeighbour(GameBoardCardinalPosition.TOP);
		}
		@Override
		public IGameBoardCell right() {
			return this.getNeighbour(GameBoardCardinalPosition.RIGHT);
		}
		@Override
		public IGameBoardCell bottom() {
			return this.getNeighbour(GameBoardCardinalPosition.BOTTOM);
		}
		@Override
		public IGameBoardCell left() {
			return this.getNeighbour(GameBoardCardinalPosition.LEFT);
		}
		@Override
		public IGameBoardCell topRight() {
			return this.getNeighbour(GameBoardCardinalPosition.TOP_RIGHT);
		}
		@Override
		public IGameBoardCell topLeft() {
			return this.getNeighbour(GameBoardCardinalPosition.TOP_LEFT);
		}
		@Override
		public IGameBoardCell bottomRight() {
			return this.getNeighbour(GameBoardCardinalPosition.BOTTOM_RIGHT);
		}
		@Override
		public IGameBoardCell bottomLeft() {
			return this.getNeighbour(GameBoardCardinalPosition.BOTTOM_LEFT);
		}
		// ---------------------------------------------------------------------		
	}
	// =====================================================================

}