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

package abstractions.direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import abstractions.dimension.API.DimensionFactory;
import abstractions.dimension.API.DimensionInterface;

public final class DirectionManager implements DirectionManagerInterface {

    public enum NamedDirection implements DirectionInterface {

        /*
        -------------
        |   |   |   |
        -------------
        |   | . |   |
        -------------
        |   |   |   |
        -------------
        NULL(0, 0),
        */

        /*
        -------------
        |   | x |   |
        -------------
        |   | . |   |
        -------------
        |   |   |   |
        -------------
         */
        TOP(-1, 0),

        /*
        -------------
        |   |   | x |
        -------------
        |   | . |   |
        -------------
        |   |   |   |
        -------------
        */
        TOP_RIGHT(-1, 1),

        /*
        -------------
        |   |   |   |
        -------------
        |   | . | x |
        -------------
        |   |   |   |
        -------------
        */
        RIGHT(0, 1),

        /*
        -------------
        |   |   |   |
        -------------
        |   | . |   |
        -------------
        |   |   | x |
        -------------
        */
        BOTTOM_RIGHT(1, 1),

        /*
        -------------
        |   |   |   |
        -------------
        |   | . |   |
        -------------
        |   | x |   |
        -------------
        */
        BOTTOM(1, 0),

        /*
        -------------
        |   |   |   |
        -------------
        |   | . |   |
        -------------
        | x |   |   |
        -------------
        */
        BOTTOM_LEFT(1, -1),

        /*
        -------------
        |   |   |   |
        -------------
        | x | . |   |
        -------------
        |   |   |   |
        -------------
        */
        LEFT(0, -1),

        /*
        -------------
        | x |   |   |
        -------------
        |   | . |   |
        -------------
        |   |   |   |
        -------------
        */
        TOP_LEFT(-1, -1);

        private final int rowDelta;
        private final int columnDelta;

        private NamedDirection(final int rowDelta, final int columnDelta) {
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

    private final static List<NamedDirection> NAMED_DIRECTIONS = Arrays.asList(NamedDirection.values());

    private final DimensionInterface dimension;
    private final int hashFactor;
    private final int hashOffset;
    private final List<DirectionInterface> data;

    private int computeHashFactor(final DimensionInterface dimension) {
        return (dimension.numberOfColumns() - 1) * 2 + 1;
    }

    private int computeHashOffset(final DimensionInterface dimension) {
        //((dimension.numberOfRows() - 1) + (dimension.numberOfRows() - 0)) * ((dimension.numberOfColumns() - 1) + (dimension.numberOfColumns() - 0)) / 2
        return (2 * dimension.numberOfRows() - 1) * (2 * dimension.numberOfColumns() - 1) / 2;
    }

    private final int computeNaturalHash(final int rowDelta, final int columnDelta) {
        return this.hashOffset + this.hashFactor * rowDelta + columnDelta;
    }

    private DirectionInterface newDirection(final int rowDelta, final int columnDelta) {
        return new Direction(rowDelta, columnDelta);
    }

    private DirectionInterface newDirection(final NamedDirection namedDirection) {
        return new Direction(namedDirection.getRowDelta(), namedDirection.getColumnDelta());
    }

    private List<DirectionInterface> initializeData(final List<DirectionInterface> data) {
        final int maxRowDelta = this.dimension.numberOfRows() - 1;
        final int maxColumnDelta = this.dimension.numberOfColumns() - 1;
        for (int checkSum = 0, rowDelta = -maxRowDelta; rowDelta <= maxRowDelta; ++rowDelta) {
            for (int columnDelta = -maxColumnDelta; columnDelta <= maxColumnDelta; ++columnDelta) {
                final int hash = this.computeNaturalHash(rowDelta, columnDelta);
                if (hash != checkSum++) {
                    throw new RuntimeException("Error in hash function.");
                }
                data.add(hash, null);
            }
        }
        int index;
        for (final NamedDirection namedDirection : NamedDirection.values()) {
            index = this.computeNaturalHash(namedDirection.getRowDelta(), namedDirection.getColumnDelta());
            if (index > -1 && index < data.size()) {
                data.set(index, this.newDirection(namedDirection));
            }
        }
        return data;
    }

    public DirectionManager(final DimensionInterface dimension) {
        this.dimension = dimension;
        this.hashFactor = this.computeHashFactor(this.dimension);
        this.hashOffset = this.computeHashOffset(this.dimension);
        this.data = this.initializeData(new ArrayList<DirectionInterface>(2 * this.hashOffset + 1));
    }

    public DirectionInterface getDirection(final int rowDelta, final int columnDelta) {
        if (!this.dimension.contains(Math.abs(rowDelta) + 1, Math.abs(columnDelta) + 1)) {
            throw new IllegalDirectionException(rowDelta, columnDelta);
        }
        final int hash = this.computeNaturalHash(rowDelta, columnDelta);
        DirectionInterface direction = this.data.get(hash);
        if (direction == null) {
            direction = this.newDirection(rowDelta, columnDelta);
            this.data.set(hash, direction);
        }
        return direction;
    }

    public DirectionInterface getNamedDirection(final NamedDirection namedDirection) {
        return this.getDirection(namedDirection.getRowDelta(), namedDirection.getColumnDelta());
    }

    public List<NamedDirection> getNamedDirections() {
        return DirectionManager.NAMED_DIRECTIONS;
    }

    public DirectionInterface reduce(final Collection<? extends DirectionInterface> directions) {
        int reducedRowDelta = 0;
        int reducedColumnDelta = 0;
        for (final DirectionInterface direction : directions) {
            reducedRowDelta += direction.getRowDelta();
            reducedColumnDelta += direction.getColumnDelta();
        }
        return this.getDirection(reducedRowDelta, reducedColumnDelta);
    }

    public static void main(final String[] args) {
        final DirectionManager directionManager = new DirectionManager(DimensionFactory.Dimension(10, 10));
        final long startTime = System.currentTimeMillis();
        for (int n = 0; n < 10000000; ++n) {
            directionManager.getNamedDirection(NamedDirection.TOP);
        }
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time with 'getNamedDirectionFromList': " + (endTime - startTime) + " ms");
    }

    public DimensionInterface getDimensionManager() {
        return this.dimension;
    }

}
