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

import abstractions.dimension.API.DimensionInterface;

import com.google.common.base.Preconditions;

final class Dimension implements DimensionInterface {

    private final RowsRange rowRange;
    private final ColumnsRange columRange;

    public Dimension(final RowsRange rowsRange, final ColumnsRange columnsRange) {
        Preconditions.checkNotNull(rowsRange, "Argument 'rowsRange' must not be null.");
        Preconditions.checkNotNull(columnsRange, "Argument 'columnsRange' must not be null.");
        this.rowRange = rowsRange;
        this.columRange = columnsRange;
    }

    @Override
    public final int lowerBoundForRows() {
        return this.rowRange.getLowerBound();
    }

    @Override
    public final int upperBoundForRows() {
        return this.rowRange.getUpperBound();
    }

    @Override
    public final int lowerBoundForColumns() {
        return this.columRange.getLowerBound();
    }

    @Override
    public final int upperBoundForColumns() {
        return this.columRange.getUpperBound();
    }

    @Override
    public final int numberOfRows() {
        return this.rowRange.getCapacity();
    }

    @Override
    public final int numberOfColumns() {
        return this.columRange.getCapacity();
    }

    @Override
    public final int boardCapacity() {
        return this.numberOfRows() * this.numberOfColumns();
    }

    @Override
    public final boolean contains(final int rowIndex, final int columnIndex) {
        return this.rowRange.contains(rowIndex) && this.columRange.contains(columnIndex);
    }

}
