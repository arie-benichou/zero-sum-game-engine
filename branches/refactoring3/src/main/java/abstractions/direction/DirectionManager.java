
package abstractions.direction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import abstractions.dimension.API.DimensionFactory;
import abstractions.dimension.API.DimensionInterface;

import com.google.common.collect.ImmutableMap;

public final class DirectionManager implements DirectionManagerInterface {

    //private final static DirectionInterface NULL_DIRECTION = new Direction(0, 0);

    private static DirectionInterface newDirection(final int rowDelta, final int columnDelta) {
        return new Direction(rowDelta, columnDelta);
    }

    private static DirectionInterface newDirection(final NamedDirection namedDirection) {
        return DirectionManager.newDirection(namedDirection.getRowDelta(), namedDirection.getColumnDelta());
    }

    private final static Map<NamedDirection, DirectionInterface> NAMED_DIRECTIONS =
            new ImmutableMap.Builder<NamedDirection, DirectionInterface>()
                    .put(NamedDirection.TOP, DirectionManager.newDirection(NamedDirection.TOP))
                    .put(NamedDirection.TOP_RIGHT, DirectionManager.newDirection(NamedDirection.TOP_RIGHT))
                    .put(NamedDirection.RIGHT, DirectionManager.newDirection(NamedDirection.RIGHT))
                    .put(NamedDirection.BOTTOM_RIGHT, DirectionManager.newDirection(NamedDirection.BOTTOM_RIGHT))
                    .put(NamedDirection.BOTTOM, DirectionManager.newDirection(NamedDirection.BOTTOM))
                    .put(NamedDirection.BOTTOM_LEFT, DirectionManager.newDirection(NamedDirection.BOTTOM_LEFT))
                    .put(NamedDirection.LEFT, DirectionManager.newDirection(NamedDirection.LEFT))
                    .put(NamedDirection.TOP_LEFT, DirectionManager.newDirection(NamedDirection.TOP_LEFT))
                    .build();

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
        for (final DirectionInterface direction : this.getDirectionsMap().values()) {
            data.set(this.computeNaturalHash(direction.getRowDelta(), direction.getColumnDelta()), direction);
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
        final int hash = this.computeNaturalHash(rowDelta, columnDelta);
        DirectionInterface direction = this.data.get(hash);
        if (direction == null) {
            direction = DirectionManager.newDirection(rowDelta, columnDelta);
            this.data.set(hash, direction);
        }
        return direction;
    }

    public Map<NamedDirection, DirectionInterface> getDirectionsMap() {
        return DirectionManager.NAMED_DIRECTIONS;
    }

    public DirectionInterface getNamedDirectionFromMap(final NamedDirection namedDirection) {
        return DirectionManager.NAMED_DIRECTIONS.get(namedDirection);
    }

    public DirectionInterface getNamedDirectionFromList(final NamedDirection namedDirection) {
        return this.data.get(this.computeNaturalHash(namedDirection.getRowDelta(), namedDirection.getColumnDelta()));
    }

    public DirectionInterface reduce(final Collection<DirectionInterface> directions) {
        int reducedRowDelta = 0;
        int reducedColumnDelta = 0;
        for (final DirectionInterface direction : directions) {
            reducedRowDelta += direction.getRowDelta();
            reducedColumnDelta += direction.getColumnDelta();
        }
        return this.getDirection(reducedRowDelta, reducedColumnDelta);
    }

    public static void measureGetNamedDirectionFromList() {
        final DirectionManager directionManager = new DirectionManager(DimensionFactory.Dimension(10, 10));
        final long startTime = System.currentTimeMillis();
        for (int n = 0; n < 500000000; ++n) {
            directionManager.getNamedDirectionFromList(NamedDirection.TOP);
        }
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time with 'getNamedDirectionFromList': " + (endTime - startTime) + " ms");
    }

    public static void measureGetNamedDirectionFromMap() {
        final DirectionManager directionManager = new DirectionManager(DimensionFactory.Dimension(10, 10));
        final long startTime = System.currentTimeMillis();
        for (int n = 0; n < 500000000; ++n) {
            directionManager.getNamedDirectionFromMap(NamedDirection.TOP);
        }
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time with 'getNamedDirectionFromMap' : " + (endTime - startTime) + " ms");
    }

    public static void main(final String[] args) {
        DirectionManager.measureGetNamedDirectionFromList();
        DirectionManager.measureGetNamedDirectionFromMap();
    }
}
