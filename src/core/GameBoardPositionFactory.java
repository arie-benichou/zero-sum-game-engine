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

import core.interfaces.IGameBoardDimension;
import core.interfaces.IGameBoardPosition;
import core.interfaces.IGameBoardPositionFactory;
import core.types.GameBoardCardinalPosition;

public class GameBoardPositionFactory implements IGameBoardPositionFactory {
	// ---------------------------------------------------------------------
	protected IGameBoardDimension boardDimension;

	protected final void setBoardDimension(IGameBoardDimension boardDimension) {
		this.boardDimension = boardDimension;
	}

	@Override
	public final IGameBoardDimension getBoardDimension() {
		return this.boardDimension;
	}

	// ---------------------------------------------------------------------
	protected IGameBoardPosition[][] gameBoardPositions;

	protected final void setBoardPositionsCache(
			IGameBoardPosition[][] boardPositionsCache) {
		this.gameBoardPositions = boardPositionsCache;
	}

	@Override
	public final IGameBoardPosition[][] getBoardPositions() {
		return gameBoardPositions;
	}

	// ---------------------------------------------------------------------
	protected int numberOfPositions;

	protected final void incrementNumberOfPositions() {
		++this.numberOfPositions;
	}

	@Override
	public final int getNumberOfPositions() {
		return this.numberOfPositions;
	}
	// ---------------------------------------------------------------------
	private final IGameBoardPosition nullPosition = new GameBoardNullPosition(0,0,-1,-1);
	@Override
	public final IGameBoardPosition getNullPosition() {
		return this.nullPosition;
	}
	// ---------------------------------------------------------------------
	protected int computeInternalRowIndex(int clientRowIndex) {
		return clientRowIndex - this.getBoardDimension().getMinRowIndex();
	}
	protected int computeInternalColumnIndex(int clientColumnIndex) {
		return clientColumnIndex - this.getBoardDimension().getMinColumnIndex();
	}
	// ---------------------------------------------------------------------	
	protected final IGameBoardPosition createPosition(int clientRowIndex, int clientColumnIndex) {
		this.incrementNumberOfPositions();
		return new GameBoardPosition(clientRowIndex, clientColumnIndex, this.computeInternalRowIndex(clientRowIndex), this.computeInternalRowIndex(clientColumnIndex));
	}
	
	protected final IGameBoardPosition createNullPosition(int clientRowIndex, int clientColumnIndex) {
		return new GameBoardNullPosition(clientRowIndex, clientColumnIndex, this.computeInternalRowIndex(clientRowIndex), this.computeInternalRowIndex(clientColumnIndex));
	}
	// ---------------------------------------------------------------------
	protected IGameBoardPosition[][] createBoardPositionsCache() {
		return new IGameBoardPosition[this.getBoardDimension().getRowIndexRangeSize()][this.getBoardDimension().getColumnIndexRangeSize()];
	}
	// ---------------------------------------------------------------------
	protected IGameBoardPosition[][] initializeBoardPositionsCache(
			IGameBoardPosition[][] boardPositionsCache) {

		///System.out.println("\nInitialisation des " + this.getBoardDimension().getCapacity() + " positions...");

		int minColumnIndex = this.getBoardDimension().getMinColumnIndex();
		int maxColumnIndex = this.getBoardDimension().getMaxColumnIndex();
		int minRowIndex = this.getBoardDimension().getMinRowIndex();
		int maxRowIndex = this.getBoardDimension().getMaxRowIndex();

		///int numberOfDigitsInNumberOfPositions = this.getBoardDimension().getCapacity() == 0 ? 1 : (int) Math.log10(Math.abs(this.getBoardDimension().getCapacity())) + 1;

		///int n = 0;
		IGameBoardPosition position;
		for (int clientRowIndex = minRowIndex; clientRowIndex <= maxRowIndex; ++clientRowIndex) {
			for (int clientColumnIndex = minColumnIndex; clientColumnIndex <= maxColumnIndex; ++clientColumnIndex) {
				///System.out.format("%0" + numberOfDigitsInNumberOfPositions + "d : ", ++n);
				position = this.createPosition(clientRowIndex, clientColumnIndex);
				///System.out.print(position);
				boardPositionsCache[position.getInternalRowIndex()][position.getInternalColumnIndex()] = position;
				///System.out.print("\n");
			}
		}

		return boardPositionsCache;
	}
	// ---------------------------------------------------------------------
	// TODO créer les éventuelles positions nulles après l'initialisation
	private IGameBoardPosition[][] fillWithNullPositions(IGameBoardPosition[][] boardPositionsCache) {
		
		/*
		int yMax = this.getBoardDimension().getRowIndexRangeSize();
		int xMax = this.getBoardDimension().getColumnIndexRangeSize();
		for (int y = 0; y < yMax; ++y)
			for (int x = 0; x < xMax; ++x)
				boardPositionsCache[y][x] = this.getNullPosition();
		return boardPositionsCache;
		*/
		
		int minColumnIndex = this.getBoardDimension().getMinColumnIndex();
		int maxColumnIndex = this.getBoardDimension().getMaxColumnIndex();
		int minRowIndex = this.getBoardDimension().getMinRowIndex();
		int maxRowIndex = this.getBoardDimension().getMaxRowIndex();

		IGameBoardPosition nullPosition;
		for (int clientRowIndex = minRowIndex; clientRowIndex <= maxRowIndex; ++clientRowIndex) {
			for (int clientColumnIndex = minColumnIndex; clientColumnIndex <= maxColumnIndex; ++clientColumnIndex) {
				nullPosition = this.createNullPosition(clientRowIndex, clientColumnIndex);
				boardPositionsCache[nullPosition.getInternalRowIndex()][nullPosition.getInternalColumnIndex()] = nullPosition;
				//System.out.print("\n");
			}
		}

		return boardPositionsCache;		
		
	}	
	// ---------------------------------------------------------------------
	public GameBoardPositionFactory(IGameBoardDimension boardDimension) {
		this.setBoardDimension(boardDimension);
		this.setBoardPositionsCache(
			this.initializeBoardPositionsCache(
				this.fillWithNullPositions(
					this.createBoardPositionsCache()
				)
			)
		);
	}
	// ---------------------------------------------------------------------
	public IGameBoardPosition position(int clientRowIndex, int clientColumnIndex) {
		
		// si la position est hors-dimension, nulle position n'est retournée.
		if (!this.getBoardDimension().contains(clientRowIndex, clientColumnIndex)) {
			return this.nullPosition;
		}
		
		IGameBoardPosition position = this.getBoardPositions()[this.computeInternalRowIndex(clientRowIndex)][this.computeInternalColumnIndex(clientColumnIndex)];
		
		// si la position n'est pas hors-dimension,
		// mais qu'elle n'a pas été définie, c'est l'exception
		// GameBoardIllegalPositionException qui est levée
		if(position == null) {
			throw new GameBoardIllegalPositionException(clientRowIndex, clientColumnIndex);
		}
		
		return position;
		
	}
	// ---------------------------------------------------------------------
	// TODO faire le mapping au niveau des cellules
	private IGameBoardPosition getAdjacentPositionTo(IGameBoardPosition position, GameBoardCardinalPosition direction) {
		return this.position(position.getClientRowIndex() + direction.getDeltaRowIndex(), position.getClientColumnIndex() + direction.getDeltaColumnIndex());
	}
	// ---------------------------------------------------------------------	
	
	public IGameBoardPosition leftOf(IGameBoardPosition position) {
		return this.getAdjacentPositionTo(position, GameBoardCardinalPosition.LEFT);
	}
	// ---------------------------------------------------------------------
	public IGameBoardPosition rightOf(IGameBoardPosition position) {
		return this.getAdjacentPositionTo(position, GameBoardCardinalPosition.RIGHT);
	}
	// ---------------------------------------------------------------------
	public IGameBoardPosition topOf(IGameBoardPosition position) {
		return this.getAdjacentPositionTo(position, GameBoardCardinalPosition.TOP);
	}
	// ---------------------------------------------------------------------
	public IGameBoardPosition bottomOf(IGameBoardPosition position) {
		return this.getAdjacentPositionTo(position, GameBoardCardinalPosition.BOTTOM);		
	}

	// ---------------------------------------------------------------------
	public IGameBoardPosition topLeftOf(IGameBoardPosition position) {
		return this.getAdjacentPositionTo(position, GameBoardCardinalPosition.TOP_LEFT);
	}

	// ---------------------------------------------------------------------
	public IGameBoardPosition topRightOf(IGameBoardPosition position) {
		return this.getAdjacentPositionTo(position, GameBoardCardinalPosition.TOP_RIGHT);
	}

	// ---------------------------------------------------------------------
	public IGameBoardPosition bottomLeftOf(IGameBoardPosition position) {
		return this.getAdjacentPositionTo(position, GameBoardCardinalPosition.BOTTOM_LEFT);
	}

	// ---------------------------------------------------------------------
	public IGameBoardPosition bottomRightOf(IGameBoardPosition position) {
		return this.getAdjacentPositionTo(position, GameBoardCardinalPosition.BOTTOM_RIGHT);
	}
	// =====================================================================
	private class GameBoardIllegalPositionException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		private int clientRowIndex;
		private int clientColumnIndex;
		
		public GameBoardIllegalPositionException(int clientRowIndex, int clientColumnIndex) {
			this.clientRowIndex = clientRowIndex;
			this.clientColumnIndex = clientColumnIndex;
		}		

		@Override
		public String getMessage() {
			return "La position (clientRowIndex=" + this.clientRowIndex
					+ ", clientColumnIndex=" + this.clientColumnIndex
					+ ") est illégale.";
		}

	}
	// =====================================================================
	private class GameBoardNullPosition implements IGameBoardPosition {
		// ---------------------------------------------------------------------
		protected int clientRowIndex;
		public final int getClientRowIndex() {
			return this.clientRowIndex;
		}
		private final void setClientRowIndex(int clientRowIndex) {
			this.clientRowIndex = clientRowIndex;
		}
		// ---------------------------------------------------------------------
		protected int clientColumnIndex;
		public final int getClientColumnIndex() {
			return this.clientColumnIndex;
		}				
		private final void setClientColumnIndex(int clientColumnIndex) {
			this.clientColumnIndex = clientColumnIndex;
		}
		// ---------------------------------------------------------------------
		protected int internalRowIndex;
		public final int getInternalRowIndex() {
			return this.internalRowIndex;
		}				
		private final void setInternalRowIndex(int internalRowIndex) {
			this.internalRowIndex = internalRowIndex;
		}
		// ---------------------------------------------------------------------		
		protected int internalColumnIndex;
		public final int getInternalColumnIndex() {
			return this.internalColumnIndex;
		}				
		private final void setInternalColumnIndex(int internalColumnIndex) {
			this.internalColumnIndex = internalColumnIndex;
		}				
		// ---------------------------------------------------------------------
		public GameBoardNullPosition(int clientRowIndex, int clientColumnIndex, int internalRowIndex, int internalColumnIndex) {
			this.setClientRowIndex(clientRowIndex);
			this.setClientColumnIndex(clientColumnIndex);
			this.setInternalRowIndex(internalRowIndex);
			this.setInternalColumnIndex(internalColumnIndex);
		}
		// ---------------------------------------------------------------------		
		@Override
		public boolean isNull() {
			return true;
		}
		// ---------------------------------------------------------------------
		@Override
		public String toString() {
			return  "null position";			
		}
		// ---------------------------------------------------------------------
	}
	// =====================================================================
	private class GameBoardPosition extends GameBoardNullPosition {
		// ---------------------------------------------------------------------
		public GameBoardPosition(int clientRowIndex, int clientColumnIndex, int internalRowIndex, int internalColumnIndex) {
			super(clientRowIndex, clientColumnIndex, internalRowIndex, internalColumnIndex);
		}
		// ---------------------------------------------------------------------
		@Override
		public boolean isNull() {
			return false;
		}
		// ---------------------------------------------------------------------
		@Override
		public String toString() {
			return "[row " + this.getClientRowIndex() + "][column " + this.getClientColumnIndex() + "]";			
		}
		// ---------------------------------------------------------------------		
	}
	// =====================================================================
}