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

/**
 * API related to dimensions.
 */

package abstractions.dimension;

// TODO à intégrer dans le dimension manager
public final class DimensionFactory {

    private DimensionFactory() {}

    public static DimensionManagerInterface dimension(final int numberOfRows, final int numberOfColumns) {

        try {
            return new DimensionManager(new RowDimension(1, numberOfRows), new ColumnDimension(1, numberOfColumns));
        }
        catch (final IllegalArgumentException e) {
            throw new IllegalDimensionException(numberOfRows, numberOfColumns); // NOPMD 
        }

    }

}