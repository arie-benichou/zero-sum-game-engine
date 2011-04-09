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

package abstractions.position;

import java.util.Collections;
import java.util.Set;

import abstractions.dimension.API.DimensionInterface;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

/**
 * The position factory.
 */
final class PositionSetFactory implements PositionSetFactoryInterface {

    private PositionInterface newPosition(final int rowIndex, final int columnIndex) {
        return new Position(rowIndex, columnIndex);
    }

    @Override
    public Set<PositionInterface> newPositionSet(final DimensionInterface dimension) {
        Preconditions.checkNotNull(dimension, "Argument 'dimension' is not intended to be null.");
        final Set<PositionInterface> positions = Sets.newHashSetWithExpectedSize(dimension.boardCapacity() + 1);
        positions.add(this.newPosition(0, 0));
        for (int rowIndex = dimension.lowerBoundForRows(), maxRowIndex = dimension.upperBoundForRows(); rowIndex <= maxRowIndex; ++rowIndex) {
            for (int columnIndex = dimension.lowerBoundForColumns(), maxColumnIndex = dimension.upperBoundForColumns(); columnIndex <= maxColumnIndex; ++columnIndex) {
                positions.add(this.newPosition(rowIndex, columnIndex));
            }
        }
        return Collections.unmodifiableSet(positions);

    }

}