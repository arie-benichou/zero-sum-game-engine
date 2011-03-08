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

public class GameBoardDimension implements IGameBoardDimension {
	// ---------------------------------------------------------------------
	private int minRowIndex;

	private final void setMinRowIndex(int minRowIndex) {
		this.minRowIndex = minRowIndex;
	}

	public final int getMinRowIndex() {
		return this.minRowIndex;
	}

	// ---------------------------------------------------------------------
	private int maxRowIndex;

	private final void setMaxRowIndex(int maxRowIndex) {
		this.maxRowIndex = maxRowIndex;
	}

	public final int getMaxRowIndex() {
		return this.maxRowIndex;
	}

	// ---------------------------------------------------------------------
	private int minColumnIndex;

	private final void setMinColumnIndex(int minColumnIndex) {
		this.minColumnIndex = minColumnIndex;
	}

	public final int getMinColumnIndex() {
		return this.minColumnIndex;
	}

	// ---------------------------------------------------------------------
	private int maxColumnIndex;

	private final void setMaxColumnIndex(int maxColumnIndex) {
		this.maxColumnIndex = maxColumnIndex;
	}

	public final int getMaxColumnIndex() {
		return this.maxColumnIndex;
	}

	// ---------------------------------------------------------------------
	public final int getColumnIndexRangeSize() {
		return this.maxColumnIndex - this.minColumnIndex + 1;
	}

	public final int getRowIndexRangeSize() {
		return this.maxRowIndex - this.minRowIndex + 1;
	}

	// ---------------------------------------------------------------------
	public final int getCapacity() {
		return this.getColumnIndexRangeSize() * this.getRowIndexRangeSize();
	}

	// ---------------------------------------------------------------------
	public GameBoardDimension(int minRowIndex, int maxRowIndex,
			int minColumnIndex, int maxColumnIndex) throws RuntimeException {
		if (maxRowIndex < minRowIndex) {
			// TODO utiliser une BoardDimension exception
			throw new RuntimeException("maxRowIndex < minRowIndex");
		}
		if (maxColumnIndex < minColumnIndex) {
			// TODO utiliser une BoardDimension exception
			throw new RuntimeException("maxColumnIndex < minColumnIndex");
		}
		this.setMinRowIndex(minRowIndex);
		this.setMaxRowIndex(maxRowIndex);
		this.setMinColumnIndex(minColumnIndex);
		this.setMaxColumnIndex(maxColumnIndex);

		///System.out.println(this);

	}

	// ---------------------------------------------------------------------
	public boolean contains(int rowIndex, int columnIndex) {
		if (rowIndex < this.getMinRowIndex())
			return false;
		if (rowIndex > this.getMaxRowIndex())
			return false;
		if (columnIndex < this.getMinColumnIndex())
			return false;
		if (columnIndex > this.getMaxColumnIndex())
			return false;
		return true;
	}

	// ---------------------------------------------------------------------
	@Override
	public String toString() {
		return "BoardDimension [minRowIndex=" + minRowIndex + ", maxRowIndex="
				+ maxRowIndex + ", minColumnIndex=" + minColumnIndex
				+ ", maxColumnIndex=" + maxColumnIndex + "]";
	}
	// ---------------------------------------------------------------------
}