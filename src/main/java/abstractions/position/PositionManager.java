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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import abstractions.direction.DirectionInterface;
import abstractions.direction.DirectionManagerInterface;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public final class PositionManager implements PositionManagerInterface {

    private final int hashBase;
    private final Map<Integer, PositionInterface> data;
    private final PositionInterface nullPosition;

    private final DirectionManagerInterface directionManager;

    private final int hash(final int row, final int column) {
        return this.hashBase * row + column;
    }

    public PositionInterface getNullPosition() {
        return this.nullPosition;
    }

    private PositionInterface newPosition(final int rowIndex, final int columnIndex) {
        return new Position(rowIndex, columnIndex);
    }

    public Set<PositionInterface> newPositionSet() {
        final Set<PositionInterface> positions = Sets.newHashSetWithExpectedSize(this.directionManager.getDimensionManager().capacity() + 1);
        positions.add(this.newPosition(0, 0));
        final int maxRowIndex = this.directionManager.getDimensionManager().upperBoundForRows();
        final int maxColumnIndex = this.directionManager.getDimensionManager().upperBoundForColumns();
        for (int rowIndex = this.directionManager.getDimensionManager().lowerBoundForRows(); rowIndex <= maxRowIndex; ++rowIndex) {
            for (int columnIndex = this.directionManager.getDimensionManager().lowerBoundForColumns(); columnIndex <= maxColumnIndex; ++columnIndex) {
                positions.add(this.newPosition(rowIndex, columnIndex));
            }
        }
        return Collections.unmodifiableSet(positions);
    }

    private Map<Integer, PositionInterface> initializeData(final Set<PositionInterface> set) {
        final Map<Integer, PositionInterface> data = Maps.newHashMapWithExpectedSize(set.size());
        for (final PositionInterface element : set) {
            data.put(this.hash(element.getRow(), element.getColumn()), element);
        }
        // TODO regarder l'API du builder
        return ImmutableMap.copyOf(data);
    }

    public PositionManager(final DirectionManagerInterface directionManager) {
        this.directionManager = directionManager;
        this.hashBase = Math.max(this.directionManager.getDimensionManager().numberOfRows(), this.directionManager.getDimensionManager().numberOfColumns());
        this.data = this.initializeData(this.newPositionSet());
        this.nullPosition = this.data.get(0);
    }

    public PositionInterface getPosition(final int row, final int column) {
        final PositionInterface position;
        if (this.directionManager.getDimensionManager().contains(row, column)) {
            position = this.data.get(this.hash(row, column));
        }
        else {
            position = this.nullPosition;
        }
        return position;
    }

    public PositionInterface getPosition(final PositionInterface position, final DirectionInterface direction) {
        return this.getPosition(position.getRow() + direction.getRowDelta(), position.getColumn() + direction.getColumnDelta());
    }

    /*
    // TODO ? utiliser le DirectionManager
    public PositionInterface getPosition(final PositionInterface position, final NamedDirection namedDirection) {
        return this.getPosition(position.getRow() + namedDirection.getRowDelta(), position.getColumn() + namedDirection.getColumnDelta());
    }
    */

    public Iterator<PositionInterface> iterator() {
        return this.data.values().iterator();
    }

    // TODO ? faire une méthode getRelativePositions()
    public List<? extends DirectionInterface> getNamedDirections() {
        return this.directionManager.getNamedDirections();
    }

}
