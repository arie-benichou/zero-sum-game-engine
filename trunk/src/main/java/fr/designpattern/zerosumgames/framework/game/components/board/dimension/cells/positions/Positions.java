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

package fr.designpattern.zerosumgames.framework.game.components.board.dimension.cells.positions;

import fr.designpattern.zerosumgames.framework.game.components.board.dimension.BoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.DimensionInterface;

public class Positions implements PositionsInterface {
	// ---------------------------------------------------------------------
	private DimensionInterface boardDimension;

	private final void setBoardDimension(final DimensionInterface boardDimension) {
		this.boardDimension = boardDimension;
	}

	public final DimensionInterface getBoardDimension() {
		return this.boardDimension;
	}

	// ---------------------------------------------------------------------
	private transient PositionInterface[][] positions;

	private final void setBoardPositionsCache(final PositionInterface[][] positions) {
		this.positions = positions;
	}

	public final PositionInterface[][] getBoardPositions() {
		return positions;
	}

	// ---------------------------------------------------------------------
	private transient int numberOfPositions;

	private final void incrementNumberOfPositions() {
		++this.numberOfPositions;
	}

	public final int getNumberOfPositions() {
		return this.numberOfPositions;
	}
	// ---------------------------------------------------------------------
	private final transient PositionInterface nullPosition = new GameBoardNullPosition(0,0,-1,-1);
	public final PositionInterface getNullPosition() {
		return this.nullPosition;
	}
	// ---------------------------------------------------------------------
	private int computeInternalRowIndex(final int clientRowIndex) {
		return clientRowIndex - this.getBoardDimension().getMinRowIndex();
	}
	private int computeInternalColumnIndex(final int clientColumnIndex) {
		return clientColumnIndex - this.getBoardDimension().getMinColumnIndex();
	}
	// ---------------------------------------------------------------------	
	private final PositionInterface createPosition(final int clientRowIndex, final int clientColumnIndex) {
		this.incrementNumberOfPositions();
		return new GameBoardPosition(clientRowIndex, clientColumnIndex, this.computeInternalRowIndex(clientRowIndex), this.computeInternalRowIndex(clientColumnIndex));
	}
	
	private final PositionInterface createNullPosition(final int clientRowIndex, final int clientColumnIndex) {
		return new GameBoardNullPosition(clientRowIndex, clientColumnIndex, this.computeInternalRowIndex(clientRowIndex), this.computeInternalRowIndex(clientColumnIndex));
	}
	// ---------------------------------------------------------------------
	private PositionInterface[][] createBoardPositionsCache() {
		return new PositionInterface[this.getBoardDimension().getRowIndexRangeSize()][this.getBoardDimension().getColumnIndexRangeSize()];
	}
	// ---------------------------------------------------------------------
	private PositionInterface[][] initializeBoardPositionsCache(PositionInterface[][] positions) {

		///System.out.println("\nInitialisation des " + this.getBoardDimension().getCapacity() + " positions...");

		final int minColumnIndex = this.getBoardDimension().getMinColumnIndex();
		final int maxColumnIndex = this.getBoardDimension().getMaxColumnIndex();
		final int minRowIndex = this.getBoardDimension().getMinRowIndex();
		final int maxRowIndex = this.getBoardDimension().getMaxRowIndex();

		///int numberOfDigitsInNumberOfPositions = this.getBoardDimension().getCapacity() == 0 ? 1 : (int) Math.log10(Math.abs(this.getBoardDimension().getCapacity())) + 1;

		///int n = 0;
		PositionInterface position;
		for (int clientRowIndex = minRowIndex; clientRowIndex <= maxRowIndex; ++clientRowIndex) {
			for (int clientColumnIndex = minColumnIndex; clientColumnIndex <= maxColumnIndex; ++clientColumnIndex) {
				///System.out.format("%0" + numberOfDigitsInNumberOfPositions + "d : ", ++n);
				position = this.createPosition(clientRowIndex, clientColumnIndex);
				///System.out.print(position);
				positions[position.getInternalRowIndex()][position.getInternalColumnIndex()] = position;
				///System.out.print("\n");
			}
		}

		return positions;
	}
	// ---------------------------------------------------------------------
	// TODO créer les éventuelles positions nulles après l'initialisation
	private PositionInterface[][] fillWithNullPositions(PositionInterface[][] positions) {
		
		/*
		int yMax = this.getBoardDimension().getRowIndexRangeSize();
		int xMax = this.getBoardDimension().getColumnIndexRangeSize();
		for (int y = 0; y < yMax; ++y)
			for (int x = 0; x < xMax; ++x)
				boardPositionsCache[y][x] = this.getNullPosition();
		return boardPositionsCache;
		*/
		
		final int minColumnIndex = this.getBoardDimension().getMinColumnIndex();
		final int maxColumnIndex = this.getBoardDimension().getMaxColumnIndex();
		final int minRowIndex = this.getBoardDimension().getMinRowIndex();
		final int maxRowIndex = this.getBoardDimension().getMaxRowIndex();

		PositionInterface nullPosition;
		for (int clientRowIndex = minRowIndex; clientRowIndex <= maxRowIndex; ++clientRowIndex) {
			for (int clientColumnIndex = minColumnIndex; clientColumnIndex <= maxColumnIndex; ++clientColumnIndex) {
				nullPosition = this.createNullPosition(clientRowIndex, clientColumnIndex);
				positions[nullPosition.getInternalRowIndex()][nullPosition.getInternalColumnIndex()] = nullPosition;
				//System.out.print("\n");
			}
		}

		return positions;		
		
	}	
	// ---------------------------------------------------------------------
	public Positions(final DimensionInterface boardDimension) {
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
	public PositionInterface position(final int clientRowIndex, final int clientColumnIndex) {
		
		PositionInterface position;
		
		// si la position est hors-dimension, nulle position n'est retournée.
		if (this.getBoardDimension().contains(clientRowIndex, clientColumnIndex)) {
			position = this.getBoardPositions()[this.computeInternalRowIndex(clientRowIndex)][this.computeInternalColumnIndex(clientColumnIndex)];
		}
		else {
			position =  this.nullPosition;
		}

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
	private PositionInterface getAdjacentPositionTo(final PositionInterface position, final BoardCardinalPosition direction) {
		return this.position(position.getClientRowIndex() + direction.getDeltaRowIndex(), position.getClientColumnIndex() + direction.getDeltaColumnIndex());
	}
	// ---------------------------------------------------------------------	
	
	public PositionInterface leftOf(final PositionInterface position) {
		return this.getAdjacentPositionTo(position, BoardCardinalPosition.LEFT);
	}
	// ---------------------------------------------------------------------
	public PositionInterface rightOf(final PositionInterface position) {
		return this.getAdjacentPositionTo(position, BoardCardinalPosition.RIGHT);
	}
	// ---------------------------------------------------------------------
	public PositionInterface topOf(final PositionInterface position) {
		return this.getAdjacentPositionTo(position, BoardCardinalPosition.TOP);
	}
	// ---------------------------------------------------------------------
	public PositionInterface bottomOf(final PositionInterface position) {
		return this.getAdjacentPositionTo(position, BoardCardinalPosition.BOTTOM);		
	}

	// ---------------------------------------------------------------------
	public PositionInterface topLeftOf(final PositionInterface position) {
		return this.getAdjacentPositionTo(position, BoardCardinalPosition.TOP_LEFT);
	}

	// ---------------------------------------------------------------------
	public PositionInterface topRightOf(final PositionInterface position) {
		return this.getAdjacentPositionTo(position, BoardCardinalPosition.TOP_RIGHT);
	}

	// ---------------------------------------------------------------------
	public PositionInterface bottomLeftOf(final PositionInterface position) {
		return this.getAdjacentPositionTo(position, BoardCardinalPosition.BOTTOM_LEFT);
	}

	// ---------------------------------------------------------------------
	public PositionInterface bottomRightOf(final PositionInterface position) {
		return this.getAdjacentPositionTo(position, BoardCardinalPosition.BOTTOM_RIGHT);
	}
	// =====================================================================
	private class GameBoardIllegalPositionException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		private final transient int clientRowIndex;
		private final transient int clientColumnIndex;
		
		public GameBoardIllegalPositionException(final int clientRowIndex, final int clientColumnIndex) {
			super();
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
	private class GameBoardNullPosition implements PositionInterface {
		// ---------------------------------------------------------------------
		private int clientRowIndex;
		public final int getClientRowIndex() {
			return this.clientRowIndex;
		}
		private final void setClientRowIndex(final int clientRowIndex) {
			this.clientRowIndex = clientRowIndex;
		}
		// ---------------------------------------------------------------------
		private int clientColumnIndex;
		public final int getClientColumnIndex() {
			return this.clientColumnIndex;
		}				
		private final void setClientColumnIndex(final int clientColumnIndex) {
			this.clientColumnIndex = clientColumnIndex;
		}
		// ---------------------------------------------------------------------
		private int internalRowIndex;
		public final int getInternalRowIndex() {
			return this.internalRowIndex;
		}				
		private final void setInternalRowIndex(final int internalRowIndex) {
			this.internalRowIndex = internalRowIndex;
		}
		// ---------------------------------------------------------------------		
		private int internalColumnIndex;
		public final int getInternalColumnIndex() {
			return this.internalColumnIndex;
		}				
		private final void setInternalColumnIndex(final int internalColumnIndex) {
			this.internalColumnIndex = internalColumnIndex;
		}				
		// ---------------------------------------------------------------------
		public GameBoardNullPosition(final int clientRowIndex, final int clientColumnIndex, final int internalRowIndex, final int internalColumnIndex) {
			this.setClientRowIndex(clientRowIndex);
			this.setClientColumnIndex(clientColumnIndex);
			this.setInternalRowIndex(internalRowIndex);
			this.setInternalColumnIndex(internalColumnIndex);
		}
		// ---------------------------------------------------------------------		
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
		public GameBoardPosition(final int clientRowIndex, final int clientColumnIndex, final int internalRowIndex, final int internalColumnIndex) {
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