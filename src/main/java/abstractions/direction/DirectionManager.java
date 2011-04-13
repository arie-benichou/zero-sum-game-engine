
package abstractions.direction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import abstractions.dimension.API.DimensionFactory;
import abstractions.dimension.API.DimensionInterface;

public final class DirectionManager implements DirectionManagerInterface {

    private final static DirectionInterface NULL_DIRECTION = new Direction(0, 0);

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
        final int hash = this.computeNaturalHash(rowDelta, columnDelta);

        System.out.println("\ngetDirection(" + rowDelta + ", " + columnDelta + ")");
        System.out.println(hash);
        System.out.println(hash < 0);
        System.out.println(hash >= this.data.size());

        if (hash < 0 || hash >= this.data.size()) { // TODO utiliser les dimensions
            throw new IllegalDirectionException(rowDelta, columnDelta);
        }
        DirectionInterface direction = this.data.get(hash);
        if (direction == null) {
            direction = this.newDirection(rowDelta, columnDelta);
            this.data.set(hash, direction);
        }
        return direction;
    }

    public Map<NamedDirection, DirectionInterface> getDirectionsMap() {
        //return DirectionManager.NAMED_DIRECTIONS;
        return null;
    }

    public DirectionInterface getNamedDirectionFromMap(final NamedDirection namedDirection) {
        //return DirectionManager.NAMED_DIRECTIONS.get(namedDirection);
        return null;
    }

    public DirectionInterface getNamedDirectionFromList(final NamedDirection namedDirection) {
        try { // TODO utiliser les dimensions
            return this.data.get(this.computeNaturalHash(namedDirection.getRowDelta(), namedDirection.getColumnDelta()));
        }
        catch (final ArrayIndexOutOfBoundsException e) {
            throw new IllegalDirectionException(namedDirection.getRowDelta(), namedDirection.getColumnDelta());
        }
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

    public static void main(final String[] args) {
        DirectionManager.measureGetNamedDirectionFromList();
    }
}
