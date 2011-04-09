
package abstractions.position;

import java.util.Map;
import java.util.Set;

import abstractions.dimension.API.DimensionInterface;

import com.google.common.collect.Maps;

public class PositionManager implements PositionManagerInterface {

    public static interface DirectionInterface {

        /**
         * Returns the row index of this position.
         * 
         * @return the row index of this position
         */
        int getRowDelta();

        /**
         * Returns the column index of this position.
         * 
         * @return the column index of this position
         */
        int getColumnDelta();

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

        private int rowDelta;
        private int columnDelta;

        private Direction(final int rowDelta, final int columnDelta) {
            this.rowDelta = rowDelta;
            this.columnDelta = columnDelta;
        }

        @Override
        public final int getRowDelta() {
            return this.rowDelta;
        }

        @Override
        public final int getColumnDelta() {
            return this.columnDelta;
        }

        @Override
        public String toString() {
            return "[rowDelta = " + this.getRowDelta() + "]" + "[columnDelta = " + this.getColumnDelta() + "]";
        }

    }

    private final PositionSetFactoryInterface factory = new PositionSetFactory();
    private final Map<Integer, PositionInterface> data;
    private final int hashBase;

    private final PositionInterface nullPosition;
    private final DimensionInterface dimension;

    private final int hash(final int row, final int column) {
        return this.hashBase * row + column;
    }

    @Override
    public PositionInterface getNullPosition() {
        return this.data.get(0);
    }

    public PositionManager(final DimensionInterface dimension) {
        this.dimension = dimension;
        this.hashBase = Math.max(dimension.numberOfRows(), dimension.numberOfColumns());
        final Set<PositionInterface> set = this.factory.newPositionSet(dimension);
        this.data = Maps.newHashMapWithExpectedSize(set.size());
        for (final PositionInterface element : set) {
            this.data.put(this.hash(element.getRow(), element.getColumn()), element);
        }
        this.nullPosition = this.getNullPosition();
    }

    @Override
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

    @Override
    public PositionInterface getPosition(final PositionInterface position, final DirectionInterface direction) {
        return this.getPosition(position.getRow() + direction.getRowDelta(), position.getColumn() + direction.getColumnDelta());

    }
}
