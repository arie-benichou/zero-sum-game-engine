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

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import abstractions.dimension.API.DimensionInterface;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

public final class PositionManager implements PositionManagerInterface {

    public static interface DirectionInterface {

    }

    public static enum Direction implements DirectionInterface {

        // -------------
        // |   | x |   |
        // -------------
        // |   | . |   |
        // -------------
        // |   |   |   |
        // -------------    
        TOP(-1, 0),

        // -------------
        // |   |   |   |
        // -------------
        // |   | . | x |
        // -------------
        // |   |   |   |
        // -------------                    
        RIGHT(0, 1),

        // -------------
        // |   |   |   |
        // -------------
        // |   | . |   |
        // -------------
        // |   | x |   |
        // -------------            
        BOTTOM(1, 0),

        // -------------
        // |   |   |   |
        // -------------
        // | x | . |   |
        // -------------
        // |   |   |   |
        // -------------            
        LEFT(0, -1),

        // -------------
        // |   |   |   |
        // -------------
        // |   | . |   |
        // -------------
        // |   |   |   |
        // -------------            
        //NULL(0, 0),

        // -------------
        // |   |   | x |
        // -------------
        // |   | . |   |
        // -------------
        // |   |   |   |
        // -------------    
        TOP_RIGHT(-1, 1),

        // -------------
        // | x |   |   |
        // -------------
        // |   | . |   |
        // -------------
        // |   |   |   |
        // -------------    
        TOP_LEFT(-1, -1),

        // -------------
        // |   |   |   |
        // -------------
        // |   | . |   |
        // -------------
        // |   |   | x |
        // -------------    
        BOTTOM_RIGHT(1, 1),

        // -------------
        // |   |   |   |
        // -------------
        // |   | . |   |
        // -------------
        // | x |   |   |
        // -------------        
        BOTTOM_LEFT(1, -1);

        private final int rowDelta;
        private final int columnDelta;

        private Direction(final int rowDelta, final int columnDelta) {
            this.rowDelta = rowDelta;
            this.columnDelta = columnDelta;
        }

        public final int getRowDelta() {
            return this.rowDelta;
        }

        public final int getColumnDelta() {
            return this.columnDelta;
        }



        }

    }


    private final static PositionSetFactoryInterface factory = new PositionSetFactory();

    private final DimensionInterface dimension;
    private final int hashBase;
    private final Map<Integer, PositionInterface> data;
    private final PositionInterface nullPosition;

    private final int hash(final int row, final int column) {
        return this.hashBase * row + column;
    }

    public PositionInterface getNullPosition() {
        return this.nullPosition;
    }

    private Map<Integer, PositionInterface> initializeData(final Set<PositionInterface> set) {
        final Map<Integer, PositionInterface> data = Maps.newHashMapWithExpectedSize(set.size());
        for (final PositionInterface element : set) {
            data.put(this.hash(element.getRow(), element.getColumn()), element);
        }
        // TODO regarder l'API du builder
        return ImmutableMap.copyOf(data);
    }

    // TODO ? DimensionManager(DirectionManager) / DirectionManager(DimensionManager) 
    public PositionManager(final DimensionInterface dimension) {
        this.dimension = dimension;
        this.hashBase = Math.max(dimension.numberOfRows(), dimension.numberOfColumns());
        this.data = this.initializeData(PositionManager.factory.newPositionSet(this.dimension));
        this.nullPosition = this.data.get(0);
    }

    public PositionInterface getPosition(final int row, final int column) {
        final PositionInterface position;
        if (this.dimension.contains(row, column)) {
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

    public Iterator<PositionInterface> iterator() {
        return this.data.values().iterator();
    }

    public List<Direction> getDirections() {
        return PositionManager.DIRECTIONS;
    }

}
