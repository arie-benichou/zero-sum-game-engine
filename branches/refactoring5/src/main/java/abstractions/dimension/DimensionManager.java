/*
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package abstractions.dimension;

import annotations.Immutable;

@Immutable
public final class DimensionManager implements DimensionManagerInterface {

    private final RowDimension rowRange;
    private final ColumnDimension columRange;

    public DimensionManager(final int numberOfRows, final int numberOfColumns) {
        try {
            this.rowRange = new RowDimension(1, numberOfRows);
            this.columRange = new ColumnDimension(1, numberOfColumns);
        }
        catch (final IllegalArgumentException e) {
            throw new IllegalDimensionException(numberOfRows, numberOfColumns); // NOPMD 
        }
    }

    @Override
    public int lowerBoundForRows() {
        return this.rowRange.getLowerBound();
    }

    @Override
    public int upperBoundForRows() {
        return this.rowRange.getUpperBound();
    }

    @Override
    public int lowerBoundForColumns() {
        return this.columRange.getLowerBound();
    }

    @Override
    public int upperBoundForColumns() {
        return this.columRange.getUpperBound();
    }

    @Override
    public int numberOfRows() {
        return this.rowRange.getCapacity();
    }

    @Override
    public int numberOfColumns() {
        return this.columRange.getCapacity();
    }

    @Override
    public int capacity() {
        return this.numberOfRows() * this.numberOfColumns();
    }

    @Override
    public boolean contains(final int rowIndex, final int columnIndex) {
        return this.rowRange.contains(rowIndex) && this.columRange.contains(columnIndex);
    }

    @Override
    public String toString() {
        return this.rowRange.toString() + " " + this.columRange.toString();
    }

}
